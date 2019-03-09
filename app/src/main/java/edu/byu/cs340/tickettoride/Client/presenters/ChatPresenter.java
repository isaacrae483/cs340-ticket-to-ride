package edu.byu.cs340.tickettoride.Client.presenters;

import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.IModelFacade;
import edu.byu.cs340.tickettoride.Client.model.ModelFacade;
import edu.byu.cs340.tickettoride.Client.views.IChatView;
import edu.byu.cs340.tickettoride.shared.Game.Chat.ChatMessage;
import edu.byu.cs340.tickettoride.shared.User.Password;

public class ChatPresenter extends Presenter implements IChatPresenter {
    private IChatView view;
    private ModelFacade mModelFacade;
    private ChatMessage mChatMessage = null;

    public ChatPresenter(IChatView view) {

        this.view = view;
    }

    @Override
    public void syncWithModel() {
        super.syncWithModel();
    }

    @Override
    public void update(Observable observable, Object o) {

        super.update(observable, o);
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
