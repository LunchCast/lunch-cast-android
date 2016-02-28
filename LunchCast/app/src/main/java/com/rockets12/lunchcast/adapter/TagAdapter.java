package com.rockets12.lunchcast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rockets12.lunchcast.R;
import com.rockets12.lunchcast.backendless.Tag;

import java.util.List;

/**
 * Created by Stevan Kovacevic on 2/28/16.
 */
public class TagAdapter extends BaseAdapter {

    private List<Tag> mTags;
    private List<Tag> mUserTags;
    private LayoutInflater mInflater;
    private Context mContext;

    public TagAdapter(Context context, List<Tag> tags, List<Tag> userTags) {
        mTags = tags;
        mUserTags = userTags;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mTags.size();
    }

    @Override
    public Object getItem(int position) {
        return mTags.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TagViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tag, null);
            holder = new TagViewHolder();
            holder.tag = (TextView) convertView.findViewWithTag(R.id.text_tag);
            convertView.setTag(holder);
        } else {
            holder = (TagViewHolder) convertView.getTag();
        }

        Tag tag = mTags.get(position);
        holder.tag.setText(tag.getName());
        if (mUserTags.contains(tag)) {
            convertView.setBackgroundResource(R.color.colorTagBackgroundOn);
        } else {
            convertView.setBackgroundResource(R.color.colorTagBackgroundOff);
        }

        return convertView;
    }

    public void setUserTags(List<Tag> userTags) {
        mUserTags = userTags;
        notifyDataSetChanged();
    }

    class TagViewHolder {
        TextView tag;
    }
}
