package com.example.structure.ui.monitor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.MonitorEVSEResponseModel;
import com.example.structure.ui.monitor.listener.MonitorEVSEPortListener;
import com.example.structure.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 25-03-2020
 */
public class MonitorEVSEAdapter extends RecyclerView.Adapter<MonitorEVSEAdapter.PortItemViewHolder>  {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private int selectedItem;
    private MonitorEVSEPortListener monitorEVSEPortListener;
    private ArrayList<MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean> stationPortList;
    private  String chargeboxId;

    public MonitorEVSEAdapter(Context mContext, String chargeboxId,
                              ArrayList<MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean> stationPortList,
                              String curruntPortName, MonitorEVSEPortListener monitorEVSEPortListener) {
        this.mContext = mContext;
        this.stationPortList = stationPortList;
        this.chargeboxId = chargeboxId;
        this.monitorEVSEPortListener = monitorEVSEPortListener;
        for (int i = 0; i < stationPortList.size(); i++) {
            if (curruntPortName.contains(String.valueOf(stationPortList.get(i).getConnectorId())))
                selectedItem = i;
        }
    }

    @NonNull
    @Override
    public PortItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.evse_port_item_layout, parent, false);
        return new MonitorEVSEAdapter.PortItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortItemViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        MonitorEVSEResponseModel.MonitorOcppBean.StationPortsBean stationPortsBean = stationPortList.get(position);
        holder.port_name_tview.setText(chargeboxId + " | " + stationPortsBean.getPortName() + " | " + stationPortsBean.getConnectorId());
        /*if(curruntPortName.equals(portList.get(position)))
            holder.ic_green_check.setVisibility(View.VISIBLE);
        else holder.ic_green_check.setVisibility(View.GONE);*/

        if (selectedItem == position) {
            holder.ic_green_check.setVisibility(View.VISIBLE);;
        } else holder.ic_green_check.setVisibility(View.GONE);

        holder.port_item_rlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monitorEVSEPortListener.portSelected(stationPortsBean.getPortName(), stationPortsBean.getPortId());
                int previousItem = selectedItem;
                selectedItem = position;

                notifyDataSetChanged();
//                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationPortList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class PortItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.port_item_rlay)
        RelativeLayout port_item_rlay;
        @BindView(R.id.ic_green_check)
        ImageView ic_green_check;
        @BindView(R.id.port_name_tview)
        TextView port_name_tview;
        @BindView(R.id.port_dview)
        View port_dview;
        PortItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Utils.setRegularFonts(mContext, new TextView[]{port_name_tview});
        }
    }
}
