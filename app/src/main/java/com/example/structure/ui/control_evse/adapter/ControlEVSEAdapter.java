package com.example.structure.ui.control_evse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.ui.control_evse.listener.EVSEPortListener;
import com.example.structure.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 12-03-2020
 */
public class ControlEVSEAdapter extends RecyclerView.Adapter<ControlEVSEAdapter.PortItemViewHolder> {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private ArrayList<String> portList;
    private ArrayList<Integer> portListId;
    private String curruntPortName;
    private static int lastClickedPosition = -1;
    private int selectedItem;
    private EVSEPortListener evsePortListener;

    public ControlEVSEAdapter(Context mContext, ArrayList<String> portList, ArrayList<Integer> portListId, String curruntPortName, EVSEPortListener evsePortListener) {
        this.mContext = mContext;
        this.portList = portList;
        this.portListId = portListId;
        this.curruntPortName = curruntPortName;
        this.evsePortListener = evsePortListener;
        for (int i = 0; i < portList.size(); i++) {
            if (curruntPortName.equals(portList.get(i)))
                selectedItem = i;
        }
//        selectedItem = -1;
    }

    @NonNull
    @Override
    public PortItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.evse_port_item_layout, parent, false);
        return new PortItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortItemViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.port_name_tview.setText(portList.get(position));
        /*if(curruntPortName.equals(portList.get(position)))
            holder.ic_green_check.setVisibility(View.VISIBLE);
        else holder.ic_green_check.setVisibility(View.GONE);*/

        if (selectedItem == position) {
            holder.ic_green_check.setVisibility(View.VISIBLE);;
        } else holder.ic_green_check.setVisibility(View.GONE);

        holder.port_item_rlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evsePortListener.portSelected(portList.get(position), portListId.get(position));
                int previousItem = selectedItem;
                selectedItem = position;

                notifyDataSetChanged();
//                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return portList.size();
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
