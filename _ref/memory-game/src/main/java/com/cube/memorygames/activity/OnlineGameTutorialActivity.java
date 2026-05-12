package com.cube.memorygames.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cube.memorygames.Games;
import com.cube.memorygames.MainMenuActivity;
import com.cube.memorygames.model.GameInfo;
import com.cube.memorygames.ui.CustomSwipeAdapter;
import com.memory.brain.training.games.R;
import com.viewpagerindicator.CirclePageIndicator;

public class OnlineGameTutorialActivity extends AppCompatActivity {
    private static final int SLIDE_TIME = 2000;
    PagerAdapter adapter;
    @Bind({2131624121})
    ImageView btnBack;
    private String fontPath = "Roboto-Regular.ttf";
    private GameInfo gameInfo;
    @Bind({2131624079})
    CirclePageIndicator indicator;
    private Handler mHandler;
    Runnable mStatusChecker = new Runnable() {
        public void run() {
            try {
                if (OnlineGameTutorialActivity.this.viewPager.getCurrentItem() >= OnlineGameTutorialActivity.this.adapter.getCount() - 1) {
                    OnlineGameTutorialActivity.this.setResult(-1);
                    OnlineGameTutorialActivity.this.finish();
                } else {
                    OnlineGameTutorialActivity.this.viewPager.setCurrentItem(OnlineGameTutorialActivity.this.viewPager.getCurrentItem() + 1, true);
                }
                OnlineGameTutorialActivity.this.mHandler.postDelayed(OnlineGameTutorialActivity.this.mStatusChecker, 2000);
            } catch (Throwable th) {
                OnlineGameTutorialActivity.this.mHandler.postDelayed(OnlineGameTutorialActivity.this.mStatusChecker, 2000);
            }
        }
    };
    @Bind({2131624123})
    TextView textBotton;
    @Bind({2131624032})
    TextView title;
    private Typeface typeface;
    @Bind({2131624122})
    ViewPager viewPager;

    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    void startRepeatingTask() {
        this.mHandler.postDelayed(this.mStatusChecker, 2000);
    }

    void stopRepeatingTask() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mStatusChecker);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_online_game_tutorial);
        ButterKnife.bind((Activity) this);
        this.gameInfo = (GameInfo) getIntent().getParcelableExtra(MainMenuActivity.EXTRA_GAME_INFO);
        this.typeface = Typeface.createFromAsset(getAssets(), this.fontPath);
        this.textBotton.setTypeface(this.typeface, 2);
        this.title.setTypeface(this.typeface);
        showTutorial();
        setResult(-1);
    }

    private void showTutorial() {
        this.title.setText(getString(R.string.get_ready) + " " + getString(this.gameInfo.getGameNameRes()));
        this.viewPager.setPadding(0, 0, 0, 0);
        this.textBotton.setVisibility(0);
        this.adapter = new CustomSwipeAdapter(this.gameInfo);
        this.viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                OnlineGameTutorialActivity.this.textBotton.setText(((Integer) Games.get().getTutorialStrings(OnlineGameTutorialActivity.this.gameInfo).get(position)).intValue());
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
        this.textBotton.setText(((Integer) Games.get().getTutorialStrings(this.gameInfo).get(0)).intValue());
        this.viewPager.setAdapter(this.adapter);
        this.indicator.setViewPager(this.viewPager);
        this.mHandler = new Handler();
        startRepeatingTask();
    }

    public void onBackPressed() {
        setResult(-1);
        finish();
    }

    @OnClick({2131624124})
    void skipClick() {
        onBackPressed();
    }

    @OnClick({2131624121})
    void backClick() {
        onBackPressed();
    }
}
