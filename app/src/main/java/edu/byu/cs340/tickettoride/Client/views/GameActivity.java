package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.byu.cs340.tickettoride.Client.Demo;
import edu.byu.cs340.tickettoride.Client.presenters.GamePresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.Cards.TrainCard;

/**
 * Created by Thomas Lewis on 2/6/19.
 */
public class GameActivity extends PresenterViewActivity implements IGameView, IPlayerCardsView, IDeckView {

    private GamePresenter mGamePresenter;
    private ImageButton mViewChatButton;
    private ImageButton mViewPlayersButton;
    private ImageButton mViewDestCardsButton;

    //Player cards members
    //    BLACK, WHITE, ORANGE, BLUE, YELLOW, PINK, GREEN, RED, RAINBOW
    TextView mBlackCards;
    TextView mWhiteCards;
    TextView mOrangeCards;
    TextView mBlueCards;
    TextView mYellowCards;
    TextView mPinkCards;
    TextView mGreenCards;
    TextView mRedCards;
    TextView mRainbowCards;

    //Deck cards members
    ImageView mDeckButton;
    ImageView mCardOne;
    ImageView mCardTwo;
    ImageView mCardThree;
    ImageView mCardFour;
    ImageView mCardFive;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, GameActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        makeToast(getString(R.string.game_started));
        mViewChatButton = findViewById(R.id.chatButton);
        mViewPlayersButton = findViewById(R.id.playersButton);
        mViewDestCardsButton = findViewById(R.id.destCardsButton);

        // Player cards members
        mBlackCards = findViewById(R.id.blackCards);
        mWhiteCards = findViewById(R.id.whiteCards);
        mOrangeCards = findViewById(R.id.orangeCards);
        mBlueCards = findViewById(R.id.blueCards);
        mYellowCards = findViewById(R.id.yellowCards);
        mPinkCards = findViewById(R.id.pinkCards);
        mGreenCards = findViewById(R.id.greenCards);
        mRedCards = findViewById(R.id.redCards);
        mRainbowCards = findViewById(R.id.wildCards);

        //Deck cards members
        mDeckButton = findViewById(R.id.deck);
        mCardOne = findViewById(R.id.cardOne);
        mCardTwo = findViewById(R.id.cardTwo);
        mCardThree = findViewById(R.id.cardThree);
        mCardFour = findViewById(R.id.cardFour);
        mCardFive = findViewById(R.id.cardFive);


        GamePresenter gamePresenter = new GamePresenter(this);
        mGamePresenter = gamePresenter;
        setPresenter(mGamePresenter);

        mViewChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGamePresenter.viewChatPressed();
            }
        });


        mViewPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGamePresenter.viewPlayersPressed();
            }
        });

        mViewDestCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGamePresenter.viewDestCardsPressed();
            }
        });

        //Demo demo = new Demo(this);
        //demo.execute();
    }

    @Override
    public void moveToChat() {
        startActivity(ChatActivity.newIntent(getApplicationContext()));
    }

    @Override
    public void moveToPlayerView() {
        startActivity(PlayerListActivity.newIntent(getApplicationContext()));
    }

    @Override
    public void moveToDestCards() {
        //startActivity(DestCardActivity.newIntent(getApplicationContext()));
    }

    @Override
    public void setCardsAvailable(List<TrainCard> availableCards) {

    }

    @Override
    public void setDeckVisible(Boolean isVisible) {

    }

    @Override
    public void setPlayerCards(List<TrainCard> playerCards) {
        //    BLACK, WHITE, ORANGE, BLUE, YELLOW, PINK, GREEN, RED, RAINBOW
        int blackCards = 0;
        int whiteCards = 0;
        int orangeCards = 0;
        int blueCards = 0;
        int yellowCards = 0;
        int pinkCards = 0;
        int greenCards = 0;
        int redCards = 0;
        int wildCards = 0;
        for (TrainCard card : playerCards) {
            switch (card.getColor()) {
                case BLACK:
                    blackCards++;
                    break;
                case WHITE:
                    whiteCards++;
                    break;
                case ORANGE:
                    orangeCards++;
                    break;
                case BLUE:
                    blueCards++;
                    break;
                case YELLOW:
                    yellowCards++;
                    break;
                case PINK:
                    pinkCards++;
                    break;
                case GREEN:
                    greenCards++;
                    break;
                case RED:
                    redCards++;
                    break;
                case RAINBOW:
                    wildCards++;
                    break;
            }
        }
        setPlayerCardText(mBlackCards, blackCards);
        setPlayerCardText(mWhiteCards, whiteCards);
        setPlayerCardText(mOrangeCards, orangeCards);
        setPlayerCardText(mBlueCards, blueCards);
        setPlayerCardText(mYellowCards, yellowCards);
        setPlayerCardText(mPinkCards, pinkCards);
        setPlayerCardText(mGreenCards, greenCards);
        setPlayerCardText(mRedCards, redCards);
        setPlayerCardText(mRainbowCards, wildCards);


    }

    private void setPlayerCardText(TextView cardCountDisplay, int cardCount) {
        if (cardCount == 0) {
            cardCountDisplay.setVisibility(View.INVISIBLE);
        }
        cardCountDisplay.setText(Integer.toString(cardCount));
    }
}
