package mx.innart.tools.sdk


import mx.innart.tools.sdk.email.EmailApiRequest
import mx.innart.tools.sdk.exceptions.ApiErrorCodes
import mx.innart.tools.sdk.exceptions.ApiResponseException
import mx.innart.tools.sdk.email.EmailApiClient
import mx.innart.tools.sdk.email.EmailApiRequestItem
import mx.innart.tools.sdk.i18n.Languages
import spock.lang.Specification

class EmailApiClientTest extends Specification {

  EmailApiClient client = EmailApiClient.create('2c632310e5024cc3a1268cad561d6501', '40162e60798b0bb76880c7f2b23e09c4638c5ef412770ad070adc160454c8b5d')

  def 'should fail because of a not found template'() {
    given:
    EmailApiRequest request = new EmailApiRequest()
    EmailApiRequestItem item1 = new EmailApiRequestItem('test', Languages.SPANISH.getCode())
    item1.addTo('arturo@innart.mx')
    item1.setVariable('nombre', 'Arturo')
    item1.setVariable('apellido', 'Rojas Rodríguez')

    request.addItem(item1)

    when:
    def response = client.send(request)

    then:
    def error = thrown(ApiResponseException)
    error.code == ApiErrorCodes.NOTIFICATION_NOT_FOUND
  }

  def 'should fail because wrong credentials'() {
    given:
    EmailApiClient client = EmailApiClient.create('8f3caa330f77cfaff11233906265f3b', 'wrong_private_key')
    EmailApiRequest request = new EmailApiRequest()
    EmailApiRequestItem item1 = new EmailApiRequestItem('test', Languages.SPANISH.getCode())
    request.addItem(item1)

    when:
    def response = client.send(request)

    then:
    def error = thrown(ApiResponseException)
    error.code == ApiErrorCodes.ACCESS_ERROR
  }
}
