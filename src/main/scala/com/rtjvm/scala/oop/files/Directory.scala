package com.rtjvm.scala.oop.files

class Directory(override val parentPath: String , override val name: String) extends DirEntry(parentPath, name) {
  def path =  parentPath + Directory.SEPARATOR + name
  def contents: Option[List[DirEntry]] = {
    None
  }
  def getAllFoldersInPath = {
    val dirs = parentPath.split(Directory.SEPARATOR)
    (dirs :+ name).toList
  }
  def hasEntry(entry: String): Boolean = {
    true
  }
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"
  def empty(parentPath: String, name: String): Directory = {
    new Directory(parentPath, name)
  }

  def ROOT = empty("","")

}
