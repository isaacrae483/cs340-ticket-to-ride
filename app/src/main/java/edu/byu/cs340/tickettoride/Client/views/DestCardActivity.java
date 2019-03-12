package edu.byu.cs340.tickettoride.Client.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class DestCardActivity extends PresenterViewActivity implements IDestCardActivity {

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

    @Override
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
        window.dismiss();
        button.setEnabled(true);
    }

    @Override
   public void onCardDraw(DestCard card1, DestCard card2, DestCard card3) {

        View popupView = ((LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.draw_popup, null);


        if (window == null) {
            int height = this.getWindow().getDecorView().getHeight();
            int width = this.getWindow().getDecorView().getWidth();
            window = new PopupWindow(popupView, width, height, false);
        }

        window.showAtLocation(new View(this), Gravity.CENTER, 0, 0);
        button.setEnabled(false);

        popupView.findViewById(R.id.drawDestConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeWindow();
            }
        });

        draw1 = card1;
        draw2 = card2;
        draw3 = card3;
        setCards();

        cardsReturned = 0;
    }

    @Override
    public void onCardReturn(DestCard card, ReturnCardLimit limit) {
        ++cardsReturned;
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
        }
        if (cardsReturned >= limit.value()) {
            closeWindow();
        }
    }

    @Override
    public void SetDeckSize(int size) {
        TextView cardsLeft = findViewById(R.id.destCardsLeft);
        cardsLeft.setText(size + " cards left");
    }

    private void setCards() {
        setDestCard(window.getContentView().findViewById(R.id.drawDest1), draw1);
        setDestCard(window.getContentView().findViewById(R.id.drawDest2), draw2);
        setDestCard(window.getContentView().findViewById(R.id.drawDest3), draw3);
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
