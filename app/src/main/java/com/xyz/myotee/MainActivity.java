package com.xyz.myotee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.xyz.myotee.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, ToggleButton.OnCheckedChangeListener {

    private ToggleButton sound;
    private Button share, man, woman;
    private ImageView feedback, joinus, team;
    private LinearLayout doubleMode, history, newProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
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
}
