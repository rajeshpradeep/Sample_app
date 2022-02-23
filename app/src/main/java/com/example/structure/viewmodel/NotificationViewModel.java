package com.example.structure.viewmodel;

import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.NotificationResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.data.repository.NotificationRepository;
import com.example.structure.support.CustomLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

/**
 * Created by Rajesh Pradeep G on 05-02-2020
 */
public class NotificationViewModel extends ViewModel {

    private String TAG = getClass().getSimpleName();
    private NotificationRepository notificationRepository;
    private CustomLiveData<CommonResponseModel> commonResponseCustomLiveData;
    private CustomLiveData<NotificationResponseModel> notificationCustomLiveData;

    @Inject
    public NotificationViewModel(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * Get the notification
     */
    public CustomLiveData<NotificationResponseModel> notificationViewModel(String beararToken) {
        notificationCustomLiveData = notificationRepository.notificationAPIRepository(beararToken);
        return notificationCustomLiveData;
    }

    /**
     * Update the notification settings
     */
    public CustomLiveData<CommonResponseModel> updateNotificationViewModel(String beararToken, ParamModel paramModel) {
        commonResponseCustomLiveData = notificationRepository.updatePersonalInfoAPIRepository(beararToken, paramModel);
        return commonResponseCustomLiveData;
    }
}
