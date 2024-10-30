package com.dannaward.junitlab.junitlab.model

import com.dannaward.junitlab.junitlab.model.answer.BooleanAnswer
import com.dannaward.junitlab.junitlab.model.question.BooleanQuestion
import com.dannaward.junitlab.junitlab.model.question.Question
import java.util.concurrent.atomic.AtomicInteger


class StatCompiler {
    private val controller = QuestionController()

    fun responsesByQuestion(
        answers: List<BooleanAnswer>
    ): Map<String, Map<Boolean, AtomicInteger>> {
        val responses = mutableMapOf<Int, Map<Boolean, AtomicInteger>>()
        answers.forEach {
            incrementHistogram(responses, it)
        }
        return convertHistogramIdsToText(responses)
    }

    private fun convertHistogramIdsToText(
        responses: Map<Int, Map<Boolean, AtomicInteger>>
    ): Map<String, Map<Boolean, AtomicInteger>> {
        val textResponses = mutableMapOf<String, Map<Boolean, AtomicInteger>>()
        responses.keys.forEach {
            textResponses[controller.find(it).getText()] = responses[it]!!
        }
        return textResponses
    }

    private fun incrementHistogram(
        responses: MutableMap<Int, Map<Boolean, AtomicInteger>>,
        answer: BooleanAnswer
    ) {
        val histogram =
            getHistogram(responses, answer.questionId)
        histogram[answer.value]!!.getAndIncrement()
    }

    private fun getHistogram(
        responses: MutableMap<Int, Map<Boolean, AtomicInteger>>,
        id: Int
    ): Map<Boolean, AtomicInteger> {
        return if (responses.containsKey(id)) {
            responses[id]!!
        } else {
            val histogram = createNewHistogram()
            responses[id] = histogram
            histogram
        }
    }

    private fun createNewHistogram(): Map<Boolean, AtomicInteger> {
        return mutableMapOf<Boolean, AtomicInteger>().apply {
            put(false, AtomicInteger(0))
            put(true, AtomicInteger(0))
        }
    }

    companion object {
        val q1 = BooleanQuestion(1, "Tuition reimbursement?")
        val q2 = BooleanQuestion(2, "Relocation package?")
    }

    class QuestionController {
        fun find(id: Int): Question {
            return if (id == 1) q1
            else q2
        }
    }
}