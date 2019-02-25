package edu.byu.cs340.tickettoride.Client.views;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import edu.byu.cs340.tickettoride.Client.presenters.IPresenter;

/**
 * Created by Thomas Lewis on 2/24/19.
 */
abstract public class PresenterViewFragment extends Fragment implements IPresenterView{

    private IPresenter mPresenter;

    /**Set the presenter to be used with this view. Allows the presenter to see changes in the PresenterViewFragment's lifecycle
     *
     * @param presenter The presenter you want to be associated with this view
     */
    protected void setPresenter(IPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.viewResumed();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.viewPaused();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.viewDestroyed();
    }

    public void makeToast(String toastText) {
        Toast.makeText(this.getContext(), toastText, Toast.LENGTH_LONG).show();
    }
}
