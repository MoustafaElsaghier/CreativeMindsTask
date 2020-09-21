package com.test.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.test.Models.RepoItem;
import com.test.R;
import com.test.Utilities.AppUtilities;

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
        // bind the data to views
        holder.bindViews(object);

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

        public void bindViews(RepoItem repoItem) {
            repoName.setText(repoItem.getName());
            repoDesc.setText(repoItem.getDescription());
            repoFullName.setText(repoItem.getFullName());

            if (repoItem.isFork()) itemView.setBackgroundColor(Color.WHITE);
            else itemView.setBackgroundColor(Color.GREEN);

            itemView.setOnLongClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(context.getString(R.string.repo_dialog_message));
                builder.setPositiveButton(context.getString(R.string.repo_url), (dialog, which) -> AppUtilities.openURL(context, repoItem.getHtmlUrl()));
                builder.setNegativeButton(context.getString(R.string.owner_url), (dialog, id) -> AppUtilities.openURL(context, repoItem.getOwner().getHtmlUrl()));
                builder.show();

                return false;
            });
        }
    }
}
