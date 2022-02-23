package com.example.structure.ui.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
public class DashboardSCAAdapter extends RecyclerView.Adapter<DashboardSCAAdapter.DashboardSCAViewHolder> {
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private ArrayList<String> dashboard_SCA_KeyList;
    private ArrayList<String> dashboard_SCA_ValueList;
    private ArrayList<Integer> dashboard_SCA_Image;

    public DashboardSCAAdapter(Context context, ArrayList<String> dashboard_SCA_KeyList, ArrayList<String> dashboard_SCA_ValueList,
                               ArrayList<Integer> dashboard_SCA_Image) {
        mContext = context;
        this.dashboard_SCA_KeyList = dashboard_SCA_KeyList;
        this.dashboard_SCA_ValueList = dashboard_SCA_ValueList;
        this.dashboard_SCA_Image = dashboard_SCA_Image;
    }

    @NonNull
    @Override
    public DashboardSCAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dashboard_sca_item, parent, false);
        return new DashboardSCAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardSCAViewHolder holder, int position) {
        holder.sca_key_tview.setText(dashboard_SCA_KeyList.get(position));
        holder.ic_sca_img.setImageResource(dashboard_SCA_Image.get(position));
        holder.sca_value_tview.setText(dashboard_SCA_ValueList.get(position));

        if (position == dashboard_SCA_KeyList.size() - 1)
            holder.dashboard_sca_dview.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return dashboard_SCA_KeyList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class DashboardSCAViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.dashboard_sca_item_llay)
//        ConstraintLayout dashboard_sca_item_llay;
        @BindView(R.id.ic_sca_img)
        ImageView ic_sca_img;
        @BindView(R.id.sca_key_tview)
        TextView sca_key_tview;
        @BindView(R.id.sca_value_tview)
        TextView sca_value_tview;
        @BindView(R.id.dashboard_sca_dview)
        View dashboard_sca_dview;

        DashboardSCAViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            Utils.setRegularFonts(mContext, new TextView[]{sca_key_tview});
            Utils.setBoldFonts(mContext, new TextView[]{sca_value_tview});
            /*dashboard_sca_item_llay.setOnClickListener(view -> {
                Toast.makeText(mContext, dashboard_SCA_KeyList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            });*/
        }

        /**
         * Configured and load the webview
         *//*
        @SuppressLint("SetJavaScriptEnabled")
        private void loadWebview(String webViewURL) {
            try {
                if (webViewURL != null) {
                    WebSettings webSettings = sca_value_tview.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                    webSettings.setAllowFileAccessFromFileURLs(true);
                    webSettings.setAllowUniversalAccessFromFileURLs(true);
                    webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webSettings.setAllowFileAccess(true);
                    webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                    sca_value_tview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//                webview.post(() -> {

                    sca_value_tview.loadUrl(webViewURL);

//                });

                    sca_value_tview.setWebChromeClient(new WebChromeClient());
                    sca_value_tview.setWebViewClient(new CustomWebView());
                }
            } catch (Exception e) {
                Log.i(TAG, "loadWebview: " + e.toString());
                e.printStackTrace();
            }
        }*/

    }
}
