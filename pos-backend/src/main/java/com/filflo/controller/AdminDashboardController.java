package com.filflo.controller;

import com.filflo.payload.AdminAnalysis.DashboardSummaryDTO;
import com.filflo.payload.AdminAnalysis.StoreRegistrationStatDTO;
import com.filflo.payload.AdminAnalysis.StoreStatusDistributionDTO;
import com.filflo.service.AdminDashboardService;
import com.filflo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/super-admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;
    private final StoreService storeService;

    /**
     * 📊 Get summary stats for dashboard cards
     * - 🏪 totalStores
     * - ✅ activeStores
     * - ⏳ pendingStores
     * - ⛔ blockedStores
     */
    @GetMapping("/dashboard/summary")
    public DashboardSummaryDTO getDashboardSummary() {
        return adminDashboardService.getDashboardSummary();
    }

    /**
     * 📈 Get number of store registrations in the last 7 days
     * Used for 📅 chart data (line/bar)
     */
    @GetMapping("/dashboard/store-registrations")
    public List<StoreRegistrationStatDTO> getLast7DayRegistrationStats() {
        return adminDashboardService.getLast7DayRegistrationStats();
    }

    /**
     * 🥧 Get store status distribution
     * - ✅ active
     * - ⛔ blocked
     * - ⏳ pending
     * Used for pie chart 📊
     */
    @GetMapping("/dashboard/store-status-distribution")
    public StoreStatusDistributionDTO getStoreStatusDistribution() {
        return adminDashboardService.getStoreStatusDistribution();
    }


}
