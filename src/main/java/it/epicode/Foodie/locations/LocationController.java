package it.epicode.Foodie.locations;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public LocationResponse create(@RequestBody @Valid LocationRequest locationRequest) {
        return locationService.create(locationRequest);
    }

    @GetMapping
    public List<LocationResponse> findAll() {
        return locationService.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public LocationResponse update(@PathVariable Long id, @RequestBody @Valid LocationRequest locationRequest) {
        return locationService.update(id, locationRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        locationService.delete(id);
    }
}
