package com.cube.memorygames.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.memory.brain.training.games.R;
import com.viewpagerindicator.CirclePageIndicator;

public class OnlineTutorialActivity extends AppCompatActivity {
    public static final String PREF_ONLINE_SHOWED = "prefOnlineShowed";
    OnlineTutorialAdapter adapter;
    @Bind({2131624121})
    ImageView btnBack;
    private String fontPath = "Roboto-Regular.ttf";
    private String fontPathLight = "Roboto-Light.ttf";
    @Bind({2131624079})
    CirclePageIndicator indicator;
    @Bind({2131624123})
    TextView textBotton;
    @Bind({2131624148})
    TextView textPlayButton;
    private Typeface typeface;
    private Typeface typefaceLight;
    @Bind({2131624122})
    ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_start_game);
        ButterKnife.bind((Activity) this);
        this.textPlayButton.setText(R.string.next);
        setUI();
        showTutorial();
    }

    private void showTutorial() {
        this.viewPager.setPadding(0, 0, 0, 0);
        this.textBotton.setVisibility(0);
        this.adapter = new OnlineTutorialAdapter(this.typefaceLight);
        this.textBotton.setText(this.adapter.getTitle(0));
        this.viewPager.setAdapter(this.adapter);
        this.indicator.setViewPager(this.viewPager);
        this.indicator.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                if (position >= OnlineTutorialActivity.this.adapter.getCount() - 1) {
                    OnlineTutorialActivity.this.textPlayButton.setText(R.string.play);
                } else {
                    OnlineTutorialActivity.this.textPlayButton.setText(R.string.next);
                }
                OnlineTutorialActivity.this.textBotton.setText(OnlineTutorialActivity.this.adapter.getTitle(position));
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change, R.anim.slide_to_right);
    }

    @OnClick({2131624147})
    protected void onPlayClick() {
        if (this.viewPager.getCurrentItem() >= this.adapter.getCount() - 1) {
            startActivity(new Intent(this, PlayOnlineActivity.class));
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(PREF_ONLINE_SHOWED, true).apply();
            finish();
            return;
        }
        this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() + 1, true);
    }

    private void setUI() {
        this.btnBack.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                OnlineTutorialActivity.this.onBackPressed();
            }
        });
        this.typeface = Typeface.createFromAsset(getAssets(), this.fontPath);
        this.typefaceLight = Typeface.createFromAsset(getAssets(), this.fontPathLight);
        this.textBotton.setTypeface(this.typeface, 2);
        this.textPlayButton.setTypeface(this.typeface);
    }
}
