package edu.byu.cs340.tickettoride.Client.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import edu.byu.cs340.tickettoride.Client.presenters.ILoginPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.LoginPresenter;
import edu.byu.cs340.tickettoride.R;


/**
 * Login Activity
 *
 * Point of entry for the app
 */
public class LoginActivity extends PresenterViewActivity implements ILoginView {

    private Button mSignInButton;
    private Button mRegisterButton;
    private EditText mServerHostField;
    private EditText mUsernameField;
    private EditText mPasswordField;

    private ILoginPresenter mILoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mSignInButton = findViewById(R.id.signInButton);
        mRegisterButton = findViewById(R.id.registerButton);
        mServerHostField = findViewById(R.id.serverHostField);
        mUsernameField = findViewById(R.id.usernameField);
        mPasswordField = findViewById(R.id.passwordField);

        // Attach a login presenter to this PresenterViewActivity after all members have been defined
        LoginPresenter loginPresenter = new LoginPresenter(this);
        mILoginPresenter = loginPresenter;
        setPresenter(loginPresenter);

        // Attach event handlers
        mSignInButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                mILoginPresenter.loginPressed();
            }
        });

        mRegisterButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                mILoginPresenter.registerPressed();
            }
        });

        mServerHostField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mILoginPresenter.serverHostChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mUsernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mILoginPresenter.usernameChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mILoginPresenter.passwordChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void warnHost() {
        mServerHostField.setError(getString(R.string.url_incorrect_format));
    }

    public void warnPassword() {
        mPasswordField.setError(getString(R.string.password_incorrect_format));
    }

    @Override
    public void moveToGameList() {
        startActivity(GameListActivity.newIntent(getApplicationContext()));
    }

    public void warnUsername() {
        mUsernameField.setError(getString(R.string.username_incorrect_format));
    }

    @Override
    public void displayRegisterSuccess() {
        makeToast(getString(R.string.register_success));
    }

    @Override
    public void displayRegisterFailed() {
        makeToast(getString(R.string.register_failed));
    }

    @Override
    public void displayLoginSuccess() {
        makeToast(getString(R.string.login_success));
    }

    @Override
    public void displayLoginFailed() {
        makeToast(getString(R.string.login_failed));
    }

    @Override
    public void enableButtons(Boolean isEnabled) {
        mSignInButton.setEnabled(isEnabled);
        mRegisterButton.setEnabled(isEnabled);
    }
}
