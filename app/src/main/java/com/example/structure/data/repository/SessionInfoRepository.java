package com.example.structure.data.repository;

import com.example.structure.data.models.DetailedSessionResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.SessionInfoResponseModel;
import com.example.structure.retrofit.SessionInfoWebService;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 27-02-2020
 */
public class SessionInfoRepository {

    private String TAG = getClass().getSimpleName();
    private SessionInfoWebService sessionInfoWebService;

    @Inject
    public SessionInfoRepository(SessionInfoWebService sessionInfoWebService) {
        this.sessionInfoWebService = sessionInfoWebService;
    }

    /**
     * Get Change Password response
     * @param paramModel contains user id
     */
    public CustomLiveData<SessionInfoResponseModel> getSessionInfoResponse(String token, ParamModel paramModel) {
        CustomLiveData<SessionInfoResponseModel> sessionInfoCustomLiveData = new CustomLiveData<>();
        sessionInfoWebService.sessionInfoAPI(token, paramModel).enqueue(new Callback<SessionInfoResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<SessionInfoResponseModel> call, @NotNull Response<SessionInfoResponseModel> response) {
                if (response.isSuccessful()) {
                    sessionInfoCustomLiveData.postSuccess(response.body());
                } else sessionInfoCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<SessionInfoResponseModel> call, @NotNull Throwable t) {
                sessionInfoCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return sessionInfoCustomLiveData;
    }

    /**
     * Get Graph Detail response
     */
    public CustomLiveData<DetailedSessionResponseModel> getSessionGraphResponse(String token, String transaction_id, String transaction_type) {
        CustomLiveData<DetailedSessionResponseModel> detailedSessionCustomLiveData = new CustomLiveData<>();
        sessionInfoWebService.sessionGraphInfoAPI(token, transaction_id, transaction_type).enqueue(new Callback<DetailedSessionResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<DetailedSessionResponseModel> call, @NotNull Response<DetailedSessionResponseModel> response) {
                if (response.isSuccessful()) {
                    detailedSessionCustomLiveData.postSuccess(response.body());
                } else detailedSessionCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<DetailedSessionResponseModel> call, @NotNull Throwable t) {
                detailedSessionCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return detailedSessionCustomLiveData;
    }
}
