package com.rockets12.lunchcast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.backendless.Restaurant;
import com.rockets12.lunchcast.backendless.Tag;

import java.util.List;

/**
 * Created by Stevan Kovacevic on 3/2/2016.
 */
public class RestaurantAdapter extends BaseAdapter {

    private List<Restaurant> mRestaurants;
    private Context mContext;
    private LayoutInflater mInflater;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        mRestaurants = restaurants;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return mRestaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_restaurant, null);
            holder = new RestaurantViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.item_restaurant_title);
            holder.desc = (TextView) convertView.findViewById(R.id.item_restaurant_desc);
            holder.eta = (TextView) convertView.findViewById(R.id.item_restaurant_eta);
            holder.min = (TextView) convertView.findViewById(R.id.item_restaurant_min);
            holder.tags = (TextView) convertView.findViewById(R.id.item_restaurant_tags);
            holder.choose = (ImageView) convertView.findViewById(R.id.item_restaurant_choose);
            convertView.setTag(holder);
        } else {
            holder = (RestaurantViewHolder) convertView.getTag();
        }
        Restaurant res = mRestaurants.get(position);
        holder.title.setText(res.getName());
        holder.eta.setText("Average delivery time: " + res.getEta() + " mins");
        holder.min.setText("Minimum for order: " + res.getMinAmount() + " din");
        StringBuffer sb = new StringBuffer();
        for (Tag tag : res.getTags()) {
            sb.append("#" + tag.getName() + " ");
        }
        holder.tags.setText(sb.toString());
        if (position % 2 > 0) {
            holder.choose.setImageResource(R.drawable.choose_button_2);
        } else {
            holder.choose.setImageResource(R.drawable.choose_button_1);
        }
        return convertView;
    }

    class RestaurantViewHolder {
        TextView title;
        TextView desc;
        TextView eta;
        TextView min;
        TextView tags;
        ImageView choose;
    }
}