class ValidationProfileTest extends BaseTest {
  private val validationProfileDialectUrl = "file://src/main/dialects/validation-profile.yaml"
  private val profiles                    = "file://src/main/instances/validation"

  private def profileShouldConform(profileUrl: String) = instanceShouldConform(profileUrl, validationProfileDialectUrl)

  test("Validation profile dialect should conform") { dialectShouldConform(validationProfileDialectUrl) }

  test("Validation profile1 should conform") { profileShouldConform(s"$profiles/profile1.yaml") }

  test("Validation profile2 should conform") { profileShouldConform(s"$profiles/profile2.yaml") }

  test("Validation profile3 should conform") { profileShouldConform(s"$profiles/profile3.yaml") }

  test("Validation profile4 should conform") { profileShouldConform(s"$profiles/profile4.yaml") }

  test("Validation profile5 should conform") { profileShouldConform(s"$profiles/profile5.yaml") }

  test("Validation profile6 should conform") { profileShouldConform(s"$profiles/profile6.yaml") }

  test("Validation profile7 should conform") { profileShouldConform(s"$profiles/profile7.yaml") }

  test("Validation profile8 should conform") { profileShouldConform(s"$profiles/profile8.yaml") }

  test("Validation profile9 should conform") { profileShouldConform(s"$profiles/profile9.yaml") }

  test("Validation profile10 should conform") { profileShouldConform(s"$profiles/profile10.yaml") }

  test("Validation profile11 should conform") { profileShouldConform(s"$profiles/profile11.yaml") }

  test("Validation profile12 should conform") { profileShouldConform(s"$profiles/profile12.yaml") }

  test("Validation profile13 should conform") { profileShouldConform(s"$profiles/profile13.yaml") }

}
