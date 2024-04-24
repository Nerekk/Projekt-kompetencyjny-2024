package com.pk2024.backend;

import com.pk2024.backend.DTO.MediumModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "model")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping(path = "/{meters}/{longitude}/{latitude}/{city}")
    public ResponseEntity<Integer> getPredictedValue(@PathVariable("meters") Double meters,
                                                    @PathVariable("longitude") Double longitude,
                                                    @PathVariable("latitude") Double latitude,
                                                    @PathVariable("city") String city) {
        return modelService.getPredictedValueSmall(meters, longitude, latitude, city);
    }

    @GetMapping(path = "/{meters}/{longitude}/{latitude}/{city}/{centreDistance}/{floorCount}/{rooms}")
    public ResponseEntity<Integer> getPredictedValue(@PathVariable("meters") Double meters,
                                                     @PathVariable("longitude") Double longitude,
                                                     @PathVariable("latitude") Double latitude,
                                                     @PathVariable("city") String city,
                                                     @PathVariable("centreDistance") Double centreDistance,
                                                     @PathVariable("floorCount") Integer floorCount,
                                                     @PathVariable("rooms") Integer rooms) {
        return modelService.getPredictedValueMedium(new MediumModelDTO(city, meters, longitude, latitude, centreDistance, floorCount, rooms));
    }
}
