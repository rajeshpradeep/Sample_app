package com.example.structure.ui.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.structure.R;
import com.example.structure.utils.Utils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 16-10-2019
 */
public class DashboardGridAdapter extends RecyclerView.Adapter<DashboardGridAdapter.DashboardViewHolder> {

    private String TAG = getClass().getSimpleName();
    private Context mContext;
    ArrayList<String> dashboardGridKeyList, dashboardGridValList;
    ArrayList<Integer> dashboardGridListImage;
    String toolTip, toolTipTitle;

    public DashboardGridAdapter(Context context, ArrayList<String> dashboardGridKeyList, ArrayList<String> dashboardGridValList, ArrayList<Integer> dashboardGridListImage) {
        mContext = context;
        this.dashboardGridKeyList = dashboardGridKeyList;
        this.dashboardGridValList = dashboardGridValList;
        this.dashboardGridListImage = dashboardGridListImage;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dashboard_gridview_item, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        holder.key_tview.setText(dashboardGridKeyList.get(position));
        holder.ic_dashboard_item.setImageResource(dashboardGridListImage.get(position));
        holder.values_tview.setText(dashboardGridValList.get(position));
    }

    @Override
    public int getItemCount() {
        return dashboardGridKeyList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class DashboardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dashboard_item_llay)
        LinearLayout dashboard_item_llay;
        @BindView(R.id.ic_info)
        ImageView ic_info;
        @BindView(R.id.ic_dashboard_item)
        ImageView ic_dashboard_item;
        @BindView(R.id.values_tview)
        TextView values_tview;
        @BindView(R.id.key_tview)
        TextView key_tview;

        DashboardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Utils.setRegularFonts(mContext, new TextView[]{key_tview});

            dashboard_item_llay.setOnClickListener(view -> {
                Toast.makeText(mContext, dashboardGridKeyList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            });

            ic_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() == 0) {
                        toolTipTitle = mContext.getString(R.string.charge_sec);
                        toolTip = mContext.getString(R.string.charge_session_tip);
                    } else if(getAdapterPosition() == 1) {
                        toolTipTitle = mContext.getString(R.string.ghg_save);
                        toolTip = mContext.getString(R.string.ghg_saving_tip);
                    } else if (getAdapterPosition() == 2) {
                        toolTipTitle = mContext.getString(R.string.monetary_save);
                        toolTip = mContext.getString(R.string.monetary_saving_tip);
                    } else if(getAdapterPosition() == 3) {
                        toolTipTitle = mContext.getString(R.string.gas_save);
                        toolTip = mContext.getString(R.string.gasoline_saving_tip);
                    }
                    Utils.showOKCustomAlert(mContext, toolTipTitle, toolTip, "dashboard_tip");
                }
            });
        }

        /**
         * Configured and load the webview
         *//*
        @SuppressLint("SetJavaScriptEnabled")
        private void loadWebview(String webViewURL) {
            try {
                if (webViewURL != null) {
                    WebSettings webSettings = values_tview.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                    webSettings.setAllowFileAccessFromFileURLs(true);
                    webSettings.setAllowUniversalAccessFromFileURLs(true);
                    webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webSettings.setAllowFileAccess(true);
                    webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                    values_tview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//                webview.post(() -> {

                    values_tview.loadUrl(webViewURL);

//                });

                    values_tview.setWebChromeClient(new WebChromeClient());
                    values_tview.setWebViewClient(new CustomWebView());
                }
            } catch (Exception e) {
                Log.i(TAG, "loadWebview: " + e.toString());
                e.printStackTrace();
            }
        }*/
    }
}
