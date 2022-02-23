package com.example.structure.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.example.structure.R;
import com.example.structure.ui.base.BaseActivity;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.login.fragment.ForgotPasswordFragment;
import com.example.structure.ui.login.fragment.LoginFragment;
import com.example.structure.ui.registration.fragment.AddElectricVehicleFragment;
import com.example.structure.ui.registration.fragment.CreateAccountFragment;
import com.example.structure.ui.registration.fragment.NotificaionsCreateAccountFragment;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/*
 * we use our AppComponent (now prefixed with Dagger)
 * to inject our Application class.
 * This way a DispatchingAndroidInjector is injected which is
 * then returned when an injector for an Fragment is requested.
 * */
public class LoginActivity extends BaseActivity implements HasSupportFragmentInjector {

    private String TAG = getClass().getSimpleName();
    /*For Fragment */
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    FragmentManager fragmentManager;

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
        if(sharedPreferencesUtil.getUserID(Constant.USER_ID ) > 0) {
            Intent intent = new Intent(this, BottomMenuActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_login);
            fragmentManager = getSupportFragmentManager();
            initUI();
        }
    }

    /**
     * initialize the UI
     * */
    private void initUI() {
//        removeStatusBar();
        setFragment(new LoginFragment());
    }

    /**
     * Landing fragment
     **/
    public void setFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
        //For initially add stack for backpress event
        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager
                .beginTransaction()
                .add(R.id.login_frame, fragment).addToBackStack(Constant.BACK_STACK_ROOT_TAG)
                .commit();
    }

    /**
     * load the Create account fragment
     **/
    public void redirectToCreateAccFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
        //For initially add stack for backpress event
//        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager
                .beginTransaction()
                .add(R.id.login_frame, fragment).addToBackStack(Constant.BACK_STACK_ROOT_TAG)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    /**
     * doing sepcific action when clicking the back button
     * */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.login_frame);
        if(currentFragment instanceof LoginFragment)
            Utils.showCustomAlert(this, null, "Are you sure to exit?", "app_close");
        else if(currentFragment instanceof ForgotPasswordFragment)
            setFragment(new LoginFragment());
        else if(currentFragment instanceof CreateAccountFragment)
            setFragment(new LoginFragment());
        else if (currentFragment instanceof AddElectricVehicleFragment)
            redirectToCreateAccFragment(new CreateAccountFragment());
        else if (currentFragment instanceof NotificaionsCreateAccountFragment)
            redirectToCreateAccFragment(new AddElectricVehicleFragment());
    }
}
