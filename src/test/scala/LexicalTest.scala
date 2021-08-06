class LexicalTest extends BaseTest {
  val lexicalVocabularyUrl = "file://src/main/vocabularies/lexical.yaml"
  val lexicalDialectUrl    = "file://src/main/dialects/lexical.yaml"

  test("Lexical vocabulary should conform") { vocabularyShouldConform(lexicalVocabularyUrl) }

  test("Lexical dialect library should conform") { dialectLibraryShouldConform(lexicalDialectUrl) }

}
