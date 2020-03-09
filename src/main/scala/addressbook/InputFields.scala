package addressbook

import scala.scalajs.js.Date

import org.scalajs.dom
import dom.html
import org.scalajs.dom.html.{Div, Button, Input}
import org.scalajs.dom.raw.MouseEvent
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object InputFields {

  val addName   = input("Name").render
  val addNumber = input("Number").render
  val addEmail  = input("Email").render
  val addButton = button("Add a new contact").render

  @JSExport
  def main(target: html.Div): Unit = {

    addButton.onclick = (x: MouseEvent) => {
      val name   = addName.value
      val number = addNumber.value
      val email  = addEmail.value
      Book.createContact(name, number, email)

      refreshScreen(target)
    }

    refreshScreen(target)
  }

  def refreshScreen(target: Div): Unit = {
    target.innerHTML = ""
    target.appendChild(
      rebuildUI(target, addName, addNumber, addEmail, addButton)
    )
  }

  def rebuildUI(target: html.Div, addName: Input, addNumber: Input, addEmail: Input, addButton: Button): Div =
    div(addForm).render

  val addForm = Array(
    div(addName, addNumber, addEmail),
    div(addButton)
  )

}

