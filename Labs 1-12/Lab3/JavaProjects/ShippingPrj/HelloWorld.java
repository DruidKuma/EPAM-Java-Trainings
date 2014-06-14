package ua.kpi.pti;

/**
*
* @author Yuriy Miedviediev
* @version 1.0 Build 31/03/2014
*
*/

//classes are in the same package and are allowed not to be imported (II)
//import ua.kpi.pti.*;


//even if needed class is located in sub package of current package, it still
//have to be imported 
import ua.kpi.pti.pkg.*;
 
public class HelloWorld {
	public static void main(String[] args) {
		HelloWorld hw = new HelloWorld();
		PrintHello.say(); 
	}
	
	public void sayHello() {
		System.out.println("Hello World!");
	}
}