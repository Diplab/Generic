package generic;


public class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
	gia = new Generic[SIZE];
	System.out.println(gia.getClass().getSimpleName());
	
	gia[0] = new Generic<Integer>();
	
	//Type mismatch: cannot convert from Generic<Double> to Generic<Integer>
	//gia[1] = new Generic<Double>();
    }

}

class Generic<T> {

}
