package com.dannaward.junitlab.junitlab.model

enum class Weight(
    private val value: Int
) {
    MustMatch(Int.MAX_VALUE),
    VeryImportant(5000),
    Important(1000),
    WouldPrefer(100),
    DontCare(0);

    fun getValue(): Int = value
}