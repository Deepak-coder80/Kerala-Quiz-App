package com.example.keralaquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.keralaquizapp.databinding.ActivityMainBinding;
import com.example.keralaquizapp.model.Questions;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private  int currentQuestionIndex =0;
    private Questions[] questionsBank = new Questions[] {
        //create question
        new Questions(R.string.question_populationstate,false),
        new Questions(R.string.question_cm,true),
        new Questions(R.string.question_geology,true),
        new Questions(R.string.question_dis,false),
        new Questions(R.string.question_news,true),
        new Questions(R.string.question_sports,true),
        new Questions(R.string.question_sportsman,true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.textView2.setText(questionsBank[currentQuestionIndex].getAnswerResId());

        binding.trueButton.setOnClickListener(view -> checkAnswer(true));
        binding.falseButton.setOnClickListener(view -> checkAnswer(false));

        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex+1) % questionsBank.length;

            updateQuestion();
        });

        binding.previousButton.setOnClickListener(view -> {
            if (currentQuestionIndex >0){
                currentQuestionIndex = (currentQuestionIndex-1)%questionsBank.length;
                updateQuestion();
            }
        });


    }

    private void checkAnswer(boolean userChoose){
        boolean answerTrue = questionsBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if (answerTrue == userChoose) {
            messageId = R.string.correct_answer;
        }else {
            messageId = R.string.wrong_answer;
        }
        Snackbar.make(binding.imageView,messageId,Snackbar.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        binding.textView2.setText(questionsBank[currentQuestionIndex].getAnswerResId());
    }
}