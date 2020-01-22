package mx.innart.tools.sdk.email

class EmailApiRequestItem {
  String keyName
  String lang
  Map<String, String> variables = [:]
  List<String> to = []
  List<String> cc = []
  List<String> bcc = []

  EmailApiRequestItem() {}

  EmailApiRequestItem(String keyName, String lang) {
    this.keyName = keyName
    this.lang = lang
  }

  EmailApiRequestItem addTo(String... emailAddress) {
    emailAddress.each {
      this.to << it
    }
    return this
  }

  EmailApiRequestItem addCc(String... emailAddress) {
    emailAddress.each {
      this.cc << it
    }
    return this
  }

  EmailApiRequestItem addBcc(String... emailAddress) {
    emailAddress.each {
      this.bcc << it
    }
    return this
  }

  EmailApiRequestItem setVariable(String key, String value) {
    this.variables.put(key, value)
    return this
  }
}
