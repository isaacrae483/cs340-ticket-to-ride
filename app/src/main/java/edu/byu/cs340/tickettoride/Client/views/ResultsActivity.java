package edu.byu.cs340.tickettoride.Client.views;

import android.support.v7.app.AppCompatActivity;

import edu.byu.cs340.tickettoride.Client.presenters.IResultsPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.ResultsPresenter;
import edu.byu.cs340.tickettoride.R;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends PresenterViewActivity implements IResultsView {

    private TextView mPlayer1;
    private TextView mPlayer1TicketScore;
    private TextView mPlayer1NegTicketScore;
    private TextView mPlayer1TrainScore;
    private TextView mPlayer1LongTrainScore;
    private TextView mPlayer1TotalScore;
    private TextView mPlayer2;
    private TextView mPlayer2TicketScore;
    private TextView mPlayer2NegTicketScore;
    private TextView mPlayer2TrainScore;
    private TextView mPlayer2LongTrainScore;
    private TextView mPlayer2TotalScore;
    private TextView mPlayer3;
    private TextView mPlayer3TicketScore;
    private TextView mPlayer3NegTicketScore;
    private TextView mPlayer3TrainScore;
    private TextView mPlayer3LongTrainScore;
    private TextView mPlayer3TotalScore;
    private TextView mPlayer4;
    private TextView mPlayer4TicketScore;
    private TextView mPlayer4NegTicketScore;
    private TextView mPlayer4TrainScore;
    private TextView mPlayer4LongTrainScore;
    private TextView mPlayer4TotalScore;
    private TextView mPlayer5;
    private TextView mPlayer5TicketScore;
    private TextView mPlayer5NegTicketScore;
    private TextView mPlayer5TrainScore;
    private TextView mPlayer5LongTrainScore;
    private TextView mPlayer5TotalScore;
    private TextView mWinner;

    private IResultsPresenter mIResultsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mPlayer1 = findViewById(R.id.player1);
        mPlayer1TicketScore = findViewById(R.id.player1ticket);
        mPlayer1NegTicketScore = findViewById(R.id.player1negTicket);
        mPlayer1TrainScore = findViewById(R.id.player1train);
        mPlayer1LongTrainScore = findViewById(R.id.player1longTrain);
        mPlayer1TotalScore = findViewById(R.id.player1total);
        mPlayer2 = findViewById(R.id.player2);
        mPlayer2TicketScore = findViewById(R.id.player2ticket);
        mPlayer2NegTicketScore = findViewById(R.id.player2negTicket);
        mPlayer2TrainScore = findViewById(R.id.player2train);
        mPlayer2LongTrainScore = findViewById(R.id.player2longTrain);
        mPlayer2TotalScore = findViewById(R.id.player2total);
        mPlayer3 = findViewById(R.id.player3);
        mPlayer3TicketScore = findViewById(R.id.player3ticket);
        mPlayer3NegTicketScore = findViewById(R.id.player3negTicket);
        mPlayer3TrainScore = findViewById(R.id.player3train);
        mPlayer3LongTrainScore = findViewById(R.id.player3longTrain);
        mPlayer3TotalScore = findViewById(R.id.player3total);
        mPlayer4 = findViewById(R.id.player4);
        mPlayer4TicketScore = findViewById(R.id.player4ticket);
        mPlayer4NegTicketScore = findViewById(R.id.player4negTicket);
        mPlayer4TrainScore = findViewById(R.id.player4train);
        mPlayer4LongTrainScore = findViewById(R.id.player4longTrain);
        mPlayer4TotalScore = findViewById(R.id.player4total);
        mPlayer5 = findViewById(R.id.player5);
        mPlayer5TicketScore = findViewById(R.id.player5ticket);
        mPlayer5NegTicketScore = findViewById(R.id.player5negTicket);
        mPlayer5TrainScore = findViewById(R.id.player5train);
        mPlayer5LongTrainScore = findViewById(R.id.player5longTrain);
        mPlayer5TotalScore = findViewById(R.id.player5total);
        mWinner = findViewById(R.id.winningPlayer);


        ResultsPresenter resultsPresenter = new ResultsPresenter(this);
        mIResultsPresenter = resultsPresenter;
        setPresenter(resultsPresenter);

    }
}
