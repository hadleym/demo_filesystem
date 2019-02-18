import com.rtjvm.scala.oop.commands.{Command, EmptyCommand, IncompleteCommand, UnknownCommand}
import org.scalatest._
import com.rtjvm.scala.oop.filesystem.Filesystem
import com.rtjvm.scala.oop.files.Directory
import com.rtjvm.scala.oop.files.DirEntry
import com.rtjvm.scala.oop.filesystem.State
class FilesystemSpec extends FlatSpec with Matchers {
  def rootSetup = {
    val root = Directory.ROOT
    State(root, root)
  }
  it should "Take a command" in {

  }

  "State" should "show the output" in {
    val state = rootSetup
    val command = "foobar"
    val newState = Command.from(command).apply(state)
    assert(newState.show == "Command Not Found")
  }

  it should "show the correct state when show method called" in {
    val state = rootSetup
    assert(state.prompt == State.SHELL_TOKEN)
  }

  it should "have a shell token" in {
    assert(State.SHELL_TOKEN == "$ ")
  }

  it should "have a set message function" in {
    val parentPath = "/foo/bar"
    val name = "current"
    val wd = new Directory(parentPath, name)
    val state = State(Directory.ROOT, wd, "")
    val newState = state.setMessage("Unknown Command")
    assert(newState.output == "Unknown Command")
    assert(newState.root == state.root)
    assert(newState.wd == state.wd)
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

  "The Mkdir Command" should "have a message of \"mkdir: Incomplete Command\" if only mkdir passed" in {
    val state = rootSetup
    val newState = Command.from("mkdir").apply(state)
    assert(newState.output == "mkdir: Incomplete Command")
  }

  it should "have a check on hasEntry and set message appropriately" in {
    val state = rootSetup
  }

  it should "have a check that the argument doesnt contain a '.' " in {
    val state = rootSetup
    val newState = Command.from("mkdir hello.world").apply(state)
    assert(newState.output == "mkdir: Illegal Argument")
  }

  it should "have a doMkdir function that creates a directory" in {
    val state = rootSetup
    val newState = Command.from("mkdir foobar").apply(state)
    val sameState = Command.from("mkdir foobar").apply(state)
    assert(sameState.output == "mkdir: Directory 'foobar' already exists")
  }

  "The Unknown Command" should "extend Command" in {
    val unknownCommand = new UnknownCommand()
    assert(unknownCommand.isInstanceOf[Command])
  }

  it should "have an error message of \"Command Not Found\"" in {
    val currentState = State(Directory.ROOT, new Directory("/foo/bar", "parent"), "")
    val unknownCommand = new UnknownCommand
    val newState = unknownCommand(currentState)
    assert(newState.output == "Command Not Found")
  }

  "The Command Object" should "have a from method" in {
    val currentState = State(Directory.ROOT, new Directory("/foo/bar", "parent"), "")
    assert(Command.from("foobar").apply(currentState).output == "Command Not Found")
  }

  it should "have a LS constant" in {
    assert(Command.LS == "ls")
  }

  // DIRECTORY
  "A Directory" should "extend DirEntry" in {
    val directory = new Directory("foo", "bar")
    directory.isInstanceOf[DirEntry]
  }

  it should "have a separator member" in {
    assert(Directory.SEPARATOR == "/")
  }
  it should "have a root path of \"/\"" in {
    assert(Directory.ROOT_PATH == "/")
  }

  it should "be able to create an empty directory" in {
    val dir = Directory.empty("","")
    assert(dir.contents == None)
  }

  it should "be able to create the root directory" in {
    val root = Directory.ROOT
    assert(root.parentPath == "")
    assert(root.name == "")
  }

  it should "have a method getAllFoldersInPath" in {
    val directory = new Directory("foo", "bar")
    assert(directory.getAllFoldersInPath == List("foo", "bar"))
    val anotherDirectory = new Directory("baz", "fuzz")
    assert(anotherDirectory.getAllFoldersInPath == List("baz", "fuzz"))
  }

  it should "have a function 'hasEntry'" in {
    val directory = new Directory("foo", "bar")
    assert(directory.hasEntry("foo"))
  }

  it should "have a findDescendant function" in {

  }

  it should "return error message if directory contains '.'" in {
    val directory = new Directory("foo", "bar")
  }    

  "DirEntry" should "have a path method" in {
    val directory = new Directory("foo", "bar")
    assert(directory.path == "foo/bar")
  }

  "Command Ls" should "have a prettyOutput method" in {
    val currentState = State(Directory.ROOT, new Directory("/foo/bar", "parent"), "")
    val message = Command.from("ls").apply(currentState).show
    assert(message == "")
  }


  "The Command Line" should "list that directory" in {
    val root = rootSetup
    val newState = Command.from("mkdir a").apply(root)
    val lsState = Command.from("ls").apply(newState)
    val message = lsState.show 
    message should equal ("a")
  }


}


