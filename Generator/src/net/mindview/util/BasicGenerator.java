package net.mindview.util;

public class BasicGenerator<T> implements Generator<T> {

	private Class<T> type;

	private BasicGenerator(Class<T> type) {
		this.type = type;
	}

	@Override
	public T next() {
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static <T> Generator<T> create(Class<T> type) {
		return new BasicGenerator<>(type);
	}

	public static void main(String[] args) {
		//get error
		// BasicGenerator.<String>create(Integer.class);
		Generator<String> StringGenerator = BasicGenerator.<String>create(String.class);
		System.out.println("it's :" +  StringGenerator.next());
	}

}
