import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

class SimpleRegexGeneratorTest {

    @Test
    fun `should validate only the strings which matches the regex`() {
        val string = "abc"
        val anotherString = "cba"

        val regex = SimpleRegexGenerator
            .createRegex()
            .containingCharacters(listOf("a", "b"))
            .containingCharacters(listOf("c"))
            .build()

        assertTrue { string.matches(regex) && anotherString.matches(regex).not()}
    }

    @Test
    fun `should validate character count of the regex`() {
        val string = "moo"
        val anotherString = "bee"

        val regex = SimpleRegexGenerator
            .createRegex()
            .containingCharacters(listOf("o"))
            .occurrencesCount(2)
            .build()

        assertTrue { string.contains(regex) && anotherString.contains(regex).not() }
    }

    @Test
    fun `should validate character count of the regex at end of string`() {
        val string = "moo"
        val anotherString = "bee"

        val regex = SimpleRegexGenerator
            .createRegex()
            .containingCharacters(listOf("o"))
            .occurrencesCount(2)
            .atEndOfString()
            .build()

        val anotherRegex = SimpleRegexGenerator
            .createRegex()
            .containingCharacters(listOf("e"))
            .atEndOfString()
            .build()

        assertTrue { string.contains(regex) && anotherString.contains(anotherRegex) }
    }
}