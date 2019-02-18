import com.rtjvm.scala.oop.commands.{Command, EmptyCommand, IncompleteCommand, UnknownCommand}
import org.scalatest._
import com.rtjvm.scala.oop.filesystem.Filesystem
import com.rtjvm.scala.oop.files.Directory
import com.rtjvm.scala.oop.files.DirEntry
import com.rtjvm.scala.oop.filesystem.State

class CommandSpec extends FlatSpec with Matchers {
  def rootSetup = {
    val root = Directory.ROOT
    State(root, root)
  }

  "Command" should "have a from method" in {
    val command = Command.from("This is a command")
    val state = rootSetup
    val emptyCommand = Command.from("")
    emptyCommand shouldBe a [EmptyCommand]
    
  }

  it should "not change the state from an empty command" in {
    val state = rootSetup
    val newState = state.setMessage("foobar")
    val anotherState = Command.from("").apply(newState)
    assert(anotherState.output == "")
    assert(anotherState.root.contents == None)
//    assert(Command.from("").apply(.isInstanceOf[EmptyCommand])
  }

  it should "return an IncompleteCommand when only the mkdir was passed" in {
    assert(Command.from("mkdir").isInstanceOf[IncompleteCommand])
  }
}
