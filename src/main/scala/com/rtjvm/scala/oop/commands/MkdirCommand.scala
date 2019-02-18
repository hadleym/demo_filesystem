package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.filesystem.State
import com.rtjvm.scala.oop.files.{Directory, DirEntry}

class MkdirCommand(name: String) extends Command {
  override def apply(state: State): State = {
    doMkDir(name, state)
    state.setMessage("success")

  }
  def doMkDir(name: String, state: State): State = {
    val wd = state.wd
    val fullPath = wd.path

    val allDirsInPath = wd.getAllFoldersInPath

    val newDir = Directory.empty(wd.path, name)

    // update entire structure
    state
  }

  def updateStructure(currentDirectory: Directory, allDirsInPath: List[String], newDir: DirEntry): Directory = {
  }

}



