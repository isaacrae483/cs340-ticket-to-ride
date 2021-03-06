package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.DestCardPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.IDestCardPresenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;

public class DestCardActivity  extends IDestCardActivity {

    private IDestCardPresenter presenter;

    private Button button;
    private RecyclerView list;
    private DestCardAdapter adapter;

    private PopupWindow window;
    private DestCard draw1;
    private DestCard draw2;
    private DestCard draw3;

    private int cardsReturned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest_card);

        button = findViewById(R.id.drawDestCard);
        list = findViewById(R.id.DestCardList);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DestCardActivity.this.drawCards();
            }
        });

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DestCardAdapter();
        list.setAdapter(adapter);

        presenter = new DestCardPresenter(this);
        this.setPresenter(presenter);
    }

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, DestCardActivity.class);
    }

    public void addCard(DestCard card) {
        adapter.addCard(card);
    }

    @Override
    public void setCards(List<DestCard> cards) {
        adapter.setCards(cards);
    }

    private void drawCards() {
        presenter.drawPressed();
    }

    private void closeWindow() {
        if (window != null) {
            window.setContentView(null);
            window.dismiss();
            button.setEnabled(true);
            window = null;
        }
    }

    @Override
   public void onCardDraw(DestCard card1, DestCard card2, DestCard card3) {
        if (window != null) {
            return;
        }

        new Handler(this.getMainLooper()).postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        final View popupView = ((LayoutInflater)
                                getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.draw_popup,
                                null);

                        int height = DestCardActivity.this.getWindow().getDecorView().getHeight();
                        int width = DestCardActivity.this.getWindow().getDecorView().getWidth();
                        window = new PopupWindow(popupView, width, height, false);
                        window.showAtLocation(new View(DestCardActivity.this), Gravity.CENTER, 0, 0);
                        enableCardButtons();
                        popupView.findViewById(R.id.drawDestConfirm).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                disableCardButtons();
                                presenter.finishDrawing();
                            }
                        });
                        setCards();
                    }
                }, 500);

        button.setEnabled(false);


        draw1 = card1;
        draw2 = card2;
        draw3 = card3;

        //cardsReturned = 0;
    }

    @Override
    public void onPause() {
        super.onPause();
        //presenter.finishDrawing();
        closeWindow();
    }

    @Override
    public void onCardReturn(DestCard card, ReturnCardLimit limit) {
        if (card != null) {
            if (card.equals(draw1)) {
                draw1 = null;
            }
            else if (card.equals(draw2)) {
                draw2 = null;
            }
            else if (card.equals(draw3)) {
                draw3 = null;
            }
            setCards();
            enableCardButtons();
        }
        if (cardsReturned >= limit.value()) {
            disableCardButtons();
            presenter.finishDrawing();
        }
    }

    @Override
    public void SetDeckSize(int size) {
        TextView cardsLeft = findViewById(R.id.destCardsLeft);
        cardsLeft.setText(size + " cards left");
    }

    @Override
    public void FinishedDrawing() {
        closeWindow();
    }

    @Override
    public void SetNumReturned(int numReturned) {
        cardsReturned = numReturned;
    }


    private void setCards() {
        setDestCard(window.getContentView().findViewById(R.id.drawDest1), draw1);
        setDestCard(window.getContentView().findViewById(R.id.drawDest2), draw2);
        setDestCard(window.getContentView().findViewById(R.id.drawDest3), draw3);
    }

    private void disableCardButtons() {
        setCardButton(R.id.drawDest1, false);
        setCardButton(R.id.drawDest2, false);
        setCardButton(R.id.drawDest3, false);
    }

    private void enableCardButtons() {
        setCardButton(R.id.drawDest1, true);
        setCardButton(R.id.drawDest2, true);
        setCardButton(R.id.drawDest3, true);
    }

    private void setCardButton(int id, boolean active) {
        if (window != null) {
            window.getContentView().findViewById(id).findViewById(R.id.returnButton).setEnabled(active);
        }
    }

    private void setDestCard(View view, final DestCard card) {
        if (card == null) {
            ((TextView)view.findViewById(R.id.city1Select)).setText("");
            ((TextView)view.findViewById(R.id.city2Select)).setText("");
            ((TextView)view.findViewById(R.id.pointsSelect)).setText("");
            view.findViewById(R.id.returnButton).setEnabled(false);
        }
        else {
            ((TextView)view.findViewById(R.id.city1Select)).setText(card.getCity1().toString());
            ((TextView)view.findViewById(R.id.city2Select)).setText(card.getCity2().toString());
            ((TextView)view.findViewById(R.id.pointsSelect)).setText(card.getPoints() + " pts");
            final Button returnButton = view.findViewById(R.id.returnButton);
            returnButton.setEnabled(true);
            returnButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.returnCard(card);
                }
            });
        }
    }

    private class DestCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<DestCard> cards;

        public DestCardAdapter() {
            cards = new ArrayList<>();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(DestCardActivity.this);
            return new DestCardHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((DestCardHolder) viewHolder).bind(cards.get(i));
        }

        @Override
        public int getItemCount() {
            return cards.size();
        }

        public void setCards(List<DestCard> cards) {

            this.cards = cards;
            this.notifyDataSetChanged();
        }

        public void addCard(DestCard card) {
            cards.add(card);
            this.notifyItemInserted(cards.size() - 1);
        }
/*
        public void removeCard(DestCard card) {
            int pos = cards.indexOf(card);
            cards.remove(pos);
            this.notifyItemRemoved(pos);
        }*/

        private class DestCardHolder extends RecyclerView.ViewHolder {

            private TextView city1;
            private TextView city2;
            private TextView points;

            public DestCardHolder(LayoutInflater inflater, @NonNull ViewGroup viewGroup) {
                super(inflater.inflate(R.layout.list_item_dest_card, viewGroup, false));

                city1 = itemView.findViewById(R.id.City1);
                city2 = itemView.findViewById(R.id.City2);
                points = itemView.findViewById(R.id.points);
            }

            public void bind(DestCard card) {
                city1.setText(card.getCity1().toString());
                city2.setText(card.getCity2().toString());
                points.setText("" + card.getPoints());
            }
        }
    }
}
