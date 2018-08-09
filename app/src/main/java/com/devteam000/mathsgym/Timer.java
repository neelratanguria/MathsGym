package com.devteam000.mathsgym;

import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;

import static com.devteam000.mathsgym.MainActivity.mTimer;

public abstract class Timer {

    private final String FORMAT = "%02d:%02d";

    public void StartTimer()
    {
        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimer.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {

            }
        };

    }

    CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTimer.setText("" + String.format(FORMAT,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
        }

        @Override
        public void onFinish() {
            mTimer.setText("00:00");
        }
    };
}
