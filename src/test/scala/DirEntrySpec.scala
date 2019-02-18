
import com.rtjvm.scala.oop.commands.{Command, EmptyCommand, IncompleteCommand, UnknownCommand}
import org.scalatest._
import com.rtjvm.scala.oop.filesystem.Filesystem
import com.rtjvm.scala.oop.files.Directory
import com.rtjvm.scala.oop.files.DirEntry
import com.rtjvm.scala.oop.filesystem.State

class DirEntrySpec extends FlatSpec with Matchers {
  "DirEntry" should "have a path method" in {
    val directory = new Directory("foo", "bar")
    assert(directory.path == "foo/bar")
  }
}
