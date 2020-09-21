package com.test.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.Models.RepoItem;
import com.test.R;

import java.util.ArrayList;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RepoItem> arrayListData;

    public ReposAdapter(Context context, ArrayList<RepoItem> arrayListData) {
        this.context = context;
        this.arrayListData = arrayListData;
    }

    @NonNull
    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.ViewHolder holder, int position) {
        final RepoItem object = arrayListData.get(position);


        // hide the divider between items in last item
        if (position == getItemCount() - 1)
            holder.dividerLine.setVisibility(View.GONE);
        else
            holder.dividerLine.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return arrayListData == null ? 0 : arrayListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView repoName, repoDesc, repoFullName;
        View dividerLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dividerLine = itemView.findViewById(R.id.dividerLine);
            repoName = itemView.findViewById(R.id.repoName);
            repoFullName = itemView.findViewById(R.id.repoFullName);
            repoDesc = itemView.findViewById(R.id.repoDesc);
        }
    }
}
