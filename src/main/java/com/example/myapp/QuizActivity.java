package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;


public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private final Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private TextView mResultQuiz;
    private Button mCheatButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView;
    private ImageButton mPrevButton;
    private Button mTryAgainButton;
    private static final String TAG = "QuizActivity";
    private static final String TAG_mCurrentIndex = "mCurrentIndex";
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;
    private double answerTrueCount;
    private static final String EXTRA_ANSWER_IS_TRUE = "extraAnswerIsTrue";
    ArrayList<Boolean> answerTrue = new ArrayList<>(mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "MainActivity: onCreate()");

        mTrueButton = findViewById(R.id.button_true);
        mFalseButton = findViewById(R.id.button_false);
        mNextButton = findViewById(R.id.next_button);
        mPrevButton = findViewById(R.id.prev_button);
        mResultQuiz = findViewById(R.id.resultView);
        mTryAgainButton = findViewById(R.id.try_again_button);
        mCheatButton = findViewById(R.id.cheat_button_quiz);


        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPrevButton.setOnClickListener(this);
        mTryAgainButton.setOnClickListener(this);
        mCheatButton.setOnClickListener(this);

        mQuestionTextView = findViewById(R.id.question_text_view);
        updateQuestion();

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            Log.d(TAG_mCurrentIndex, savedInstanceState.toString());
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstance");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    //Слушатель для всех кнопок в текущей вьюхе
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_true:
                checkAnswer(true);
                break;
            case R.id.button_false:
                checkAnswer(false);
                break;
            case R.id.next_button:
                if (mCurrentIndex < mQuestionBank.length - 1) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                }
                break;
            case R.id.prev_button:
                if (mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }
                break;
            case R.id.try_again_button:
                mCurrentIndex = 0;
                answerTrue.clear();
                answerTrueCount = 0;
                break;
            case R.id.cheat_button_quiz:
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerCheck();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivity(intent);
                break;
        }
        updateQuestion();
    }

    //Обновление текущего вопроса для пользователя. Вызывается каждый раз при ответе на вопрос
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getNameQuestion();
        mQuestionTextView.setText(question);
        mResultQuiz.setText("True answer: " + answerTrueCount(answerTrue) + " %");
    }

    //Проверка правильности введенного пользователем ответа
    private void checkAnswer(boolean userPassedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerCheck();
        int messageResId;
        if (answerIsTrue == userPassedTrue) {
            //messageResId = R.string.correct_toast;
            answerTrue.add(true);
            //mResultQuiz.setText("true");
        } else {
            //messageResId = R.string.incorrect_toast;
            answerTrue.add(false);
            //mResultQuiz.setText("false");
        }
        if (mCurrentIndex < mQuestionBank.length - 1) {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        }
    }

    //Подсчитывает правильные ответы пользователя в реальном времени и выводит процент правильных ответов
    private int answerTrueCount(ArrayList<Boolean> list){
        int allAnswer = 0;
        answerTrueCount = 0;
        for(Boolean o : list){
            if(o && answerTrueCount < mQuestionBank.length && allAnswer < mQuestionBank.length){
                answerTrueCount++;
            }
            allAnswer++;
        }
        return (int)((answerTrueCount / (double)mQuestionBank.length) * 100);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");
    }
}