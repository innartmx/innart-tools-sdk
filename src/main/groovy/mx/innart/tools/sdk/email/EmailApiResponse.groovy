package mx.innart.tools.sdk.email

class EmailApiResponse {
  String code
  EmailApiResponseStatusResult status

  EmailApiResponse(String code, EmailApiResponseStatusResult status) {
    this.code = code
    this.status = status
  }
}
