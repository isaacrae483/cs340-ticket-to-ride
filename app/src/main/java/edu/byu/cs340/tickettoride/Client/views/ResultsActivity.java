package edu.byu.cs340.tickettoride.Client.views;

import android.support.v7.app.AppCompatActivity;
import edu.byu.cs340.tickettoride.R;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView mPlayer1;
    TextView mPlayer1TicketScore;
    TextView mPlayer1NegTicketScore;
    TextView mPlayer1TrainScore;
    TextView mPlayer1LongTrainScore;
    TextView mPlayer1TotalScore;
    TextView mPlayer2;
    TextView mPlayer2TicketScore;
    TextView mPlayer2NegTicketScore;
    TextView mPlayer2TrainScore;
    TextView mPlayer2LongTrainScore;
    TextView mPlayer2TotalScore;
    TextView mPlayer3;
    TextView mPlayer3TicketScore;
    TextView mPlayer3NegTicketScore;
    TextView mPlayer3TrainScore;
    TextView mPlayer3LongTrainScore;
    TextView mPlayer3TotalScore;
    TextView mPlayer4;
    TextView mPlayer4TicketScore;
    TextView mPlayer4NegTicketScore;
    TextView mPlayer4TrainScore;
    TextView mPlayer4LongTrainScore;
    TextView mPlayer4TotalScore;
    TextView mPlayer5;
    TextView mPlayer5TicketScore;
    TextView mPlayer5NegTicketScore;
    TextView mPlayer5TrainScore;
    TextView mPlayer5LongTrainScore;
    TextView mPlayer5TotalScore;
    TextView mWinner;

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

    }
}
