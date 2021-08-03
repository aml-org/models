package utils

object RicherClasses {
  implicit class RichString(val str: String) extends AnyVal {
    def stripProtocol: String = str.replaceFirst("[a-zA-Z0-9]*://", "")
    def substringAfterLast(sep: String): String = {
      val beginIdx = str.lastIndexOf(sep) + 1
      str.substring(beginIdx)
    }
  }
}
