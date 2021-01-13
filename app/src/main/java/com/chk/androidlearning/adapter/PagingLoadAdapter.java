package com.chk.androidlearning.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chk.androidlearning.databinding.ItemLoadStateBinding;

import org.jetbrains.annotations.NotNull;

import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CHK on 21-1-13.
 */
public class PagingLoadAdapter extends LoadStateAdapter<PagingLoadAdapter.LoadStateViewHolder> {

    @NotNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, @NotNull LoadState loadState) {
        return new LoadStateViewHolder(ItemLoadStateBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NotNull LoadStateViewHolder loadStateViewHolder, @NotNull LoadState loadState) {
        loadStateViewHolder.bindTo(loadState);
    }

    static class LoadStateViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar mProgressBar;
        private TextView mErrorMsg;
        private Button mRetry;


        public LoadStateViewHolder(ItemLoadStateBinding binding) {
            super(binding.getRoot());
            mProgressBar = binding.progressBar;
            mErrorMsg = binding.errorMsg;
            mRetry = binding.retryButton;
        }

        public void bindTo(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                mErrorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }
            mProgressBar.setVisibility(loadState instanceof LoadState.Loading
                    ? View.VISIBLE : View.GONE);
            mRetry.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);
            mErrorMsg.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);
        }
    }

}
