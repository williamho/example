package com.example

import com.thoughtworks.binding
import com.thoughtworks.binding.Binding._
import org.scalajs.dom.raw._
import org.scalajs.dom.document
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.JSApp
import scala.util.Random

object Application extends JSApp {
  val initialItems = (1 to 10).map(i => s"initial item $i")
  val items = Vars(initialItems: _*)

  def main(): Unit = {
    binding.dom.render(document.body, list(items))
  }

  def insertRandom(items: Vars[String]) = {
    val buffer = items.get
    val index = if (buffer.isEmpty) 0 else Random.nextInt(buffer.length)
    buffer.insert(index, s"new item ${Random.nextInt.abs}")
  }

  @binding.dom
  def list(listItems: BindingSeq[String]) = {
    <div>total items: { listItems.bind.length.toString }</div>
    <button onclick={ (e: Event) => insertRandom(items) }>Add random item</button>
    <button onclick={ (e: Event) => items.get.clear() }>Clear</button>
    <ol>{
      listItems.map { item =>
        <li>{ item }</li>
      }
    }</ol>
  }
}

