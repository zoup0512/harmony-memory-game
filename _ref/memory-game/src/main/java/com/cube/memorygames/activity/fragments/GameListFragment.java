package com.cube.memorygames.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.cube.memorygames.activity.OnlineGameListAdapter;
import com.cube.memorygames.pushes.model.OnlineMatchUser;
import com.cube.memorygames.ui.TimerView;
import com.memory.brain.training.games.R;
import java.util.ArrayList;
import java.util.List;

public class GameListFragment extends Fragment {
    private static final String EXTRA_CURRENT_GAMES = "extra_current_games";
    private static final String EXTRA_MATCH_USER = "extra_match_user";
    private static final String EXTRA_MY_MATCH_USER = "extra_my_match_user";
    private static final String EXTRA_TIME_OUT = "extra_time_out";
    private List<String> currentGamesList;
    private OnlineGameListAdapter gameListAdapter;
    private OnlineMatchUser myOnlineMatchUser;
    private OnlineMatchUser onlineMatchUser;
    @Bind({2131624112})
    RecyclerView recyclerView;
    private int timeOut;
    @Bind({2131624093})
    TimerView timerView;

    public static GameListFragment getFragment(List<String> currentGamesList, OnlineMatchUser myOnlineMatchUser, OnlineMatchUser onlineMatchUser, int timeOut) {
        GameListFragment gameListFragment = new GameListFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(EXTRA_CURRENT_GAMES, new ArrayList(currentGamesList));
        bundle.putParcelable(EXTRA_MY_MATCH_USER, myOnlineMatchUser);
        bundle.putParcelable(EXTRA_MATCH_USER, onlineMatchUser);
        bundle.putInt(EXTRA_TIME_OUT, timeOut);
        gameListFragment.setArguments(bundle);
        return gameListFragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentGamesList = getArguments().getStringArrayList(EXTRA_CURRENT_GAMES);
        this.myOnlineMatchUser = (OnlineMatchUser) getArguments().getParcelable(EXTRA_MY_MATCH_USER);
        this.onlineMatchUser = (OnlineMatchUser) getArguments().getParcelable(EXTRA_MATCH_USER);
        this.timeOut = getArguments().getInt(EXTRA_TIME_OUT);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_game, container, false);
        ButterKnife.bind((Object) this, view);
        Log.e(getClass().getSimpleName(), "onCreateView");
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initHeader();
        initGamesList();
    }

    private void initHeader() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.header_fragment);
        if ((fragment instanceof HeaderFragment) && this.myOnlineMatchUser != null && this.onlineMatchUser != null) {
            ((HeaderFragment) fragment).setMatchFound(this.myOnlineMatchUser, this.onlineMatchUser);
        }
    }

    private void initGamesList() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.gameListAdapter = new OnlineGameListAdapter(this.currentGamesList);
        this.recyclerView.setAdapter(this.gameListAdapter);
        this.recyclerView.setVisibility(0);
        this.timerView.showTimer(this.timeOut, false);
    }
}
