package generic;

/***
 * Generic is designed to solve below problem. Make this kind of error found in
 * compile time.
 * 
 * @author timmy00274672
 * 
 */
public class CovariantArrays {

    public static void main(String[] args) {
	Fruit f[] = new Apple[10];
	System.out.println(f.getClass().getSimpleName());

	try {
	    f[0] = new Oragne();
	} catch (ArrayStoreException e) {
	    System.err.println(e);
	}
    }

}

class Fruit {
}

class Apple extends Fruit {
}

class Oragne extends Fruit {
}