package generic;

/**
 * What's the reason I can't create generic array types in Java?
 * 
 * It's because Java's arrays (unlike generics) contain, at runtime, information
 * about its component type. So you must know the component type when you create
 * the array. Since you don't know what T is at runtime, you can't create the
 * array.
 * 
 * @author diplab
 * 
 * @param <T>
 *            for test
 */
public class GenericArray<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArray(int size) {
	array = (T[]) new Object[size];
	System.out.println(array.getClass().getSimpleName());
    }

    public void put(int index, T item) {
	array[index] = item;
    }

    public T get(int index) {
	return array[index];
    }

    public T[] rep() {
	return array;
    }

    public static void main(String[] args) {
	GenericArray<Integer> gai = new GenericArray<>(10);

	try {
	    @SuppressWarnings("unused")
	    Integer[] ia = gai.rep();
	} catch (ClassCastException e) {
	    System.out.println(e);
	}

	@SuppressWarnings("unused")
	Object[] oa = gai.rep();
    }

}
