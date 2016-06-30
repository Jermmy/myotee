package com.xyz.myotee;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.xyz.myotee.data.FeatureIconLoadEngine;

import java.util.ArrayList;

/**
 * Created by xyz on 16/6/28.
 */
public class GridFragment extends Fragment {

    private GridView gridView;
    private GridAdapter adapter;

    private OnItemSelectCallBack onItemSelectCallBack;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.item_fragment_viewpager, container, false);
        initView(layout);
        initData();
        return layout;
    }

    private void initView(View layout) {
        gridView = (GridView) layout.findViewById(R.id.gridview);
    }

    private void initData() {
        Bundle bundle = getArguments();
        final int index = bundle.getInt("index");
        FeatureIconLoadEngine loadEngine = FeatureIconLoadEngine.getInstance(getActivity().getApplicationContext());
        ArrayList<Integer> icons = loadEngine.getFeatureIcons().get(index);
        adapter = new GridAdapter(getActivity(), icons);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("GridFragment", "onItemClick=====>" + position);
                adapter.setSelected(position);
                if (onItemSelectCallBack != null) {
                    onItemSelectCallBack.onItemSelected(view, position, id, index);
                }
            }
        });
    }

    public void setOnItemSelectCallBack(OnItemSelectCallBack callBack) {
        this.onItemSelectCallBack = callBack;
    }

    public interface OnItemSelectCallBack {
        void onItemSelected(View view, int position, long id, int featureType);
    }



    class GridAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<Integer> icons;
        LayoutInflater inflater;
        boolean isSelected[];

        GridAdapter(Context context, ArrayList<Integer> icons) {
            this.mContext = context;
            this.icons = icons;
            this.inflater = LayoutInflater.from(mContext);
            this.isSelected = new boolean[icons.size()];
            for (int i = 0; i < icons.size(); i++) {
                isSelected[i] = false;
            }
            this.isSelected[0] = true;
        }

        @Override
        public int getCount() {
            return icons.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_gridview_edit, null, false);
                ImageView image = (ImageView) convertView.findViewById(R.id.image);
                image.setImageResource(icons.get(position));
                image.setBackgroundResource(R.drawable.shape_item_gridview_border);
            }
            if (isSelected[position]) {
                convertView.setBackgroundResource(R.drawable.shape_item_gridview_edit);
            } else {
                convertView.setBackgroundResource(R.color.color_homepage);
            }
            return convertView;
        }

        public void setSelected(int position) {
            for (int i = 0; i < isSelected.length; i++) {
                isSelected[i] = false;
            }
            this.isSelected[position] = true;
            notifyDataSetChanged();
        }

    }



}
