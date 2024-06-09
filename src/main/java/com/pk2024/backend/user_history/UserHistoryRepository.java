package com.pk2024.backend.user_history;

import com.pk2024.backend.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    Page<UserHistory> findByUserOrderByPredictionDateDesc(User user, Pageable pageable);
    @Query("SELECT uh.city as parameter, COUNT(uh.city) as occurrences FROM UserHistory uh GROUP BY uh.city ORDER BY occurrences DESC")
    List<Object[]> findTopUsedCities(Pageable pageable);

    @Query("SELECT uh.squareMeters as parameter, COUNT(uh.squareMeters) as occurrences FROM UserHistory uh GROUP BY uh.squareMeters ORDER BY occurrences DESC")
    List<Object[]> findTopUsedSquareMeters(Pageable pageable);

    @Query("SELECT uh.modelType as parameter, COUNT(uh.modelType) as occurrences FROM UserHistory uh GROUP BY uh.modelType ORDER BY occurrences DESC")
    List<Object[]> findTopUsedModelTypes(Pageable pageable);
}
