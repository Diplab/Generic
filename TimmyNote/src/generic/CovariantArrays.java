package generic;

import java.util.ArrayList;
import java.util.List;

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

	ArrayList<Apple> arrayList = new ArrayList<Apple>();
	arrayList.add(new Apple());
	List<? extends Fruit> fList = arrayList; // upper cast make it cannot do
						 // anything
	System.out.println(fList.getClass().getSimpleName());

	// The method add(capture#4-of ? extends Fruit) in the type
	// List<capture#4-of ? extends Fruit> is not applicable for the
	// arguments (xxx)
	// fList.add(new Oragne());
	// fList.add(new Apple());
	// fList.add(new Fruit());
	// fList.add(new Object());

	System.out.println(fList.get(0).getClass().getSimpleName());
	fList.get(0).f(); // it seems to be Fruit not Apple

	/*
	 * Unresolved compilation problem: The method a() is undefined for the
	 * type capture#7-of ? extends Fruit
	 */
	// fList.get(0).a();
    }

}

class Fruit {
    void f() {
    }
}

class Apple extends Fruit {
    void a() {
    }
}

class Oragne extends Fruit {
    void o() {
    }
}