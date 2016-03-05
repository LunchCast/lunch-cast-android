package com.rockets12.lunchcast.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.backendless.BackendlessUser;
import com.rockets12.lunchcast.CallbackInterface;
import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.adapter.OrderDetail;
import com.rockets12.lunchcast.adapter.OrderDetailsAdapter;
import com.rockets12.lunchcast.backendless.Order;
import com.rockets12.lunchcast.backendless.OrderItem;
import com.rockets12.lunchcast.backendless.Tag;

import java.util.ArrayList;

/**
 * Created by Stevan Kovacevic on 3/4/2016.
 */
public class OrderDetailsFragment extends Fragment {

    private static final String ARG_RES = "orders";
    private static final String ARG_USER = "user";
    private static final String ARG_ORDER = "order";

    private CallbackInterface mCallback;

    private ArrayList<OrderItem> mOrderItems;
    private BackendlessUser mUser;
    private Order mOrder;

    private boolean isSortedByMeals = true;

    private ListView mListOrders;
    private TextView mTextTitle, mTextDesc, mTextEta, mTextMin, mTextTags, mTextAdmin, mTextAmount;
    private RelativeLayout mLayoutOrderCommands;
    private Button mButtonCancel, mButtonComplete;

    public OrderDetailsFragment() {
        // Required empty public constructor
    }

    public static OrderDetailsFragment newInstance(ArrayList<OrderItem> orders, BackendlessUser
            user, Order order) {
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RES, orders);
        args.putSerializable(ARG_USER, user);
        args.putSerializable(ARG_ORDER, order);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallbackInterface) {
            mCallback = (CallbackInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOrderItems = (ArrayList<OrderItem>) getArguments().getSerializable(ARG_RES);
            mUser = (BackendlessUser) getArguments().getSerializable(ARG_USER);
            mOrder = (Order) getArguments().getSerializable(ARG_ORDER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_details, container, false);
        mTextTitle = (TextView) v.findViewById(R.id.text_restaurant_title);
        mTextTitle.setText(mOrder.getRestaurant().getName());
        mTextDesc = (TextView) v.findViewById(R.id.text_restaurant_desc);
        mTextEta = (TextView) v.findViewById(R.id.text_restaurant_eta);
        mTextEta.setText("Average delivery time: " + mOrder.getRestaurant().getEta() + " min");
        mTextMin = (TextView) v.findViewById(R.id.text_restaurant_min);
        mTextMin.setText("Minimum for order: " + mOrder.getRestaurant().getMinAmount() + " din");
        mTextTags = (TextView) v.findViewById(R.id.text_restaurant_tags);
        StringBuffer sb = new StringBuffer();
        for (Tag tag : mOrder.getRestaurant().getTags()) {
            sb.append("#" + tag.getName() + " ");
        }
        mTextTags.setText(sb.toString());
        mTextAmount = (TextView) v.findViewById(R.id.text_restaurant_amount);
        int total = 0;
        for (OrderItem item : mOrderItems) {
            total += item.getQuantity() * Integer.valueOf(item.getMeal().getPrice());
        }
        mTextAmount.setText(total + "/" + mOrder.getRestaurant().getMinAmount());
        mTextAdmin = (TextView) v.findViewById(R.id.text_restaurant_admin);
        mTextAdmin.setText("Admin: " + mOrder.getOrder_creator().getProperty("name"));
        mLayoutOrderCommands = (RelativeLayout) v.findViewById(R.id.layout_order_commands);
        mButtonCancel = (Button) v.findViewById(R.id.button_cancel_order);
        mButtonComplete = (Button) v.findViewById(R.id.button_complete_order);
        if (isAdmin()) {
            mTextAdmin.setVisibility(View.INVISIBLE);
            mLayoutOrderCommands.setVisibility(View.VISIBLE);
        } else {
            mTextAdmin.setVisibility(View.VISIBLE);
            mLayoutOrderCommands.setVisibility(View.GONE);
        }

        mListOrders = (ListView) v.findViewById(R.id.list_orders);
        mListOrders.setAdapter(new OrderDetailsAdapter(getOrderDetails(isSortedByMeals),
                getActivity()));
        return v;
    }

    private ArrayList<OrderDetail> getOrderDetails(boolean sortByMeals) {
        ArrayList<OrderDetail> details = new ArrayList<>();
        if (sortByMeals) {
            for (OrderItem item : mOrderItems) {
                OrderDetail newDetail = new OrderDetail(item.getMeal().getName());
                newDetail.setPrice(Integer.valueOf(item.getMeal().getPrice()));
                newDetail.setQuantity(item.getQuantity());
                newDetail.setSubTitle((String) item.getOrderer().getProperty("name"));
                int indexOf = details.indexOf(newDetail);
                if (indexOf != -1) {
                    details.get(indexOf).addQuantity(item.getQuantity());
                    details.get(indexOf).addSubTitle((String) item.getOrderer().getProperty
                            ("name"));
                } else {
                    details.add(newDetail);
                }
            }
        } else {
            for (OrderItem item : mOrderItems) {
                OrderDetail newDetail = new OrderDetail((String) item.getOrderer().getProperty
                        ("name"));
                newDetail.setPrice(Integer.valueOf(item.getMeal().getPrice()) * item.getQuantity());
                newDetail.setSubTitle(item.getMeal().getName());
                int indexOf = details.indexOf(newDetail);
                if (indexOf != -1) {
                    details.get(indexOf).addTotalPrice(Integer.valueOf(item.getMeal().getPrice())
                            * item.getQuantity());
                    details.get(indexOf).addSubTitle(item.getMeal().getName());
                } else {
                    details.add(newDetail);
                }
            }
        }
        for (OrderDetail detail : details) {
            Log.e("Test", detail.toString());
        }
        return details;
    }

    private boolean isAdmin() {
        return mOrder.getOrder_creator().getObjectId().equals(mUser.getObjectId());
    }
}
