package com.example.structure.viewmodel;

import com.example.structure.data.models.MonitorEVSEResponseModel;
import com.example.structure.data.models.MonitorResponseModel;
import com.example.structure.data.repository.MonitorRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 07-02-2020
 */
public class MonitorViewModel extends ViewModel {

    private String TAG = getClass().getSimpleName();
    private CustomLiveData<MonitorResponseModel> monitorResponseCustomLiveData;
    private CustomLiveData<MonitorEVSEResponseModel> monitorEVSECustomLiveData;
    private MonitorRepository monitorRepository;

    @Inject
    public MonitorViewModel(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    /**
     * Monitor SCA API Call
     **/
    public CustomLiveData<MonitorResponseModel> monitorAPILiveData(String beararToken) {
//        Log.i(TAG, "dashboardAPILiveData:getUser_id " + paramModel.getUser_id());
        monitorResponseCustomLiveData = monitorRepository.monitorAPIRepository(beararToken);
        return monitorResponseCustomLiveData;
    }

    /**
     * Monitor EVSE API Call
     **/
    /*public CustomLiveData<MonitorEVSEResponseModel> monitorEVSEAPILiveData(String beararToken) {
//        Log.i(TAG, "dashboardAPILiveData:getUser_id " + paramModel.getUser_id());
        monitorEVSECustomLiveData = monitorRepository.monitorEVSEAPIRepository(beararToken);
        return monitorEVSECustomLiveData;
    }*/
}
