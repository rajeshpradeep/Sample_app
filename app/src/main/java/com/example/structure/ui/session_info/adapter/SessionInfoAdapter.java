package com.example.structure.ui.session_info.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.structure.R;
import com.example.structure.data.models.SessionInfoResponseModel;
import com.example.structure.ui.session_info.listener.SessionItemListener;
import com.example.structure.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 06-02-2020
 */
public
class SessionInfoAdapter extends RecyclerView.Adapter<SessionInfoBaseViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private String TAG = getClass().getSimpleName();
    private Context mContext;

    private List<SessionInfoResponseModel.SessionInfoBean> sessionInfoBeans;
    private SessionItemListener sessionItemListener;

    private boolean isLoaderVisible = false;
    private int selectedPosition = -1;

    public SessionInfoAdapter(Context mContext, List<SessionInfoResponseModel.SessionInfoBean> sessionInfoBeans, SessionItemListener sessionItemListener) {
        this.mContext = mContext;
        this.sessionInfoBeans = sessionInfoBeans;
        this.sessionItemListener = sessionItemListener;
    }

    @NonNull
    @Override
    public SessionInfoBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return new SessionInfoViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.session_info_list_item, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SessionInfoBaseViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.onBind(position);


    }

    @Override
    public int getItemCount() {
        return sessionInfoBeans == null ? 0 : sessionInfoBeans.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == sessionInfoBeans.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    public void addItems(List<SessionInfoResponseModel.SessionInfoBean> sessionInfoBeans) {
        this.sessionInfoBeans.addAll(sessionInfoBeans);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        sessionInfoBeans.add(new SessionInfoResponseModel.SessionInfoBean());
        notifyItemInserted(sessionInfoBeans.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
//        if(leaderboardListBeans.size() > 0) {
        int position = sessionInfoBeans.size() - 1;
        SessionInfoResponseModel.SessionInfoBean item = getItem(position);
        if (item != null) {
            sessionInfoBeans.remove(position);
            notifyItemRemoved(position);
        }
//        }
    }

    public void clear() {
//        sessionInfoBeans.clear();
//        notifyDataSetChanged();
        isLoaderVisible = false;
//        while (getItemCount() > 0) {
//            removeLoading();
//        }
    }

    public void filterClear() {
        sessionInfoBeans.clear();
        notifyDataSetChanged();
    }

    private SessionInfoResponseModel.SessionInfoBean getItem(int position) {
        return sessionInfoBeans.get(position);
    }

    class SessionInfoViewHolder extends SessionInfoBaseViewHolder {

        @BindView(R.id.session_info_title_lay)
        LinearLayout session_info_title_lay;
        @BindView(R.id.plugin_date_val)
        TextView plugin_date_val;
        @BindView(R.id.plugin_time_val)
        TextView plugin_time_val;
        @BindView(R.id.session_duration_lbl)
        TextView session_duration_val;
        @BindView(R.id.charge_duration_lbl)
        TextView charge_duration_val;
        @BindView(R.id.energy_kwh_lbl)
        TextView energy_kwh_val;
        @BindView(R.id.session_info_dview)
        View session_info_dview;
        @BindView(R.id.highlight_view)
        View highlight_view;

        /*@BindView(R.id.transaction_id_lbl)
        TextView transaction_id_lbl;
        @BindView(R.id.device_name_lbl)
        TextView device_name_lbl;
        @BindView(R.id.port_name_lbl)
        TextView port_name_lbl;
        @BindView(R.id.group_name_lbl)
        TextView group_name_lbl;
        @BindView(R.id.station_type_lbl)
        TextView station_type_lbl;
        @BindView(R.id.peak_power_lbl)
        TextView peak_power_lbl;
        @BindView(R.id.avg_power_lbl)
        TextView avg_power_lbl;
        @BindView(R.id.id_tag_lbl)
        TextView id_tag_lbl;
        @BindView(R.id.session_start_lbl)
        TextView session_start_lbl;
        @BindView(R.id.session_end_lbl)
        TextView session_end_lbl;
        @BindView(R.id.charge_start_lbl)
        TextView charge_start_lbl;
        @BindView(R.id.charge_end_lbl)
        TextView charge_end_lbl;
        @BindView(R.id.end_reason_lbl)
        TextView end_reason_lbl;
        @BindView(R.id.ghg_savings_lbl)
        TextView ghg_savings_lbl;
        @BindView(R.id.ghg_saving_gals_lbl)
        TextView ghg_saving_gals_lbl;
        @BindView(R.id.start_soc_lbl)
        TextView start_soc_lbl;
        @BindView(R.id.end_soc_lbl)
        TextView end_soc_lbl;*/

        SessionInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            Utils.setRegularFonts(mContext, new TextView[]{plugin_time_val, session_duration_val, charge_duration_val, energy_kwh_val});
            /*Utils.setRegularFonts(mContext, new TextView[]{plugin_time_val, session_duration_val, charge_duration_val, energy_kwh_val,
                    transaction_id_lbl, device_name_lbl, port_name_lbl, group_name_lbl, station_type_lbl, peak_power_lbl, avg_power_lbl,
                    id_tag_lbl, session_start_lbl, session_end_lbl, charge_start_lbl, charge_end_lbl, end_reason_lbl, ghg_savings_lbl,
                    ghg_saving_gals_lbl, start_soc_lbl, end_soc_lbl});*/
            Utils.setBoldFonts(mContext, new TextView[]{plugin_date_val});
            session_info_title_lay.setSelected(false);
            session_info_title_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
/*
                    Toast.makeText(mContext, getAdapterPosition() + 1 + " clicked", Toast.LENGTH_SHORT).show();
*/

//                    if (selectedPosition != getAdapterPosition() || selectedPosition == -1) {
//                        session_info_title_lay.setSelected(true);
                    sessionItemListener.SessionGraphItem(sessionInfoBeans.get(getAdapterPosition()), itemView, highlight_view, getAdapterPosition(), true);
                    selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
//                    } else {
//                        sessionItemListener.SessionGraphItem(sessionInfoBeans.get(getAdapterPosition()), itemView, highlight_view, getAdapterPosition(), false);
////                     if (session_info_title_lay.isSelected()) {
//                            session_info_title_lay.setSelected(false);
//                            highlight_view.setVisibility(View.GONE);
//                            itemView.setBackgroundColor(Color.parseColor("#ffffff"));
//                            notifyItemChanged(getAdapterPosition());
////                        notifyDataSetChanged();
////                        }
//
//                    }
                }
            });
        }

        public void onBind(int position) {
            SessionInfoResponseModel.SessionInfoBean sessionInfoBean = sessionInfoBeans.get(position);
            String formatConversion = "MM-dd-yyyy HH:mm:ss Z";
            String sessionDate = Utils.getLocalTimeFromUTCByFormat(sessionInfoBean.getSessionDate(),
                    formatConversion, "MM/dd/yyyy");
            String sessionTime = Utils.getLocalTimeFromUTCByFormat(sessionInfoBean.getSessionDate(),
                    formatConversion, "hh:mm a");
//            String[] dateTime = sessionDate.split(" ");
//            dateTime[1] = dateTime[1].substring(0, dateTime[1].indexOf("+"));
            plugin_date_val.setText(sessionDate);
            plugin_time_val.setText(sessionTime);
            session_duration_val.setText(sessionInfoBean.getSessionDuration());
            charge_duration_val.setText(sessionInfoBean.getChargeDuration());
            energy_kwh_val.setText(sessionInfoBean.getTotalEnergy());

            /*transaction_id_lbl.setText(sessionInfoBean.getTransaction_id());
            device_name_lbl.setText(sessionInfoBean.getDevice_name());
            port_name_lbl.setText(sessionInfoBean.getPortName());
            group_name_lbl.setText(sessionInfoBean.getGroup_name());
            station_type_lbl.setText(sessionInfoBean.getStation_type());
            peak_power_lbl.setText(sessionInfoBean.getPeak_power());
            avg_power_lbl.setText(sessionInfoBean.getAvg_power());
            id_tag_lbl.setText(sessionInfoBean.getId_tag());
            session_start_lbl.setText(sessionInfoBean.getSession_start_time());
            session_end_lbl.setText(sessionInfoBean.getSession_end_time());
            charge_start_lbl.setText(sessionInfoBean.getCharge_start_time());
            charge_end_lbl.setText(sessionInfoBean.getCharge_end_time());
            end_reason_lbl.setText(sessionInfoBean.getEnd_reason());
            ghg_savings_lbl.setText(String.valueOf(sessionInfoBean.getGhg_savings_kg()));
            ghg_saving_gals_lbl.setText(String.valueOf(sessionInfoBean.getGhg_savings_gal()));
            start_soc_lbl.setText(sessionInfoBean.getStart_soc());
            end_soc_lbl.setText(sessionInfoBean.getEnd_soc());*/

            if (position == sessionInfoBeans.size() - 1)
                session_info_dview.setVisibility(View.GONE);


            if (selectedPosition == position) {
                highlight_view.setVisibility(View.VISIBLE);
                itemView.setBackgroundColor(Color.parseColor("#DDE0CF"));
            } else {
                highlight_view.setVisibility(View.GONE);
                itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            }

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });


        }

        @Override
        protected void clear() {

        }
    }

    private
    class ProgressHolder extends SessionInfoBaseViewHolder {
        ProgressHolder(View view) {
            super(view);
//            customProgressDialog = CustomProgressDialog.show(mContext, true, false);
            ButterKnife.bind(this, view);
        }

        @Override
        protected void clear() {

        }
    }
}
