package mx.innart.tools.sdk.exceptions

class ApiResponseException extends RuntimeException {

  String code

  ApiResponseException(String code, String message = '') {
    super(message)
    this.code = code
  }

  String getCode() {
    return this.code
  }
}
