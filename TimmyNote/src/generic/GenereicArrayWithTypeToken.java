package generic;

import java.lang.reflect.Array;

/**
 * One solution for 'generic array error'
 * 
 * @author diplab
 * @see GenericArray
 * @param <T>
 */
public class GenereicArrayWithTypeToken<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenereicArrayWithTypeToken(Class<T> clazz, int size) {
	array = (T[]) Array.newInstance(clazz, size);
	System.out.println(array.getClass().getSimpleName());
    }

    public static void main(String[] args) {
	@SuppressWarnings("unused")
	GenereicArrayWithTypeToken<Integer> gia = new GenereicArrayWithTypeToken<>(
		Integer.class, 1);
    }

}
