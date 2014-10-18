package generic;

/**
 * 
 * @author timmy00274672
 * 
 */
public class HijackedInterface {

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}

class Pet2 implements Comparable<Pet2> {

    @Override
    public int compareTo(Pet2 o) {
	return this.hashCode() - o.hashCode();
    }

}

/*
 * The interface Comparable cannot be implemented more than once with different
 * arguments: Comparable<Pet2> and Comparable<Cat2>
 */
// class Cat2 extends Pet2 implements Comparable<Cat2> {}

class Cat2 extends Pet2{
    @Override
    public int compareTo(Pet2 o) {
        return super.compareTo(o);
    }
}