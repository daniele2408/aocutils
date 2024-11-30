package org.structure

class SolutionRunner(
    val inputsFileFolder: String,
    val sampleFileFolder: String,
    val solutions: List<SolutionBundle>,
    val skipAllButLast: Boolean = true
) {

    fun run() {
        val (toSkip, toRun) = if (skipAllButLast) {
            solutions.subList(0, solutions.size-1) to listOf(solutions.last())
        } else {
            solutions.partition { it.skip }
        }
        if (toSkip.isNotEmpty()) println("Going to skip solution${if (toSkip.size > 1) "s" else ""} ${toSkip.joinToString(", ") { it.dayNumber.toString() }}")
        toRun.forEach { solutionBundle ->
            val dayInput = solutionBundle.dayNumber
            val decoHeader = "#".repeat(20)
            val sampleFilepath = generateFilenameSample(dayInput)
            val inputFilepath = generateFilenameInput(dayInput)
            println("$decoHeader RUN DAY $dayInput $decoHeader")
            val (sampleRes, mainRes) = solutionBundle.part1.run(sampleFilepath, inputFilepath)
            println(" [PART 1]")
            println(printResult("SAMPLE RESULT", sampleRes))
            println(printResult("MAIN RESULT", mainRes))
            val (sampleResPart2, mainResPart2) = solutionBundle.part2.run(sampleFilepath, inputFilepath)
            println(" [PART 2]")
            println(printResult("SAMPLE RESULT", sampleResPart2))
            println(printResult("MAIN RESULT", mainResPart2))
        }
    }

    private fun generateFilenameSample(dayNumber : Int) =
        "$sampleFileFolder/"+ "day${dayNumber.toString().padStart(2, '0')}.txt"

    private fun generateFilenameInput(dayNumber : Int) =
        "$inputsFileFolder/"+ "day${dayNumber.toString().padStart(2, '0')}.txt"

    private fun printResult(header: String, res: Number?) : String {
        if (res != null) {
            return "\t $header: $res"
        }
        return "\t $header SOLUTION YET NOT IMPLEMENTED"
    }

}