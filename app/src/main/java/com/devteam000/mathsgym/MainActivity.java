package com.devteam000.mathsgym;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public static TextView txtQues;
    public static TextView mScore;
    public static TextView mTimer;
    public static Button buttonOne;
    public static Button buttonTwo;
    public static Button buttonThree;
    public static Button buttonFour;
    public static Button playAgainBtn;
    private int Score = 0;

    public static Game game;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get TextViews
        mTextMessage = findViewById(R.id.message);
        mScore = findViewById(R.id.txtScore);
        mTimer = findViewById(R.id.txtTimer);
        txtQues = findViewById(R.id.question);

        // Getting buttons
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        playAgainBtn = findViewById(R.id.PlayAgain);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        game = new Game();

        txtQues.setText(Integer.toString(game.getValueOne()) + " + " + Integer.toString(game.getValueTwo()) + " = ?");

        game.countDownTimer.start();


        mScore.setText(String.format("%02d", Score)+ "/15");

        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.resetGame();
            }
        });
    }


    public void GetAnswer(View view)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        String text =  view.getTag().toString().trim();
        int i = Integer.parseInt(text);
        if (i == game.trueButton)
        {
            Score = game.updateScore();
            mScore.setText(String.format("%02d", Score)+ "/15");
            if(Score == 15)
            {
                Toast toast = Toast.makeText(context, "Game Won!", duration);
                toast.show();
                game.resetGame();
                mScore.setText("00/15");
            }
            Toast toast = Toast.makeText(context, "CORRECT", duration);
            toast.show();
        }
        game.setNext();
        txtQues.setText(Integer.toString(game.getValueOne()) + " + " + Integer.toString(game.getValueTwo()) + " = ?");
    }
}
