package mx.innart.tools.sdk.i18n

enum Languages {
  SPANISH('es'),
  ENGLISH('en'),
  FRENCH('fr'),
  RUSSIAN('ru'),
  PORTUGUESE('pt'),
  ITALIAN('it')

  private String code

  Languages(String code) {
    this.code = code
  }

  String getCode() {
    return this.code
  }
}
