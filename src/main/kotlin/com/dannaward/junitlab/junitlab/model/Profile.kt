package com.dannaward.junitlab.junitlab.model


class Profile(
    private val name: String
) {
    private val answers: MutableMap<String, Answer> = hashMapOf()
    private var score = 0

    fun getName(): String = name

    fun add(answer: Answer) {
        answers[answer.getQuestionText()] = answer
    }

    fun matches(criteria: Criteria): Boolean {
        score = 0

        var kill = false
        var anyMatches = false
        for (criterion in criteria) {
            val answer: Answer = answers[criterion.getAnswer().getQuestionText()] ?: continue
            val match = criterion.getWeight() === Weight.DontCare || answer.match(criterion.getAnswer())

            if (!match && criterion.getWeight() === Weight.MustMatch) {
                kill = true
            }
            if (match) {
                score += criterion.getWeight().getValue()
            }
            anyMatches = anyMatches or match
        }
        if (kill) return false
        return anyMatches
    }

    fun score(): Int = score
}