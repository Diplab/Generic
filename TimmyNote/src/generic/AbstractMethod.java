package generic;

/**
 * @author timmy00274672
 * 
 */
public class AbstractMethod {

    public static void main(String[] args) {
	DerivedSelfBound derivedSelfBound = new DerivedSelfBound();
	derivedSelfBound.set(derivedSelfBound);
    }

}

abstract class BaseSelfBound<T extends BaseSelfBound<T>> {
    abstract void set(T arg);
}

class DerivedSelfBound extends BaseSelfBound<DerivedSelfBound> {

    @Override
    void set(DerivedSelfBound arg) {
	System.out.println("DerivedSelfBound.set(DerivedSelfBound)");
    }

}
