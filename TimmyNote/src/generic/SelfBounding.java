/**
 * 
 */
package generic;

import java.util.Arrays;

/**
 * The value of self-bounding is <b>covariant argument types</b>.</br> Note:
 * <b>covariant return types</b> are introduced when JavaSE 5.
 * 
 * @author timmy00274672
 * 
 */
public class SelfBounding<T extends SelfBounding<T>> {

    public static <T extends SelfBounding<T>> T f(T arg) {
	return arg.set(arg).get();
    }

    T element;

    SelfBounding<T> set(T arg) {
	element = arg;
	return this;
    }

    T get() {
	return element;
    }

    public static void main(String[] args) {
	A a = new A();
	a.set(new A());
	a = a.set(new A()).get();
	a = a.get();
	C c = new C();
	c = c.setAndGet(new C());

	B b = new B();
	b.set(new A()); // A class parameter
	@SuppressWarnings("unused")
	A aa = b.get(); // return A object
	System.out.println(Arrays.toString(A.class.getTypeParameters()));
	System.out.println(Arrays.toString(B.class.getTypeParameters()));
	System.out.println(Arrays.toString(C.class.getTypeParameters()));
	System.out.println(Arrays.toString(D.class.getTypeParameters()));
	System.out.println(Arrays.toString(F.class.getTypeParameters()));

    }

}

class A extends SelfBounding<A> {
}

class B extends SelfBounding<A> {
}

class C extends SelfBounding<C> {
    C setAndGet(C arg) {
	set(arg);
	return get();
    }
}

class D {
}

/*
 * Bound mismatch: The type D is not a valid substitute for the bounded
 * parameter <T extends SelfBounding<T>> of the type SelfBounding<T>
 */
// class E extends SelfBounding<D> {}

@SuppressWarnings("rawtypes")
class F extends SelfBounding {
}

/*
 * The type G cannot extend or implement SelfBounding<?>. A supertype may not
 * specify any wildcard
 */
// class G extends SelfBounding<?> {}