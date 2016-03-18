package com.vita.ishow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

public class GenderActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private FrameLayout maleFL, femaleFL;
    private CheckBox maleCB, femaleCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        initView();

    }

    private void initView()
    {
        maleFL = (FrameLayout) findViewById(R.id.male_fl);
        femaleFL = (FrameLayout) findViewById(R.id.female_fl);
        maleCB = (CheckBox) findViewById(R.id.male_cb);
        femaleCB = (CheckBox) findViewById(R.id.female_cb);

        maleFL.setOnClickListener(this);
        femaleFL.setOnClickListener(this);
        maleCB.setOnCheckedChangeListener(this);
        femaleCB.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.male_fl:
                maleCB.setChecked(true);
                femaleCB.setChecked(false);
                break;
            case R.id.female_fl:
                maleCB.setChecked(false);
                femaleCB.setChecked(true);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            String gender = "male";
            SharedPreferences sp = this.getSharedPreferences("user_gender", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            switch (buttonView.getId())
            {
                case R.id.male_cb:
                    gender = "male";
                    break;
                case R.id.female_cb:
                    gender = "female";
                    break;
            }
            editor.putString("gender", gender);
            editor.commit();

            Intent intent = new Intent(GenderActivity.this, MainActivity.class);
            intent.putExtra("gender", gender);
            startActivity(intent);
            finish();
        }
    }
}
