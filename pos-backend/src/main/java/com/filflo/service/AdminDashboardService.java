package com.filflo.service;



import com.filflo.payload.AdminAnalysis.DashboardSummaryDTO;
import com.filflo.payload.AdminAnalysis.StoreRegistrationStatDTO;
import com.filflo.payload.AdminAnalysis.StoreStatusDistributionDTO;


import java.util.List;

public interface AdminDashboardService {

    DashboardSummaryDTO getDashboardSummary();

    List<StoreRegistrationStatDTO> getLast7DayRegistrationStats();

    StoreStatusDistributionDTO getStoreStatusDistribution();
}
