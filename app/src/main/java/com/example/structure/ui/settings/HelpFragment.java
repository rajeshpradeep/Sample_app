package com.example.structure.ui.settings;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.ui.settings.adapter.HelpQuestionListAdapter;
import com.example.structure.utils.Utils;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {

    private String TAG = getClass().getSimpleName();
    Context mContext;
    private Unbinder unbinder;
    FragmentManager fragmentManager;
    HelpQuestionListAdapter helpQuestionListAdapter;

    @BindView(R.id.title_lbl)
    TextView title_lbl;
    @BindView(R.id.ic_back_arrow)
    ImageView ic_back_arrow;
    @BindView(R.id.settings_img)
    ImageView settings_img;
    @BindView(R.id.help_rview)
    RecyclerView help_rview;

    private NavController navController;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        fragmentManager = getFragmentManager();
        initUI();
        return view;
    }

    private void initUI() {
        setHeader();
        setFont();
        setHelpQuestionRecyclerViewAdapter();

        navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[] {title_lbl});
    }

    private void setHeader() {
        ic_back_arrow.setVisibility(View.VISIBLE);
        settings_img.setVisibility(View.GONE);
        title_lbl.setText(getString(R.string.help));
    }

    private void setHelpQuestionRecyclerViewAdapter() {

        helpQuestionListAdapter = new HelpQuestionListAdapter(mContext);
        help_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        help_rview.setHasFixedSize(true);
        help_rview.setNestedScrollingEnabled(false);
        help_rview.setItemAnimator(new DefaultItemAnimator());
        help_rview.setAdapter(helpQuestionListAdapter);
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.ic_back_arrow})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.ic_back_arrow:
//                setFragment(new SettingsFragment());
                navController.popBackStack();
                break;
        }
    }

    //setting launching  fragment
    /*public void setFragment(Fragment fragment) {
        //FOr initially add stack for backpress event
        fragmentManager.popBackStack(Constant.BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        fragmentTransaction.replace(R.id.qmulus_frame, fragment, Constant.BACK_STACK_ZERO);
        fragmentTransaction.addToBackStack(Constant.BACK_STACK_ROOT_TAG);
        fragmentTransaction.commit();
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
