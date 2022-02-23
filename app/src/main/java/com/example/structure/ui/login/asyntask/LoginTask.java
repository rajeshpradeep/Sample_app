package com.example.structure.ui.login.asyntask;

import android.util.Log;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.GetQuestionResponseModel;
import com.example.structure.data.models.LoginResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.retrofit.LoginWebService;
import com.example.structure.utils.ResponseInterface;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 29-10-2019
 */
public class LoginTask {

    private String TAG = getClass().getSimpleName();
    private LoginWebService loginWebService;

    /**
     * implementing the web service api call
     * @param loginWebService login web service
     */
    @Inject
    public LoginTask(LoginWebService loginWebService) {
        this.loginWebService = loginWebService;
    }

    /**
     * get the login response if response return success response
     * @param paramModel        contains password
     * @param responseInterface will perform API status
     */
    public void loginAPI(ParamModel paramModel, ResponseInterface responseInterface) {
        loginWebService.loginAPI(paramModel).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponseModel> call, @NotNull Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "loginWithAPICall: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * get the user type response
     * @param responseInterface will perform API status
     */
    public void getUserTypeAPI(String authToken, ResponseInterface responseInterface) {
        loginWebService.getUserTypeAPI(authToken).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "getUserTypeAPI: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * get the secret question
     *
     * @param responseInterface will perform API status
     */
    public void secretQuestionAPI(ResponseInterface responseInterface) {
        loginWebService.getQuestionAPI().enqueue(new Callback<GetQuestionResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<GetQuestionResponseModel> call, @NotNull Response<GetQuestionResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<GetQuestionResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "secretQuestionAPI: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * get the forgot password response if response return success response
     *
     * @param paramModel        email, secret question and secret answer
     * @param responseInterface will perform API status
     */
    public void forgotPasswordAPI(ParamModel paramModel, ResponseInterface responseInterface) {
        Log.i(TAG, "forgotPasswordAPI: " + paramModel.getEmail() + " answer:" + paramModel.getSecret_answer());
        loginWebService.forgotPasswordAPI(paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "forgotPasswordAPI: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }

    /**
     * get the logout response
     * @param paramModel        contains user id and type
     * @param responseInterface will perform API status
     */
    public void logoutAPI(String bearerToken, ParamModel paramModel, ResponseInterface responseInterface) {
        Log.i(TAG, "logoutAPI: " + paramModel.getUser_id());
        loginWebService.logoutAPI(bearerToken, paramModel).enqueue(new Callback<CommonResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CommonResponseModel> call, @NotNull Response<CommonResponseModel> response) {
                if (response.isSuccessful()) {
                    responseInterface.onSuccess(response.body());
                } else responseInterface.onFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CommonResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "getUserTypeAPI: " + t.toString());
                responseInterface.onError(t);
                call.cancel();
            }
        });
    }
}
