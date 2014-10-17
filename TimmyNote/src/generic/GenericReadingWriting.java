package generic;

import java.util.ArrayList;
import java.util.List;

public class GenericReadingWriting {

    static <T> void writeWithWildcard(List<? super T> list, T item) {
	list.add(item);
    }

    static <T> T readCovariant(List<? extends T> list) {
	return list.get(0);
    }

    public static void main(String[] args) {
	ArrayList<Apple> appleList = new ArrayList<Apple>();
	ArrayList<Fruit> fruitList = new ArrayList<Fruit>();

	writeWithWildcard(appleList, new Apple());
	writeWithWildcard(fruitList, new Apple());

	readCovariant(appleList).a(); // it return Apple
	readCovariant(fruitList).f(); // it return Fruit
	GenericReadingWriting.<Fruit> readCovariant(appleList).f(); // it return
								    // Fruit
	/*
	 * The parameterized method <Apple>readCovariant(List<? extends Apple>)
	 * of type GenericReadingWriting is not applicable for the arguments
	 * (ArrayList<Fruit>)
	 */
	// GenericReadingWriting.<Apple> readCovariant(fruitList);
    }
}
