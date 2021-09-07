import amf.aml.client.scala.AMLConfiguration
import amf.core.client.scala.config.RenderOptions
import org.mulesoft.common.io.{AsyncFile, Fs, Utf8}
import org.mulesoft.common.test.Tests.checkDiff
import org.scalatest.Matchers.convertToAnyShouldWrapper
import org.scalatest.{Assertion, AsyncFunSuite}
import utils.RicherClasses._

import scala.concurrent.Future

trait BaseTest extends AsyncFunSuite {

  protected def baseConfig: AMLConfiguration = AMLConfiguration.predefined().withRenderOptions(RenderOptions().withCompactUris.withPrettyPrint)

  // -------------------------------------------------------------------------------------------------------------------
  // Validation --------------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  def instanceShouldConform(instanceUrl: String, dialectUrl: String): Future[Assertion] = {
    for {
      config     <- baseConfig.withDialect(dialectUrl)
      client     <- Future.successful(config.baseUnitClient())
      parsing    <- client.parseDialectInstance(instanceUrl)
      validation <- client.validate(parsing.dialectInstance)
    } yield {
      parsing.results ++ validation.results shouldBe Nil
    }
  }

  def dialectShouldConform(dialectUrl: String): Future[Assertion] = {
    val client = baseConfig.baseUnitClient()
    for {
      parsing    <- client.parseDialect(dialectUrl)
      validation <- client.validate(parsing.dialect)
    } yield {
      parsing.results ++ validation.results shouldBe Nil
    }
  }

  def dialectLibraryShouldConform(dialectLibraryUrl: String): Future[Assertion] = {
    val client = baseConfig.baseUnitClient()
    for {
      parsing    <- client.parse(dialectLibraryUrl)
      validation <- client.validate(parsing.baseUnit)
    } yield {
      parsing.results ++ validation.results shouldBe Nil
    }
  }

  def vocabularyShouldConform(vocabularyUrl: String): Future[Assertion] = {
    val client = baseConfig.baseUnitClient()
    for {
      parsing    <- client.parseVocabulary(vocabularyUrl)
      validation <- client.validate(parsing.vocabulary)
    } yield {
      parsing.results ++ validation.results shouldBe Nil
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Rendering ---------------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  protected def temporaryFile(content: String): Future[AsyncFile] = {
    val tmpPath = System.getProperty("java.io.tmpdir") + System.nanoTime() + ".tmp"
    val tmpFile = Fs.asyncFile(tmpPath)
    tmpFile.write(content).map(_ => tmpFile)
  }

  def renderedDialectShouldMatchGolden(dialectUrl: String, golden: String): Future[Assertion] = {
    val client = baseConfig.baseUnitClient()
    for {
      parsing   <- client.parseDialect(dialectUrl)
      content   <- Future.successful(client.render(parsing.dialect))
      actual    <- temporaryFile(content)
      expected  <- Future.successful(Fs.asyncFile(golden.stripProtocol))
      assertion <- checkDiff(actual, expected, Utf8)
    } yield {
      assertion
    }
  }

  def renderedDialectInstanceShouldMatchGolden(instanceUrl: String,
                                               goldenUrl: String,
                                               dialectUrl: String,
                                               mediaType: String = "application/ld+json"): Future[Assertion] = {
    for {
      config    <- baseConfig.withDialect(dialectUrl)
      client    <- Future.successful(config.baseUnitClient())
      parsing   <- client.parseDialectInstance(instanceUrl)
      actual    <- temporaryFile(client.render(parsing.dialectInstance, mediaType))
      expected  <- Future.successful(Fs.asyncFile(goldenUrl.stripProtocol))
      assertion <- checkDiff(actual, expected, Utf8)
    } yield {
      assertion
    }
  }
}
