package edu.byu.cs340.tickettoride.Client.views;

import android.support.v7.app.AppCompatActivity;
import edu.byu.cs340.tickettoride.R;

import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView mPlayer1;
    TextView mPlayer1Score;
    TextView mPlayer2;
    TextView mPlayer2Score;
    TextView mPlayer3;
    TextView mPlayer3Score;
    TextView mPlayer4;
    TextView mPlayer4Score;
    TextView mPlayer5;
    TextView mPlayer5Score;
    TextView mWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mPlayer1 = findViewById(R.id.player1);
        mPlayer1Score = findViewById(R.id.player1Score);
        mPlayer2 = findViewById(R.id.player2);
        mPlayer2Score = findViewById(R.id.player2Score);
        mPlayer3 = findViewById(R.id.player3);
        mPlayer3Score = findViewById(R.id.player3Score);
        mPlayer4 = findViewById(R.id.player4);
        mPlayer4Score = findViewById(R.id.player4Score);
        mPlayer5 = findViewById(R.id.player5);
        mPlayer5Score = findViewById(R.id.player5Score);
        mWinner = findViewById(R.id.winningPlayer);

    }
}
