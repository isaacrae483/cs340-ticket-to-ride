package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.IPlayerListPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.PlayerListPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

public class PlayerListActivity extends PresenterViewActivity implements IPlayerListView {
    private IPlayerListPresenter mPlayerListPresenter;
    private RecyclerView mPlayerList;
    private PlayerAdapter mPlayerAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, PlayerListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityPlayerList);

        mPlayerList = findViewById(R.id.playerList);
        // TODO: link to live model data
        mPlayerAdapter = new PlayerAdapter(new ArrayList<IPlayer>());
        mPlayerList.setAdapter(mPlayerAdapter);

        mPlayerListPresenter = new PlayerListPresenter();
        setPresenter(mPlayerListPresenter);
    }

    private class PlayerHolder extends RecyclerView.ViewHolder {
        private ImageView mPlayerColorIcon;
        private TextView mPlayerDescription;

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_player, parent));
            mPlayerColorIcon = itemView.findViewById(R.id.player_list_icon);
            mPlayerDescription = itemView.findViewById(R.id.player_list_description);
        }

        public void bind(IPlayer player) {
            // TODO: change color of icon based on player data
            mPlayerColorIcon.setImageResource(R.drawable.ic_person_black_60dp);
            mPlayerDescription.setText(player.getPlayerName().toString());
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<IPlayer> mPlayerList;

        public PlayerAdapter(List<IPlayer> mPlayerList) {
            this.mPlayerList = mPlayerList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(PlayerListActivity.this);
            return new PlayerHolder(inflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((PlayerHolder) viewHolder).bind(mPlayerList.get(i));

        }

        @Override
        public int getItemCount() {
            return mPlayerList.size();
        }
    }
}
