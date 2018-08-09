package com.devteam000.mathsgym;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class Game extends Timer {
    // Defining variables
    private int Score;
    private int currentAns;
    private int valueOne;
    private int valueTwo;
    private int falseValueOne;
    private int falseValueTwo;
    private int falseValueThree;

    public int trueButton;


    private Random rand = new Random();

    public Game() {
        resetGame();
    }

    // Getters
    public int getScore() {
        return Score;
    }

    public int getValueOne()
    {
        return valueOne;
    }

    public int getValueTwo()
    {
        return valueTwo;
    }

    // Setters


    // Reset Game
    public void resetGame()
    {
        Score = 0;
        setNext();
    }

    public int updateScore()
    {
        Score++;
        return Score;
    }

    public void setNext()
    {
        generateValues();
    }

    private void generateValues()
    {
        // Generate Two values
        do {
            valueOne = rand.nextInt(100+1);
        }
        while (valueOne==0);
        do {
            valueTwo = rand.nextInt(100+1);
        }
        while (valueTwo==0);

        currentAns = valueOne + valueTwo;

        // Generate three false answers
        do {
            falseValueOne = rand.nextInt(currentAns+11) + (currentAns-10);
        }
        while (falseValueOne==0 && ((falseValueOne%10)==(currentAns%10)));
        do {
            falseValueTwo = rand.nextInt(currentAns+11) + (currentAns-10);
        }
        while (falseValueTwo==0);
        do {
            falseValueThree = rand.nextInt(currentAns+11) + (currentAns-10);
        }
        while (falseValueThree==0 && ((falseValueThree%10)==(currentAns%10)));

        Log.e( "generateValues: ", Integer.toString(currentAns%10));

        // Id for true button
        trueButton = rand.nextInt(4);
        SetValue();
    }

    public void SetValue()
    {
        switch (trueButton)
        {
            case 0:
                MainActivity.buttonOne.setText(Integer.toString(currentAns));
                MainActivity.buttonTwo.setText(Integer.toString(falseValueOne));
                MainActivity.buttonThree.setText(Integer.toString(falseValueTwo));
                MainActivity.buttonFour.setText(Integer.toString(falseValueThree));
                break;
            case 1:
                MainActivity.buttonOne.setText(Integer.toString(falseValueOne));
                MainActivity.buttonTwo.setText(Integer.toString(currentAns));
                MainActivity.buttonThree.setText(Integer.toString(falseValueTwo));
                MainActivity.buttonFour.setText(Integer.toString(falseValueThree));
                break;
            case 2:
                MainActivity.buttonOne.setText(Integer.toString(falseValueOne));
                MainActivity.buttonTwo.setText(Integer.toString(falseValueTwo));
                MainActivity.buttonThree.setText(Integer.toString(currentAns));
                MainActivity.buttonFour.setText(Integer.toString(falseValueThree));
                break;
            case 3:
                MainActivity.buttonOne.setText(Integer.toString(falseValueOne));
                MainActivity.buttonTwo.setText(Integer.toString(falseValueTwo));
                MainActivity.buttonThree.setText(Integer.toString(falseValueThree));
                MainActivity.buttonFour.setText(Integer.toString(currentAns));
                break;
        }
    }
}
