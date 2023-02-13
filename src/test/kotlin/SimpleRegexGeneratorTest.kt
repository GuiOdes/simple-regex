import enums.Preset
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

class SimpleRegexGeneratorTest {

    @Test
    fun `should validate only the strings which matches the regex`() {
        val string = "abc"
        val anotherString = "cba"

        val regex = SimpleRegexGenerator
            .createRegex()
            .containingSequences(listOf("a", "b"))
            .containingSequences(listOf("c"))
            .build()

        assertTrue { string.matches(regex) && anotherString.matches(regex).not()}
    }

    @Test
    fun `should validate character count of the regex`() {
        val string = "moo"
        val anotherString = "bee"

        val regex = SimpleRegexGenerator
            .createRegex()
            .containingSequences(listOf("o"))
            .occurrencesCount(2)
            .build()

        assertTrue { string.contains(regex) && anotherString.contains(regex).not() }
    }

    @Test
    fun `should validate character count of the regex at end of sequence`() {
        val string = "moo"
        val anotherString = "bee"

        val regex = SimpleRegexGenerator
            .createRegex()
            .containingSequences(listOf("o"))
            .occurrencesCount(2)
            .atEndOfSequence()
            .build()

        val anotherRegex = SimpleRegexGenerator
            .createRegex()
            .containingSequences(listOf("e"))
            .atEndOfSequence()
            .build()

        assertTrue { string.contains(regex) && anotherString.contains(anotherRegex) }
    }

    @Test
    fun `should matches regex of character at start of sequence`() {

        val string = "monday"

        val regex = SimpleRegexGenerator
            .createRegex()
            .containingSequences(listOf("m"))
            .containingSequences(listOf("o"))
            .atStartOfSequence()
            .build()

        val falseRegex = SimpleRegexGenerator
            .createRegex()
            .containingSequences(listOf("m"))
            .containingSequences(listOf("o"))
            .atEndOfSequence()
            .build()

        assertTrue { string.contains(regex) && string.contains(falseRegex).not() }
    }

    @Test
    fun `should validate documents and cep from presets`() {
        val regexCpf = SimpleRegexGenerator.createRegex().ofPreset(Preset.CPF).build()
        val regexCnpj = SimpleRegexGenerator.createRegex().ofPreset(Preset.CNPJ).build()
        val regexCep = SimpleRegexGenerator.createRegex().ofPreset(Preset.CEP).build()

        val cpf = "123.123.123-11"
        val cnpj = "12.345.678/0002-00"
        val cep = "12345-111"

        val fakeCpf = "123.123.123-1"
        val fakeCnpj = "12.345.678/002-00"
        val fakeCep = "12345111"

        assertTrue { cpf.matches(regexCpf) && cnpj.matches(regexCnpj) && cep.matches(regexCep) }
        assertFalse { fakeCpf.matches(regexCpf) || fakeCnpj.matches(regexCnpj) || fakeCep.matches(regexCep) }
    }
}