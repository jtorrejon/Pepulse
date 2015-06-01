package pack.pepulse.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.ProgressBar;

/**
 * Created by pepe on 22/01/15.
 */
public class SplashScreenActivity extends Activity {

    private static final int seconds = 5;
    private static final int miliseconds = seconds*1000;
    private static final int delay = 2;
    private ProgressBar pbar;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_screen);
        pbar = (ProgressBar) findViewById(R.id.splash_bar);
        pbar.setMax(maxProgress());
        startAnimation();

    }

    public void startAnimation(){

        new CountDownTimer(miliseconds,1000){


            @Override
            public void onTick(long millisUntilFinished) {

                pbar.setProgress(establish_progress(millisUntilFinished));
            }

            @Override
            public void onFinish() {

                Intent i = new Intent(SplashScreenActivity.this, DeviceListActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }

    public int establish_progress(long millis){

        return (int)(miliseconds - millis)/1000;
    }

    public int maxProgress(){

        return seconds-delay;
    }
}
