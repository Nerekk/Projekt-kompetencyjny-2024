package com.pk2024.backend.user_history;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.pk2024.backend.settings.Settings.STATISTICS_MAPPING;

@RestController
@RequestMapping(path = STATISTICS_MAPPING)
@RequiredArgsConstructor
public class StatisticsController {
    private final UserHistoryService service;

    @GetMapping("/most-used")
    public ResponseEntity<Map<String, Map<String, Object>>> getMostUsedParameters() {
        Map<String, Map<String, Object>> stats = service.getMostUsedParameters();
        return ResponseEntity.ok(stats);
    }
}
