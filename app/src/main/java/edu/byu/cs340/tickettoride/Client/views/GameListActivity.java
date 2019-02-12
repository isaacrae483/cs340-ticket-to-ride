package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.GameListPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.IGameListPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.ID;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;

/**
 * Created by Thomas Lewis on 2/4/19.
 */
public class GameListActivity extends PresenterViewActivity implements IGameListView {

    RecyclerView mGameList;
    GameAdapter mGameAdapter;

    FloatingActionButton mAddGameButton;
    IGameListPresenter mGameListPresenter;



    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, GameListActivity.class);
    }

    public void setGameList(List<IGameListEntry> games) {
        mGameAdapter.setNewDataset(games);
        mGameAdapter.notifyDataSetChanged();
    }

    public void addGameToList(IGameListEntry game) {
        mGameAdapter.addGameEntry(game);
    }

    public void updateGame(IGameListEntry game) {
        mGameAdapter.gameChanged(game);
    }

    @Override
    public void displayGameJoinError() {
        makeToast(getString(R.string.game_join_error));
    }

    @Override
    public void moveToGameLobby() {
        startActivity(GameLobbyActivity.newIntent(getApplicationContext()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        mAddGameButton = findViewById(R.id.newGameButton);
        mGameList = findViewById(R.id.gameList);
        getSupportActionBar().setTitle(R.string.game_list_screen_title);

        mGameList.setLayoutManager(new LinearLayoutManager(this));
        mGameAdapter = new GameAdapter(new ArrayList<IGameListEntry>());
        mGameList.setAdapter(mGameAdapter);

        // Initialize presenter after all members have been defined
        GameListPresenter gameListPresenter = new GameListPresenter(this);
        mGameListPresenter = gameListPresenter;
        setPresenter(gameListPresenter);

        mAddGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameListPresenter.addGamePressed();
            }
        });



    }

    private class GameHolder extends RecyclerView.ViewHolder {
        ID mGameID;
        int mPlayerCount;
        ImageView mJoinGameButton;
        TextView mPlayerCountView;
        TextView mGameNameView;
        String playerCountTemplate = getResources().getString(R.string.player_count_list);

        public GameHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_game, parent, false));
            mJoinGameButton = itemView.findViewById(R.id.joinButton);
            mJoinGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mGameListPresenter.joinGamePressed(mGameID);
                }
            });
            mPlayerCountView = itemView.findViewById(R.id.playerCount);
            mGameNameView = itemView.findViewById(R.id.playerName);
        }

        public void bind(IGameListEntry game) {
            mGameID = game.getId();
            mPlayerCount = game.getPlayerCount();
            mGameNameView.setText(mGameID.getId());
            mPlayerCountView.setText(mPlayerCount + playerCountTemplate);
            if (game.isGameFull())
                mJoinGameButton.setVisibility(View.INVISIBLE);
            else
                mJoinGameButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Adapter for holding objects that implement IGameListEntry
     *
     */
    private class GameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<IGameListEntry> mGameList;

        public GameAdapter(List<IGameListEntry> initialGameList) {
            if (initialGameList != null)
                mGameList = initialGameList;
            else
                mGameList = new ArrayList<>();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(GameListActivity.this);
            return new GameHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            ((GameHolder) viewHolder).bind(mGameList.get(position));
        }

        @Override
        public int getItemCount() {
            return mGameList.size();
        }

        void setNewDataset(List<IGameListEntry> gameList) {
            mGameList = gameList;
        }

        void addGameEntry(IGameListEntry entry) {
            mGameList.add(entry);
            this.notifyItemInserted(mGameList.size() - 1);
        }

        void gameChanged(IGameListEntry game) {
            for (int i = 0; i < mGameList.size(); i++) {
                if (mGameList.get(i).getId().equals(game.getId())) {
                    mGameList.set(i, game);
                    notifyItemChanged(i);
                }
            }
        }
    }


}
