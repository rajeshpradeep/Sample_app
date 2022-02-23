package com.example.structure.ui.dashboard.asyntask;

import android.util.Log;

import com.example.structure.data.models.DashboardResponseModel;
import com.example.structure.retrofit.DashboardWebService;
import com.example.structure.utils.ResponseInterface;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 12-11-2019
 */
public class DashboardTask {

    private String TAG = getClass().getSimpleName();
    private DashboardWebService dashboardWebService;

    /**
     * implementing the web service api call
     *
     * @param dashboardWebService Dashboard web service
     */
    @Inject
    public DashboardTask(DashboardWebService dashboardWebService) {
        this.dashboardWebService = dashboardWebService;
    }

    /**
     * get the Dashboard response if response return success response
     * @param beararToken       login session token in header
     * @param responseInterface will perform API status
     */
    public void dashboardAPI(String beararToken, ResponseInterface responseInterface) {
        dashboardWebService.dashboardAPI(beararToken).enqueue(new Callback<DashboardResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<DashboardResponseModel> call, @NotNull Response<DashboardResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<DashboardResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "loginWithAPICall: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }
}
