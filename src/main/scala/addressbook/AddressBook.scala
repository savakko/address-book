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
  for (i <- 0 to 15) {
    Book.createContact("Sauli Kokkonen", "0400380216", "sauli.kokkonen@aalto.fi")
    Book.createContact("Teemu Teekkari", "12345", "teemu@teekkari.fi")
    Book.createContact("Minna Mattila", "54321", "mattila@suomi24.fi")
  }

  @JSExport
  def main(target: html.Div): Unit = 
    refreshScreen(target)

  def refreshScreen(target: Div): Unit = {
    target.innerHTML = ""
    target.appendChild(
      rebuildUI(target)
    )
  }

  def rebuildUI(target: html.Div): Div =
    div(
      table(
        tr( th("Name"), th("Number"), th("Email"), th("") ),
        for (c <- Book.contacts) yield
          tr( td(c.name), td(c.phone), td(c.email), createEditButton(target, c), createDeleteButton(target, c) )
      )
    ).render

  def createEditButton(target: Div, contact: Contact) = {
    val b = button("Edit").render
    b.onclick = (_: MouseEvent) => {
      val name   = InputFields.addName.value
      val number = InputFields.addNumber.value
      val email  = InputFields.addEmail.value
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

  val addForm = {
    val (name, num, email, button) = (
	  InputFields.addName,
	  InputFields.addNumber,
	  InputFields.addEmail,
	  InputFields.addButton
	)
    Array(
      div(name, num, email),
      div(button)
    )
  }

}

