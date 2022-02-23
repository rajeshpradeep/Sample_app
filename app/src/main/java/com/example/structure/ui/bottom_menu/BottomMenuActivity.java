package com.example.structure.ui.bottom_menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.structure.R;
import com.example.structure.ui.base.BaseActivity;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class BottomMenuActivity extends BaseActivity implements NavHost, HasSupportFragmentInjector {

    private String TAG = getClass().getSimpleName();
    /*For Fragment */
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @BindView(R.id.bottom_nav_menu)
    BottomNavigationView bottom_nav_menu;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        configureDagger();
        ButterKnife.bind(this);
        initUI();
    }

    /**
     * initialize the dagger
     * */
    public void configureDagger() {
        AndroidInjection.inject(this);
    }

    void initUI() {
        setupNavigationView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
//        return onSupportNavigateUp();
    }

    private void setupNavigationView() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.my_nav_host_fragment);
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        NavigationUI.setupWithNavController(bottom_nav_menu, navHostFragment.getNavController());
        hideMenu();

            // Select first menu item by default and show Fragment accordingly.
//            Menu menu = bottom_nav_menu.getMenu();
//            selectFragment(menu.getItem(0));*/

            // Set action to perform when any menu-item is selected.
            /*bottom_nav_menu.setOnNavigationItemSelectedListener(item -> {
                BottomMenuActivity.this.selectFragment(item);
                return false;
            });*/
            bottom_nav_menu.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                }
            });
    }

    public void setNavigationalVisibility(boolean shown) {
        if (shown) bottom_nav_menu.setVisibility(View.VISIBLE);
        else bottom_nav_menu.setVisibility(View.GONE);
    }

    private void hideMenu() {
        if(sharedPreferencesUtil.getUserType(Constant.USER_TYPE) != null) {
            String userType = sharedPreferencesUtil.getUserType(Constant.USER_TYPE);
            if(userType.equalsIgnoreCase("EVSE"))
                bottom_nav_menu.inflateMenu(R.menu.bottom_nav_evse_menu);
            else
                bottom_nav_menu.inflateMenu(R.menu.bottom_nav_menu);
        }
    }

    /**
     * Perform action when any item is selected.
     * @param item Item that is selected.
     */
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.bottom_menu_dashboard:
                // Action to perform when Dashboard Menu item is selected.
//                setFragment(new DashboardFragment());
                toastMessage("Dashboard");
                break;
            case R.id.bottom_menu_monitor:
                // Action to perform when Monitor Menu item is selected.
//                navController.navigate(R.id.action_navigation_dashboard_to_monitorFragment);
//                setFragment(new MonitorFragment());
                toastMessage("Monitor");
                break;
            case R.id.bottom_menu_control:
                // Action to perform when Control Menu item is selected.
//                navController.navigate(R.id.action_navigation_dashboard_to_controlFragment);
//                setFragment(new ControlFragment());
                toastMessage("Control");
                break;
            case R.id.bottom_menu_session_info:
                // Action to perform when Session Info Menu item is selected.
//                navController.navigate(R.id.action_navigation_dashboard_to_sessionInfoFragment);
//                setFragment(new SessionInfoFragment());
                toastMessage("Session info");
                break;
            case R.id.bottom_menu_leaderboard:
                // Action to perform when LeaderBoard Menu item is selected.
//                navController.navigate(R.id.action_navigation_dashboard_to_leaderboardFragment);
//                setFragment(new LeaderboardFragment());
                toastMessage("Leaderboard");
                break;
        }
    }


    /**
     * setting launching  fragment
     */
    /*public void setFragment(Fragment fragment) {

        if (fragment == null) return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        //For initially add stack for backpress event
        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.qmulus_frame, fragment, Constant.BACK_STACK_ZERO);
        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }*/
    @NonNull
    @Override
    public NavController getNavController() {
        return navController;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
