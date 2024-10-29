package com.dannaward.junitlab.junitlab.model.criteria

import java.util.*
import kotlin.math.pow


class Criteria : Iterable<Criterion> {
    private val criteria: MutableList<Criterion> = ArrayList()

    fun add(criterion: Criterion) = criteria.add(criterion)

    override fun iterator(): Iterator<Criterion> = criteria.iterator()

    fun arithmeticMean(): Int = 0

    fun geometricMean(numbers: IntArray): Double {
        val totalProduct = numbers.fold(1) { product, number -> product * number }
        return totalProduct.toDouble().pow(1.0 / numbers.size)
    }
}