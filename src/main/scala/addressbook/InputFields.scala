package addressbook

import org.scalajs.dom
import dom.html
import org.scalajs.dom.html.{Div, Button, Input}
import org.scalajs.dom.raw.MouseEvent
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

/** 
 *  InputFields controls inputs by the user.
 */
@JSExport
object InputFields {

  /** HTML components */
  val addName   = input("Name").render
  val addNumber = input("Number").render
  val addEmail  = input("Email").render
  val addButton = button("Add a new contact").render

  /** The main method is called by the script in addressbook.html */
  @JSExport
  def main(target: html.Div, other: html.Div): Unit = {

    addButton.onclick = (x: MouseEvent) => {
      val name   = addName.value
      val number = addNumber.value
      val email  = addEmail.value
      Book.createContact(name, number, email)

      AddressBook.refreshScreen(other)
      refreshScreen(target)
    }

    refreshScreen(target)
  }

  /** Clears the input field values. */
  def refreshValues: Unit = {
    addName.value   = ""
	addNumber.value = ""
	addEmail.value  = ""
  }

  /** Redraws the components. */
  def refreshScreen(target: Div): Unit = {
    target.innerHTML = ""
	this.refreshValues
    target.appendChild( div(addForm).render )
  }

  val addForm = Array(
    div( table( tr( th("Name"), th("Phone"), th("Email") ) ) ),
    div(addName, addNumber, addEmail),
    div(addButton)
  )

}

