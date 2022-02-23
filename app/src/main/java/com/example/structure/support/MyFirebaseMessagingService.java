package com.example.structure.support;

import android.util.Log;

import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.android.AndroidInjection;

/**
 * Created by Rajesh Pradeep G on 14-01-2020
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String TAG = getClass().getSimpleName();
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configureDagger();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG, "onMessageReceived: " + remoteMessage.getData().toString());
    }

    @Override
    public void onSendError(@NonNull String s, @NonNull Exception e) {
        super.onSendError(s, e);
        Log.i(TAG, "onSendError: " + s);
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        super.onMessageSent(s);
        Log.i(TAG, "onMessageSent: " + s);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.i(TAG, "onDeletedMessages: ");
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.i(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token) {
        sharedPreferencesUtil.saveFirebaseToken(Constant.FIREBASE_TOKEN, token);
    }
}
