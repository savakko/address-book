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
  Book.createContact("Sauli Kokkonen", "0400380216", "sauli.kokkonen@aalto.fi")
  Book.createContact("Teemu Teekkari", "0123456789", "teemu@teekkari.fi")
  Book.createContact("Minna Mattila", "", "mattila@suomi24.fi")
  Book.createContact("Matti Meikäläinen", "8765432109", "meikamattilainen@hotmail.com")
  Book.createContact("Merikki Lappalainen", "0987654321", "merkillinen@paivola.fi")
  Book.createContact("Yhteystieto", "4536271809", "yhteinen@tieto.fi")
  Book.createContact("Ville Nuppolainen", "0987123465", "nupponen.vili@espoo.fi")
  Book.createContact("Numeropalvelu", "0202020202", "")
  Book.createContact("Sanna Suorama", "7657892104", "eteenpain@mummo.lumessa.fi")

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
        // Draw each contact.
        for (c <- Book.contacts) yield {
          div(
            tr( td(createLinkButton(target, c)), td(), td(), createDeleteButton(target, c) ),
            { if (c.isOpen) tr( td(c.name), td(c.phone), td(c.email), td(createEditButton(target, c)) ) }
          )
        }
      )
    ).render

  /** Uses a contact's name to create a button
   *  which will open the contact's info. */
  def createLinkButton(target: html.Div, contact: Contact) = {
    val b = button(contact.name).render
    b.onclick = (_: MouseEvent) => {
      contact.toggle
      refreshScreen(target)
    }
    b
  }

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
    val b = button("Delete").render
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

