package generic;

/**
 * We always see Holder and Holer<?> the same, but it's not. <br/>
 * We can distinct two from {@link Wildcards#rawArgs} and
 * {@link Wildcards#unboundedArg}.
 * 
 * @author timmy00274672
 * @see Holder
 */
public class Wildcards {

    /**
     * It may lead client error, see below sample
     */
    @SuppressWarnings("unchecked")
    static void rawArgs(@SuppressWarnings("rawtypes") Holder holder, Object arg) {
	holder.setValue(arg);
    }

    /**
     * It give an error instead of warnings.
     * 
     * @param holder
     * @param arg
     */
    static void unboundedArg(Holder<?> holder, Object arg) {
	// The method setValue(capture#1-of ?) in the type Holder<capture#1-of
	// ?> is not applicable for the arguments (Object)
	// it may be right if Holder<T> has method setValue(Object)
	// holder.setValue(arg);
    }

    /**
     * It's much better than {@link Wildcards#rawArgs(Holder, Object)}
     * 
     * @param holder
     * @param arg
     */
    static <T> void wildSuperType(Holder<? super T> holder, T arg) {
	holder.setValue(arg);
    }

    public static void main(String[] args) {
	Holder<Apple> appleHolder = new Holder<>(new Apple());
	try {
	    rawArgs(appleHolder, new Oragne());
	    // what a bad writing
	    appleHolder.getValue().a();
	} catch (ClassCastException e) {
	    System.err.println(e);
	}

	/*
	 * The method wildSuperType(Holder<? super T>, T) in the type Wildcards
	 * is not applicable for the arguments (Holder<Apple>, Oragne)
	 */

	// Wildcards.wildSuperType(appleHolder, new Oragne());
    }

}
