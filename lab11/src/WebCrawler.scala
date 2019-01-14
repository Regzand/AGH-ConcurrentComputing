import java.net.URL

import org.htmlcleaner.HtmlCleaner

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

class Node(val url: URL, val links: List[Node] = List[Node]())

object WebCrawler extends App {

  val cleaner = new HtmlCleaner

  def getLinks(url: URL): Future[List[URL]] = {
    Future {
      println(s"[Visiting $url]")

      cleaner.clean(url)
        .getElementsByName("a", true)
        .map(n => n.getAttributeByName("href"))
        .filter(s => s != null && !s.isEmpty)
        .map(l => new URL(url, l))
        .toList

    } recoverWith {
      case e =>
        System.err.println(s"Visiting $url failed with error: ${e.toString}")

        Future.successful(List[URL]())

    }
  }

  def crawl(url: URL, depth: Int = 3): Future[Node] = {
    if(depth > 0)
      getLinks(url)
        .flatMap(links => Future.sequence(links.map(l => crawl(l, depth-1))))
        .map(nodes => new Node(url, nodes))
    else
      Future.successful(new Node(url))
  }

  def display(graph: Node, depth: Int = 0): Unit = {
    println("  " * depth + "- " + graph.url)
    for(n <- graph.links)
      display(n, depth+1)
  }

  // test
  val result = Await.result(crawl(new URL("http://home.agh.edu.pl/~kzajac/dydakt/tw/index.html")), 60.seconds)
  display(result)

}
