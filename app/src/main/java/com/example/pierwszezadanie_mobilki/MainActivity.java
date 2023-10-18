package com.example.pierwszezadanie_mobilki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private TextView pointsTextView;

    private Question[] questions = new Question[] {
            new Question(R.string.q_activity,true),
            new Question(R.string.q_find_resources,false),
            new Question(R.string.q_listener,true),
            new Question(R.string.q_resources,true),
            new Question(R.string.q_version,false),
    };

    private int currentIndex = 0;
    private int pointsForGoodAnswer = 0;
    private String pointsString = "Punkty: 0";

    protected void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
        pointsTextView.setText(pointsString);
    }

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
            pointsForGoodAnswer += 1;
            pointsString = "Punkty: " + pointsForGoodAnswer;
        } else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.button_true);
        falseButton = findViewById(R.id.button_false);
        nextButton = findViewById(R.id.button_next);
        questionTextView = findViewById(R.id.question_text_view);
        pointsTextView = findViewById(R.id.points_Text_View);

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
                setNextQuestion();
            }
        });
        setNextQuestion();
    }
}