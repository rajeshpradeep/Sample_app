package com.example.structure.ui.session_info.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Rajesh Pradeep G on 27-02-2020
 */
public abstract class SessionInfoBaseViewHolder extends RecyclerView.ViewHolder {
    public SessionInfoBaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private int mCurrentPosition;

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
