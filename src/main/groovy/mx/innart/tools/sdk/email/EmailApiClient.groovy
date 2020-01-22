package mx.innart.tools.sdk.email

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import mx.innart.tools.sdk.Itools
import mx.innart.tools.sdk.exceptions.ApiErrorCodes
import mx.innart.tools.sdk.exceptions.ApiResponseException

class EmailApiClient extends Itools {
  private final HEADER_PUBLIC_KEY = 'public_key'
  private final HEADER_PRIVATE_KEY = 'private_key'
  private final JSON_CONTENT_TYPE = 'application/json'
  private String publicKey
  private String privateKey
  private RESTClient client

  static EmailApiClient create(String publicKey, String privateKey) {
    return new EmailApiClient(publicKey, privateKey)
  }

  private EmailApiClient(String publicKey, String privateKey) {
    this.publicKey = publicKey
    this.privateKey = privateKey
    this.client = new RESTClient(API_ENDPOINT)
    this.client.headers.put(HEADER_PUBLIC_KEY, publicKey)
    this.client.headers.put(HEADER_PRIVATE_KEY, privateKey)
  }

  List<EmailApiResponse> send(EmailApiRequest requestBody) throws ApiResponseException {
    try {
      def response = this.client.post(
        path: 'email',
        body: requestBody.items,
        contentType: JSON_CONTENT_TYPE + ';charset=UTF-8'
      ) as HttpResponseDecorator
      return response.getData() as List<EmailApiResponse>
    } catch (Exception ex) {
      switch (ex) {
        case { it instanceof HttpResponseException }:
          HttpResponseDecorator response = (ex as HttpResponseException).response
          switch (response.getStatus()) {
            case 400:
              String errorCode = (response.getData() as Map)?.get('code')
              throw new ApiResponseException(errorCode)
            case 401:
              throw new ApiResponseException(ApiErrorCodes.ACCESS_ERROR, ex.message)
          }
          break
        default:
          throw new ApiResponseException(ApiErrorCodes.CONNECTION_ERROR, ex.message)
      }
    }
  }
}
