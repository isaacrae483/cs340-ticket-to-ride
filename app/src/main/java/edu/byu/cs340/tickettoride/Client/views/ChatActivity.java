package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Player.Player;

public class ChatActivity extends PresenterViewActivity implements IChatView {
    RecyclerView mRecyclerView;
    Button mSendButton;
    EditText mMessageText;

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, ChatActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

    }

    @Override
    public void displayNewMessage(Player player, String message) {

    }
}
