package com.cube.memorygames.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.cube.memorygames.activity.OnlineGameListAdapter;
import com.cube.memorygames.pushes.model.OnlineMatchUser;
import com.memory.brain.training.games.R;
import java.util.ArrayList;
import java.util.List;

public class GameResultsFragment extends Fragment {
    private static final String EXTRA_CURRENT_GAMES = "extra_current_games";
    private static final String EXTRA_MATCH_USER = "extra_match_user";
    private static final String EXTRA_MY_MATCH_USER = "extra_my_match_user";
    private List<String> currentGamesList;
    private OnlineGameListAdapter gameListAdapter;
    private OnlineMatchUser myOnlineMatchUser;
    private OnlineMatchUser onlineMatchUser;
    private List<Integer> player1Results;
    private List<Integer> player2Results;
    @Bind({2131624112})
    RecyclerView recyclerView;

    public static GameResultsFragment getFragment(List<String> currentGamesList, OnlineMatchUser myOnlineMatchUser, OnlineMatchUser onlineMatchUser) {
        GameResultsFragment gameListFragment = new GameResultsFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(EXTRA_CURRENT_GAMES, new ArrayList(currentGamesList));
        bundle.putParcelable(EXTRA_MY_MATCH_USER, myOnlineMatchUser);
        bundle.putParcelable(EXTRA_MATCH_USER, onlineMatchUser);
        gameListFragment.setArguments(bundle);
        return gameListFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_results, container, false);
        ButterKnife.bind((Object) this, view);
        this.currentGamesList = getArguments().getStringArrayList(EXTRA_CURRENT_GAMES);
        this.myOnlineMatchUser = (OnlineMatchUser) getArguments().getParcelable(EXTRA_MY_MATCH_USER);
        this.onlineMatchUser = (OnlineMatchUser) getArguments().getParcelable(EXTRA_MATCH_USER);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initHeader();
        initGamesList();
    }

    private void initHeader() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.header_fragment);
        if (fragment instanceof HeaderFragment) {
            ((HeaderFragment) fragment).setMatchFound(this.myOnlineMatchUser, this.onlineMatchUser);
        }
    }

    private void initGamesList() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.gameListAdapter = new OnlineGameListAdapter(this.currentGamesList);
        this.recyclerView.setAdapter(this.gameListAdapter);
        this.recyclerView.setVisibility(0);
        this.gameListAdapter.setPlayer1Results(this.player1Results);
        this.gameListAdapter.setPlayer2Results(this.player2Results);
        this.gameListAdapter.notifyDataSetChanged();
    }

    public void updateUserRank(float newRank) {
        if (this.myOnlineMatchUser != null) {
            this.myOnlineMatchUser.setOnlineRating(newRank);
            initHeader();
        }
    }

    public void setPlayer1Results(List<Integer> player1Results) {
        this.player1Results = player1Results;
        if (this.gameListAdapter != null) {
            this.gameListAdapter.setPlayer1Results(player1Results);
        }
    }

    public void setPlayer2Results(List<Integer> player2Results) {
        this.player2Results = player2Results;
        if (this.gameListAdapter != null) {
            this.gameListAdapter.setPlayer2Results(player2Results);
        }
    }

    public void notifyDataSetChanged() {
        if (this.gameListAdapter != null) {
            this.gameListAdapter.notifyDataSetChanged();
        }
    }
}
