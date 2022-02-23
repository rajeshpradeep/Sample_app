package com.example.structure.ui.leaderboard.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Rajesh Pradeep G on 13-01-2020
 */
public abstract class LeadboardBaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;

    public LeadboardBaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

}
