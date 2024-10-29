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
        profile.add(Answer(question, Bool.FALSE))
        criteria.add(
            Criterion(
                Answer(question, Bool.TRUE),
                Weight.MustMatch
            )
        )

        // when
        val matches = profile.matches(criteria)

        // then
        assertFalse { matches }
    }

    @Test
    fun matchAnswersTrueForAnyDontCareCriteria() {
        // given
        profile.add(Answer(question, Bool.FALSE))
        criteria.add(
            Criterion(
                Answer(question, Bool.TRUE),
                Weight.DontCare
            )
        )

        // when
        val matches = profile.matches(criteria)

        // then
        assertTrue { matches }
    }
}