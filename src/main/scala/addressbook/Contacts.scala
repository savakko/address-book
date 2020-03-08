package addressbook

import collection.mutable.Buffer

object Book {
  val contacts = Buffer[Contact]()

  def listContacts: String = contacts.mkString("\n")

  def createContact(n: String, p: String, e: String): Unit = {
    if ( !(n == "" && p == "" && e == "") )
      contacts += Contact(n.trim, p.trim, e.trim)
  }
  def createContact(c: Contact): Unit = contacts += c

  def deleteContact(c: Contact): Unit = contacts -= c
}

class Contact(var name: String, var phone: String, var email: String) {

  def update(n: String, p: String, e: String): Unit = {
    this.name  = {if (n != "") n else this.name}
    this.phone = {if (p != "") p else this.phone}
    this.email = {if (e != "") e else this.email}
  }

  override def toString: String = s"$name, $phone, $email"

}

object Contact {
  
  def apply(n: String, p: String, e: String) = {
    val name  = {if (n != "") n else "-"}
    val phone = {if (p != "") p else "-"}
    val email = {if (e != "") e else "-"}
    new Contact(name, phone, email)
  }
}

