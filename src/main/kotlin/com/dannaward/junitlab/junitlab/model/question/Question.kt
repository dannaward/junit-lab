package com.dannaward.junitlab.junitlab.model.question

import com.dannaward.junitlab.junitlab.model.answer.Answer

abstract class Question(
    private val id: Int,
    private val text: String,
    private val answerChoices: Array<String>
) {
    open fun getText(): String = text

    fun getAnswerChoice(i: Int): String = answerChoices[i]

    fun match(answer: Answer): Boolean = false

    abstract fun match(expected: Int, actual: Int): Boolean

    fun indexOf(matchingAnswerChoice: String): Int {
        for (i in answerChoices.indices) if (answerChoices[i] == matchingAnswerChoice) return i
        return -1
    }
}