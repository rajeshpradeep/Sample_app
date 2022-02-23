package com.example.structure.ui.monitor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.MonitorSCAResponseModel;
import com.example.structure.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 04-02-2020
 */
public class MonitorGridAdapter extends RecyclerView.Adapter<MonitorGridAdapter.MonitorViewHolder> {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private ArrayList<MonitorSCAResponseModel> monitorSCAArrayList;

    public MonitorGridAdapter(Context mContext, ArrayList<MonitorSCAResponseModel> monitorSCAArrayList) {
        this.mContext = mContext;
        this.monitorSCAArrayList = monitorSCAArrayList;
    }

    @NonNull
    @Override
    public MonitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.monitor_gridview_item, parent, false);
        return new MonitorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonitorViewHolder holder, int position) {
        MonitorSCAResponseModel monitorSCAResponseModel = monitorSCAArrayList.get(position);
        holder.ic_monitor_item.setImageResource(monitorSCAResponseModel.getMonitor_icon());
        holder.monitor_key_tview.setText(monitorSCAResponseModel.getMonitorKey());
        holder.monitor_values_tview.setText(monitorSCAResponseModel.getMonitorVal());
    }

    @Override
    public int getItemCount() {
        return monitorSCAArrayList.size();
    }

    public class MonitorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.monitor_item_llay)
        LinearLayout monitor_item_llay;
        @BindView(R.id.ic_monitor_item)
        ImageView ic_monitor_item;
        @BindView(R.id.monitor_values_tview)
        TextView monitor_values_tview;
        @BindView(R.id.monitor_key_tview)
        TextView monitor_key_tview;

        public MonitorViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Utils.setRegularFonts(mContext, new TextView[]{monitor_key_tview, monitor_values_tview});
//            Utils.setBoldFonts(mContext, new TextView[]{monitor_values_tview});
        }
    }
}
