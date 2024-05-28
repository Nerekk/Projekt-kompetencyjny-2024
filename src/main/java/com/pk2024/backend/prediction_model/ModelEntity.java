package com.pk2024.backend.prediction_model;

import com.pk2024.backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "model_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ModelType modelType;

    @Column(nullable = false)
    private Integer predictedValue;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date predictionDate;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Double squareMeters;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double centreDistance;

    @Column(nullable = false)
    private Integer floorCount;

    @Column(nullable = false)
    private Integer rooms;

    @Column(nullable = false)
    private Double kindergartenDistance;

    @Column(nullable = false)
    private Double restaurantDistance;

    @Column(nullable = false)
    private Double collegeDistance;

    @Column(nullable = false)
    private Double postOfficeDistance;

    @Column(nullable = false)
    private Double clinicDistance;

    @Column(nullable = false)
    private Double schoolDistance;

    @Column(nullable = false)
    private Double pharmacyDistance;

    @Column(nullable = false)
    private Integer poiCount;

    public ModelEntity(ModelRequest modelRequest, ModelType modelType, Integer predictedValue) {
        this.modelType = modelType;
        this.predictedValue = predictedValue;
        this.predictionDate = new Date();
        this.city = modelRequest.getCity();
        this.squareMeters = modelRequest.getSquareMeters();
        this.longitude = modelRequest.getLongitude();
        this.latitude = modelRequest.getLatitude();
        this.centreDistance = modelRequest.getCentreDistance();
        this.floorCount = modelRequest.getFloorCount();
        this.rooms = modelRequest.getRooms();
        this.kindergartenDistance = modelRequest.getKindergartenDistance();
        this.restaurantDistance = modelRequest.getRestaurantDistance();
        this.collegeDistance = modelRequest.getCollegeDistance();
        this.postOfficeDistance = modelRequest.getPostOfficeDistance();
        this.clinicDistance = modelRequest.getClinicDistance();
        this.schoolDistance = modelRequest.getSchoolDistance();
        this.pharmacyDistance = modelRequest.getPharmacyDistance();
        this.poiCount = modelRequest.getPoiCount();
    }

}
