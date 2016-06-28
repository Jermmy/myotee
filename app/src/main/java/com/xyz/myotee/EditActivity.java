package com.xyz.myotee;

import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.xyz.myotee.base.BaseFragActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 16/6/28.
 */
public class EditActivity extends BaseFragActivity implements View.OnClickListener {

    private WebView webview;
    private ScrollIndicatorView indicator;
    private ViewPager viewPager;
    private Button back, save, share;
    private MyAdapter adapter;
    private IndicatorViewPager indicatorViewPager;

    private String type[] = new String[] {
        "头发", "发色", "脸型", "肤色", "睫毛", "眼睛", "嘴巴", "鼻子", "胡子", "眼镜", "衣服",
        "帽子", "装扮", "背景", "心情", "气泡", "风格" };

    private final int selectedRes[] = new int[] {
            R.mipmap.tab_1_down, R.mipmap.tab_2_down, R.mipmap.tab_3_down, R.mipmap.tab_4_down,
            R.mipmap.tab_5_down, R.mipmap.tab_6_down, R.mipmap.tab_7_down, R.mipmap.tab_8_down,
            R.mipmap.tab_9_down, R.mipmap.tab_10_down, R.mipmap.tab_11_down, R.mipmap.tab_12_down,
            R.mipmap.tab_13_down, R.mipmap.tab_14_down, R.mipmap.tab_15_down, R.mipmap.tab_16_down,
            R.mipmap.tab_17_down
    };

    private final int unselectedRes[] = new int[] {
            R.mipmap.tab_1, R.mipmap.tab_2, R.mipmap.tab_3, R.mipmap.tab_4,
            R.mipmap.tab_5, R.mipmap.tab_6, R.mipmap.tab_7, R.mipmap.tab_8,
            R.mipmap.tab_9, R.mipmap.tab_10, R.mipmap.tab_11, R.mipmap.tab_12,
            R.mipmap.tab_13, R.mipmap.tab_14, R.mipmap.tab_15, R.mipmap.tab_16,
            R.mipmap.tab_17
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();
        initAdapter();

    }


    private void initView() {
        back = (Button) findViewById(R.id.btn_back);
        save = (Button) findViewById(R.id.btn_save);
        share = (Button) findViewById(R.id.btn_share);
        webview = (WebView) findViewById(R.id.webview);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (ScrollIndicatorView) findViewById(R.id.indicator);
        indicator.setScrollBar(new ColorBar(this, Color.BLUE, 5));
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);

//        indicator.setOnTransitionListener(new Indicator.OnTransitionListener() {
//            @Override
//            public void onTransition(View view, int position, float selectPercent) {
//                Log.i("com.xyz.myotee", "onTransition================> " + view.toString() + "  " + position);
//                //view.setPressed(true);
//            }
//        });
    }


    private void initAdapter() {
        adapter = new MyAdapter(getSupportFragmentManager());
        indicatorViewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                break;
            case R.id.btn_share:
                break;
        }
    }

    class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        LayoutInflater inflater = LayoutInflater.from(EditActivity.this);
        private StateListDrawable selectors[];
        private List<Fragment> fragments;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            initSelectors();
            initFragments();
        }

        private void initFragments() {
            fragments = new ArrayList<>();
            for (int i = 0; i < selectors.length; i++) {
                GridFragment fragment = new GridFragment();
                fragment.setText(type[i]);
                fragments.add(fragment);
            }
        }

        @Override
        public int getCount() {
            return selectors.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_indicator_tab, container, false);
            }
            Button button = (Button) convertView.findViewById(R.id.img_indicator);
            button.setCompoundDrawablesWithIntrinsicBounds(null, selectors[position], null, null);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            return fragments.get(position);
        }

        private void initSelectors() {
            selectors = new StateListDrawable[selectedRes.length];
            for (int i = 0; i < selectedRes.length; i++) {
                selectors[i] = new StateListDrawable();
                selectors[i].addState(new int[]{android.R.attr.state_selected}, getResources().getDrawable(selectedRes[i]));
                selectors[i].addState(new int[]{-android.R.attr.state_selected}, getResources().getDrawable(unselectedRes[i]));
//                selectors[i].addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(selectedRes[i]));
//                selectors[i].addState(new int[]{-android.R.attr.state_pressed}, getResources().getDrawable(unselectedRes[i]));
            }
        }

    }
}
