package generic;

import java.util.List;

public class SuperTypeWildcards {

    static void writeTo(List<? super Apple> apples) {
	apples.add(new Apple());
	// apples.add(new Fruit());
	apples.get(0); // it return Object
    }

    public static void main(String[] args) {

    }

}
