package org.structure

class SolutionBundle(
    val dayNumber: Int,
    val part1: SolutionPart,
    val part2: SolutionPart = SolutionPart(),
    val skip: Boolean = false
)