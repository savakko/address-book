package addressbook

import scala.scalajs.js.Date

import org.scalajs.dom
import dom.html
import org.scalajs.dom.html.{Div, Button, Input}
import org.scalajs.dom.raw.MouseEvent
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

@JSExport
object AddressBook {

  /** These are example contacts */
  for (i <- 0 to 5) {
    Book.createContact("Sauli Kokkonen", "0400380216", "sauli.kokkonen@aalto.fi")
    Book.createContact("Teemu Teekkari", "12345", "teemu@teekkari.fi")
    Book.createContact("Johanna Tukiainen", "54321", "tukiainen@suomi24.fi")
  }

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
    div(
      table(
        tr( th("Name"), th("Number"), th("Email"), th("") ),
        for (c <- Book.contacts) yield
          tr( td(c.name), td(c.phone), td(c.email), createEditButton(target, c), createDeleteButton(target, c) )
      ),
      addForm
    ).render

  def createEditButton(target: Div, contact: Contact) = {
    val b = button("Edit").render
    b.onclick = (_: MouseEvent) => {
      val name   = addName.value
      val number = addNumber.value
      val email  = addEmail.value
      contact.update(name, number, email)
      refreshScreen(target)
    }
    b
  }

  def createDeleteButton(target: Div, contact: Contact) = {
    val b = button("X").render
    b.onclick = (_: MouseEvent) => {
      val verified = dom.window.confirm(s"You are about to delete:\n${contact}")
      if (verified) {
        Book.deleteContact(contact)
        refreshScreen(target)
      }
    }
    b
  }

  val addForm = Array(
    div(addName, addNumber, addEmail),
    div(addButton)
  )

}

