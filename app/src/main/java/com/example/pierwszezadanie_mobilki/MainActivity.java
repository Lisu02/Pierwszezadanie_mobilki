package com.example.pierwszezadanie_mobilki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.annotation.Nullable;


public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.example.pierwszezadanie_mobilki.answerShown";
    private static final int REQUEST_CODE_PROMPT = 0;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button podpowiedzButton;
    private TextView questionTextView;
    private TextView pointsTextView;

    private boolean answerWasShown = false;

    private Question[] questions = new Question[] {
            new Question(R.string.q_activity,true),
            new Question(R.string.q_find_resources,false),
            new Question(R.string.q_listener,true),
            new Question(R.string.q_resources,true),
            new Question(R.string.q_version,false),
    };

    private int currentIndex = 0;
    private int pointsForGoodAnswer = 0;
    private boolean questionAnswered;
    private String pointsString = "Punkty: 0";

    protected void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
        pointsTextView.setText(pointsString);
    }



    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        } else
            {
                if (userAnswer == correctAnswer){
                 resultMessageId = R.string.correct_answer;
                 if(questionAnswered == false)
                 {
                     pointsForGoodAnswer += 1;
                     pointsString = "Punkty: " + pointsForGoodAnswer;
                     questionAnswered = true;
                 }

             } else {
                  resultMessageId = R.string.incorrect_answer;
            }
            }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected  void onActivityResult (int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode != RESULT_OK) {return;}
        if(requestCode == REQUEST_CODE_PROMPT){
            if(data == null){return;}
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN,false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Create","Start metody onCreate");

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton = findViewById(R.id.button_true);
        falseButton = findViewById(R.id.button_false);
        nextButton = findViewById(R.id.button_next);
        questionTextView = findViewById(R.id.question_text_view);
        pointsTextView = findViewById(R.id.points_Text_View);

        podpowiedzButton = findViewById(R.id.button_podpowiedz);


        podpowiedzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PromptActivity.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER,correctAnswer);
                startActivityForResult(intent,REQUEST_CODE_PROMPT);

            }

            });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
             checkAnswerCorrectness(false);
         }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex = (currentIndex + 1)%questions.length;
                questionAnswered = false;
                answerWasShown = false;
                setNextQuestion();
            }
        });
        setNextQuestion();

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart","Uruchomienie cyklu onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","Uruchomienie cyklu onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause","Uruchomienie cyklu onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","Uruchomienie cyklu onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","Uruchomienie cyklu onDestroy");
        //onSaveInstanceState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("QUIZ_TAG","Wywołana została metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX,currentIndex);
    }

}