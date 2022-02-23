package com.example.structure.ui.leaderboard.listener;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Rajesh Pradeep G on 13-01-2020
 */
public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    private String TAG = getClass().getSimpleName();
    public static final int PAGE_START = 1;
    @NonNull
    private LinearLayoutManager layoutManager;
    /**
     * Set scrolling threshold here (for now i'm assuming 10 item in one page)
     */
    private static final int PAGE_SIZE = 10;
    private int previousTotal = 0;
    private int visibleThreshold = 10;

    /**
     * Supporting only LinearLayoutManager for now.
     */
    public PaginationListener(@NonNull LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {

            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int adapterCount = recyclerView.getAdapter().getItemCount();
            int totalRecordsCount = getTotalPageCount() * 10;
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            int lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            Log.i(TAG, "onScrolled:visibleItemCount " + visibleItemCount + " totalItemCount:" + totalItemCount);
            Log.i(TAG, "onScrolled:firstVisibleItemPosition " + firstVisibleItemPosition + " lastVisibleItemPosition:" + lastVisibleItemPosition);
            Log.i(TAG, "onScrolled:adapterCount " + adapterCount + " previousTotal :" + previousTotal);
            if (!isLoading() && !isLastPage()) {
                Log.i(TAG, "isLoading: " + isLoading() + " isLastPage:" + isLastPage());
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }

            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
