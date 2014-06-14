/** Basic Person with full name (first name and surname)
* and age in format "dd.mm.yyyy"
* 
* @author Yuriy Miedviediev
* @version 1.0 Build Mar 27, 2014.
*/
public class Person {
	// To keep it simple, we won't use the benefits of incapsulation this time
	
	/** Person's first name */
	String name;
	
	/** Person's last name */
	String surname;
	
	/** Person's date of birth (DOB) */
	String dateOfBirth;
	
	/** Constructor for the Person class
	*
	* @param n		(String)First Name
	* @param sn		(String)Last Name (Surname)
	* @param dob	(String)Date Of Birth (dd.mm.yyyy)
	*/
	public Person(String n, String sn, String dob) {
		name = n;
		surname = sn;
		dateOfBirth = dob;
	}
}
