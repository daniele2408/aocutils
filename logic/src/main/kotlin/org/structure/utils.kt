package org.structure

fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(addSlashIfMissing(path))?.readText()

fun addSlashIfMissing(path: String) : String = if (!path.startsWith("/")) "/$path" else path

fun getInputRows(filename: String) : List<String> {
    return retrieveRowsFromFile(filename)
}

fun generateFilename(sample: Boolean, noDay: Int) =
    (if (sample) "sample/" else "inputs/") + "day${noDay.toString().padStart(2, '0')}.txt"

fun retrieveRowsFromFile(filePath: String): List<String> {
    val contentMatches = getResourceAsText(filePath)
    contentMatches ?: require(false) { "No file found at $filePath" }
    return splitTextFileInRows(contentMatches)
}

fun splitTextFileInRows(contentMatches: String?) = contentMatches?.split("\n") ?: emptyList()
