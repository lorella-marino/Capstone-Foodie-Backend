package it.epicode.Foodie.locations;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<LocationResponse> findAll() {
        return locationRepository.findAll().stream()
                .map(l -> new LocationResponse(l.getId(), l.getVia(), l.getUrl()))
                .toList();
    }


    public LocationResponse create(LocationRequest locationRequest) {
        Location location = new Location();
        BeanUtils.copyProperties(locationRequest, location);
        locationRepository.save(location);
        return new LocationResponse(location.getId(), location.getVia(), location.getUrl());
    }

    public LocationResponse update(Long id, LocationRequest locationRequest) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location non trovata"));
        BeanUtils.copyProperties(locationRequest, location);
        locationRepository.save(location);
        return new LocationResponse(location.getId(), location.getVia(), location.getUrl() );
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
