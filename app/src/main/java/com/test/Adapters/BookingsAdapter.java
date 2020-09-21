package com.test.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.R;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> bookingList;
    private boolean isCancelled;

    public BookingsAdapter(Context context, ArrayList<String> bookingList, boolean isCancelled) {
        this.context = context;
        this.bookingList = bookingList;
        this.isCancelled = isCancelled;
    }

    @NonNull
    @Override
    public BookingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsAdapter.ViewHolder holder, int position) {
        final String object = bookingList.get(position);
        // bind the data to views
        holder.orderName.setText(object);

        if (isCancelled)
            holder.cancelButton.setVisibility(View.GONE);

        if (position % 2 == 0) {
            holder.orangeCircle.setVisibility(View.GONE);
            holder.orderImage.setImageResource(R.drawable.type_food);
        } else {
            holder.orangeCircle.setVisibility(VISIBLE);
            holder.orderImage.setImageResource(R.drawable.type_car);
        }

        // hide the divider between items in last item
        if (position == getItemCount() - 1)
            holder.dividerLine.setVisibility(View.GONE);
        else
            holder.dividerLine.setVisibility(VISIBLE);

    }

    @Override
    public int getItemCount() {
        return bookingList == null ? 0 : bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orderName;
        View dividerLine;
        Button cancelButton;
        ImageView orderImage, orangeCircle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orangeCircle = itemView.findViewById(R.id.orangeCircle);
            dividerLine = itemView.findViewById(R.id.dividerLine);
            orderName = itemView.findViewById(R.id.orderName);
            cancelButton = itemView.findViewById(R.id.cancelButton);
            orderImage = itemView.findViewById(R.id.orderImage);
        }

    }
}
