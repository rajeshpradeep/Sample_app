package com.example.structure.data.repository;

import android.util.Log;

import com.example.structure.data.models.MonitorEVSEResponseModel;
import com.example.structure.data.models.MonitorResponseModel;
import com.example.structure.retrofit.MonitorWebService;
import com.example.structure.support.CustomLiveData;
import com.example.structure.utils.ResponseInterface;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 07-02-2020
 */
public class MonitorRepository {

    private String TAG = getClass().getSimpleName();
    private MonitorWebService monitorWebService;

    @Inject
    public MonitorRepository(MonitorWebService monitorWebService) {
        this.monitorWebService = monitorWebService;
    }

    /*public MonitorRepository(@NonNull Context context, @NonNull WorkerParameters workerParams, MonitorWebService monitorWebService) {
        super(context, workerParams);
        this.monitorWebService = monitorWebService;
    }*/

    /**
     * get the Dashboard response if response return success response
     *
     * @param beararToken login session token in header
     */
    public CustomLiveData<MonitorResponseModel> monitorAPIRepository(String beararToken) {
//        Log.i(TAG, "DashboardAPICall:getUser_id " + paramModel.getUser_id());
        CustomLiveData<MonitorResponseModel> monitorCustomLiveData = new CustomLiveData<>();
        monitorWebService.monitorAPI(beararToken).enqueue(new Callback<MonitorResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MonitorResponseModel> call, @NotNull Response<MonitorResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1"))
                        monitorCustomLiveData.postSuccess(response.body());
                    else monitorCustomLiveData.postSuccess(response.body());
                } else monitorCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<MonitorResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "MonitorError: " + t.toString());
                monitorCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return monitorCustomLiveData;
    }

    /**
     * get the Dashboard response if response return success response
     *
     * @param beararToken login session token in header
     */
    public void monitorAPIData(String beararToken, ResponseInterface responseInterface) {
        monitorWebService.monitorAPI(beararToken).enqueue(new Callback<MonitorResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MonitorResponseModel> call, @NotNull Response<MonitorResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<MonitorResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "MonitorError: " + t.toString());
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
    /*public CustomLiveData<MonitorEVSEResponseModel> monitorEVSEAPIRepository(String beararToken) {
//        Log.i(TAG, "DashboardAPICall:getUser_id " + paramModel.getUser_id());
        CustomLiveData<MonitorEVSEResponseModel> monitorEVSECustomLiveData = new CustomLiveData<>();
        monitorWebService.monitorEVSEAPI(beararToken).enqueue(new Callback<MonitorEVSEResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MonitorEVSEResponseModel> call, @NotNull Response<MonitorEVSEResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1"))
                        monitorEVSECustomLiveData.postSuccess(response.body());
                    else monitorEVSECustomLiveData.postSuccess(response.body());
                } else monitorEVSECustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<MonitorEVSEResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "MonitorError: " + t.toString());
                monitorEVSECustomLiveData.postError(t);
                call.cancel();
            }
        });
        return monitorEVSECustomLiveData;
    }*/

    /**
     * get the Dashboard response if response return success response
     *
     * @param beararToken login session token in header
     */
    public void monitorEVSEAPIData(String beararToken, ResponseInterface responseInterface) {
//        Log.i(TAG, "DashboardAPICall:getUser_id " + paramModel.getUser_id());
        monitorWebService.monitorEVSEAPI(beararToken).enqueue(new Callback<MonitorEVSEResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MonitorEVSEResponseModel> call, @NotNull Response<MonitorEVSEResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1"))
                        responseInterface.onSuccess(response.body());
                    else responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<MonitorEVSEResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "MonitorError: " + t.toString());
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
    public void monitorEVSEPortAPIData(String beararToken, String portId, ResponseInterface responseInterface) {
//        Log.i(TAG, "DashboardAPICall:getUser_id " + paramModel.getUser_id());
        monitorWebService.monitorEVSEPortAPI(beararToken, portId).enqueue(new Callback<MonitorEVSEResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MonitorEVSEResponseModel> call, @NotNull Response<MonitorEVSEResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<MonitorEVSEResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "MonitorError: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

}
