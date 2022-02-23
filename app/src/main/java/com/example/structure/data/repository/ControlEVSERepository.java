package com.example.structure.data.repository;

import android.util.Log;

import com.example.structure.data.models.ControlEVSEResponseModel;
import com.example.structure.data.models.EVSEModeStatusResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.retrofit.ControlEVSEWebService;
import com.example.structure.support.CustomLiveData;
import com.example.structure.utils.ResponseInterface;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 10-03-2020
 */
public class ControlEVSERepository {

    private String TAG = getClass().getSimpleName();
    private ControlEVSEWebService controlEVSEWebService;

    @Inject
    public ControlEVSERepository(ControlEVSEWebService controlEVSEWebService) {
        this.controlEVSEWebService = controlEVSEWebService;
    }

    /**
     * Get Control EVSE response
     * @param paramModel contains user id
     */
   /* public CustomLiveData<ControlEVSEResponseModel> getControlEVSEResponse(String token, ParamModel paramModel) {
        CustomLiveData<ControlEVSEResponseModel> controlEVSEResponseModelCustomLiveData = new CustomLiveData<>();
        controlEVSEWebService.controlEVSEAPI(token, paramModel).enqueue(new Callback<ControlEVSEResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ControlEVSEResponseModel> call, @NotNull Response<ControlEVSEResponseModel> response) {
                if (response.isSuccessful()) {
                    controlEVSEResponseModelCustomLiveData.postSuccess(response.body());
                } else controlEVSEResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<ControlEVSEResponseModel> call, @NotNull Throwable t) {
                controlEVSEResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return controlEVSEResponseModelCustomLiveData;
    }*/

    /**
     * Get Control EVSE response
     */
    public void getControlEVSEResponse(String token, ResponseInterface responseInterface) {
        controlEVSEWebService.controlEVSEAPI(token).enqueue(new Callback<ControlEVSEResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ControlEVSEResponseModel> call, @NotNull Response<ControlEVSEResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<ControlEVSEResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "ControlError: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * Get Control EVSE response
     */
    public void getControlEVSEPortResponse(String token, String connectorId, String portId, ResponseInterface responseInterface) {
        controlEVSEWebService.controlEVSEPortAPI(token, connectorId, portId).enqueue(new Callback<ControlEVSEResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ControlEVSEResponseModel> call, @NotNull Response<ControlEVSEResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<ControlEVSEResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "ControlError: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * Get Evse Mode Status response
     * @param paramModel contains user id
     */
    public CustomLiveData<EVSEModeStatusResponseModel> getEVSEModeStatusResponse(String token, ParamModel paramModel) {
        CustomLiveData<EVSEModeStatusResponseModel> evseModeStatusCustomLiveData = new CustomLiveData<>();
        controlEVSEWebService.setEvseModeStatusAPI(token, paramModel).enqueue(new Callback<EVSEModeStatusResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<EVSEModeStatusResponseModel> call, @NotNull Response<EVSEModeStatusResponseModel> response) {
                if (response.isSuccessful()) {
                    evseModeStatusCustomLiveData.postSuccess(response.body());
                } else evseModeStatusCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<EVSEModeStatusResponseModel> call, @NotNull Throwable t) {
                evseModeStatusCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return evseModeStatusCustomLiveData;
    }

    /**
     * Get Remote Operation response
     * @param paramModel contains user id
     */
    public CustomLiveData<EVSEModeStatusResponseModel> getRemoteOperationResponse(String token, ParamModel paramModel) {
        CustomLiveData<EVSEModeStatusResponseModel> evseModeStatusCustomLiveData = new CustomLiveData<>();
        controlEVSEWebService.updateRemoteOperationAPI(token, paramModel).enqueue(new Callback<EVSEModeStatusResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<EVSEModeStatusResponseModel> call, @NotNull Response<EVSEModeStatusResponseModel> response) {
                if (response.isSuccessful()) {
                    evseModeStatusCustomLiveData.postSuccess(response.body());
                } else evseModeStatusCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<EVSEModeStatusResponseModel> call, @NotNull Throwable t) {
                evseModeStatusCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return evseModeStatusCustomLiveData;
    }
}
