package com.rtjvm.scala.oop.filesystem

import com.rtjvm.scala.oop.files.Directory

class State (val root: Directory, val wd: Directory, val output: String) {
  def setMessage(msg: String): State = {
    State(root, wd, msg)
  }

  def show: String = {
    output
  }
  def prompt = State.SHELL_TOKEN
}

object State{
  def apply(root: Directory, wd: Directory, output: String = "") =
    new State(root, wd, output)
  val SHELL_TOKEN="$ "
}