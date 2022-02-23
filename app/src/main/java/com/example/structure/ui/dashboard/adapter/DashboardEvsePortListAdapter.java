package com.example.structure.ui.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.DashboardEvseResponseModel;
import com.example.structure.ui.dashboard.listener.DashboardEVSEPortListener;
import com.example.structure.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 10-04-2020
 */
public class DashboardEvsePortListAdapter extends RecyclerView.Adapter<DashboardEvsePortListAdapter.PortItemViewHolder>  {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private int selectedItem;
    private DashboardEVSEPortListener dashboardEVSEPortListener;
    private ArrayList<DashboardEvseResponseModel.PortsBean> portsBeanArrayList;
    private  String chargeboxId, selectedPortName;

    public DashboardEvsePortListAdapter(Context mContext,
                                        ArrayList<DashboardEvseResponseModel.PortsBean> portsBeanArrayList, String chargeboxId,
                                        String selectedPortName, DashboardEVSEPortListener dashboardEVSEPortListener) {
        this.mContext = mContext;
        this.dashboardEVSEPortListener = dashboardEVSEPortListener;
        this.portsBeanArrayList = portsBeanArrayList;
        this.chargeboxId = chargeboxId;
        this.selectedPortName = selectedPortName;
        for (int i = 0; i < portsBeanArrayList.size(); i++) {
            if (selectedPortName.contains(String.valueOf(portsBeanArrayList.get(i).getConnectorId())))
                selectedItem = i;
        }
    }

    @NonNull
    @Override
    public DashboardEvsePortListAdapter.PortItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.evse_port_item_layout, parent, false);
        return new DashboardEvsePortListAdapter.PortItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardEvsePortListAdapter.PortItemViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        DashboardEvseResponseModel.PortsBean portsBean = portsBeanArrayList.get(position);
        holder.port_name_tview.setText(chargeboxId + " | " + portsBean.getName() + " | " + portsBean.getConnectorId());
        /*if(curruntPortName.equals(portList.get(position)))
            holder.ic_green_check.setVisibility(View.VISIBLE);
        else holder.ic_green_check.setVisibility(View.GONE);*/

        if (selectedItem == position) {
            holder.ic_green_check.setVisibility(View.VISIBLE);;
        } else holder.ic_green_check.setVisibility(View.GONE);

        holder.port_item_rlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashboardEVSEPortListener.portSelected(portsBean.getName(), portsBean.getConnectorId());
                int previousItem = selectedItem;
                selectedItem = position;

                notifyDataSetChanged();
//                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return portsBeanArrayList.size();
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
