package com.example.myapp;

public class Question {
    private int nameQuestion;
    private boolean answerCheck;

    Question(int questionName, boolean checkAnswer){
        nameQuestion = questionName;
        answerCheck = checkAnswer;
    }

    public int getNameQuestion() {
        return nameQuestion;
    }

    public void setNameQuestion(int nameQuestion) {
        this.nameQuestion = nameQuestion;
    }

    public boolean isAnswerCheck() {
        return answerCheck;
    }

    public void setAnswerCheck(boolean answerCheck) {
        this.answerCheck = answerCheck;
    }
}
