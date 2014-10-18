package generic;

/**
 * @author timmy00274672
 * @see SelfBounding
 */
public class NotSelfBounded<T> {

    T element;

    NotSelfBounded<T> set(T arg) {
	element = arg;
	return this;
    }

    T get() {
	return element;
    }

    public static void main(String[] args) {

    }

}

class A2 extends NotSelfBounded<A2> {
}

class B2 extends NotSelfBounded<B2> {
}

class C2 extends NotSelfBounded<C2> {
    C2 setAndGet(C2 arg) {
	set(arg);
	return get();
    }
}

class D2 {
}

/**
 * Now it is ok. Compare it with packaged class D/E in {@link SelfBounding}.
 * 
 * @author timmy00274672
 * 
 */
class E2 extends NotSelfBounded<D2> {
}