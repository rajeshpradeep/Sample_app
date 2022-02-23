package com.example.structure.ui.leaderboard;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.LeaderboardResponseModel;
import com.example.structure.support.CustomProgressDialog;
import com.example.structure.ui.base.BaseFragment;
import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.leaderboard.adapter.LeaderboardRankAdapter;
import com.example.structure.ui.leaderboard.listener.PaginationListener;
import com.example.structure.utils.ApiResponse;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.example.structure.utils.Utils;
import com.example.structure.viewmodel.LeaderboardViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;
import static com.example.structure.ui.leaderboard.listener.PaginationListener.PAGE_START;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends BaseFragment {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    private LeaderboardViewModel leaderboardViewModel;
    private LeaderboardRankAdapter leaderboardRankAdapter;

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
    //    @BindView(R.id.leaderboard_swiperefresh)
//    SwipeRefreshLayout leaderboard_swiperefresh;
    @BindView(R.id.leaderboard_rview)
    RecyclerView leaderboard_rview;
    @BindView(R.id.ic_medal_badge)
    ImageView ic_medal_badge;
    @BindView(R.id.your_pos_lbl)
    TextView your_pos_lbl;
    @BindView(R.id.rank_value_tview)
    TextView rank_value_tview;
    @BindView(R.id.points_value_tview)
    TextView points_value_tview;
    @BindView(R.id.no_rec_found_lbl)
    TextView no_rec_found_lbl;

    //    ArrayList<LeaderboardResponseModel.LeaderboardListBean> leaderboardRankList;
    private CustomProgressDialog customProgressDialog;

    private int currentPage;
    private boolean isLastPage, isLoading;
    private int totalPage, limit, totalRecCount, userPosition;
    private ArrayList<LeaderboardResponseModel.LeaderboardListBean> leaderboardList;

    public LeaderboardFragment() {
        // Required empty public constructor
    }

    /**
     * implement the dagger
     */
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    /**
     * Initialize the Leaderboard Viewmodel
     */
    private void configureViewModel() {
        leaderboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(LeaderboardViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
        configureDagger();
        configureViewModel();
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        Log.i(TAG, "onCreateView: ");
        unbinder = ButterKnife.bind(this, view);
        fragmentManager = getFragmentManager();
        leaderboardList = new ArrayList<>();
        currentPage = PAGE_START;
        totalPage = 0;
        limit = 0;
        totalRecCount = 0;
        userPosition = -1;
        isLoading = false;
        isLastPage = false;
        leaderboardAPICall();
        initUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
        ((BottomMenuActivity) mContext).setNavigationalVisibility(true);
    }

    private void initUI() {
//        setTransparentStatusBar();
        setStatusBarGradiant(getActivity());
        setHeader();
        setFont();
        ic_back_arrow.setImageResource(R.drawable.ic_filter);
//        leaderboard_swiperefresh.setOnRefreshListener(this);
        setLeaderboardRankAdapter(new ArrayList<>());

        /**
         * add scroll listener while user reach in bottom load more will call
         */
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, VERTICAL, false);
        LinearLayoutManager layoutManager = (LinearLayoutManager) leaderboard_rview.getLayoutManager();
        leaderboard_rview.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                if (totalPage >= currentPage) {
                    isLoading = true;
                    limit = limit + 15;
                    currentPage++;
                    Log.i(TAG, "loadMoreItems: " + currentPage);
                    leaderboardAPICall();
                } else toastMessage("Pagination end");
            }

            @Override
            public int getTotalPageCount() {
                return totalPage;
            }

            @Override
            public boolean isLastPage() {
                Log.i(TAG, "isLastPage: " + isLastPage);
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                Log.i(TAG, "isLoading: " + isLoading);
                return isLoading;
            }
        });
    }

    private void setFont() {
        Utils.setRegularFonts(mContext, new TextView[]{title_lbl, your_pos_lbl, rank_value_tview, no_rec_found_lbl});
        Utils.setBoldFonts(mContext, new TextView[]{points_value_tview});
    }

    private void setHeader() {
        ic_back_arrow.setImageResource(R.drawable.ic_filter);
        ic_back_arrow.setVisibility(View.GONE);
        settings_img.setVisibility(View.VISIBLE);
        title_lbl.setText(getString(R.string.leaderboard));
    }

    /**
     * implementing the click action
     */
    @OnClick({R.id.settings_img, R.id.ic_back_arrow})
    void buttonAction(View view) {
        switch (view.getId()) {
            case R.id.ic_back_arrow:
                toastMessage("Will Filter");
                break;
            case R.id.settings_img:
                NavController navController = Navigation.findNavController((Activity) mContext, R.id.my_nav_host_fragment);
                navController.navigate(R.id.action_leaderboardFragment_to_navigation_settings);
                break;
        }
    }

    private void updateUI(LeaderboardResponseModel leaderboardResponseModel) {
        leaderboardList = new ArrayList<>();
        if (totalRecCount > 0) {
            if (leaderboardResponseModel.getUserRank() != null)
                userPosition = leaderboardResponseModel.getUserRank().getPosition();
            no_rec_found_lbl.setVisibility(View.GONE);
            new Handler().postDelayed(() -> {
                for (int i = 0; i < leaderboardResponseModel.getLeaderboard_list().size(); i++) {
                    LeaderboardResponseModel.LeaderboardListBean leaderboardListBean = new LeaderboardResponseModel.LeaderboardListBean();
                    leaderboardListBean = leaderboardResponseModel.getLeaderboard_list().get(i);
                    leaderboardList.add(leaderboardListBean);
                }
                leaderboardRankAdapter.clear();
                /**
                 * manage progress view
                 */
                Log.i(TAG, "updateUI:leaderboardList.size() " + leaderboardList.size() +
                        " leaderboardRankAdapter count:" + leaderboardRankAdapter.getItemCount());
                Log.i(TAG, "updateUI:currentPage " + currentPage);
                if (currentPage != PAGE_START)
                    leaderboardRankAdapter.removeLoading();
                leaderboardRankAdapter.addItems(leaderboardList, userPosition);
//                leaderboard_swiperefresh.setRefreshing(false);

                // check weather is last page or not
                if (currentPage < totalPage) {
                    leaderboardRankAdapter.addLoading();
                } else {
                    isLastPage = true;
                }
                isLoading = false;
            }, 1000);
        } else no_rec_found_lbl.setVisibility(View.VISIBLE);
    }

    /**
     * call the leaderboard API
     */
    private void leaderboardAPICall() {

        Log.i(TAG, "leaderboardAPICall:Token: " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN));
        if (!TextUtils.isEmpty(sharedPreferencesUtil.getToken(Constant.USER_TOKEN))) {
            String token = "Bearer " + sharedPreferencesUtil.getToken(Constant.USER_TOKEN);
//            ParamModel paramModel = new ParamModel();
//            paramModel.setOffset(String.valueOf(limit));
//            paramModel.setLimit("15");
            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    leaderboardViewModel.getLeaderboardResponse(token, String.valueOf(limit), "15").observe((LifecycleOwner) mContext, new Observer<ApiResponse<LeaderboardResponseModel>>() {
                        @Override
                        public void onChanged(ApiResponse<LeaderboardResponseModel> leaderboardResponseModelApiResponse) {
                            leaderboardResponse(leaderboardResponseModelApiResponse);
                        }
                    });
                }
            });
        }
    }

    private void setLeaderboardRankAdapter(ArrayList<LeaderboardResponseModel.LeaderboardListBean> leaderboardRankList) {

        leaderboardRankAdapter = new LeaderboardRankAdapter(mContext, leaderboardRankList);
        leaderboard_rview.setLayoutManager(new LinearLayoutManager(mContext, VERTICAL, false));
        leaderboard_rview.setHasFixedSize(true);
//        leaderboard_rview.setNestedScrollingEnabled(false);
        leaderboard_rview.setItemAnimator(new DefaultItemAnimator());
        leaderboard_rview.setAdapter(leaderboardRankAdapter);
    }

    private void leaderboardResponse(ApiResponse<LeaderboardResponseModel> leaderboardResponseModelApiResponse) {
        if (customProgressDialog != null && customProgressDialog.isShowing())
            customProgressDialog.dismiss();
        LeaderboardResponseModel leaderboardResponseModel = leaderboardResponseModelApiResponse.getData();
        switch (leaderboardResponseModelApiResponse.getStatus()) {
            case LOADING:
                break;
            case SUCCESS:
                if (leaderboardResponseModel.getStatus().equals("1")) {
                    if (leaderboardResponseModel.getUserRank() != null) {
                        if (leaderboardResponseModel.getUserRank().getPoints() == 0) {
                            rank_value_tview.setText("-");
                            points_value_tview.setText("-");
                        } else {
                            rank_value_tview.setText(String.valueOf(leaderboardResponseModel.getUserRank().getPosition()));
                            points_value_tview.setText(leaderboardResponseModel.getUserRank().getPoints() + " Points");
                        }
                    }
                    totalRecCount = leaderboardResponseModel.getTotalRecordsCount();
                    if (totalRecCount > 0) {
                        int div = totalRecCount / 15;
                        int mod = totalRecCount % 15;
                        totalPage = mod > 0 ? div + 1 : div;
                    }
                    Log.i(TAG, "leaderboardResponse:totalPage " + totalPage);
                    updateUI(leaderboardResponseModel);
                    toastMessage(leaderboardResponseModel.getSuccess() + " API Success");
                } else if (leaderboardResponseModel.getError().equals("TOKEN_INVALID")) {
                    Utils.showOKCustomAlert(mContext, null, "Session expired!",
                            "session_expired");
                } else if (leaderboardResponseModel.getError().equalsIgnoreCase("No Adapter Found"))
                    Utils.showOKCustomAlert(mContext, null, "Please add a device to your account to access this page.", "leaderboard_no_adapter");
                else
                    Utils.showCustomAlert(mContext, getString(R.string.alert), leaderboardResponseModel.getError(),
                            "leaderboard_failed");
                break;
            case FAILURE:
                Utils.showCustomAlert(mContext, getString(R.string.alert),
                        Utils.getConvertedErrorBody(leaderboardResponseModelApiResponse.getFailureResponse()), "leaderboard_failed");
                break;
            case ERROR:
                Utils.handleErrorResponse(leaderboardResponseModelApiResponse.getError(), mContext);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /*@Override
    public void onRefresh() {
        if (totalRecCount >= limit) {
            limit = 0;
            currentPage = PAGE_START;
            isLastPage = false;
            leaderboardRankAdapter.clear();
            leaderboardAPICall();
        } else {
            leaderboard_swiperefresh.setEnabled(false);
            leaderboard_swiperefresh.setRefreshing(false);
        }
    }*/
}
