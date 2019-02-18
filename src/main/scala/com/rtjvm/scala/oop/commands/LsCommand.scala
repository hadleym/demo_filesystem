package com.rtjvm.scala.oop.commands
import com.rtjvm.scala.oop.filesystem.State


class LsCommand extends Command {
  override def apply(state:State): State = {
    state.setMessage("a")
  }
}

