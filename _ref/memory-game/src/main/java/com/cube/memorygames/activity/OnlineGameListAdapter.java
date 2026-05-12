package com.cube.memorygames.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.cube.memorygames.Games;
import com.cube.memorygames.model.GameInfo;
import com.memory.brain.training.games.R;
import java.util.List;

public class OnlineGameListAdapter extends Adapter<GameHolder> {
    private boolean displayResult;
    private List<String> gameList;
    private List<Integer> player1Results;
    private List<Integer> player2Results;

    public static class GameHolder extends ViewHolder {
        @Bind({2131624028})
        ImageView image;
        @Bind({2131624383})
        TextView name;
        @Bind({2131624400})
        TextView player1;
        @Bind({2131624402})
        TextView player2;
        @Bind({2131624401})
        TextView result;

        public GameHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public OnlineGameListAdapter(List<String> gameList) {
        this.gameList = gameList;
    }

    public void setPlayer1Results(List<Integer> player1Results) {
        this.player1Results = player1Results;
        this.displayResult = true;
    }

    public void setGameResults(List<Integer> player1Results, List<Integer> player2Results) {
        this.player1Results = player1Results;
        this.player2Results = player2Results;
        this.displayResult = true;
    }

    public void setPlayer2Results(List<Integer> player2Results) {
        this.player2Results = player2Results;
        this.displayResult = true;
    }

    public GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GameHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_online_game, parent, false));
    }

    public void onBindViewHolder(GameHolder holder, int position) {
        boolean isCompleted = true;
        int player1Result = 0;
        int player2Result = 0;
        if (this.player1Results == null || this.player1Results.size() <= position) {
            holder.player1.setText("");
        } else {
            holder.player1.setText(String.valueOf(this.player1Results.get(position)));
            player1Result = ((Integer) this.player1Results.get(position)).intValue();
        }
        if (this.player2Results == null || this.player2Results.size() <= position) {
            holder.player2.setText("");
        } else {
            holder.player2.setText(String.valueOf(this.player2Results.get(position)));
            player2Result = ((Integer) this.player2Results.get(position)).intValue();
        }
        holder.player1.setBackgroundDrawable(null);
        holder.player2.setBackgroundDrawable(null);
        if (position < this.gameList.size()) {
            GameInfo gameInfo = Games.get().getGameBiId((String) this.gameList.get(position));
            holder.name.setText(gameInfo.getGameNameRes());
            holder.image.setImageResource(gameInfo.getGameImageRes());
            holder.name.setVisibility(0);
            holder.player1.setTypeface(holder.player1.getTypeface(), 0);
            holder.player2.setTypeface(holder.player2.getTypeface(), 0);
            holder.image.setVisibility(0);
            holder.result.setVisibility(8);
            return;
        }
        holder.name.setVisibility(8);
        holder.image.setImageBitmap(null);
        holder.image.setVisibility(8);
        holder.result.setVisibility(0);
        holder.player1.setTypeface(holder.player1.getTypeface(), 1);
        holder.player2.setTypeface(holder.player2.getTypeface(), 1);
        if (holder.player1.getText().toString().isEmpty() || holder.player2.getText().toString().isEmpty()) {
            isCompleted = false;
        }
        Drawable circle = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.online_game_win_circle);
        if (player1Result > player2Result && isCompleted) {
            holder.player1.setBackgroundDrawable(circle);
        } else if (player1Result < player2Result && isCompleted) {
            holder.player2.setBackgroundDrawable(circle);
        }
    }

    public int getItemCount() {
        return this.displayResult ? this.gameList.size() + 1 : this.gameList.size();
    }
}
