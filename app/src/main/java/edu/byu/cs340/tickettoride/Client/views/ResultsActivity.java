package edu.byu.cs340.tickettoride.Client.views;

import android.support.v7.app.AppCompatActivity;

import edu.byu.cs340.tickettoride.Client.presenters.IResultsPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.ResultsPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

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

    public void uploadPlayer1Points(Player player){
        mPlayer1.setText(player.getPlayerName().getUsername());
        mPlayer1TicketScore.setText(Integer.toString(player.getTicketPoints()));
        mPlayer1NegTicketScore.setText(Integer.toString(player.getNegTicketPoints()));
        mPlayer1TrainScore.setText(Integer.toString(player.getTrainCarPoints()));
        mPlayer1LongTrainScore.setText(Integer.toString(player.getLongTrainPoints()));
        mPlayer1TotalScore.setText(Integer.toString(player.getTotalPoints()));
    }
    public void uploadPlayer2Points(Player player){
        mPlayer2.setText(player.getPlayerName().getUsername());
        mPlayer2TicketScore.setText(Integer.toString(player.getTicketPoints()));
        mPlayer2NegTicketScore.setText(Integer.toString(player.getNegTicketPoints()));
        mPlayer2TrainScore.setText(Integer.toString(player.getTrainCarPoints()));
        mPlayer2LongTrainScore.setText(Integer.toString(player.getLongTrainPoints()));
        mPlayer2TotalScore.setText(Integer.toString(player.getTotalPoints()));
    }
    public void uploadPlayer3Points(Player player){
        mPlayer3.setText(player.getPlayerName().getUsername());
        mPlayer3TicketScore.setText(Integer.toString(player.getTicketPoints()));
        mPlayer3NegTicketScore.setText(Integer.toString(player.getNegTicketPoints()));
        mPlayer3TrainScore.setText(Integer.toString(player.getTrainCarPoints()));
        mPlayer3LongTrainScore.setText(Integer.toString(player.getLongTrainPoints()));
        mPlayer3TotalScore.setText(Integer.toString(player.getTotalPoints()));
    }
    public void uploadPlayer4Points(Player player){
        mPlayer4.setText(player.getPlayerName().getUsername());
        mPlayer4TicketScore.setText(Integer.toString(player.getTicketPoints()));
        mPlayer4NegTicketScore.setText(Integer.toString(player.getNegTicketPoints()));
        mPlayer4TrainScore.setText(Integer.toString(player.getTrainCarPoints()));
        mPlayer4LongTrainScore.setText(Integer.toString(player.getLongTrainPoints()));
        mPlayer4TotalScore.setText(Integer.toString(player.getTotalPoints()));
    }
    public void uploadPlayer5Points(Player player){
        mPlayer5.setText(player.getPlayerName().getUsername());
        mPlayer5TicketScore.setText(Integer.toString(player.getTicketPoints()));
        mPlayer5NegTicketScore.setText(Integer.toString(player.getNegTicketPoints()));
        mPlayer5TrainScore.setText(Integer.toString(player.getTrainCarPoints()));
        mPlayer5LongTrainScore.setText(Integer.toString(player.getLongTrainPoints()));
        mPlayer5TotalScore.setText(Integer.toString(player.getTotalPoints()));
    }
    public void setWinner(Username winner){
        mWinner.setText(winner.getUsername());
    }

    @Override
    public void onBackPressed(){
        makeToast("can't go back now");
    }
}
