package com.example.structure.di.module;

import com.example.structure.ui.control.ControlFragment;
import com.example.structure.ui.control_evse.ControlEVSEFragment;
import com.example.structure.ui.dashboard.DashboardFragment;
import com.example.structure.ui.leaderboard.LeaderboardFragment;
import com.example.structure.ui.login.fragment.ForgotPasswordFragment;
import com.example.structure.ui.login.fragment.LoginFragment;
import com.example.structure.ui.monitor.MonitorFragment;
import com.example.structure.ui.registration.fragment.AddElectricVehicleFragment;
import com.example.structure.ui.registration.fragment.CreateAccountFragment;
import com.example.structure.ui.registration.fragment.NotificaionsCreateAccountFragment;
import com.example.structure.ui.session_info.SessionInfoFragment;
import com.example.structure.ui.settings.ChangePasswordFragment;
import com.example.structure.ui.settings.DevicesFragment;
import com.example.structure.ui.settings.NotificationsFragment;
import com.example.structure.ui.settings.PersonalInfoFragment;
import com.example.structure.ui.settings.SettingsFragment;
import com.example.structure.ui.settings.VehicleListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector
    abstract CreateAccountFragment contributeCreateAccountFragment();

    @ContributesAndroidInjector
    abstract ForgotPasswordFragment contributeForgotPasswordFragment();

    @ContributesAndroidInjector
    abstract DashboardFragment contributeDashboardFragment();

    @ContributesAndroidInjector
    abstract SettingsFragment contributeSettingsFragment();

    @ContributesAndroidInjector
    abstract ControlFragment contributeControlFragment();

    @ContributesAndroidInjector
    abstract ChangePasswordFragment contributeChangePasswordFragment();

    @ContributesAndroidInjector
    abstract LeaderboardFragment contributeLeaderboardFragment();

    @ContributesAndroidInjector
    abstract VehicleListFragment contributeVehicleListFragment();

    @ContributesAndroidInjector
    abstract AddElectricVehicleFragment contributeAddElectricVehicleFragment();

    @ContributesAndroidInjector
    abstract NotificaionsCreateAccountFragment contributeNotificaionsCreateAccountFragment();

    @ContributesAndroidInjector
    abstract PersonalInfoFragment contributePersonalInfoFragment();

    @ContributesAndroidInjector
    abstract MonitorFragment contributeMonitorFragment();

    @ContributesAndroidInjector
    abstract NotificationsFragment contributeNotificationsFragment();

    @ContributesAndroidInjector
    abstract SessionInfoFragment contributeSessionInfoFragment();

    @ContributesAndroidInjector
    abstract DevicesFragment contributeDevicesFragment();

    @ContributesAndroidInjector
    abstract ControlEVSEFragment contributeControlEVSEFragment();
}
