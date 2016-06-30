package com.xyz.myotee;

import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.xyz.myotee.base.BaseFragActivity;
import com.xyz.myotee.data.FeatureIconLoadEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 16/6/28.
 */
public class EditActivity extends BaseFragActivity implements View.OnClickListener {

    private LinearLayout loadingLayout;
    private ImageView loadingImage;
    private RelativeLayout contentLayout;
    private WebView webview;
    private ScrollIndicatorView indicator;
    private ViewPager viewPager;
    private Button back, save, share;
    private MyAdapter adapter;
    private IndicatorViewPager indicatorViewPager;

    private FeatureIconLoadEngine loadEngine;

//    private String type[] = new String[] {
//        "头发", "发色", "脸型", "肤色", "睫毛", "眼睛", "嘴巴", "鼻子", "胡子", "眼镜", "衣服",
//        "帽子", "装扮", "背景", "心情", "气泡", "风格" };
//
//    private final int selectedRes[] = new int[] {
//            R.mipmap.tab_1_down, R.mipmap.tab_2_down, R.mipmap.tab_3_down, R.mipmap.tab_4_down,
//            R.mipmap.tab_5_down, R.mipmap.tab_6_down, R.mipmap.tab_7_down, R.mipmap.tab_8_down,
//            R.mipmap.tab_9_down, R.mipmap.tab_10_down, R.mipmap.tab_11_down, R.mipmap.tab_12_down,
//            R.mipmap.tab_13_down, R.mipmap.tab_14_down, R.mipmap.tab_15_down, R.mipmap.tab_16_down,
//            R.mipmap.tab_17_down
//    };
//
//    private final int unselectedRes[] = new int[] {
//            R.mipmap.tab_1, R.mipmap.tab_2, R.mipmap.tab_3, R.mipmap.tab_4,
//            R.mipmap.tab_5, R.mipmap.tab_6, R.mipmap.tab_7, R.mipmap.tab_8,
//            R.mipmap.tab_9, R.mipmap.tab_10, R.mipmap.tab_11, R.mipmap.tab_12,
//            R.mipmap.tab_13, R.mipmap.tab_14, R.mipmap.tab_15, R.mipmap.tab_16,
//            R.mipmap.tab_17
//    };

    private String type[] = new String[]{
            "头发", "脸型", "眼睛"};

    private final int selectedRes[] = new int[]{
            R.mipmap.tab_1_down, R.mipmap.tab_3_down, R.mipmap.tab_6_down
    };

    private final int unselectedRes[] = new int[]{
            R.mipmap.tab_1, R.mipmap.tab_3, R.mipmap.tab_6
    };

    private final int typeSize = selectedRes.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        loadingData();
        initView();
        initAdapter();
        initWebView();
    }

    private void loadingData() {
        loadEngine = FeatureIconLoadEngine.getInstance(getApplicationContext());
        loadingLayout = (LinearLayout) findViewById(R.id.ll_loading);
        loadingImage = (ImageView) findViewById(R.id.img_loading);

        if (!loadEngine.isInitiated()) {
            loadEngine.initFeatureIcons();
        }
        //loadingLayout.setVisibility(View.GONE);
    }


    private void initView() {
        contentLayout = (RelativeLayout) findViewById(R.id.rl_content);
        back = (Button) findViewById(R.id.btn_back);
        save = (Button) findViewById(R.id.btn_save);
        share = (Button) findViewById(R.id.btn_share);
        webview = (WebView) findViewById(R.id.webview);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (ScrollIndicatorView) findViewById(R.id.indicator);
        indicator.setScrollBar(new ColorBar(this, Color.BLUE, 5));

        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setPageOffscreenLimit(typeSize);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);

        //contentLayout.setVisibility(View.VISIBLE);
    }


    private void initAdapter() {
        adapter = new MyAdapter(getSupportFragmentManager());
        indicatorViewPager.setAdapter(adapter);
    }

    private void initWebView() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                editHead(0, 0);
                Log.i("EditActivity", "onPageFinished============");
                loadingLayout.setVisibility(View.GONE);
                contentLayout.setVisibility(View.VISIBLE);
            }
        });
        webview.addJavascriptInterface(new JsInterface(), "control");
//        webview.loadUrl("file:///android_asset/edit.html");
    }

    @Override
    protected void onResume() {
        super.onResume();
        webview.loadUrl("file:///android_asset/edit.html");
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

    private void editHead(int position, int featureType) {
        //webview.loadUrl("javascript:test()");
        webview.loadUrl("javascript:testMethod(\"" + "xuyongzhe" + "\")");
        //webview.loadUrl("javascript:testMethod()");

    }

    class JsInterface {
        @JavascriptInterface
        public void toastMessage(String msg) {
            Toast.makeText(EditActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
//        @JavascriptInterface
//        public void toastMessage() {
//            Toast.makeText(EditActivity.this, "toast", Toast.LENGTH_SHORT).show();
//        }
    }

    class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        private LayoutInflater inflater = LayoutInflater.from(EditActivity.this);
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
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                fragment.setArguments(bundle);
                fragment.setOnItemSelectCallBack(new GridFragment.OnItemSelectCallBack() {
                    @Override
                    public void onItemSelected(View view, int position, long id, int featureType) {

                    }
                });
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
                convertView = (ImageButton) inflater.inflate(R.layout.item_indicator_tab, container, false);
            }
            // ((Button)convertView).setCompoundDrawablesWithIntrinsicBounds(null, selectors[position], null, null);
            //((Button)convertView).setCompoundDrawablesWithIntrinsicBounds(0, selectors[position], 0, 0);
            //((ImageButton)convertView).setBackground(selectors[position]);
            ((ImageButton) convertView).setImageDrawable(selectors[position]);

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
            }
        }

    }
}
