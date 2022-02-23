package com.example.structure.data.repository;

import android.util.Log;

import com.example.structure.data.models.LeaderboardResponseModel;
import com.example.structure.retrofit.LeaderboardWebservice;
import com.example.structure.support.CustomLiveData;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajesh Pradeep G on 27-11-2019
 */
@Singleton
public class LeaderboardRepository {

    private String TAG = getClass().getSimpleName();
    private LeaderboardWebservice leaderboardWebservice;

    @Inject
    public LeaderboardRepository(LeaderboardWebservice leaderboardWebservice) {
        this.leaderboardWebservice = leaderboardWebservice;
    }

    /**
     * get the Dashboard response if response return success response
     * @param beararToken       login session token in header
     */
    public CustomLiveData<LeaderboardResponseModel> leaderboardAPIRepository(String beararToken, String offset, String limit) {
        Log.i(TAG, "LeaderboardAPICall:token:" + beararToken);
        CustomLiveData<LeaderboardResponseModel> leaderboardResponseModelCustomLiveData = new CustomLiveData<>();
        leaderboardWebservice.leaderboardAPI(beararToken, offset, limit).enqueue(new Callback<LeaderboardResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<LeaderboardResponseModel> call, @NotNull Response<LeaderboardResponseModel> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("1"))
                        leaderboardResponseModelCustomLiveData.postSuccess(response.body());
                    else leaderboardResponseModelCustomLiveData.postSuccess(response.body());
                } else leaderboardResponseModelCustomLiveData.postFailure(response);
            }

            @Override
            public void onFailure(@NotNull Call<LeaderboardResponseModel> call, @NotNull Throwable t) {
                Log.e(TAG, "LeaderboardWithAPICall: " + t.toString());
                leaderboardResponseModelCustomLiveData.postError(t);
                call.cancel();
            }
        });
        return leaderboardResponseModelCustomLiveData;
    }
}
