package com.example.structure.data.repository;

import android.util.Log;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.VehicleListResponseModel;
import com.example.structure.retrofit.VehicleWebService;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 22-01-2020
 */
public class VehicleRepository {

    private String TAG = getClass().getSimpleName();
    private VehicleWebService vehicleWebService;

    @Inject
    public VehicleRepository(VehicleWebService vehicleWebService) {
        this.vehicleWebService = vehicleWebService;
    }

    /**
     * get the Vehicle List response if response return success response
     * @param beararToken       login session token in header
     */
    public CustomLiveData<VehicleListResponseModel> vehicelListAPIRepository(String beararToken) {
        CustomLiveData<VehicleListResponseModel> vehicleListResponseModelCustomLiveData = new CustomLiveData<>();
        vehicleWebService.vehicleListAPI(beararToken).enqueue(new Callback<VehicleListResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<VehicleListResponseModel> call, @NotNull Response<VehicleListResponseModel> response) {
                if (response.isSuccessful()) {
//                    if(response.body().getStatus().equals("1"))
                        vehicleListResponseModelCustomLiveData.postSuccess(response.body());
//                    else vehicleListResponseModelCustomLiveData.postSuccess(response.body());
                } else vehicleListResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<VehicleListResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "VehicleListAPI: " + t.toString());
                vehicleListResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return vehicleListResponseModelCustomLiveData;
    }

    /**
     * get the Vehicle added response if response return success response
     * @param beararToken       login session token in header
     * @param paramModel        contains user_id
     */
    public CustomLiveData<CommonResponseModel> addVehicelAPIRepository(String beararToken, ParamModel paramModel) {
        Log.i(TAG, "VehicleListAPI: " + paramModel.getUser_id() + " token:" + beararToken);
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        vehicleWebService.addVehicleAPI(beararToken, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        commonResponseModelCustomLiveData.postSuccess(response.body());
                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else commonResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "VehicleListAPI: " + t.toString());
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * get the update the Vehicle response if response return success response
     * @param beararToken       login session token in header
     * @param paramModel        contains user_id
     */
    public CustomLiveData<CommonResponseModel> updateVehicelAPIRepository(String beararToken, ParamModel paramModel) {
        Log.i(TAG, "VehicleListAPI: " + paramModel.getUser_id() + " token:" + beararToken);
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        vehicleWebService.updateVehicleAPI(beararToken, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
//                    if(response.body().getStatus().equals("1"))
                        commonResponseModelCustomLiveData.postSuccess(response.body());
//                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else commonResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "VehicleListAPI: " + t.toString());
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * get the deleted Vehicle response if response return success response
     * @param beararToken       login session token in header
     */
    public CustomLiveData<CommonResponseModel> deleteVehicelAPIRepository(String beararToken, String vehicleId) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        vehicleWebService.deleteVehicleAPI(beararToken, vehicleId).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        commonResponseModelCustomLiveData.postSuccess(response.body());
                    else commonResponseModelCustomLiveData.postSuccess(response.body());
                } else commonResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "VehicleListAPI: " + t.toString());
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }
}
