package com.example.structure.ui.settings.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.data.models.VehicleListResponseModel;
import com.example.structure.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 25-11-2019
 */
public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder> {

    private Context mContext;
    private ArrayList<VehicleListResponseModel.UserVehiclesBean> vehicleArrayList;
    private int disableVehicleId;

    public VehicleListAdapter(Context mContext, ArrayList<VehicleListResponseModel.UserVehiclesBean> vehicleArrayList, int disableVehicleId) {
        this.mContext = mContext;
        this.vehicleArrayList = vehicleArrayList;
        this.disableVehicleId = disableVehicleId;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vehicle_list_row_item, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        VehicleListResponseModel.UserVehiclesBean userVehiclesBean = vehicleArrayList.get(position);
        float efficiency = Float.parseFloat(userVehiclesBean.getCombE());
        int rangeValue = Integer.parseInt(userVehiclesBean.getRange());
        String range = rangeValue > 0 ? userVehiclesBean.getRange() : userVehiclesBean.getRangeA();
        holder.name_tview.setText(userVehiclesBean.getMake() + " " + userVehiclesBean.getModel());
        holder.year_tview.setText(userVehiclesBean.getYear());
        holder.electric_range_value_tview.setText(range);
        holder.efficiency_value_tview.setText(String.valueOf(efficiency));
        if (disableVehicleId == userVehiclesBean.getVehicle_id())
//            holder.vehicle_row_item_llay.setBackground(mContext.getDrawable(R.drawable.bg_light_gray));
            holder.vehicle_row_item_llay.setBackgroundTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.edittext_stroke, mContext.getTheme())));
        else    holder.vehicle_row_item_llay.setBackgroundTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.white, mContext.getTheme())));
    }

    public void updateVehicleList(ArrayList<VehicleListResponseModel.UserVehiclesBean> vehicleArrayList, int position) {
//        this.vehicleArrayList = vehicleArrayList;
        vehicleArrayList.remove(position);
        notifyItemRangeRemoved(position, vehicleArrayList.size());
    }

    @Override
    public int getItemCount() {
        return vehicleArrayList.size();
    }

    class VehicleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ic_medal_badge)
        ImageView ic_medal_badge;
        @BindView(R.id.name_tview)
        TextView name_tview;
        @BindView(R.id.year_tview)
        TextView year_tview;
        @BindView(R.id.electric_range_lbl)
        TextView electric_range_lbl;
        @BindView(R.id.electric_range_value_tview)
        TextView electric_range_value_tview;
        @BindView(R.id.efficiency_lbl)
        TextView efficiency_lbl;
        @BindView(R.id.efficiency_value_tview)
        TextView efficiency_value_tview;
        @BindView(R.id.vehicle_row_item_llay)
        LinearLayout vehicle_row_item_llay;

        VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Utils.setBoldFonts(mContext, new TextView[] {name_tview, electric_range_value_tview, efficiency_value_tview});
            Utils.setRegularFonts(mContext, new TextView[] {year_tview, electric_range_lbl, efficiency_lbl});
        }
    }
}
