package com.pk2024.backend.user_history;

import com.pk2024.backend.user.User;
import com.pk2024.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserHistoryService {
    private final UserRepository userRepository;
    private final UserHistoryRepository repository;
    public Page<UserHistoryResponse> getHistoryWithPagination(String username, int page, int size) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(value -> repository.findByUserOrderByPredictionDateDesc(value, PageRequest.of(page, size))
                .map(this::convertToResponse)).orElseGet(() -> new PageImpl<>(Collections.emptyList(), PageRequest.of(page, size), 0));

    }
    private UserHistoryResponse convertToResponse(UserHistory history) {
        return UserHistoryResponse.builder()
                .modelType(history.getModelType())
                .predictedValue(history.getPredictedValue())
                .predictionDate(history.getPredictionDate())
                .city(history.getCity())
                .squareMeters(history.getSquareMeters())
                .longitude(history.getLongitude())
                .latitude(history.getLatitude())
                .centreDistance(history.getCentreDistance())
                .floorCount(history.getFloorCount())
                .rooms(history.getRooms())
                .kindergartenDistance(history.getKindergartenDistance())
                .restaurantDistance(history.getRestaurantDistance())
                .collegeDistance(history.getCollegeDistance())
                .postOfficeDistance(history.getPostOfficeDistance())
                .clinicDistance(history.getClinicDistance())
                .schoolDistance(history.getSchoolDistance())
                .pharmacyDistance(history.getPharmacyDistance())
                .poiCount(history.getPoiCount())
                .build();
    }
}
