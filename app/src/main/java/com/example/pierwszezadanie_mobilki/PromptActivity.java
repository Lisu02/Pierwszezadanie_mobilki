package com.example.pierwszezadanie_mobilki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.annotation.Nullable;

public class PromptActivity extends AppCompatActivity {

    public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.pierwszezadanie_mobilki.answerShown";

    private boolean correctAnswer;
    private TextView pytanie;
    private TextView answerTextView;

    private Button sprawdzButton;

    private void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN,answerWasShown);
        setResult(RESULT_OK,resultIntent);
    }
    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        Log.d("CreatePrompt","Start metody onCreatePrompt");
        sprawdzButton = findViewById(R.id.button_sprawdz);
        answerTextView = findViewById(R.id.textViewOdpowiedz);
        pytanie = findViewById(R.id.textView);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER,true);

        sprawdzButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //boolean correctAnswer = false;
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);

                setAnswerShownResult(true);
            }
        });
    }


    ////

}