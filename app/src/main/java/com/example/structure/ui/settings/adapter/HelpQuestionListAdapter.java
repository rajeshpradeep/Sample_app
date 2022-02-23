package com.example.structure.ui.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.structure.R;
import com.example.structure.utils.Utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh Pradeep G on 21-10-2019
 */
public class HelpQuestionListAdapter extends RecyclerView.Adapter<HelpQuestionListAdapter.HelpQuestionViewHolder> {

    private Context mContext;
    private int currentSelectedPosition = RecyclerView.NO_POSITION;

    public HelpQuestionListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public HelpQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.help_list_item, parent, false);
        return new HelpQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpQuestionViewHolder holder, int position) {
        /*holder.help_plus_img.setOnClickListener(view -> {
            currentSelectedPosition = position;
            notifyDataSetChanged();
        });*/
        holder.help_title_rlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSelectedPosition = position;
                notifyDataSetChanged();
            }
        });
        if (currentSelectedPosition == position) {
            holder.help_plus_img.setImageResource(R.drawable.ic_minus);
            holder.help_answer_tview.setVisibility(View.VISIBLE);
        } else {
            holder.help_plus_img.setImageResource(R.drawable.ic_plus);
            holder.help_answer_tview.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class HelpQuestionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.help_title_rlay)
        RelativeLayout help_title_rlay;
        @BindView(R.id.help_title_tview)
        TextView help_title_tview;
        @BindView(R.id.help_answer_tview)
        TextView help_answer_tview;
        @BindView(R.id.help_plus_img)
        ImageView help_plus_img;
        @BindView(R.id.qa_divider_view)
        View qa_divider_view;

        HelpQuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Utils.setBoldFonts(mContext, new TextView[]{help_title_tview});
            Utils.setRegularFonts(mContext, new TextView[]{help_answer_tview});
//            help_plus_img.setOnClickListener(view -> help_answer_tview.setVisibility(View.VISIBLE));
            help_title_rlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    help_answer_tview.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
