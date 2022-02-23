package com.example.structure.ui.settings;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.CommonResponseModel;
import com.example.structure.data.models.ParamModel;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.login.LoginActivity;
import com.example.structure.ui.login.asyntask.LoginTask;
import com.example.structure.ui.settings.adapter.SettingsGridAdapter;
import com.example.structure.ui.settings.listener.SettingsRedirectionListener;
import com.example.structure.utils.Constant;
import com.example.structure.utils.ResponseInterface;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment implements SettingsRedirectionListener, ResponseInterface {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    LoginTask loginTask;

    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.settings_rview)
    RecyclerView settings_rview;
    @BindView(R.id.change_password_btn)
    Button change_password_btn;
    @BindView(R.id.logout_btn)
    Button logout_btn;

    private NavController navController;
    private CustomProgressDialog customProgressDialog;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        configureDagger();
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((BottomMenuActivity) mContext).setNavigationalVisibility(false);
    }

    private void initUI() {
//        setTransparentStatusBar();
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();

        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);

        ArrayList<String> settingsMenuList = new ArrayList<>();
//        settingsMenuList.add(getString(R.string.adapter_management));
//        settingsMenuList.add(getString(R.string.add_device));
        settingsMenuList.add(getString(R.string.vehicles));
        settingsMenuList.add(getString(R.string.notifications));
        settingsMenuList.add(getString(R.string.personal_info));
        settingsMenuList.add(getString(R.string.help));
        settingsMenuList.add(getString(R.string.devices));

        ArrayList<Integer> settingsMenuListImage = new ArrayList<>();
        settingsMenuListImage.add(R.drawable.ic_vehicles);
        settingsMenuListImage.add(R.drawable.ic_notifications);
        settingsMenuListImage.add(R.drawable.ic_user_green);
        settingsMenuListImage.add(R.drawable.ic_help);
        settingsMenuListImage.add(R.drawable.ic_adapter_management);

        setGridRecyclerViewAdapter(settingsMenuList, settingsMenuListImage);
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{change_password_btn, logout_btn});
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.GONE);
        title_lbl.setText(getString(R.string.settings));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.ic_back_arrow, R.id.logout_btn, R.id.change_password_btn})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.ic_back_arrow:
                navController.popBackStack();
                break;
            case R.id.logout_btn:
                if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
                    String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
                    ParamModel paramModel = new ParamModel();
//                    paramModel.setUser_id(sharedPreferencesUtil.getUserID(Constant.USER_ID));
                    paramModel.setType("android");
                    customProgressDialog = CustomProgressDialog.show(mContext, true, false);
                    loginTask.logoutAPI(token, paramModel, this);
                }
                break;
            case R.id.change_password_btn:
//                NavController navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_navigation_settings_to_changePasswordFragment);
                break;
        }
    }

    private void setGridRecyclerViewAdapter(ArrayList<String> settingsMenuList, ArrayList<Integer> settingsMenuListImage) {

        SettingsGridAdapter settingsGridAdapter = new SettingsGridAdapter(mContext, settingsMenuList, settingsMenuListImage, this);
        settings_rview.setLayoutManager(new GridLayoutManager(mContext, 2));
        settings_rview.setHasFixedSize(true);
        settings_rview.setNestedScrollingEnabled(false);
        settings_rview.setItemAnimator(new DefaultItemAnimator());
        settings_rview.setAdapter(settingsGridAdapter);
    }

    //setting launching  fragment
    /*private void setFragment(Fragment fragment) {
        //FOr initially add stack for backpress event
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

    @Override
    public void settingsItem(String settingMenu) {
        switch (settingMenu) {
            case "Vehicles":
//                setFragment(new VehicleListFragment());
                navController.navigate(R.id.action_navigation_settings_to_vehicleListFragment);
                break;
            case "Help":
//                setFragment(new HelpFragment());
                navController.navigate(R.id.action_navigation_settings_to_navigation_help);
                break;
            case "Notifications":
//                setFragment(new NotificationsFragment());
                navController.navigate(R.id.action_navigation_settings_to_navigation_notifications);
                break;
            case "Personal Info":
//                setFragment(new PersonalInfoFragment());
                navController.navigate(R.id.action_navigation_settings_to_personalInfoFragment);
                break;
            case "Devices":
//                setFragment(new DevicesFragment());
                navController.navigate(R.id.action_navigation_settings_to_devicesFragment);
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        dismissDialog();
        CommonResponseModel commonResponseModel = (CommonResponseModel) data;
        if (commonResponseModel.getStatus().equals("1")) {
            sharedPreferencesUtil.deleteAllSession();
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        } else
            Utils.showOKCustomAlert(mContext, null, commonResponseModel.getMessage(), "logout_failed");
    }

    @Override
    public void onSuccess(List data) {
        dismissDialog();
    }

    @Override
    public void onError(Throwable throwable) {
        dismissDialog();
        Utils.handleErrorResponse(throwable, mContext);
    }

    @Override
    public void onFailure(Response response) {
        dismissDialog();
        Utils.showOKCustomAlert(mContext, getString(R.string.alert), Utils.getConvertedErrorBody(response), "login_failed");
    }

    private void dismissDialog() {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
    }
}
