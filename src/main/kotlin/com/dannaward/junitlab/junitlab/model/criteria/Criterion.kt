package com.dannaward.junitlab.junitlab.model.criteria

import com.dannaward.junitlab.junitlab.model.Answer
import com.dannaward.junitlab.junitlab.model.Weight

class Criterion(
    private val answer: Answer,
    private val weight: Weight
) : Scoreable {
    private var score: Int = 0

    fun getAnswer(): Answer = answer
    fun getWeight(): Weight = weight

    override fun getScore(): Int = score
    fun setScore(score: Int) {
        this.score = score
    }
}

