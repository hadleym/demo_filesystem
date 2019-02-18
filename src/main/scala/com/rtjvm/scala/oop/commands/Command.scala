package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.filesystem.State

trait Command {
  def apply(state:State): State
}

object Command {
  val LS = "ls"
  def emptyCommand: Command = new EmptyCommand
  def mkdirCommand(tokens: Array[String]): Command = {
    
    new MkdirCommand("")

  }
  def from(input: String): Command = {
    val tokens = input.split(" ")
    tokens(0) match {
      case "" => emptyCommand
      case "mkdir" => mkdirCommand(tokens)
      case "ls" => new LsCommand()
      case _  => new UnknownCommand

    }
  }

  
}
