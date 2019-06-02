package com.example.puzzlegame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static private final String TAG = MainActivity.class.getSimpleName();
    private GameLogic gameLogic = new GameLogic();
    private TextView mScore;
    private TextView mTextViewCountDown;
    private Button mPlayButton;
    private List<Button> buttonsList = new ArrayList<>();
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long mEndTime;
    private int Score = 0;
    private static final long START_TIME_IN_MILLIS = 600000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonsList.add((Button) findViewById(R.id.row1button1));
        buttonsList.add((Button) findViewById(R.id.row1button2));
        buttonsList.add((Button) findViewById(R.id.row1button3));
        buttonsList.add((Button) findViewById(R.id.row1button4));
        buttonsList.add((Button) findViewById(R.id.row2Button1));
        buttonsList.add((Button) findViewById(R.id.row2Button2));
        buttonsList.add((Button) findViewById(R.id.row2Button3));
        buttonsList.add((Button) findViewById(R.id.row2Button4));
        buttonsList.add((Button) findViewById(R.id.row3Button1));
        buttonsList.add((Button) findViewById(R.id.row3Button2));
        buttonsList.add((Button) findViewById(R.id.row3Button3));
        buttonsList.add((Button) findViewById(R.id.row3Button4));
        buttonsList.add((Button) findViewById(R.id.row4Button1));
        buttonsList.add((Button) findViewById(R.id.row4Button2));
        buttonsList.add((Button) findViewById(R.id.row4Button3));
        buttonsList.add((Button) findViewById(R.id.row4Button4));
        mPlayButton = findViewById(R.id.Play);
        mTextViewCountDown = findViewById(R.id.textViewTime);
        mScore = findViewById(R.id.textViewScore);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void OnPlayAgainClick(View view) {
        HashMap buttonMap;
        for (int i = 0; i < 1000; i++) {
            Random rand = new Random();
            int n = rand.nextInt(16);
            String buttonValue = Integer.toString(n);
            gameLogic.ValidateButtonPress(buttonValue);
            buttonMap = gameLogic.Maps.getButtonMap();
            setButtonText(buttonMap);
            Score = 0;

        }
        mScore.setText("0");
    }


    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        mTimerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();

        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();

            } else {
                startTimer();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void OnPlayClick(View view) {
        for (int i = 0; i < 1000; i++) {
            Random rand = new Random();
            int n = rand.nextInt(16);
            String buttonValue = Integer.toString(n);
            gameLogic.ValidateButtonPress(buttonValue);
            setButtonText(gameLogic.Maps.getButtonMap());
            Score = 0;
            mScore.setText("0");
            resetTimer();
            startTimer();
        }

    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onButtonClick(View view) {
        String buttonValue;
        switch (view.getId()) {
            case R.id.row1button1:
                buttonValue = "1";
                buttonPressActions(buttonValue);
                break;
            case R.id.row1button2:
                buttonValue = "2";
                buttonPressActions(buttonValue);
                break;
            case R.id.row1button3:
                buttonValue = "3";
                buttonPressActions(buttonValue);
                break;
            case R.id.row1button4:
                buttonValue = "4";
                buttonPressActions(buttonValue);
                break;
            case R.id.row2Button1:
                buttonValue = "5";
                buttonPressActions(buttonValue);
                break;
            case R.id.row2Button2:
                buttonValue = "6";
                buttonPressActions(buttonValue);
                break;
            case R.id.row2Button3:
                buttonValue = "7";
                buttonPressActions(buttonValue);
                break;
            case R.id.row2Button4:
                buttonValue = "8";
                buttonPressActions(buttonValue);
                break;
            case R.id.row3Button1:
                buttonValue = "9";
                buttonPressActions(buttonValue);
                break;
            case R.id.row3Button2:
                buttonValue = "10";
                buttonPressActions(buttonValue);
                break;
            case R.id.row3Button3:
                buttonValue = "11";
                buttonPressActions(buttonValue);
                break;
            case R.id.row3Button4:
                buttonValue = "12";
                buttonPressActions(buttonValue);
                break;
            case R.id.row4Button1:
                buttonValue = "13";
                buttonPressActions(buttonValue);
                break;
            case R.id.row4Button2:
                buttonValue = "14";
                buttonPressActions(buttonValue);
                break;
            case R.id.row4Button3:
                buttonValue = "15";
                buttonPressActions(buttonValue);
                break;
            case R.id.row4Button4:
                buttonValue = "16";
                buttonPressActions(buttonValue);
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void buttonPressActions(String buttonValue) {
        gameLogic.ValidateButtonPress(buttonValue);
        setButtonText(gameLogic.Maps.buttonMap);
        Score++;
        mScore.setText(Integer.toString(Score));
        if (gameLogic.Maps.win) {
            mPlayButton.setText("WIN");
        }
    }

    void setButtonText(HashMap<Integer, Integer> ButtonMap) {

        for (int i = 1; i < 17; i++) {

            if (ButtonMap.get(i) == null) {
                // to find the correct button in the button list button index has to be -1 from the looping index
                buttonsList.get(i - 1).setText("");
            } else {
                buttonsList.get(i - 1).setText(Integer.toString(ButtonMap.get(i)));
            }

        }
    }
}
