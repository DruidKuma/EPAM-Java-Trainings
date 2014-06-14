public class PersonRunner {
	public static void main(String[] args) {
		Person me = new Person("Yuriy", "Miedviediev", "17.06.1991");
		System.out.println("My name is " + me.name);
		System.out.println("My surname is " + me.surname);
		System.out.println("The date of my birth is " + me.dateOfBirth);
	}
}