package it.epicode.Foodie.locations;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class LocationRequest {
    @NotBlank(message = "Il campo 'via' non può essere vuoto")
    private String via;
    private String url;
}
