package com.dannaward.junitlab.junitlab.model

abstract class Question(private val id: Int, val text: String, private val answerChoices: Array<String>) {
    fun getText(): String = text

    fun getAnswerChoice(i: Int): String = answerChoices[i]

    fun match(answer: Answer): Boolean = false

    abstract fun match(expected: Int, actual: Int): Boolean

    fun indexOf(matchingAnswerChoice: String): Int {
        for (i in answerChoices.indices) if (answerChoices[i] == matchingAnswerChoice) return i
        return -1
    }
}