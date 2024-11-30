package org.structure

import org.structure.getInputRows

class SolutionPart(
    private val sampleFunc: (List<String>) -> Number?,
    private val mainFunc: (List<String>) -> Number?,
    private val expectedSampleResult: Number?
) {

    constructor() : this({ null }, { null }, null)

    constructor(sampleFunc: (List<String>) -> Number, expectedSampleResult: Number) : this(sampleFunc, sampleFunc, expectedSampleResult)

    fun run(filenameSample: String, filenameInputs: String) : Pair<Number?, Number?> {
        val sampleResult = sampleFunc.invoke(getInputRows(filenameSample))
        require(sampleResult == expectedSampleResult) { "Expected $expectedSampleResult != Actual $sampleResult" }
        val mainResult = mainFunc.invoke(getInputRows(filenameInputs))
        return Pair(sampleResult, mainResult)
    }

}