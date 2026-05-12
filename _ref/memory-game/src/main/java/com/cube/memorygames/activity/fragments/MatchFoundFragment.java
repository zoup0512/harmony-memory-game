package com.cube.memorygames.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.pushes.model.OnlineMatchUser;
import com.github.florent37.viewanimator.ViewAnimator;
import com.memory.brain.training.games.R;

public class MatchFoundFragment extends Fragment {
    private static final String EXTRA_MATCH_USER = "extra_match_user";
    private static final String EXTRA_MY_MATCH_USER = "extra_my_match_user";
    private MemoryApplicationModel application;
    @Bind({2131624371})
    View betsContainer;
    @Bind({2131624370})
    View betsTitle;
    @Bind({2131624363})
    TextView draws1;
    @Bind({2131624365})
    TextView draws2;
    @Bind({2131624362})
    View drawsContainer;
    @Bind({2131624358})
    View losesContainer;
    @Bind({2131624359})
    TextView losses1;
    @Bind({2131624361})
    TextView losses2;
    private OnlineMatchUser myOnlineMatchUser;
    private OnlineMatchUser onlineMatchUser;
    @Bind({2131624367})
    TextView ratio1;
    @Bind({2131624369})
    TextView ratio2;
    @Bind({2131624366})
    View ratioContainer;
    @Bind({2131624353})
    View statsTitle;
    @Bind({2131624355})
    TextView wins1;
    @Bind({2131624357})
    TextView wins2;
    @Bind({2131624354})
    View winsContainer;

    public static MatchFoundFragment getFragment(OnlineMatchUser myOnlineMatchUser, OnlineMatchUser onlineMatchUser) {
        MatchFoundFragment matchFoundFragment = new MatchFoundFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MY_MATCH_USER, myOnlineMatchUser);
        bundle.putParcelable(EXTRA_MATCH_USER, onlineMatchUser);
        matchFoundFragment.setArguments(bundle);
        return matchFoundFragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = MemoryApplicationModel.getInstance();
        this.myOnlineMatchUser = (OnlineMatchUser) getArguments().getParcelable(EXTRA_MY_MATCH_USER);
        this.onlineMatchUser = (OnlineMatchUser) getArguments().getParcelable(EXTRA_MATCH_USER);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online_stats, container, false);
        ButterKnife.bind((Object) this, view);
        Log.e(getClass().getSimpleName(), "onCreateView");
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.header_fragment);
        if (fragment instanceof HeaderFragment) {
            ((HeaderFragment) fragment).setMatchFound(this.myOnlineMatchUser, this.onlineMatchUser);
            initStats();
        }
    }

    private void initStats() {
        int ratioPlayer1;
        float totalGamesPlayer1 = (float) ((this.myOnlineMatchUser.getOnlineGamesWon() + this.myOnlineMatchUser.getOnlineGamesLost()) + this.myOnlineMatchUser.getOnlineGamesDraw());
        if (totalGamesPlayer1 == 0.0f) {
            ratioPlayer1 = 0;
        } else {
            ratioPlayer1 = Math.round((((float) this.myOnlineMatchUser.getOnlineGamesWon()) / totalGamesPlayer1) * 100.0f);
        }
        this.wins1.setText(String.valueOf(this.myOnlineMatchUser.getOnlineGamesWon()));
        this.losses1.setText(String.valueOf(this.myOnlineMatchUser.getOnlineGamesLost()));
        this.draws1.setText(String.valueOf(this.myOnlineMatchUser.getOnlineGamesDraw()));
        this.ratio1.setText(String.valueOf(ratioPlayer1) + "%");
        LocalUser localUser = this.application.getLocalDataManager().getLocalUser();
        localUser.onlineRank = (int) this.myOnlineMatchUser.getOnlineRating();
        localUser.save();
        int totalGamesPlayer2 = (this.onlineMatchUser.getOnlineGamesWon() + this.onlineMatchUser.getOnlineGamesLost()) + this.onlineMatchUser.getOnlineGamesDraw();
        int ratioPlayer2 = totalGamesPlayer2 == 0 ? 0 : Math.round((((float) this.onlineMatchUser.getOnlineGamesWon()) / ((float) totalGamesPlayer2)) * 100.0f);
        this.wins2.setText(String.valueOf(this.onlineMatchUser.getOnlineGamesWon()));
        this.losses2.setText(String.valueOf(this.onlineMatchUser.getOnlineGamesLost()));
        this.draws2.setText(String.valueOf(this.onlineMatchUser.getOnlineGamesDraw()));
        this.ratio2.setText(String.valueOf(ratioPlayer2) + "%");
        this.statsTitle.setAlpha(0.0f);
        this.winsContainer.setAlpha(0.0f);
        this.losesContainer.setAlpha(0.0f);
        this.drawsContainer.setAlpha(0.0f);
        this.ratioContainer.setAlpha(0.0f);
        this.betsContainer.setAlpha(0.0f);
        this.betsTitle.setAlpha(0.0f);
        this.statsTitle.setVisibility(0);
        this.winsContainer.setVisibility(0);
        this.losesContainer.setVisibility(0);
        this.drawsContainer.setVisibility(0);
        this.ratioContainer.setVisibility(0);
        this.betsContainer.setVisibility(0);
        this.betsTitle.setVisibility(0);
        ViewAnimator.animate(this.statsTitle).fadeIn().duration((long) 100).thenAnimate(this.winsContainer).fadeIn().duration((long) 100).thenAnimate(this.losesContainer).fadeIn().duration((long) 100).thenAnimate(this.drawsContainer).fadeIn().duration((long) 100).thenAnimate(this.ratioContainer).fadeIn().duration((long) 100).thenAnimate(this.betsTitle).fadeIn().duration((long) 100).thenAnimate(this.betsContainer).fadeIn().duration((long) 100).start();
    }
}
