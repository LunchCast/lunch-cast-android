package com.rockets12.lunchcast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rockets12.lunchcast.LunchCastActivity;
import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.backendless.Order;

import java.util.List;

/**
 * Created by Stevan Kovacevic on 3/4/16.
 */
public class OrderAdapter extends BaseAdapter {
    private List<Order> mOrders;
    private Context mContext;
    private LayoutInflater mInflater;

    public OrderAdapter(Context context, List<Order> orders) {
        mOrders = orders;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return mOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_order, null);
            holder = new RestaurantViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.item_order_title);
            holder.desc = (TextView) convertView.findViewById(R.id.item_order_desc);
            holder.eta = (TextView) convertView.findViewById(R.id.item_order_eta);
            holder.admin = (TextView) convertView.findViewById(R.id.item_order_admin);
            holder.choose = (ImageView) convertView.findViewById(R.id.item_order_choose);
            convertView.setTag(holder);
        } else {
            holder = (RestaurantViewHolder) convertView.getTag();
        }
        Order order = mOrders.get(position);
        holder.title.setText(order.getRestaurant().getName());
        holder.eta.setText("Average delivery time: " + order.getRestaurant().getEta() + " mins");
        holder.admin.setText("Order creator: " + (String) order.getOrder_creator().getProperty
                ("name"));
        if (order.getState() == LunchCastActivity.ORDER_STATE_OPEN) {
            holder.choose.setImageResource(R.drawable.open_sign);
        } else {
            holder.choose.setImageResource(R.drawable.closed_sign);
        }
        return convertView;
    }

    class RestaurantViewHolder {
        TextView title;
        TextView desc;
        TextView eta;
        TextView admin;
        ImageView choose;
    }
}
