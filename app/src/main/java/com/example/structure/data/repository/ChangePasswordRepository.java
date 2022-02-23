package com.example.structure.data.repository;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.retrofit.ChangePasswordWebService;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 20-02-2020
 */
public class ChangePasswordRepository {

    private String TAG = getClass().getSimpleName();
    private ChangePasswordWebService changePasswordWebService;

    @Inject
    public ChangePasswordRepository(ChangePasswordWebService changePasswordWebService) {
        this.changePasswordWebService = changePasswordWebService;
    }

    /**
     * Get Change Password response
     * @param paramModel contains user id
     */
    public CustomLiveData<CommonResponseModel> getChangePasswordResponse(String token, ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> customLiveData = new CustomLiveData<>();
        changePasswordWebService.changePasswordAPI(token, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    customLiveData.postSuccess(response.body());
                } else customLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                customLiveData.postError(t);
                call.cancel();
            }
        });
        return customLiveData;
    }
}
