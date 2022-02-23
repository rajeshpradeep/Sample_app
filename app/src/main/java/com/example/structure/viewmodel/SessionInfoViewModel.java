package com.example.structure.viewmodel;

import com.example.structure.data.models.DetailedSessionResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.models.SessionInfoResponseModel;
import com.example.structure.data.repository.SessionInfoRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 27-02-2020
 */
public class SessionInfoViewModel extends ViewModel {

    private CustomLiveData<SessionInfoResponseModel> sessionInfoResponseLiveData;
    private CustomLiveData<DetailedSessionResponseModel> detailedSessionCustomLiveData;
    private SessionInfoRepository sessionInfoRepository;

    @Inject
    public SessionInfoViewModel(SessionInfoRepository sessionInfoRepository) {
        this.sessionInfoRepository = sessionInfoRepository;
    }

    /**
     * Calling Change password View model
     */
    public CustomLiveData<SessionInfoResponseModel> getSessionInfoViewModel(String token, ParamModel paramModel) {
        sessionInfoResponseLiveData = sessionInfoRepository.getSessionInfoResponse(token, paramModel);
        return sessionInfoResponseLiveData;
    }

    /**
     * Calling graph detail View model
     */
    public CustomLiveData<DetailedSessionResponseModel> getSessionGraphInfoViewModel(String token,String transaction_id, String transaction_type) {
        detailedSessionCustomLiveData = sessionInfoRepository.getSessionGraphResponse(token, transaction_id, transaction_type);
        return detailedSessionCustomLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
