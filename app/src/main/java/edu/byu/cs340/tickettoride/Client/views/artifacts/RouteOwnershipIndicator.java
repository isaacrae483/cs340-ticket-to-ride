package edu.byu.cs340.tickettoride.Client.views.artifacts;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import edu.byu.cs340.tickettoride.R;
import edu.byu.cs340.tickettoride.shared.Interface.IPlayer;

/**
 * Created by Thomas Lewis on 3/3/19.
 */
public class RouteOwnershipIndicator extends AppCompatImageView implements IRouterOwnershipIndicator {


    public RouteOwnershipIndicator(Context context) {
        super(context);
        init();
    }

    public RouteOwnershipIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RouteOwnershipIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setColor(IPlayer.Color color) {
        switch (color) {
            case BLACK:
                this.setBackgroundResource(R.drawable.ic_rhomb_outline);
                break;
            case BLUE:
                this.setBackgroundResource(R.drawable.ic_rhomb_outline);
                break;
            case RED:
                this.setBackgroundResource(R.drawable.ic_rhomb_outline);
                break;
            case GREEN:
                this.setBackgroundResource(R.drawable.ic_rhomb_outline);
                break;
            case YELLOW:
                this.setBackgroundResource(R.drawable.ic_rhomb_outline);
                break;
                default:
                    this.setBackgroundResource(R.drawable.ic_rhomb_outline);
        }
    }

    private void init() {
        this.setBackgroundResource(R.drawable.ic_rhomb_outline);
    }
}
