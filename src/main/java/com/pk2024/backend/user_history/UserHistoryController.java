package com.pk2024.backend.user_history;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pk2024.backend.settings.Settings.USER_HISTORY_MAPPING;

@RestController
@RequestMapping(path = USER_HISTORY_MAPPING)
@RequiredArgsConstructor
public class UserHistoryController {
    private final UserHistoryService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public Page<UserHistoryResponse> getHistoryWithPagination(@AuthenticationPrincipal UserDetails userDetails,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        return service.getHistoryWithPagination(userDetails.getUsername(), page, size);
    }
}
