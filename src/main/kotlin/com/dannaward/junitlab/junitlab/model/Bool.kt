package com.dannaward.junitlab.junitlab.model

enum class Bool(
    private val value: Int
) {
    False(0),
    True(1);

    companion object {
        const val FALSE: Int = 0
        const val TRUE: Int = 1
    }

    fun getValue(): Int = value
}