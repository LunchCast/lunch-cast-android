package com.rockets12.lunchcast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.backendless.Order;

import java.util.ArrayList;

/**
 * Created by Stevan Kovacevic on 3/4/2016.
 */
public class OrderDetailsAdapter extends BaseAdapter {

    private ArrayList<OrderDetail> mDetails;
    private Context mContext;
    private LayoutInflater mInflater;
    private boolean isSortedByMeals;

    public OrderDetailsAdapter(ArrayList<OrderDetail> details, Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDetails = details;
        isSortedByMeals = true;
    }

    @Override
    public int getCount() {
        return mDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return mDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setDetails(ArrayList<OrderDetail> details, boolean sortedByMeals) {
        this.mDetails = details;
        this.isSortedByMeals = sortedByMeals;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderDetail detail = mDetails.get(position);
        DetailViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_order_detail, null);
            holder = new DetailViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text_detail_title);
            holder.subtitle = (TextView) convertView.findViewById(R.id.text_detail_subtitle);
            holder.total = (TextView) convertView.findViewById(R.id.text_detail_total);
            convertView.setTag(holder);
        } else {
            holder = (DetailViewHolder) convertView.getTag();
        }

        holder.title.setText(detail.getTitle());
        holder.subtitle.setText(detail.getSubTitle());
        if (isSortedByMeals) {
            holder.total.setText(String.valueOf(detail.getPrice() * detail.getQuantity()));
        } else {
            holder.total.setText(String.valueOf(detail.getPrice()));
        }

        return convertView;
    }

    class DetailViewHolder {
        TextView title;
        TextView subtitle;
        TextView total;
    }
}
