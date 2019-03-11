package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.presenters.IPlayerListPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.PlayerListPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.Game;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class PlayerListActivity extends PresenterViewActivity implements IPlayerListView {
    Game mGame;

    private IPlayerListPresenter mPlayerListPresenter;
    private RecyclerView mPlayerList;
    private PlayerAdapter mPlayerAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, PlayerListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        mGame = ClientModel.instance().getActiveGame();

        mPlayerList = findViewById(R.id.playerList);
        mPlayerList.setLayoutManager(new LinearLayoutManager(this));
        mPlayerList.addItemDecoration(
                new DividerItemDecoration(
                        mPlayerList.getContext(),
                        DividerItemDecoration.VERTICAL
                )
        );

/*
        List<Player> list = new ArrayList<>();
        try {
            list.add(new Player(new Username("dude"), IPlayer.Color.GREEN));
            list.add(new Player(new Username("guy"), IPlayer.Color.BLUE));
        } catch (Username.InvalidUserNameException e) {
            e.printStackTrace();
        }

        mPlayerAdapter = new PlayerAdapter(list);
*/
        mPlayerAdapter = new PlayerAdapter(mGame.getPlayers());
        mPlayerList.setAdapter(mPlayerAdapter);

        mPlayerListPresenter = new PlayerListPresenter();
        setPresenter(mPlayerListPresenter);
    }

    private class PlayerHolder extends RecyclerView.ViewHolder {
        private TextView mTurnIndicator;
        private ImageView mPlayerColorIcon;
        private TextView mPlayerDescription;
        private TextView mPlayerPoints;
        private TextView mPlayerTrainCards;
        private TextView mPlayerDestCards;

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_player, parent, false));
            mTurnIndicator = itemView.findViewById(R.id.player_list_turn);
            mPlayerColorIcon = itemView.findViewById(R.id.player_list_icon);
            mPlayerDescription = itemView.findViewById(R.id.player_list_description);
            mPlayerPoints = itemView.findViewById(R.id.player_list_points);
            mPlayerTrainCards = itemView.findViewById(R.id.player_list_train_cards);
            mPlayerDestCards = itemView.findViewById(R.id.player_list_dest_cards);
        }

        public void bind(Player player, int i) {
            if (i != mGame.getPlayerTurnIndex()) {
//            if (i != 0) {
                mTurnIndicator.setVisibility(View.INVISIBLE);
            }
            mPlayerDescription.setText(player.getPlayerName().toString());

            switch (player.getColor()) {
                case RED:
                    mPlayerColorIcon.setColorFilter(Color.RED);
                    break;

                case GREEN:
                    mPlayerColorIcon.setColorFilter(Color.GREEN);
                    break;

                case BLUE:
                    mPlayerColorIcon.setColorFilter(Color.BLUE);
                    break;

                case YELLOW:
                    mPlayerColorIcon.setColorFilter(Color.YELLOW);
                    break;

                case BLACK:
                default:
                    break;
            }

            mPlayerPoints.setText(player.getPoints().toString() + "\npoints");
            mPlayerTrainCards.setText(player.getHand().getNumTrainCards() + "\ntrain cards");
            mPlayerDestCards.setText(player.getHand().getNumDestCards() + "\ndestinations");
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {
        private List<Player> mPlayerList;

        public PlayerAdapter(List<Player> mPlayerList) {
            this.mPlayerList = mPlayerList;
        }

        @NonNull
        @Override
        public PlayerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(PlayerListActivity.this);
            return new PlayerHolder(inflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull PlayerHolder viewHolder, int i) {
            viewHolder.bind(mPlayerList.get(i), i);
        }

        @Override
        public int getItemCount() {
            return mPlayerList.size();
        }
    }
}
