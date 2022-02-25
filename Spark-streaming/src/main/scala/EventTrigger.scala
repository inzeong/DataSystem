import java.net.URI
import java.time.LocalDateTime
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs._
import org.apache.hadoop.hdfs.client._
import org.apache.hadoop.hdfs.inotify._
import org.apache.commons.lang.StringEscapeUtils.escapeJava
import scala.collection.mutable.ListBuffer

object EventTrigger {
  def main(args: Array[String]): Unit = {

    val url = new URI("hdfs://localhost:9000")
    val conf = new Configuration(false)
    val dfs = new HdfsAdmin(url, conf)
    val stream = dfs.getInotifyEventStream()
    val lis = new ListBuffer[String]()

    val thread = new Thread {
      private def mapValueListAdd(path: String): Unit = {
        lis += path
      }

      private def addMapSub(ty: String, p: String): Unit = {
        mapValueListAdd(
          p
        )
      }

      private def addMap(event: Event): Unit = event match {
        case s: Event.CreateEvent => addMapSub("CREATE", s.getPath)
        case other                => s"Invalid event $event"
      }

      private def toStr(e: String): String = {
        s""""path": "${e}""""
      }

      override def run(): Unit = {
        while (true) {
          var batch = stream.poll()
          while (batch != null) {
            // process event
            for (event <- batch.getEvents) {
              addMap(event)
            }
            batch = stream.poll()
          }
          for (e <- lis) {
            // println(s"> [LOG] ${toStr(e)}")

          }
          lis.clear()
          Thread.sleep(500)
        }
      }
    }
    thread.start()
  }
}
