package com.example.structure.viewmodel;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.DashboardResponseModel;
import com.example.structure.data.repository.DashboardRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 21-11-2019
 */
public class DashboardViewModel extends ViewModel {
    private String TAG = getClass().getSimpleName();
    private CustomLiveData<DashboardResponseModel> dashboardResponseModelCustomLiveData;
    private CustomLiveData<CommonResponseModel> customLiveData;
    private DashboardRepository dashboardRepository;

    @Inject
    public DashboardViewModel(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public CustomLiveData<DashboardResponseModel> dashboardAPILiveData(String beararToken) {
        dashboardResponseModelCustomLiveData = dashboardRepository.dashboardAPIRepository(beararToken);
        return dashboardResponseModelCustomLiveData;
    }

    public CustomLiveData<CommonResponseModel> resendEmailAPILiveData(String beararToken) {
        customLiveData = dashboardRepository.resendEmailAPIRepository(beararToken);
        return customLiveData;
    }

    public CustomLiveData<DashboardResponseModel> getDashboardResponse() {
        return dashboardResponseModelCustomLiveData;
    }
}
