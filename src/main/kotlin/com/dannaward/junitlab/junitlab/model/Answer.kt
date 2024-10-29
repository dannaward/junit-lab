package com.dannaward.junitlab.junitlab.model

import com.dannaward.junitlab.junitlab.model.question.Question

class Answer {
    private var i: Int
    private var question: Question

    constructor(question: Question, i: Int) {
        this.question = question
        this.i = i
    }

    constructor(characteristic: Question, matchingValue: String) {
        this.question = characteristic
        this.i = characteristic.indexOf(matchingValue)
    }

    fun getQuestionText(): String = question.getText()

    override fun toString(): String = java.lang.String.format("%s %s", question.getText(), question.getAnswerChoice(i))

    fun match(expected: Int): Boolean = question.match(expected, i)

    fun match(otherAnswer: Answer): Boolean = question.match(i, otherAnswer.i)

    fun getCharacteristic(): Question = question
}