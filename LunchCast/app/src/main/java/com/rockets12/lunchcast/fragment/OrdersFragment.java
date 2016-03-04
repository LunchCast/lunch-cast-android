package com.rockets12.lunchcast.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rockets12.lunchcast.CallbackInterface;
import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.adapter.OrderAdapter;
import com.rockets12.lunchcast.adapter.RestaurantAdapter;
import com.rockets12.lunchcast.backendless.Order;
import com.rockets12.lunchcast.backendless.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    private static final String ARG_RES = "res";

    private CallbackInterface mCallback;

    private List<Order> mOrders;

    private ListView mList;
    private OrderAdapter mAdapter;

    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance(ArrayList<Order> orders) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RES, orders);
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
            mOrders = (ArrayList<Order>) getArguments().getSerializable(ARG_RES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orders, container, false);
        mList = (ListView) v.findViewById(R.id.list_orders);
        mAdapter = new OrderAdapter(getActivity(), mOrders);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onOrderClicked(mOrders.get(position));
            }
        });
        return v;
    }

}
