package com.example.structure.data.repository;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ControlResponseModel;
import com.example.structure.data.models.EvMileageChangeVehicleResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.retrofit.ControlWebService;
import com.example.structure.support.CustomLiveData;
import com.example.structure.utils.ResponseInterface;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 21-11-2019
 */
public class ControlRepository {

    private String TAG = getClass().getSimpleName();
    private ControlWebService controlWebService;

    @Inject
    public ControlRepository(ControlWebService controlWebService) {
        this.controlWebService = controlWebService;
    }

    /**
     * Get Control response
     */
    public CustomLiveData<ControlResponseModel> getControlResponse(String token) {
        CustomLiveData<ControlResponseModel> controlResponseModelCustomLiveData = new CustomLiveData<>();
        controlWebService.controlAPI(token).enqueue(new Callback<ControlResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ControlResponseModel> call, @NotNull Response<ControlResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        controlResponseModelCustomLiveData.postSuccess(response.body());
                    else controlResponseModelCustomLiveData.postSuccess(response.body());
                } else controlResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<ControlResponseModel> call, @NotNull Throwable t) {
                controlResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return controlResponseModelCustomLiveData;
    }

    /**
     * Get Control response
     */
    public void getControlData(String token, ResponseInterface responseInterface) {
        controlWebService.controlAPI(token).enqueue(new Callback<ControlResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ControlResponseModel> call, @NotNull Response<ControlResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<ControlResponseModel> call, @NotNull Throwable t) {
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * Get EVMileageChangeVehicle response
     */
    public CustomLiveData<EvMileageChangeVehicleResponseModel> getEVMileageChangeVehicleResponse(String token, String deviceId,
                                                                                                 String activeSession, String vehicleId) {
        CustomLiveData<EvMileageChangeVehicleResponseModel> evMileageChangeVehicleCustomLiveData = new CustomLiveData<>();
        controlWebService.evMileageChangeVehiclelAPI(token, deviceId, activeSession, vehicleId).enqueue(new Callback<EvMileageChangeVehicleResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<EvMileageChangeVehicleResponseModel> call, @NotNull Response<EvMileageChangeVehicleResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        evMileageChangeVehicleCustomLiveData.postSuccess(response.body());
                    else evMileageChangeVehicleCustomLiveData.postSuccess(response.body());
                } else evMileageChangeVehicleCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<EvMileageChangeVehicleResponseModel> call, @NotNull Throwable t) {
                evMileageChangeVehicleCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return evMileageChangeVehicleCustomLiveData;
    }

    /**
     * Get Apply PublishChargeMode response
     *
     * @param paramModel contains user id, vehicle id, device id, evstatus and ev milage needed
     */
    public CustomLiveData<CommonResponseModel> getApplyPublishChargeModeResponse(String token, ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        controlWebService.applyPublishChargeModeAPI(token, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        commonResponseModelCustomLiveData.postSuccess(response.body());
                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else commonResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * Get Apply PublishChargeMode response
     *
     * @param paramModel contains user id, vehicle id, device id, evstatus and ev milage needed
     */
    public CustomLiveData<CommonResponseModel> getApplyPublishChargeModeResponse(String token, ParamModel paramModel, ResponseInterface responseInterface) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        controlWebService.applyPublishChargeModeAPI(token, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        responseInterface.onSuccess(response.body());
//                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                responseInterface.onError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * Get ApplyEvMileage response
     *
     * @param paramModel contains user id, vehicle id, device id, evstatus and ev milage needed
     */
    public CustomLiveData<CommonResponseModel> getApplyEvMileageResponse(String token, ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        controlWebService.applyEvMileageAPI(token, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        commonResponseModelCustomLiveData.postSuccess(response.body());
//                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else commonResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * Get ApplyEvMileage response
     *
     * @param paramModel contains user id, vehicle id, device id, evstatus and ev milage needed
     */
    public CustomLiveData<CommonResponseModel> getApplyEvMileageResponse(String token, ParamModel paramModel, ResponseInterface responseInterface) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        controlWebService.applyEvMileageAPI(token, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        responseInterface.onSuccess(response.body());
//                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                responseInterface.onError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * Get ApplyEvMileage response
     *
     * @param paramModel contains user id, device id, hour, minutes and timerstatus
     */
    public CustomLiveData<CommonResponseModel> getapplyTimerControlResponse(String token, ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        controlWebService.applyTimerControlAPI(token, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        commonResponseModelCustomLiveData.postSuccess(response.body());
                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else commonResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * Get ApplyEvMileage response
     *
     * @param paramModel contains user id, device id, hour, minutes and timerstatus
     */
    public void getapplyTimeControlResponse(String token, ParamModel paramModel, ResponseInterface responseInterface) {
        controlWebService.applyTimerControlAPI(token, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
//                    if (response.body().getStatus().equalsIgnoreCase("1"))
                        responseInterface.onSuccess(response.body());
//                    else responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }
}
