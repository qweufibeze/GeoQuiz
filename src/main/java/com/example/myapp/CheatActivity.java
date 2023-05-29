package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private Button mBackToQuizButton;
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.answer_is_true";
    private boolean mAnswerIsTrue;


    public static Intent newIntent (Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mBackToQuizButton = findViewById(R.id.back_to_qiuz_button);
        mBackToQuizButton.setOnClickListener(this);
        mAnswerTextView.setOnClickListener(this);
        mShowAnswerButton.setOnClickListener(this);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_answer_button:
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(String.valueOf(mAnswerIsTrue));
                }else{
                    mAnswerTextView.setText(String.valueOf(mAnswerIsTrue));
                }
                break;
            case R.id.back_to_qiuz_button:
                Intent intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
        }
    }
}