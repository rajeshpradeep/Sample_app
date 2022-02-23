package com.example.structure.viewmodel;

import com.example.structure.data.models.LeaderboardResponseModel;
import com.example.structure.data.repository.LeaderboardRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 27-11-2019
 */
public class LeaderboardViewModel extends ViewModel {

    private CustomLiveData<LeaderboardResponseModel> leaderboardResponseModelCustomLiveData;
    private LeaderboardRepository leaderboardRepository;

    @Inject
    public LeaderboardViewModel(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public CustomLiveData<LeaderboardResponseModel> getLeaderboardResponse(String beararToken, String offset, String limit) {
        return  leaderboardResponseModelCustomLiveData = leaderboardRepository.leaderboardAPIRepository(beararToken, offset, limit);
    }
}
