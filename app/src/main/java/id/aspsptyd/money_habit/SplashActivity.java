package id.aspsptyd.money_habit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Handler holdOn;

    TextView tvSplashApp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvSplashApp = (TextView) findViewById(R.id.txWelcome);

        tvSplashApp.setText(getString(R.string.welcome_splash) + " " + getString(R.string.version_app));

        holdOn = new Handler();

        holdOn.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        }, 2500);

    }
}