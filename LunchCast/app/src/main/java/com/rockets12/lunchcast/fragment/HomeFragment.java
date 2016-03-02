package com.rockets12.lunchcast.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.rockets12.lunchcast.CallbackInterface;
import com.rockets12.lunchcast.R;


public class HomeFragment extends Fragment {

    private CallbackInterface mCallback;

    private ImageButton mButtonOrders, mButtonCreate, mButtonSubscriptions;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mButtonOrders = (ImageButton)view.findViewById(R.id.button_home_orders);
        mButtonOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.displayOrders();
            }
        });
        mButtonCreate = (ImageButton)view.findViewById(R.id.button_home_create);
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.createNewOrder();
            }
        });
        mButtonSubscriptions = (ImageButton)view.findViewById(R.id.button_home_subscriptions);
        mButtonSubscriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.displaySubscriptions();
            }
        });
        return view;
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

}
