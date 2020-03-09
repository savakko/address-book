package addressbook


/** 
 *  Book is a singleton object which handles all contacts.
 *  Contacts can be added, edited and removed.
 */
object Book {
  import collection.mutable.Buffer

  val contacts = Buffer[Contact]()


  def listContacts: String = contacts.mkString("\n")

  def createContact(c: Contact): Unit = contacts += c
  def createContact(n: String, p: String, e: String): Unit = {
    if ( !(n == "" && p == "" && e == "") )
      contacts += Contact(n.trim, p.trim, e.trim)
  }

  def deleteContact(c: Contact): Unit = contacts -= c

}

/** 
 *  See [Book]
 */
class Contact(var name: String, var phone: String, var email: String) {

  private var open = false // Indicates whether the user is viewing this contact.

  def isOpen = open
  def toggle = {open = !open}

  def update(n: String, p: String, e: String): Unit = {
    this.name  = {if (n != "") n else this.name}
    this.phone = {if (p != "") p else this.phone}
    this.email = {if (e != "") e else this.email}
  }

  def info: String = s"Phone number: $phone Email: $email"

  override def toString: String = s"$name, $phone, $email"

}

/**
 *  This is mainly a constructor.
 */
object Contact {
  
  def apply(n: String, p: String, e: String) = {
    val name  = {if (n != "") n else "-"}
    val phone = {if (p != "") p else "-"}
    val email = {if (e != "") e else "-"}
    new Contact(name, phone, email)
  }
}

