package com.xyz.myotee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.xyz.myotee.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, ToggleButton.OnCheckedChangeListener {

    private ToggleButton sound;
    private Button share, man, woman;
    private ImageView feedback, joinus, team;
    private LinearLayout doubleMode, history, newProduct;
    private DialogPlus dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDialog();
    }

    private void initView() {
        sound = (ToggleButton) findViewById(R.id.togbtn_sound);
        share = (Button) findViewById(R.id.btn_share);
        man = (Button) findViewById(R.id.btn_man);
        woman = (Button) findViewById(R.id.btn_woman);
        feedback = (ImageView) findViewById(R.id.btn_feedback);
        doubleMode = (LinearLayout) findViewById(R.id.ll_double_mode);
        history = (LinearLayout) findViewById(R.id.ll_history);
        newProduct = (LinearLayout) findViewById(R.id.ll_new_product);
        joinus = (ImageView) findViewById(R.id.img_joinus);
        team = (ImageView) findViewById(R.id.img_team);

        sound.setOnCheckedChangeListener(this);
        share.setOnClickListener(this);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
        feedback.setOnClickListener(this);
        doubleMode.setOnClickListener(this);
        history.setOnClickListener(this);
        newProduct.setOnClickListener(this);
        joinus.setOnClickListener(this);
        team.setOnClickListener(this);
    }

    private void initDialog() {
        GridAdapter adapter = new GridAdapter(this);
        dialog = DialogPlus.newDialog(this).setAdapter(adapter).
                setGravity(Gravity.CENTER).setCancelable(true).setContentBackgroundResource(R.drawable.shape_dialog).
                setHeader(R.layout.item_headview_dialog).setMargin(150, 100, 150, 100).
                setContentHolder(new GridHolder(2)).create();
        dialog.getHeaderView().findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                dialog.show();
                break;
            case R.id.btn_man:
                startActivity(new Intent(this, EditActivity.class));
                break;
            case R.id.btn_woman:
                break;
            case R.id.btn_feedback:
                break;
            case R.id.ll_double_mode:
                break;
            case R.id.ll_history:
                break;
            case R.id.ll_new_product:
                break;
            case R.id.img_joinus:
                break;
            case R.id.img_team:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.togbtn_sound:
                break;
        }
    }

    class GridAdapter extends BaseAdapter {

        int imgId[] = new int[]{
                R.drawable.selector_btn_qq_share, R.drawable.selector_btn_qzone_share,
                R.drawable.selector_btn_weixin_share, R.drawable.selector_btn_circle_share
        };

        String shareString[] = new String[]{
                "QQ好友", "QQ空间", "微信", "朋友圈"
        };

        LayoutInflater inflater;

        GridAdapter(Context mContext) {
            this.inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return shareString.length;
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
            convertView = inflater.inflate(R.layout.item_dialog_share, parent, false);
            ImageView image = (ImageView) convertView.findViewById(R.id.img_share_logo);
            TextView name = (TextView) convertView.findViewById(R.id.tv_share);
            image.setBackgroundResource(imgId[position]);
            name.setText(shareString[position]);
            return convertView;
        }
    }
}
