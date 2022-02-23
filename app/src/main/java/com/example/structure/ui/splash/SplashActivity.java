package com.example.structure.ui.splash;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import retrofit2.Response;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.ui.base.BaseActivity;
import com.example.structure.ui.login.LoginActivity;
import com.example.structure.ui.login.asyntask.LoginTask;
import com.example.structure.utils.Constant;
import com.example.structure.utils.ResponseInterface;
import com.example.structure.utils.SharedPreferencesUtil;

import java.util.List;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements ResponseInterface, HasActivityInjector {

    private String TAG = getClass().getSimpleName();
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    LoginTask loginTask;

    /**
     * initialize the dagger
     * */
    public void configureDagger() {
        AndroidInjection.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureDagger();
        removeStatusBar();
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
//            if (getIntent().getData() != null)
//                handleIntent(getIntent());
//            else {
            /*FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            Log.i(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.i(TAG, "onComplete:FCM Token " + token);
//                        sharedPreferencesUtil.saveFirebaseToken(Constant.FIREBASE_TOKEN, token);
                        // Log and toast
//                            String msg = getString(R.string.fcm_msg_token, token);
//                            Log.d(TAG, msg);
//                            Toast.makeText(SplashActivity.this, msg, Toast.LENGTH_SHORT).show();
                    });*/
            if(sharedPreferencesUtil.getUserID(Constant.USER_ID) > 0) {
                String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                ParamModel paramModel = new ParamModel();
//                paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                loginTask.getUserTypeAPI(token, this);
            }
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
//            }
        }, Constant.SPLASH_TIME_OUT); // SPLASH_TIME_OUT 2 seconds
    }

    @Override
    public void onSuccess(Object data) {
        CommonResponseModel commonResponseModel = (CommonResponseModel) data;
        if (commonResponseModel.getStatus().equals("1")) {
            sharedPreferencesUtil.saveUserType(Constant.USER_TYPE, commonResponseModel.getUserType());
            Log.i(TAG, "onSuccess:USER_TYPE " + sharedPreferencesUtil.getUserType(Constant.USER_TYPE));
        }
    }

    @Override
    public void onSuccess(List data) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onFailure(Response response) {

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
