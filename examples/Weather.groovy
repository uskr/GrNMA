//
// Icy roads warning
//

// get the RSS feed of the weather conditions in Copenhagen, Denmark
def rss = new XmlParser().parseText("http://weather.yahooapis.com/forecastrss?w=554890&u=c".toURL().text)

// reg the yweather namespace
def yweather = new groovy.xml.Namespace("http://xml.weather.yahoo.com/ns/rss/1.0", 'yweather')

// get the current temp in Celcius
def temp = rss.channel.item[yweather.condition].@temp[0]

// if cold - icy roads - NotifyMyAndroid me
if (Integer.parseInt(temp) < 2) {
  def result = NMA.notify("your_api_key", "Weather", "Warning!", "Icy roads. The current temperature is ${temp}", 1)

  if (result.success)
    println "NotifyMyAndroid sent!"
  else
    println "Error sending notification: ${result.errorMessage}"
}