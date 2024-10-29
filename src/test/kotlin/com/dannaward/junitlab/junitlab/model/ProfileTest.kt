package com.dannaward.junitlab.junitlab.model

import com.dannaward.junitlab.junitlab.model.criteria.Criteria
import com.dannaward.junitlab.junitlab.model.criteria.Criterion
import com.dannaward.junitlab.junitlab.model.question.BooleanQuestion
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class ProfileTest {
    private lateinit var profile: Profile
    private lateinit var question: BooleanQuestion
    private lateinit var criteria: Criteria

    @BeforeEach
    fun create() {
        profile = Profile("Bull Hockey, Inc.")
        question = BooleanQuestion(1, "Got bonuses?")
        criteria = Criteria()
    }

    @Test
    fun matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // given
        val profileAnswer = Answer(question, Bool.FALSE)

        val criteriaAnswer = Answer(question, Bool.TRUE)
        val criterion = Criterion(criteriaAnswer, Weight.MustMatch)

        profile.add(profileAnswer)
        criteria.add(criterion)

        // when
        val matches = profile.matches(criteria)

        // then
        assertFalse { matches }
    }

    @Test
    fun matchAnswersTrueForAnyDontCareCriteria() {
        // given
        val profileAnswer = Answer(question, Bool.FALSE)

        val criteriaAnswer = Answer(question, Bool.TRUE)
        val criterion = Criterion(criteriaAnswer, Weight.DontCare)

        profile.add(profileAnswer)
        criteria.add(criterion)

        // when
        val matches = profile.matches(criteria)

        // then
        assertTrue { matches }
    }
}