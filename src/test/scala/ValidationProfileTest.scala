import amf.aml.client.scala.AMLConfiguration
import org.scalatest.Assertion
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers._

import scala.concurrent.Future

class ValidationProfileTest extends AsyncFunSuite with should.Matchers {
  private val validationProfileDialectUrl = "file://src/main/dialects/validation-profile.yaml"
  private val profiles                    = "src/main/instances/validation"

  test("Validation profile dialect should conform") {
    val client = AMLConfiguration.predefined().baseUnitClient()
    for {
      parsing    <- client.parseDialect(validationProfileDialectUrl)
      validation <- client.validate(parsing.dialect)
    } yield {
      parsing.conforms && validation.conforms shouldBe true
    }
  }

  test("Validation profile1 must conform") { instanceShouldConform(s"file://$profiles/profile1.yaml") }

  test("Validation profile2 must conform") { instanceShouldConform(s"file://$profiles/profile2.yaml") }

  test("Validation profile3 must conform") { instanceShouldConform(s"file://$profiles/profile3.yaml") }

  test("Validation profile4 must conform") { instanceShouldConform(s"file://$profiles/profile4.yaml") }

  test("Validation profile5 must conform") { instanceShouldConform(s"file://$profiles/profile5.yaml") }

  test("Validation profile6 must conform") { instanceShouldConform(s"file://$profiles/profile6.yaml") }

  test("Validation profile7 must conform") { instanceShouldConform(s"file://$profiles/profile7.yaml") }

  test("Validation profile8 must conform") { instanceShouldConform(s"file://$profiles/profile8.yaml") }

  test("Validation profile9 must conform") { instanceShouldConform(s"file://$profiles/profile9.yaml") }

  test("Validation profile10 must conform") { instanceShouldConform(s"file://$profiles/profile10.yaml") }

  test("Validation profile11 must conform") { instanceShouldConform(s"file://$profiles/profile11.yaml") }

  test("Validation profile12 must conform") { instanceShouldConform(s"file://$profiles/profile12.yaml") }

  test("Validation profile13 must conform") { instanceShouldConform(s"file://$profiles/profile13.yaml") }

  def instanceShouldConform(instanceUrl: String): Future[Assertion] = {
    for {
      config     <- AMLConfiguration.predefined().withDialect(validationProfileDialectUrl)
      client     <- Future.successful(config.baseUnitClient())
      parsing    <- client.parseDialectInstance(instanceUrl)
      validation <- client.validate(parsing.dialectInstance)
    } yield {
      parsing.conforms && validation.conforms shouldBe true
    }
  }
}
