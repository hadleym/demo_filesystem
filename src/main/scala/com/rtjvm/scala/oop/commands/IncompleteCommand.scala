package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.filesystem.State

class IncompleteCommand extends Command {
  override def apply(state: State): State = {
    state.setMessage("mkdir: Incomplete Command")
  }
}
