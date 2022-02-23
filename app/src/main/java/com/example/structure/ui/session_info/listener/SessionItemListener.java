package com.example.structure.ui.session_info.listener;

import android.view.View;

import com.example.structure.data.models.SessionInfoResponseModel;

/**
 * Created by Rajesh Pradeep G on 16-03-2020
 */
public interface SessionItemListener {

    void SessionGraphItem(SessionInfoResponseModel.SessionInfoBean sessionInfoBean, View  view,View highLightView,int position,boolean value);
}
