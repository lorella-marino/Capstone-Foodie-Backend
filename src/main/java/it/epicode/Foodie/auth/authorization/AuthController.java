package it.epicode.Foodie.auth.authorization;

import it.epicode.Foodie.auth.app_user.AppUser;
import it.epicode.Foodie.auth.app_user.AppUserService;
import it.epicode.Foodie.auth.app_user.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AuthController {

    private final AppUserService appUserService;

    @GetMapping("/current-user")
    @PreAuthorize("isAuthenticated()")
    public AppUser getCurrentUser(@AuthenticationPrincipal AppUser appUser) {
        return appUser;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        appUserService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getNome(),
                registerRequest.getCognome(),
                registerRequest.getTelefono(),
                registerRequest.getEmail(),
                Set.of(Role.ROLE_USER) // Assegna il ruolo di default
        );
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = appUserService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        AppUser user = appUserService.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Trasforma in stringhe i ruoli
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.name())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(new AuthResponse(
                token,
                user.getUsername(),
                user.getNome(),
                user.getCognome(),
                user.getEmail(),
                user.getTelefono(),
                roles
        ));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(appUserService.findAllUsers());
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RegisterRequest> updateUser(@AuthenticationPrincipal AppUser appUser, @RequestBody @Valid RegisterRequest updatedUser) {
        RegisterRequest updatedUserResponse = appUserService.update(appUser.getUsername(), updatedUser);
        return ResponseEntity.ok(updatedUserResponse);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal AppUser appUser) {
        appUserService.delete(appUser.getUsername());
        return ResponseEntity.ok("Utente eliminato con successo");
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        try {
            appUserService.delete(username);
            return ResponseEntity.ok("Utente eliminato con successo");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
