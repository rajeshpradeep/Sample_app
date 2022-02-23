package com.example.structure.data.repository;

import android.util.Log;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.CountryStateResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.PersonalInfoResponseModel;
import com.example.structure.retrofit.PeronalInfoWebService;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 04-02-2020
 */
public class PeronalInfoRepository {

    private String TAG = getClass().getSimpleName();
    PeronalInfoWebService peronalInfoWebService;

    @Inject
    public PeronalInfoRepository(PeronalInfoWebService peronalInfoWebService) {
        this.peronalInfoWebService = peronalInfoWebService;
    }

    /**
     * Check email exist
     */
    public CustomLiveData<CommonResponseModel> getEmailExist(ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        peronalInfoWebService.isEmailExist(paramModel).enqueue(new Callback<CommonResponseModel>() {
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
        peronalInfoWebService.getCountryList().enqueue(new Callback<CountryStateResponseModel>() {
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
        peronalInfoWebService.getStateList().enqueue(new Callback<CountryStateResponseModel>() {
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
     * call Leader board alias exist API
     */
    public CustomLiveData<CommonResponseModel> getAliasExistRepo(ParamModel paramModel) {
        CustomLiveData<CommonResponseModel> commonResponseModelCustomLiveData = new CustomLiveData<>();
        peronalInfoWebService.isAliasExist(paramModel).enqueue(new Callback<CommonResponseModel>() {
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
     * get the PersonalInfo response if response return success response
     * @param beararToken       login session token in header
     */
    public CustomLiveData<PersonalInfoResponseModel> personalInfoAPIRepository(String beararToken) {
        CustomLiveData<PersonalInfoResponseModel> personalInfoResponseModelCustomLiveData = new CustomLiveData<>();
        peronalInfoWebService.personalInfoAPI(beararToken).enqueue(new Callback<PersonalInfoResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<PersonalInfoResponseModel> call, @NotNull Response<PersonalInfoResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        personalInfoResponseModelCustomLiveData.postSuccess(response.body());
                    else personalInfoResponseModelCustomLiveData.postSuccess(response.body());
                } else personalInfoResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<PersonalInfoResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "LeaderboardWithAPICall: " + t.toString());
                personalInfoResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return personalInfoResponseModelCustomLiveData;
    }

    /**
     * get the Updated PersonalInfo response if response return success response
     * @param beararToken       login session token in header
     * @param paramModel        contains user_id, email, name, addresses, city, state, country, postal code and alias
     */
    public CustomLiveData<CommonResponseModel> updatePersonalInfoAPIRepository(String beararToken, ParamModel paramModel) {
        Log.i(TAG, "LeaderboardAPICall: " + paramModel.getUser_id() + " token:" + beararToken);
        CustomLiveData<CommonResponseModel> commonResponseCustomLiveData = new CustomLiveData<>();
        peronalInfoWebService.updatePersonalInfoAPI(beararToken, paramModel).enqueue(new Callback<CommonResponseModel>() {
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
