package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import edu.byu.cs340.tickettoride.Client.presenters.GamePresenter;
import edu.byu.cs340.tickettoride.R;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public class GameActivity extends PresenterViewActivity implements IGameView {

    private GamePresenter mGamePresenter;
    private ImageButton mViewChatButton;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, GameActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        makeToast(getString(R.string.game_started));
        mViewChatButton = findViewById(R.id.chatButton);

        GamePresenter gamePresenter = new GamePresenter(this);
        mGamePresenter = gamePresenter;
        setPresenter(mGamePresenter);

        mViewChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGamePresenter.viewChatPressed();
            }
        });
    }

    @Override
    public void moveToChat() {
        startActivity(ChatActivity.newIntent(this));
    }

    @Override
    public void moveToPlayerView() {
        startActivity(PlayerListActivity.newIntent(getApplicationContext()));
    }

    @Override
    public void moveToDestCards() {

    }

    @Override
    public void viewDeck() {

    }
}
