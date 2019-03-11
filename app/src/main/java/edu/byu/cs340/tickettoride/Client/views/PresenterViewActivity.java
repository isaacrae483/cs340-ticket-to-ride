package edu.byu.cs340.tickettoride.Client.views;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import edu.byu.cs340.tickettoride.Client.presenters.IPresenter;

/**
 * Created by Thomas Lewis on 2/10/19.
 */
abstract class PresenterViewActivity extends AppCompatActivity implements IPresenterView{
    private IPresenter mPresenter;

    /**Set the presenter to be used with this view. Allows the presenter to see changes in the PresenterViewActivity's lifecycle
     *
     * @param presenter The presenter you want to be associated with this view
     */
    protected void setPresenter(IPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.viewResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.viewPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.viewDestroyed();
    }

    public void makeToast(String toastText) {
        Toast.makeText(this.getApplicationContext(), toastText, Toast.LENGTH_LONG).show();
    }
}
