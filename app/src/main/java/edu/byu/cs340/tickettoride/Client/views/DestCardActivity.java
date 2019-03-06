package edu.byu.cs340.tickettoride.Client.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs340.tickettoride.Client.presenters.DestCardPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.IDestCardPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.Presenter;
import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Game.Cards.DestCard;
import edu.byu.cs340.tickettoride.shared.Game.Decks.DestCardDeck;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

public class DestCardActivity extends PresenterViewActivity implements IDestCardActivity {

    private IDestCardPresenter p;

    private Button button;
    private RecyclerView list;
    private DestCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = new DestCardPresenter(this);
        this.setPresenter(p);
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
        p.drawPressed();
    }

    @Override
   public void OnCardDraw(DestCard card1, DestCard card2, DestCard card3) {

        View popupView = ((LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.draw_popup, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, false);
        popupWindow.getBackground();
        popupWindow.showAtLocation(new View(this), Gravity.CENTER, 0, 0);

        button.setEnabled(false);

        popupView.findViewById(R.id.drawDestConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(true);
                popupWindow.dismiss();
            }
        });
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
        }

        public void addCard(DestCard card) {
            cards.add(card);
            this.notifyItemInserted(cards.size() - 1);
        }

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
