package com.example.qr_scanner.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qr_scanner.R;
import com.example.qr_scanner.database.entity.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<HistoryModel> historyModels;
    private CallBack callBack;

    public HistoryAdapter(CallBack callBack) {
        this.historyModels = new ArrayList<>();
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryModel historyModel = historyModels.get(position);
        holder.nameHistory.setText(historyModel.getData());
        holder.timeHistory.setText(historyModel.getCreatedAt().toString());

        holder.deleteHistory.setOnClickListener(view -> {
            callBack.deleteHistory(historyModel);
        });
    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public void setHistoryModels(List<HistoryModel> historyModels) {
        this.historyModels = historyModels;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void deleteHistory(HistoryModel historyModel);
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView nameHistory, timeHistory;
        ImageView deleteHistory;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameHistory = itemView.findViewById(R.id.nameHistory);
            timeHistory = itemView.findViewById(R.id.timeHistory);
            deleteHistory = itemView.findViewById(R.id.deleteHistory);
        }
    }
}
