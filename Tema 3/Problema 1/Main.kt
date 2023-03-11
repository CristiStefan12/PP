import org.jsoup.Jsoup
import java.net.URL


data class RSS_Elem(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String
)

data class RSS_Feed(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val item: List<RSS_Elem>
)

fun main() {
    val url = URL("https://rust-analyzer.github.io//feed.xml")
    val doc = Jsoup.parse(url, 5000)

    val items = doc.select("item").map {
        val title = it.select("title").text()
        val link = it.select("link").text()
        val description = it.select("description").text()
        val pubDate = it.select("pubDate").text()
        RSS_Elem(title, link, description, pubDate)
    }
    val title = doc.select("title").first()!!.text()
    val link = doc.select("link").first()!!.text()
    val description = doc.select("description").first()!!.text()
    val pubDate = doc.select("pubDate").first()!!.text()

    val feed = RSS_Feed(title, link, description, pubDate, items)

    feed.item.forEach {
        println("Title: ${it.title}")
        println("Link: ${it.link}")
        println()
    }
}
