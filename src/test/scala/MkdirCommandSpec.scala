import com.rtjvm.scala.oop.commands.{Command, MkdirCommand}
import org.scalatest._
import com.rtjvm.scala.oop.filesystem.Filesystem
import com.rtjvm.scala.oop.files.Directory
import com.rtjvm.scala.oop.files.DirEntry
import com.rtjvm.scala.oop.filesystem.State

class MkdirCommandSpec extends FlatSpec with Matchers {
  def rootSetup = {
    val root = Directory.ROOT
    State(root, root)
  }

  "MkdirCommand" should "Create a directory" in {
    val state = rootSetup
    val newState = Command.from("mkdir a").apply(state)
    val lsState = Command.from("ls").apply(newState)

    lsState.show should equal ("a")
    val moreLsState = Command.from("mkdir b").apply(lsState)
    moreLsState.show should equal ("a b")
  }

  "doMkDir" should "do things" in {
    val state = rootSetup
    /*
    val mkdirCommand = Command.from("mkdir a")

    val newState = mkdirCommand.doMkDir(state, "a")
    */
  }

  it should "have a updateStructure method" in {
    val state = rootSetup
    val newDir = Directory.empty(state.wd.path, "a")
    val allDirsInPath = state.wd.getAllFoldersInPath 
    val mkdirCommand = new MkdirCommand("b")
    mkdirCommand.updateStructure(state, allDirsInPath, newDir)
  }
}

