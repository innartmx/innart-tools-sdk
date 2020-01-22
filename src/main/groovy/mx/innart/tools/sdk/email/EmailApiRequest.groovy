package mx.innart.tools.sdk.email

class EmailApiRequest {

  def items = []

  EmailApiRequest addItem(EmailApiRequestItem item) {
    this.items << item
    return this
  }
}
