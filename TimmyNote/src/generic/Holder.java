package generic;


public class Holder<T> {
    private T value;

    public Holder() {
    }

    public Holder(T valueT) {
	this.value = valueT;
    }

    public T getValue() {
	return value;
    }

    public void setValue(T value) {
	this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Holder) {
	    Holder<?> tmpHolder = (Holder<?>) obj;
	    return value.equals(tmpHolder.getValue());
	}
	return false;
    }

    public static void main(String[] args) {
	Holder<Apple> appleHolder = new Holder<>(new Apple());
	appleHolder.getValue().a(); // it's Apple.

	// another sample:
	Holder<? extends Fruit> fHolder = appleHolder;
	fHolder.getValue().f(); // it's Fruit
	((Apple) fHolder.getValue()).a(); // it can be cast to Apple

	/*
	 * The method setValue(capture#5-of ? extends Fruit) in the type
	 * Holder<capture#5-of ? extends Fruit> is not applicable for the
	 * arguments (XXX)
	 */
	// fHolder.setValue(new Apple());
	// fHolder.setValue(new Fruit());
    }
}
