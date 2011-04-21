class NMA {

  static notify(apikey, application, event, description, priority = 0) {
    use(StringCategory) {
      send "https://nma.usk.bz/publicapi/notify?apikey=${apikey}&application=${application.encode()}&event=${event.encode()}&description=${description.encode()}&priority=${priority}"
    }
  }

  static verify(apikey) {
    send "https://nma.usk.bz/publicapi/verify?apikey=${apikey}"
  }

  static send(url) {
    def result = new Expando()
    try {
      def nma = new XmlParser().parseText(url.toURL().text)
      result.remaining = nma.success[0].@remaining
      result.resettimer = nma.success[0].@resettimer
      result.success = true
    } catch (Exception ex) {
      result.success = false
      result.errorMessage = ex.message
    }
    return result
  }

}

class StringCategory {
  static encode(string) {
    URLEncoder.encode(string, "UTF-8")
  }
}
