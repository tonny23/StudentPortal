package com.example.studentportal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalItemAdapter extends RecyclerView.Adapter<PortalItemAdapter.ViewHolder> {

    private List<PortalItem> mPortalItems;
    private ItemClickListener mItemClickListener;

    // Provide a reference to the views for each data item
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.textView);
            mView = itemView;
        }
    }

    public PortalItemAdapter(List<PortalItem> portalItems, ItemClickListener clickListener) {
        mPortalItems = portalItems;
        mItemClickListener = clickListener;
    }

    @Override
    public PortalItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View bucketListView = inflater.inflate(R.layout.portal_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(bucketListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PortalItemAdapter.ViewHolder holder, final int position) {
        // Get the data model based on position
        String portalItem = mPortalItems.get(position).getTitle();

        // Set item views based on your views and data model
        final TextView textView = holder.mTextView;
        textView.setText(portalItem);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPortalItems.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
