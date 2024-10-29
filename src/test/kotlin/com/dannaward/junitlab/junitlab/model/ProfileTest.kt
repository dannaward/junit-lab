package com.dannaward.junitlab.junitlab.model

import com.dannaward.junitlab.junitlab.model.criteria.Criteria
import com.dannaward.junitlab.junitlab.model.criteria.Criterion
import com.dannaward.junitlab.junitlab.model.question.BooleanQuestion
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class ProfileTest {
    @Test
    fun matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // given
        val question = BooleanQuestion(1, "Got bonuses?")

        val profile = Profile("Bull Hockey, Inc.")
        val profileAnswer = Answer(question, Bool.FALSE)

        val criteria = Criteria()
        val criteriaAnswer = Answer(question, Bool.TRUE)
        val criterion = Criterion(criteriaAnswer, Weight.MustMatch)

        profile.add(profileAnswer)
        criteria.add(criterion)

        // when
        val matches = profile.matches(criteria)

        // then
        assertFalse { matches }
    }
}