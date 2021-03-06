package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.List;
import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.views.ChatActivity;
import edu.byu.cs340.tickettoride.Client.views.IChatView;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.Game.events.chat.ChatAdded;

public class ChatPresenter extends Presenter implements IChatPresenter {
    private IChatView mChatView;
    private ModelFacade mModelFacade;
    private ChatMessage mChatMessage = null;

    public ChatPresenter(ChatActivity view) {
        mModelFacade = ModelFacade.instance();
        this.mChatView = view;
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
        if (mClientModel.getChatMessages() != null) {
            List<ChatMessage> chatList = mClientModel.getChatMessages().getMessages(0);
            mChatView.setChatList(chatList);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);
        if (o instanceof ChatAdded) {
            ChatAdded e = (ChatAdded) o;
            mChatView.displayNewMessage(e.getMessage());
        } else {
            syncWithModel();
        }
    }

    @Override
    public void sendPressed() {
        if(mChatMessage != null){
            mModelFacade.sendChat(mChatMessage);
        }
    }

    @Override
    public void messageTextChanged(String message) {
        mChatMessage = new ChatMessage(message,
                ClientModel.instance().getUsername(),
                ClientModel.instance().getActiveGameID());
    }
}
