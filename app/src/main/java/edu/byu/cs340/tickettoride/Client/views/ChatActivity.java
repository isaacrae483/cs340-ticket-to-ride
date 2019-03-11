package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.ChatPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.IChatPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.Chat.Chat;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Interface.IGameListEntry;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;
import edu.byu.cs340.tickettoride.shared.Player.Player;
import edu.byu.cs340.tickettoride.shared.User.Username;

public class ChatActivity extends PresenterViewActivity implements IChatView {
    private RecyclerView mChatRecyclerView;
    private ChatAdapter mChatAdapter;
    private Button mSendButton;
    private IChatPresenter mIChatPresenter;
    private EditText mMessageText;

    public void setChatList(List<ChatMessage> messages) {
        mChatAdapter.setNewDataset(messages);
        mChatAdapter.notifyDataSetChanged();
    }

    public void addChatToList(ChatMessage message) {
        mChatAdapter.addChatEntry(message);
    }

    public void updateGame(ChatMessage message) {
        mChatAdapter.chatChanged(message);
    }

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, ChatActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mChatRecyclerView = findViewById(R.id.chatMessageList);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChatAdapter = new ChatAdapter(new ArrayList<ChatMessage>());
        mChatRecyclerView.setAdapter(mChatAdapter);
        mSendButton = findViewById(R.id.sendButton);
        mMessageText = findViewById(R.id.composeMessage);
        mIChatPresenter = new ChatPresenter(this);
        setPresenter(mIChatPresenter);

        mSendButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                mIChatPresenter.sendPressed();
            }
        });

        mMessageText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mIChatPresenter.messageTextChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void displayNewMessage(ChatMessage message) {
        mChatAdapter.addChatEntry(message);
    }

    private class ChatHolder extends RecyclerView.ViewHolder {
        TextView mChatMessage;
        TextView mPlayerNameView;
        ImageView mPlayerIcon;


        public ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_chat_history, parent, false));
            mPlayerNameView = itemView.findViewById(R.id.playerName);
            mPlayerIcon = itemView.findViewById(R.id.playerIcon);
            mChatMessage = itemView.findViewById(R.id.chatMessage);
        }

        public void bind(ChatMessage message) {
            mPlayerNameView.setText(message.getUser().getUsername());
            //mPlayerIcon.setColorFilter(getColorFromEnum(.getColor()));
            mChatMessage.setText(message.getMessage());
        }

        /*private int getColorFromEnum(IPlayer.Color color) {
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
        }*/
    }

    private class ChatAdapter extends RecyclerView.Adapter {

        List<ChatMessage> mChatSet;

        ChatAdapter(List<ChatMessage> initialChatSet) {
            if (initialChatSet != null)
                mChatSet = initialChatSet;
            else
                mChatSet = new ArrayList<ChatMessage>();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ChatActivity.this);
            return new ChatActivity.ChatHolder(layoutInflater, parent);
        }

        @Override

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((ChatActivity.ChatHolder) holder).bind(mChatSet.get(position));
        }

        @Override
        public int getItemCount() {
            return mChatSet.size();
        }

        public void setNewDataset(List<ChatMessage> ChatSet) {
            mChatSet = ChatSet;
            notifyDataSetChanged();
        }

        void addChatEntry(ChatMessage message) {
            mChatSet.add(message);
            this.notifyItemInserted(mChatSet.size() - 1);
        }

        void chatChanged(ChatMessage message) {
            for (int i = 0; i < mChatSet.size(); i++) {
                if (mChatSet.get(i).getUser().equals(message.getUser())) {
                    mChatSet.set(i, message);
                    notifyItemChanged(i);
                }
            }
        }
    }
}
