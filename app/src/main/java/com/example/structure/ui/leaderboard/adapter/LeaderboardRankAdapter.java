package com.example.structure.ui.leaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.LeaderboardResponseModel;
import com.example.structure.utils.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 22-11-2019
 */
public class LeaderboardRankAdapter extends RecyclerView.Adapter<LeadboardBaseViewHolder> {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private List<LeaderboardResponseModel.LeaderboardListBean> leaderboardListBeans;

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private boolean isLoaderVisible = false;
    private int userPosition;

    public LeaderboardRankAdapter(Context mContext, List<LeaderboardResponseModel.LeaderboardListBean> leaderboardListBeans) {
        this.mContext = mContext;
        this.leaderboardListBeans = leaderboardListBeans;
    }

    @NonNull
    @Override
    public LeadboardBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return new LeaderboardRankViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_rank_list_item, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull LeadboardBaseViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return leaderboardListBeans == null ? 0 : leaderboardListBeans.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == leaderboardListBeans.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    public void addItems(List<LeaderboardResponseModel.LeaderboardListBean> leaderboardListBeans, int userPos) {
        this.leaderboardListBeans.addAll(leaderboardListBeans);
        userPosition = (userPos - 1);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        leaderboardListBeans.add(new LeaderboardResponseModel.LeaderboardListBean());
        notifyItemInserted(leaderboardListBeans.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
//        if(leaderboardListBeans.size() > 0) {
        int position = leaderboardListBeans.size() - 1;
        LeaderboardResponseModel.LeaderboardListBean item = getItem(position);
        if (item != null) {
            leaderboardListBeans.remove(position);
            notifyItemRemoved(position);
        }
//        }
    }

    public void clear() {
        isLoaderVisible = false;
    }

    private LeaderboardResponseModel.LeaderboardListBean getItem(int position) {
        return leaderboardListBeans.get(position);
    }


    class LeaderboardRankViewHolder extends LeadboardBaseViewHolder {

        @BindView(R.id.leaderboard_item_rlay)
        RelativeLayout leaderboard_item_rlay;
        @BindView(R.id.rank_tview)
        TextView rank_tview;
        @BindView(R.id.name_tview)
        TextView name_tview;
        @BindView(R.id.points_value_tview)
        TextView points_value_tview;
        @BindView(R.id.rank_badge_rlay)
        RelativeLayout rank_badge_rlay;
        @BindView(R.id.ic_medal_badge)
        ImageView ic_medal_badge;
        @BindView(R.id.rank_value_tview)
        TextView rank_value_tview;
        @BindView(R.id.leaderboard_rank_dview)
        View leaderboard_rank_dview;

        LeaderboardRankViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Utils.setRegularFonts(mContext, new TextView[]{name_tview, rank_tview});
            Utils.setBoldFonts(mContext, new TextView[]{points_value_tview});
            Utils.setBlackFonts(mContext, new TextView[]{rank_value_tview});
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            LeaderboardResponseModel.LeaderboardListBean leaderboardListBean = leaderboardListBeans.get(position);
            name_tview.setText(leaderboardListBean.getAlias());
            points_value_tview.setText(String.valueOf(leaderboardListBean.getPoints()));
            rank_tview.setText(leaderboardListBean.getLb_rank() + ". ");
            rank_value_tview.setText(String.valueOf(leaderboardListBean.getLb_rank()));

            int points = leaderboardListBean.getPoints();
            if (userPosition > -1 && userPosition == position) {
//                if(userPosition == position) {
                leaderboard_item_rlay.setBackgroundColor(mContext.getResources().getColor(R.color.lite_yellow, mContext.getTheme()));
//                }
            }
            if (position < 3)
                rank_badge_rlay.setVisibility(View.VISIBLE);
            else rank_badge_rlay.setVisibility(View.INVISIBLE);

            if (position == leaderboardListBeans.size() - 1)
                leaderboard_rank_dview.setVisibility(View.GONE);
        }
    }

    private class ProgressHolder extends LeadboardBaseViewHolder {
        public ProgressHolder(View view) {
            super(view);
//            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void clear() {

        }
    }
}
