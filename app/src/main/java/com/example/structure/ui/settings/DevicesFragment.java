package com.example.structure.ui.settings;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevicesFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private CustomProgressDialog customProgressDialog;

    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.device_header_lbl)
    TextView device_header_lbl;
    @BindView(R.id.manage_device_btn)
    TextView manage_device_btn;

    private NavController navController;

    public DevicesFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Dashboard Viewmodel
     */
    private void configureViewModel() {
//        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        configureDagger();
        configureViewModel();
        mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((BottomMenuActivity) mContext).setNavigationalVisibility(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_devices, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    private void initUI() {
//        setTransparentStatusBar();
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();

        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, device_header_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{manage_device_btn});
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.GONE);
        title_lbl.setText(getString(R.string.devices));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.ic_back_arrow, R.id.manage_device_btn})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.ic_back_arrow:
//                setFragment(new SettingsFragment());
                navController.popBackStack();
                break;
            case R.id.manage_device_btn:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.ARGONNE_DEVICES_URL));
                startActivity(browserIntent);
                break;
        }
    }

    //setting launching  fragment
    /*public void setFragment(Fragment fragment) {
        //For initially add stack for backpress event
//        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.qmulus_frame, fragment);
        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
