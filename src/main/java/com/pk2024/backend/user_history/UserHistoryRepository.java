package com.pk2024.backend.user_history;

import com.pk2024.backend.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    Page<UserHistory> findByUserOrderByPredictionDateDesc(User user, Pageable pageable);

}
