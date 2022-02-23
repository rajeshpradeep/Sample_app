package com.example.structure.data.repository;

import android.util.Log;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.NotificationResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.retrofit.NotificationWebService;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 05-02-2020
 */
public class NotificationRepository {

    private String TAG = getClass().getSimpleName();
    private NotificationWebService notificationWebService;

    @Inject
    public NotificationRepository(NotificationWebService notificationWebService) {
        this.notificationWebService = notificationWebService;
    }

    /**
     * get the notification response if response return success response
     * @param beararToken       login session token in header
     */
    public CustomLiveData<NotificationResponseModel> notificationAPIRepository(String beararToken) {
        CustomLiveData<NotificationResponseModel> notificationCustomLiveData = new CustomLiveData<>();
        notificationWebService.notificationAPI(beararToken).enqueue(new Callback<NotificationResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<NotificationResponseModel> call, @NotNull Response<NotificationResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        notificationCustomLiveData.postSuccess(response.body());
                    else notificationCustomLiveData.postSuccess(response.body());
                } else notificationCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<NotificationResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "LeaderboardWithAPICall: " + t.toString());
                notificationCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return notificationCustomLiveData;
    }

    /**
     * get the Updated notification response if response return success response
     * @param beararToken       login session token in header
     * @param paramModel        contains user_id, is_fully_charged,is_unexpectedly_interrupted, is_notify_to_plugin_mobile,
     *                          notify_plugin_time
     */
    public CustomLiveData<CommonResponseModel> updatePersonalInfoAPIRepository(String beararToken, ParamModel paramModel) {
        Log.i(TAG, "LeaderboardAPICall: " + paramModel.getUser_id() + " token:" + beararToken);
        CustomLiveData<CommonResponseModel> commonResponseCustomLiveData = new CustomLiveData<>();
        notificationWebService.updateNotificationAPI(beararToken, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        commonResponseCustomLiveData.postSuccess(response.body());
                    else commonResponseCustomLiveData.postSuccess(response.body());
                } else commonResponseCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "LeaderboardWithAPICall: " + t.toString());
                commonResponseCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseCustomLiveData;
    }
}
