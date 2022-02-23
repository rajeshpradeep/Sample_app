package com.example.structure.di.module;

import com.example.structure.viewmodel.AddElectricVehicleViewModel;
import com.example.structure.viewmodel.ChangePasswordViewmodel;
import com.example.structure.viewmodel.ControlEVSEViewModel;
import com.example.structure.viewmodel.ControlViewModel;
import com.example.structure.viewmodel.CreateAccountViewModel;
import com.example.structure.viewmodel.DashboardViewModel;
import com.example.structure.viewmodel.LeaderboardViewModel;
import com.example.structure.viewmodel.MonitorViewModel;
import com.example.structure.viewmodel.NotificationViewModel;
import com.example.structure.viewmodel.PersonalInfoViewModel;
import com.example.structure.viewmodel.SessionInfoViewModel;
import com.example.structure.viewmodel.VehicleViewModel;
import com.example.structure.viewmodel.factory.AllViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AllViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel.class)
    abstract ViewModel provideDashboardViewModel(DashboardViewModel dashboardViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LeaderboardViewModel.class)
    abstract ViewModel provideLeaderboardViewModel(LeaderboardViewModel leaderboardViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CreateAccountViewModel.class)
    abstract ViewModel provideCreateAccountViewModel(CreateAccountViewModel createAccountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddElectricVehicleViewModel.class)
    abstract ViewModel provideAddElectricVehicleViewModel(AddElectricVehicleViewModel addElectricVehicleViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ControlViewModel.class)
    abstract ViewModel provideControlViewModel(ControlViewModel controlViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(VehicleViewModel.class)
    abstract ViewModel provideVehicleViewModel(VehicleViewModel vehicleViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PersonalInfoViewModel.class)
    abstract ViewModel providePersonalInfoViewModel(PersonalInfoViewModel personalInfoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    abstract ViewModel provideNotificationViewModel(NotificationViewModel notificationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MonitorViewModel.class)
    abstract ViewModel provideMonitorViewModel(MonitorViewModel monitorViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewmodel.class)
    abstract ViewModel provideChangePasswordViewmodel(ChangePasswordViewmodel changePasswordViewmodel);

    @Binds
    @IntoMap
    @ViewModelKey(SessionInfoViewModel.class)
    abstract ViewModel provideSessionInfoViewModel(SessionInfoViewModel sessionInfoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ControlEVSEViewModel.class)
    abstract ViewModel provideControlEVSEViewModel(ControlEVSEViewModel controlEVSEViewModel);
}
