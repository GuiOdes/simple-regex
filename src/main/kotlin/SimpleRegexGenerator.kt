class SimpleRegexGenerator private constructor() {
    private var regexString = ""

    fun build() = Regex(regexString)

    fun containingCharacters(characterList: List<String>) = apply {
        characterList.forEach {
            regexString += "[$it]"
        }
    }

    fun occurrencesCount(count: Int) = apply { regexString += "{$count}" }

    fun atEndOfString() = apply { regexString += "$" }

    companion object {
        fun createRegex() = SimpleRegexGenerator()
    }

}