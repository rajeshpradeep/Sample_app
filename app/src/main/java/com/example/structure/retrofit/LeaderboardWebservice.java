package com.example.structure.retrofit;

import com.example.structure.data.models.LeaderboardResponseModel;
import com.example.structure.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Rajesh Pradeep G on 27-11-2019
 */
public interface LeaderboardWebservice {

    /**
     * Leaderboard API
     */
    @GET(Constant.LEADERBOARD_ENDPOINTS + "/{" + Constant.OFFSET_PARAM+ "}/{" + Constant.LIMIT_PARAM + "}")
    Call<LeaderboardResponseModel> leaderboardAPI(@Header("Authorization") String authHeader, @Path("offset") String offset,
                                                  @Path("limit") String limit);
}
