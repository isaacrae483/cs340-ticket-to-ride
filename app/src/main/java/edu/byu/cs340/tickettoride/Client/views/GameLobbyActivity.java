package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.GameLobbyPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.IGameLobbyPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

/**
 * Created by Thomas Lewis on 2/5/19.
 */

public class GameLobbyActivity extends PresenterViewActivity implements IGameLobbyView {

    RecyclerView mPlayerRecyclerView;
    PlayerAdapter mPlayerAdapter;
    Button mStartGameButton;
    IGameLobbyPresenter mGameLobbyPresenter;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, GameLobbyActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lobby);


        mPlayerRecyclerView = findViewById(R.id.waitingPlayerList);
        mPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayerAdapter = new PlayerAdapter(new ArrayList<IPlayer>());
        mPlayerRecyclerView.setAdapter(mPlayerAdapter);

        mStartGameButton = findViewById(R.id.startGameButton);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameLobbyPresenter.startGamePressed();
            }
        });

        GameLobbyPresenter gameLobbyPresenter = new GameLobbyPresenter(this);
        mGameLobbyPresenter = gameLobbyPresenter;
        setPresenter(gameLobbyPresenter);
    }

    @Override
    public void displayStartGame(boolean buttonDisplayed) {
        if (buttonDisplayed)
            mStartGameButton.setVisibility(View.VISIBLE);
        else
            mStartGameButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPlayerSet(List<? extends IPlayer> playerSet) {
        mPlayerAdapter.setNewDataset(playerSet);
    }

    @Override
    public void moveToStartGame() {
        startActivity(GameActivity.newIntent(getApplicationContext()));
    }

    @Override
    public void finishView() {
        finish();
    }

    private class PlayerHolder extends RecyclerView.ViewHolder {
        TextView mPlayerNameView;
        ImageView mPlayerIcon;


        public PlayerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_waiting_player, parent, false));
            mPlayerNameView = itemView.findViewById(R.id.playerName);
            mPlayerIcon = itemView.findViewById(R.id.playerIcon);
        }

        public void bind(IPlayer player) {
            mPlayerNameView.setText(player.getPlayerName().getUsername());
            mPlayerIcon.setColorFilter(getColorFromEnum(player.getColor()));
        }

        private int getColorFromEnum(IPlayer.Color color) {
            switch(color) {
                case GREEN:
                    return ContextCompat.getColor(getApplicationContext(), R.color.greenPlayer);
                case RED:
                    return ContextCompat.getColor(getApplicationContext(), R.color.redPlayer);
                case BLUE:
                    return ContextCompat.getColor(getApplicationContext(), R.color.bluePlayer);
                case BLACK:
                    return ContextCompat.getColor(getApplicationContext(), R.color.blackPlayer);
                case YELLOW:
                    return ContextCompat.getColor(getApplicationContext(), R.color.yellowPlayer);
            }
            return R.color.colorPrimary;
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter {

        List<? extends IPlayer> mPlayerSet;

        PlayerAdapter(List<IPlayer> initialPlayerSet) {
            if (initialPlayerSet != null)
                mPlayerSet = initialPlayerSet;
            else
                mPlayerSet = new ArrayList<IPlayer>();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(GameLobbyActivity.this);
            return new PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((PlayerHolder) holder).bind(mPlayerSet.get(position));
        }

        @Override
        public int getItemCount() {
            return mPlayerSet.size();
        }

        public void setNewDataset(List<? extends IPlayer> playerSet) {
            mPlayerSet = playerSet;
            notifyDataSetChanged();
        }
    }

}
