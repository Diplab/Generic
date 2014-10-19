import java.util.*;

public class Erased {

	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		Class c1 = new ArrayList<String>().getClass();
		@SuppressWarnings("rawtypes")
		Class c2 = new ArrayList<Integer>().getClass();
		System.out.println(c1 == c2);
	}

}
