package com.pk2024.backend.user_history;

import com.pk2024.backend.prediction_model.ModelType;
import com.pk2024.backend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryResponse {

    private ModelType modelType;

    private Integer predictedValue;

    private LocalDateTime predictionDate;

    private String city;

    private Double squareMeters;

    private Double longitude;

    private Double latitude;

    private Double centreDistance;

    private Integer floorCount;

    private Integer rooms;

    private Double kindergartenDistance;

    private Double restaurantDistance;

    private Double collegeDistance;

    private Double postOfficeDistance;

    private Double clinicDistance;

    private Double schoolDistance;

    private Double pharmacyDistance;

    private Integer poiCount;

}