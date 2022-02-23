package com.example.structure.data.repository;

import android.util.Log;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.DashboardEvseResponseModel;
import com.example.structure.data.models.DashboardResponseModel;
import com.example.structure.retrofit.DashboardWebService;
import com.example.structure.support.CustomLiveData;
import com.example.structure.utils.ResponseInterface;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 21-11-2019
 */
@Singleton
public class DashboardRepository {

    private String TAG = getClass().getSimpleName();
    private DashboardWebService dashboardWebService;

    @Inject
    public DashboardRepository(DashboardWebService dashboardWebService) {
        this.dashboardWebService = dashboardWebService;
    }

    /**
     * get the Dashboard response if response return success response
     *
     * @param beararToken login session token in header
     */
    public CustomLiveData<DashboardResponseModel> dashboardAPIRepository(String beararToken) {
        CustomLiveData<DashboardResponseModel> dashboardResponseModelCustomLiveData = new CustomLiveData<>();
        dashboardWebService.dashboardAPI(beararToken).enqueue(new Callback<DashboardResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<DashboardResponseModel> call, @NotNull Response<DashboardResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1"))
                        dashboardResponseModelCustomLiveData.postSuccess(response.body());
                    else dashboardResponseModelCustomLiveData.postSuccess(response.body());
                } else dashboardResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<DashboardResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "DashboardError: " + t.toString());
                dashboardResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return dashboardResponseModelCustomLiveData;
    }

    /**
     * get the Dashboard response if response return success response
     *
     * @param beararToken login session token in header
     */
    public void dashboardAPIdData(String beararToken, ResponseInterface responseInterface) {
        dashboardWebService.dashboardAPI(beararToken).enqueue(new Callback<DashboardResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<DashboardResponseModel> call, @NotNull Response<DashboardResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1"))
                        responseInterface.onSuccess(response.body());
                    else responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<DashboardResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "DashboardError: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * get the Dashboard response if response return success response
     *
     * @param beararToken login session token in header
     */
    public void dashboardEVSEAPIData(String beararToken, ResponseInterface responseInterface) {
        dashboardWebService.dashboardEvseAPI(beararToken).enqueue(new Callback<DashboardEvseResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<DashboardEvseResponseModel> call, @NotNull Response<DashboardEvseResponseModel> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getStatus().equals("1"))
                        responseInterface.onSuccess(response.body());
//                    else responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<DashboardEvseResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "DashboardError: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * get the Dashboard evse response if response return success response
     * @param beararToken login session token in header
     */
    public void dashboardEvsePortAPIData(String beararToken, String connectorId, ResponseInterface responseInterface) {
        dashboardWebService.dashboardEvsePortAPI(beararToken, connectorId).enqueue(new Callback<DashboardEvseResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<DashboardEvseResponseModel> call, @NotNull Response<DashboardEvseResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1"))
                        responseInterface.onSuccess(response.body());
                    else responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<DashboardEvseResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "DashboardError: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * get the Dashboard response if response return success response
     *
     * @param beararToken login session token in header
     */
    public CustomLiveData<CommonResponseModel> resendEmailAPIRepository(String beararToken) {
        CustomLiveData<CommonResponseModel> customLiveData = new CustomLiveData<>();
        dashboardWebService.resendEmailAPI(beararToken).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1"))
                        customLiveData.postSuccess(response.body());
                    else customLiveData.postSuccess(response.body());
                } else customLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "resendEmailError: " + t.toString());
                customLiveData.postError(t);
                call.cancel();
            }
        });
        return customLiveData;
    }
}
