class ValidationReportTest extends BaseTest {
  private val validationReportDialectUrl = "file://src/main/dialects/validation-report.yaml"
  private val reports                    = "file://src/main/instances/validation"

  private def reportShouldConform(reportUrl: String) = {
    instanceShouldConform(reportUrl, validationReportDialectUrl)
  }

  private def renderedProfileShouldMatchGolden(profileUrl: String, goldenUrl: String) = {
    renderedDialectInstanceShouldMatchGolden(profileUrl, goldenUrl, validationReportDialectUrl)
  }

  test("Validation report dialect should conform") { dialectShouldConform(validationReportDialectUrl) }

  test("Report 1 should conform") { reportShouldConform(s"$reports/report1.yaml") }

  test("Report 2 should conform") { reportShouldConform(s"$reports/report2.yaml") }

  test("Report 3 should conform") { reportShouldConform(s"$reports/report3.yaml") }

  test("Report 1 should match golden") {
    renderedProfileShouldMatchGolden(s"$reports/report1.yaml", s"$reports/report1.jsonld")
  }

  test("Report 2 should match golden") {
    renderedProfileShouldMatchGolden(s"$reports/report2.yaml", s"$reports/report2.jsonld")
  }

  test("Report 3 should match golden") {
    renderedProfileShouldMatchGolden(s"$reports/report3.yaml", s"$reports/report3.jsonld")
  }


}
