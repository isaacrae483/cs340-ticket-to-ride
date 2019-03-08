package edu.byu.cs340.tickettoride.Client.presenters;


import java.util.Observable;

import edu.byu.cs340.tickettoride.Client.model.ClientModel;
import edu.byu.cs340.tickettoride.Client.model.events.destCard.DestCardDraw;
import edu.byu.cs340.tickettoride.Client.model.events.destCard.DestCardReturned;
import edu.byu.cs340.tickettoride.Client.views.DestCardActivity;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Enums.City;

public class DestCardPresenter extends Presenter implements IDestCardPresenter {

    private DestCardActivity view;
    private ClientModel model;

    public DestCardPresenter(DestCardActivity view) {
        this.view = view;
        this.model = ClientModel.instance();
    }

    @Override
    public void drawPressed() {
        model.drawDestCards(
                new DestCard(City.SEATTLE, City.LOS_ANGELES, 3),
                new DestCard(City.SAULT_ST_MARIE, City.LOS_ANGELES, 5),
                new DestCard(City.OKLAHOMA_CITY, City.NEW_ORLEANS, 20)
        );
    }

    @Override
    public void returnCard(DestCard card) {
        model.returnDestCard(card);
    }

    @Override
    public void update(Observable obeservable, Object o) {
        if (o instanceof DestCardDraw) {
            DestCardDraw draw = (DestCardDraw) o;
            view.onCardDraw(draw.getCard1(), draw.getCard2(), draw.getCard3());
        }
        else if (o instanceof DestCardReturned) {
            DestCardReturned returned = (DestCardReturned) o;
            view.onCardReturn(returned.getReturned());
        }
    }
}
