package com.rockets12.lunchcast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.BackendlessUser;
import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.backendless.Meal;
import com.rockets12.lunchcast.backendless.Order;
import com.rockets12.lunchcast.backendless.OrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stevan Kovacevic on 3/2/2016.
 */
public class MealAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<Meal> mMeals;
    private List<OrderItem> mOrderItems;
    private Order mOrder;

    public MealAdapter(Context context, List<Meal> meals, List<OrderItem> orderItems, Order order) {
        mMeals = meals;
        mOrderItems = orderItems == null ? new ArrayList<OrderItem>() : orderItems;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mOrder = order;
    }

    @Override
    public int getCount() {
        return mMeals.size();
    }

    @Override
    public Object getItem(int position) {
        return mMeals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MealViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_meal, null);
            holder = new MealViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text_meal_title);
            holder.price = (TextView) convertView.findViewById(R.id.text_meal_price);
            holder.description = (TextView) convertView.findViewById(R.id.text_meal_description);
            holder.amount = (TextView) convertView.findViewById(R.id.text_meal_amount);
            holder.minus = (ImageView) convertView.findViewById(R.id.image_button_meal_minus);
            holder.plus = (ImageView) convertView.findViewById(R.id.image_button_meal_plus);
            convertView.setTag(holder);
        } else {
            holder = (MealViewHolder) convertView.getTag();
        }
        Meal meal = mMeals.get(position);
        holder.title.setText(meal.getName());
        holder.price.setText(meal.getPrice() + " din");
        holder.description.setText(meal.getDescription());
        holder.amount.setText(String.valueOf(getAmountForMeal(meal)));
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMeal(position);
                notifyDataSetChanged();
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeal(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private int getAmountForMeal(Meal m) {
        for (OrderItem item : mOrderItems) {
            if (item.getMeal().getObjectId().equals(m.getObjectId())) {
                return item.getQuantity();
            }
        }
        return 0;
    }

    private void addMeal(int position) {
        Meal m = mMeals.get(position);
        for (OrderItem item : mOrderItems) {
            if (item.getMeal().getObjectId().equals(m.getObjectId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        OrderItem newItem = new OrderItem();
        newItem.setQuantity(1);
        newItem.setMeal(m);
        newItem.setOrder_id(mOrder);
        mOrderItems.add(newItem);
    }

    private void removeMeal(int position) {
        Meal m = mMeals.get(position);
        for (int i = 0; i < mOrderItems.size(); i++) {
            OrderItem item = mOrderItems.get(i);
            if (item.getMeal().getObjectId().equals(m.getObjectId())) {
                item.setQuantity(item.getQuantity() - 1);
                if (item.getQuantity() <= 0) {
                    mOrderItems.remove(i);
                }
                return;
            }
        }
    }

    public List<OrderItem> getmOrderItems() {
        return mOrderItems;
    }

    class MealViewHolder {
        TextView title;
        TextView price;
        TextView description;
        ImageView minus;
        ImageView plus;
        TextView amount;
    }
}
