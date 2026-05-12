package com.cube.memorygames.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.api.network.model.RetrofitTopResult;
import com.memory.brain.training.games.R;
import com.squareup.picasso.Picasso;
import io.fabric.sdk.android.services.common.IdManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TopRanksAdapter extends Adapter<TopRanksViewHolder> {
    private static final int TOP_PLAYERS = 5;
    private MemoryApplicationModel application;
    private Context context;
    private String objectId;
    private List<RetrofitTopResult> topPlayers;
    private int type;
    private Typeface typeFaceRoboto;

    public class TopRanksViewHolder extends ViewHolder {
        View divider;
        TextView playerCups;
        TextView playerNick;
        TextView playerNumber;
        ImageView profilePicture;

        TopRanksViewHolder(View itemView) {
            super(itemView);
            this.playerNumber = (TextView) itemView.findViewById(R.id.number);
            this.playerNick = (TextView) itemView.findViewById(R.id.nickName);
            this.playerCups = (TextView) itemView.findViewById(R.id.score);
            this.profilePicture = (ImageView) itemView.findViewById(R.id.profilePicture);
            this.divider = itemView.findViewById(R.id.divider);
        }
    }

    public TopRanksAdapter(List<RetrofitTopResult> topPlayers, int type, Context context) {
        this.typeFaceRoboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        if (((RetrofitTopResult) topPlayers.get(topPlayers.size() - 1)).rank == topPlayers.size()) {
            this.topPlayers = topPlayers;
        } else {
            this.topPlayers = new ArrayList();
            for (int i = 0; i < topPlayers.size(); i++) {
                RetrofitTopResult topPlayer = (RetrofitTopResult) topPlayers.get(i);
                if (i == 5) {
                    RetrofitTopResult dots = new RetrofitTopResult();
                    dots.rank = -1;
                    this.topPlayers.add(dots);
                }
                this.topPlayers.add(topPlayer);
            }
        }
        this.context = context;
        this.type = type;
        this.application = MemoryApplicationModel.getInstance();
        this.objectId = this.application.getLocalDataManager().getLocalUser().objectId;
    }

    public TopRanksViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        TopRanksViewHolder topRanksViewHolder = new TopRanksViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.top_ranks_item, viewGroup, false));
        topRanksViewHolder.playerCups.setTypeface(this.typeFaceRoboto);
        topRanksViewHolder.playerNumber.setTypeface(this.typeFaceRoboto);
        topRanksViewHolder.playerNick.setTypeface(this.typeFaceRoboto);
        return topRanksViewHolder;
    }

    public void onBindViewHolder(TopRanksViewHolder holder, int position) {
        holder.playerNick.setGravity(GravityCompat.START);
        holder.playerCups.setVisibility(0);
        holder.playerNumber.setVisibility(0);
        holder.playerNick.setVisibility(0);
        holder.profilePicture.setVisibility(0);
        holder.divider.setVisibility(8);
        RetrofitTopResult retrofitTopResult = (RetrofitTopResult) this.topPlayers.get(position);
        if (retrofitTopResult.rank == -1) {
            holder.playerCups.setText("");
            holder.playerNumber.setText("");
            holder.playerNick.setGravity(17);
            holder.playerNick.setText("...");
            holder.playerCups.setVisibility(8);
            holder.playerNumber.setVisibility(8);
            holder.playerNick.setVisibility(8);
            holder.profilePicture.setVisibility(8);
            holder.divider.setVisibility(0);
            return;
        }
        if (this.type == 1) {
            holder.playerCups.setText(new DecimalFormat(IdManager.DEFAULT_VERSION_NAME).format((double) retrofitTopResult.rating));
        } else if (this.type == 2) {
            holder.playerCups.setText(String.valueOf((int) retrofitTopResult.rating));
        } else {
            holder.playerCups.setText("");
        }
        holder.playerNick.setText(retrofitTopResult.displayName.replaceFirst(" ", "\n"));
        holder.playerNumber.setText(String.valueOf(retrofitTopResult.rank));
        if (this.objectId.equals(retrofitTopResult.userId)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.current_top));
        } else {
            holder.itemView.setBackgroundColor(0);
        }
        int size = this.context.getResources().getDimensionPixelSize(R.dimen.top_avatar_size);
        Picasso.with(this.context).load(retrofitTopResult.photoUrl).error((int) R.drawable.ic_user_placeholder).placeholder((int) R.drawable.ic_user_placeholder).resize(size, size).noFade().centerCrop().into(holder.profilePicture);
    }

    public int getItemCount() {
        return this.topPlayers.size();
    }
}
