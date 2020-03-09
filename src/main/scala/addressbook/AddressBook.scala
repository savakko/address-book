package addressbook

import org.scalajs.dom
import dom.html
import org.scalajs.dom.html.{Div, Button, Input}
import org.scalajs.dom.raw.MouseEvent
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

/** 
 *  AddressBook handles the display of all contacts.
 *  See [Book]
 */
@JSExport
object AddressBook {

  /** These are example contacts */
  for (i <- 0 to 15) {
    Book.createContact("Sauli Kokkonen", "0400380216", "sauli.kokkonen@aalto.fi")
    Book.createContact("Teemu Teekkari", "12345", "teemu@teekkari.fi")
    Book.createContact("Minna Mattila", "54321", "mattila@suomi24.fi")
  }

  /** The main method is called by the script in addressbook.html */
  @JSExport
  def main(target: html.Div): Unit = 
    refreshScreen(target)

  /** Redraws the components. */
  def refreshScreen(target: Div): Unit = {
    target.innerHTML = ""
    target.appendChild(
      rebuildUI(target)
    )
  }

  /** See [refreshScreen]. */
  def rebuildUI(target: html.Div): Div =
    div(
      table(
        tr( th("Name"), th("Number"), th("Email"), th("") ),
		// One row for each contact.
        for (c <- Book.contacts) yield
          tr( td(c.name), td(c.phone), td(c.email), createEditButton(target, c), createDeleteButton(target, c) )
      )
    ).render

  /** Creates a button which can be used to edit existing contacts. */
  def createEditButton(target: Div, contact: Contact) = {
    val b = button("Edit").render
    b.onclick = (_: MouseEvent) => {
      val name   = InputFields.addName.value
      val number = InputFields.addNumber.value
      val email  = InputFields.addEmail.value
	  InputFields.refreshValues
      contact.update(name, number, email)
      refreshScreen(target)
    }
    b
  }

  /** Creates a button which can be used to delete contacts. */
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

}

