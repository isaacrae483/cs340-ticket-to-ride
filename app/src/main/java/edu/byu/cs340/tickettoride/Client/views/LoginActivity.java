package edu.byu.cs340.tickettoride.Client.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.byu.cs340.tickettoride.Client.presenters.ILoginPresenter;
import edu.byu.cs340.tickettoride.Client.presenters.LoginPresenter;
import edu.byu.cs340.tickettoride.R;


/**
 * Login Activity
 *
 * Point of entry for the app
 */
public class LoginActivity extends AppCompatActivity {

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

        // Attach a login presenter to this View after all members have been defined
        mILoginPresenter = new LoginPresenter(this);

        // Attach event handlers
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mILoginPresenter.loginPressed();
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    protected void onDestroy() {
        super.onDestroy();
        mILoginPresenter.viewDestroyed();
    }

    public void displayToast(String text) {
        Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public void warnHost(String error) {
        mServerHostField.setError(error);
    }

    public void warnPassword(String error) {
        mPasswordField.setError(error);
    }

    public void warnUsername(String error) {
        mUsernameField.setError(error);
    }

    public void enableButtons(Boolean isEnabled) {
        mSignInButton.setEnabled(isEnabled);
        mRegisterButton.setEnabled(isEnabled);
    }
}
