package com.rockets12.lunchcast.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.adapter.TagAdapter;
import com.rockets12.lunchcast.backendless.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubscriptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscriptionsFragment extends Fragment {
    private static final String ARG_PARAM1 = "tags";
    private static final String ARG_PARAM2 = "userTags";

    // TODO: Rename and change types of parameters
    private List<Tag> mTags;
    private List<Tag> mUserTags;

    private ListView mList;
    private TagAdapter mAdapter;

    public SubscriptionsFragment() {
        // Required empty public constructor
    }

    public static SubscriptionsFragment newInstance(ArrayList<Tag> tags, ArrayList<Tag> userTags) {
        SubscriptionsFragment fragment = new SubscriptionsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, tags);
        args.putSerializable(ARG_PARAM2, userTags);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTags = (ArrayList<Tag>) getArguments().getSerializable(ARG_PARAM1);
            mUserTags = (ArrayList<Tag>) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_subscriptions, container, false);
        mList = (ListView) v.findViewById(R.id.list_tags);
        mAdapter = new TagAdapter(getActivity(), mTags, mUserTags);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tag clickedTag = mTags.get(position);
                if (mUserTags.contains(clickedTag)) {
                    // remove tag from user list
                    mUserTags.remove(clickedTag);
                } else {
                    // add tag to user list
                    mUserTags.add(clickedTag);
                }
                mAdapter.setUserTags(mUserTags);
                //TODO inform activity about new tag.
            }
        });
        return v;
    }

}
