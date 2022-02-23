package com.example.structure.data.repository;

import com.example.structure.data.models.MakeModelResponseModel;
import com.example.structure.data.models.YearListResponseModel;
import com.example.structure.retrofit.AddElectricVehicleWebService;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 02-12-2019
 */
@Singleton
public class AddElectricVehicleRepository {

    private String TAG = getClass().getSimpleName();
    private AddElectricVehicleWebService addElectricVehicleWebService;

    @Inject
    public AddElectricVehicleRepository(AddElectricVehicleWebService addElectricVehicleRepository) {
        this.addElectricVehicleWebService = addElectricVehicleRepository;
    }

    /**
     * Get Year list
     */
    public CustomLiveData<YearListResponseModel> getYearListAPIRepository() {
        CustomLiveData<YearListResponseModel> addElectricVehicleResponseModelCustomLiveData = new CustomLiveData<>();
        addElectricVehicleWebService.getYearList().enqueue(new Callback<YearListResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<YearListResponseModel> call, @NotNull Response<YearListResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        addElectricVehicleResponseModelCustomLiveData.postSuccess(response.body());
                    else addElectricVehicleResponseModelCustomLiveData.postSuccess(response.body());
                } else addElectricVehicleResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<YearListResponseModel> call, @NotNull Throwable t) {
                addElectricVehicleResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return addElectricVehicleResponseModelCustomLiveData;
    }

    /**
     * Get Make list
     */
    public CustomLiveData<MakeModelResponseModel> getMakeListAPIRepository(String year) {
        CustomLiveData<MakeModelResponseModel> makeModelResponseModelCustomLiveData = new CustomLiveData<>();
        addElectricVehicleWebService.getMakeList(year).enqueue(new Callback<MakeModelResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MakeModelResponseModel> call, @NotNull Response<MakeModelResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        makeModelResponseModelCustomLiveData.postSuccess(response.body());
                    else makeModelResponseModelCustomLiveData.postSuccess(response.body());
                } else makeModelResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<MakeModelResponseModel> call, @NotNull Throwable t) {
                makeModelResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return makeModelResponseModelCustomLiveData;
    }

    /**
     * Get Make list
     */
    public CustomLiveData<MakeModelResponseModel> getModelListAPIRepository(String year, String make) {
        CustomLiveData<MakeModelResponseModel> makeModelResponseModelCustomLiveData = new CustomLiveData<>();
        addElectricVehicleWebService.getModelList(year, make).enqueue(new Callback<MakeModelResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<MakeModelResponseModel> call, @NotNull Response<MakeModelResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        makeModelResponseModelCustomLiveData.postSuccess(response.body());
                    else makeModelResponseModelCustomLiveData.postSuccess(response.body());
                } else makeModelResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<MakeModelResponseModel> call, @NotNull Throwable t) {
                makeModelResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return makeModelResponseModelCustomLiveData;
    }
}
