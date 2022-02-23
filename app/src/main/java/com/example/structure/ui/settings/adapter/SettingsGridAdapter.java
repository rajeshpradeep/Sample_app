package com.example.structure.ui.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.structure.R;
import com.example.structure.ui.settings.listener.SettingsRedirectionListener;
import com.example.structure.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 16-10-2019
 */
public class SettingsGridAdapter extends RecyclerView.Adapter<SettingsGridAdapter.SettingsViewHolder> {
    private Context mContext;
    private ArrayList<String> settingsMenuList;
    ArrayList<Integer> settingsMenuListImage;
    private SettingsRedirectionListener settingsRedirectionListener;

    public SettingsGridAdapter(Context context, ArrayList<String> settingsMenuList, ArrayList<Integer> settingsMenuListImage,
                               SettingsRedirectionListener settingsRedirectionListener) {
        mContext = context;
        this.settingsMenuList = settingsMenuList;
        this.settingsMenuListImage = settingsMenuListImage;
        this.settingsRedirectionListener = settingsRedirectionListener;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.settings_gridview_item, parent, false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        holder.settings_item_tview.setText(settingsMenuList.get(position));
        holder.settings_item_img.setImageResource(settingsMenuListImage.get(position));
    }

    @Override
    public int getItemCount() {
        return settingsMenuList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class SettingsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.settings_item_llay)
        LinearLayout settings_item_llay;
        @BindView(R.id.settings_item_img)
        ImageView settings_item_img;
        @BindView(R.id.settings_item_tview)
        TextView settings_item_tview;

        SettingsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Utils.setRegularFonts(mContext, new TextView[] {settings_item_tview});
            settings_item_llay.setOnClickListener(view -> {
                Toast.makeText(mContext, settingsMenuList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                settingsRedirectionListener.settingsItem(settingsMenuList.get(getAdapterPosition()));
            });
        }
    }
}
