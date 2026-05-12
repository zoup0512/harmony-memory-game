package com.cube.memorygames.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.memory.brain.training.games.R;

public class NoInternetActivity extends AppCompatActivity {
    @Bind({2131624120})
    TextView buttonSettings;
    @Bind({2131624118})
    TextView info;
    @Bind({2131624117})
    TextView ops;
    @Bind({2131624032})
    TextView title;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_no_internet);
        ButterKnife.bind((Activity) this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        this.title.setTypeface(typeface);
        this.ops.setTypeface(typeface);
        this.info.setTypeface(typeface);
        this.buttonSettings.setTypeface(typeface);
    }

    @OnClick({2131624115})
    void backButtonClick() {
        onBackPressed();
    }

    @OnClick({2131624120})
    void buttonSettingsClick() {
        startActivity(new Intent("android.settings.SETTINGS"));
        finish();
    }
}
