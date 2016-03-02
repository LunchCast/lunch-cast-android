package com.rockets12.lunchcast.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.backendless.BackendlessUser;
import com.rockets12.lunchcast.CallbackInterface;
import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.adapter.MealAdapter;
import com.rockets12.lunchcast.backendless.Meal;
import com.rockets12.lunchcast.backendless.Order;
import com.rockets12.lunchcast.backendless.Restaurant;
import com.rockets12.lunchcast.backendless.Tag;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealsFragment extends Fragment {

    private static final String ARG_USER = "user";
    private static final String ARG_ORDER = "order";
    private static final String ARG_RESTAURANT = "res";

    private CallbackInterface mCallback;
    private List<Meal> mMeals;
    private Order mOrder;
    private Restaurant mRestaurant;

    private TextView mTitle, mDescription, mEta, mMinAmount, mTags;
    private ListView mMealList;
    private Button mButtonCreate;
    private TextView mAmount;
    private MealAdapter mAdapter;

    public MealsFragment() {
        // Required empty public constructor
    }

    public static MealsFragment newInstance(Order
                                                    order, Restaurant res) {
        MealsFragment fragment = new MealsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, order);
        args.putSerializable(ARG_RESTAURANT, res);
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
            mOrder = (Order) getArguments().getSerializable(ARG_ORDER);
            mRestaurant = (Restaurant) getArguments().getSerializable(ARG_RESTAURANT);
            mMeals = mRestaurant.getMeals();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meals, container, false);
        mTitle = (TextView) v.findViewById(R.id.text_restaurant_title);
        mTitle.setText(mRestaurant.getName());
        mDescription = (TextView) v.findViewById(R.id.text_restaurant_desc);
        mEta = (TextView) v.findViewById(R.id.text_restaurant_eta);
        mEta.setText("Average delivery time: " + mRestaurant.getEta() + " min");
        mMinAmount = (TextView) v.findViewById(R.id.text_restaurant_min);
        mMinAmount.setText("Minimum for order: " + mRestaurant.getMinAmount() + " din");
        mTags = (TextView) v.findViewById(R.id.text_restaurant_tags);
        StringBuffer sb = new StringBuffer();
        for (Tag tag : mRestaurant.getTags()) {
            sb.append("#" + tag.getName() + " ");
        }
        mTags.setText(sb.toString());
        mMealList = (ListView) v.findViewById(R.id.list_meals);
        mButtonCreate = (Button) v.findViewById(R.id.button_create_order);
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.createOrder(mRestaurant, mAdapter.getmOrderItems());
            }
        });
        mAmount = (TextView) v.findViewById(R.id.text_meal_amount);
        mAdapter = new MealAdapter(getActivity(), mMeals, null, mOrder);
        mMealList.setAdapter(mAdapter);
        return v;
    }

}
