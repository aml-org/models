import amf.aml.client.scala.AMLConfiguration
import org.scalatest.flatspec._
import org.scalatest.matchers._

class DialectsValidationTest extends AsyncFlatSpec with should.Matchers {

  "Validation dialect" should "conform" in {
    val client = AMLConfiguration.predefined().baseUnitClient()
    for {
      parsing    <- client.parseDialect("file://src/main/dialects/validation-profile.yaml")
      validation <- client.validate(parsing.dialect)
    } yield {
      parsing.conforms && validation.conforms shouldBe true
    }
  }
}
