package com.example.structure.retrofit;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.NotificationResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;

/**
 * Created by Rajesh Pradeep G on 05-02-2020
 */
public interface NotificationWebService {

    /**
     * Notification API
     */
    @GET(Constant.NOTIFICATION_ENDPOINTS)
    Call<NotificationResponseModel> notificationAPI(@Header("Authorization") String authHeader);

    /**
     * Leaderboard API
     */
    @PATCH(Constant.UPDATE_NOTIFICATION_ENDPOINTS)
    Call<CommonResponseModel> updateNotificationAPI(@Header("Authorization") String authHeader, @Body ParamModel paramModel);
}
