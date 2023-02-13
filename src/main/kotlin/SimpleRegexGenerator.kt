import enums.Preset

class SimpleRegexGenerator private constructor() {
    private var regexString = ""

    fun build() = Regex(regexString)

    fun containingSequences(sequenceList: List<String>) = apply {
        sequenceList.forEach {
            regexString += "[$it]"
        }
    }

    fun occurrencesCount(count: Int) = apply { regexString += "{$count}" }

    fun atEndOfSequence() = apply { regexString += "$" }

    fun atStartOfSequence() = apply { regexString = "^$regexString" }

    override fun toString() = regexString

    fun ofPreset(preset: Preset) = apply {
        regexString = when (preset) {
            Preset.CEP -> "[0-9]{5}[-][0-9]{3}"
            Preset.CNPJ -> "[0-9]{2}[\\.][0-9]{3}[\\.][0-9]{3}[/][0-9]{4}[-][0-9]{2}"
            Preset.CPF -> "[0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}[-][0-9]{2}"
        }
    }

    companion object {
        fun createRegex() = SimpleRegexGenerator()
    }

}