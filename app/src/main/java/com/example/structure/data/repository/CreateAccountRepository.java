package com.example.structure.data.repository;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CreateAccountParamModel;
import com.example.structure.data.models.GetQuestionResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.retrofit.CreateAccountWebservice;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 03-12-2019
 */
@Singleton
public class CreateAccountRepository {

    private String TAG = getClass().getSimpleName();
    private CreateAccountWebservice createAccountWebservice;

    @Inject
    public CreateAccountRepository(CreateAccountWebservice createAccountWebservice) {
        this.createAccountWebservice = createAccountWebservice;
    }

    /**
     * Check email exist
     */
    public CustomLiveData<CommonResponseModel> getEmailExist(ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        createAccountWebservice.isEmailExist(paramModel).enqueue(new Callback<CommonResponseModel>() {
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
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * Check username exist
     */
    public CustomLiveData<CommonResponseModel> getUserNameExist(ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        createAccountWebservice.isUsernameExist(paramModel).enqueue(new Callback<CommonResponseModel>() {
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
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * Get Country list
     */
    public CustomLiveData<CountryStateResponseModel> getCountryListAPIRepository() {
        CustomLiveData<CountryStateResponseModel> pickerResponseModelCustomLiveData = new CustomLiveData<>();
        createAccountWebservice.getCountryList().enqueue(new Callback<CountryStateResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CountryStateResponseModel> call, @NotNull Response<CountryStateResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        pickerResponseModelCustomLiveData.postSuccess(response.body());
                    else pickerResponseModelCustomLiveData.postSuccess(response.body());
                } else pickerResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CountryStateResponseModel> call, @NotNull Throwable t) {
                pickerResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return pickerResponseModelCustomLiveData;
    }

    /**
     * Get State list
     */
    public CustomLiveData<CountryStateResponseModel> getStateListAPIRepository() {
        CustomLiveData<CountryStateResponseModel> pickerResponseModelCustomLiveData = new CustomLiveData<>();
        createAccountWebservice.getStateList().enqueue(new Callback<CountryStateResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<CountryStateResponseModel> call, @NotNull Response<CountryStateResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        pickerResponseModelCustomLiveData.postSuccess(response.body());
                    else pickerResponseModelCustomLiveData.postSuccess(response.body());
                } else pickerResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<CountryStateResponseModel> call, @NotNull Throwable t) {
                pickerResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return pickerResponseModelCustomLiveData;
    }

    /**
     * Get Secret question list
     */
    public CustomLiveData<GetQuestionResponseModel> getSecretQuestionAPIRepository() {
        CustomLiveData<GetQuestionResponseModel> questionResponseModelCustomLiveData = new CustomLiveData<>();
        createAccountWebservice.getQuestionAPI().enqueue(new Callback<GetQuestionResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<GetQuestionResponseModel> call, @NotNull Response<GetQuestionResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        questionResponseModelCustomLiveData.postSuccess(response.body());
                    else questionResponseModelCustomLiveData.postSuccess(response.body());
                } else questionResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<GetQuestionResponseModel> call, @NotNull Throwable t) {
                questionResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return questionResponseModelCustomLiveData;
    }

    /**
     * call Leader board alias exist API
     */
    public CustomLiveData<CommonResponseModel> getAliasExistRepo(ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        createAccountWebservice.isAliasExist(paramModel).enqueue(new Callback<CommonResponseModel>() {
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
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }

    /**
     * call SignUP API
     */
    public CustomLiveData<CommonResponseModel> signUpRepo(CreateAccountParamModel createAccountParamModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        createAccountWebservice.signUpAPI(createAccountParamModel).enqueue(new Callback<CommonResponseModel>() {
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
                commonResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return commonResponseModelCustomLiveData;
    }
}
