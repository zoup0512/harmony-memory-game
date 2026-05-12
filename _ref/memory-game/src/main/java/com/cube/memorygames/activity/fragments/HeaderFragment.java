package com.cube.memorygames.activity.fragments;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.NotificationManager;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.pushes.model.OnlineMatchUser;
import com.cube.memorygames.ui.OnlineAdapter;
import com.github.florent37.viewanimator.AnimationListener.Stop;
import com.github.florent37.viewanimator.ViewAnimator;
import com.memory.brain.training.games.R;
import com.mopub.volley.DefaultRetryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HeaderFragment extends Fragment implements Target {
    private static final int REFRESH_INTERVAL = 400;
    private Handler animationHandler;
    private Runnable animationRunnable;
    private MemoryApplicationModel application;
    @Bind({2131624339})
    View bolt;
    @Bind({2131624336, 2131624337, 2131624338})
    List<View> circles;
    private int position;
    private File profileFile;
    @Bind({2131624333})
    ImageView profilePicture1;
    @Bind({2131624340})
    ImageView profilePicture2;
    private Target profilePicture2Target;
    @Bind({2131624335})
    TextView rating1;
    @Bind({2131624342})
    TextView rating2;
    @Bind({2131624334})
    View ratingContainer1;
    @Bind({2131624341})
    View ratingContainer2;
    @Bind({2131624343})
    TextView userName1;
    @Bind({2131624344})
    TextView userName2;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = MemoryApplicationModel.getInstance();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        ButterKnife.bind((Object) this, view);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
        setDefaultUI();
        animateSearch();
    }

    public void onDestroy() {
        if (this.animationHandler != null) {
            this.animationHandler.removeCallbacks(this.animationRunnable);
        }
        super.onDestroy();
    }

    public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
        this.profilePicture1.setImageBitmap(bitmap);
        try {
            bitmap.compress(CompressFormat.PNG, 100, new FileOutputStream(this.profileFile));
        } catch (IOException e) {
        }
    }

    public void onBitmapFailed(Drawable errorDrawable) {
    }

    public void onPrepareLoad(Drawable placeHolderDrawable) {
    }

    private void init() {
        this.profileFile = new File(getContext().getFilesDir(), OnlineAdapter.PROFILE_PHOTO_NAME);
        this.animationHandler = new Handler();
        this.animationRunnable = new Runnable() {
            public void run() {
                NotificationManager.getInstance().showCancelNotifications(HeaderFragment.this.application.getApplicationContext());
                int i = 0;
                while (i < HeaderFragment.this.circles.size()) {
                    final View circle = (View) HeaderFragment.this.circles.get(i);
                    if (i >= HeaderFragment.this.position || i <= HeaderFragment.this.position - 4) {
                        if (circle.getVisibility() == 0) {
                            ViewAnimator.animate(circle).scale(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f).duration(200).onStop(new Stop() {
                                public void onStop() {
                                    circle.setVisibility(4);
                                }
                            }).start();
                        }
                    } else if (circle.getVisibility() == 4) {
                        circle.setVisibility(0);
                        ViewAnimator.animate(circle).scale(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).duration(200).start();
                    }
                    i++;
                }
                HeaderFragment.this.position = HeaderFragment.this.position + 1;
                if (HeaderFragment.this.position >= 6) {
                    HeaderFragment.this.position = 0;
                }
                HeaderFragment.this.animationHandler.postDelayed(HeaderFragment.this.animationRunnable, 400);
            }
        };
    }

    private void setDefaultUI() {
        LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
        this.userName1.setText(localUser.displayName);
        this.rating1.setText(String.valueOf(localUser.onlineRank));
        this.ratingContainer1.setVisibility(0);
        if (TextUtils.isEmpty(localUser.photoUrl)) {
            this.profilePicture1.setImageResource(R.drawable.ic_user_placeholder);
            return;
        }
        int size = getResources().getDimensionPixelSize(R.dimen.avatar_size);
        Picasso.with(getContext()).load(this.profileFile).noFade().into(this.profilePicture1);
        Picasso.with(getContext()).load(localUser.photoUrl).resize(size, size).centerCrop().noFade().into((Target) this);
    }

    private void animateSearch() {
        this.position = 0;
        this.animationHandler.removeCallbacks(this.animationRunnable);
        this.animationHandler.post(this.animationRunnable);
    }

    public void setMatchFound(OnlineMatchUser myOnlineMatchUser, OnlineMatchUser onlineMatchUser) {
        this.animationHandler.removeCallbacks(this.animationRunnable);
        this.rating1.setText(String.valueOf((int) myOnlineMatchUser.getOnlineRating()));
        this.rating2.setText(String.valueOf((int) onlineMatchUser.getOnlineRating()));
        this.userName2.setText(onlineMatchUser.getDisplayName());
        this.ratingContainer1.setVisibility(0);
        this.ratingContainer2.setVisibility(0);
        this.bolt.setVisibility(0);
        ViewAnimator.animate(this.bolt).alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).duration(500).start();
        int size = getResources().getDimensionPixelSize(R.dimen.avatar_size);
        this.profilePicture2Target = new Target() {
            public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
                HeaderFragment.this.profilePicture2.setImageBitmap(bitmap);
            }

            public void onBitmapFailed(Drawable errorDrawable) {
            }

            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso.with(getContext()).load(onlineMatchUser.getPhotoUrl()).resize(size, size).centerCrop().noFade().into(this.profilePicture2Target);
        for (View view : this.circles) {
            view.setVisibility(4);
        }
    }

    public void updateMyRating(int newRank) {
        this.rating1.setText(String.valueOf(newRank));
    }
}
