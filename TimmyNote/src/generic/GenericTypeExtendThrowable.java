package generic;

/**
 * This show that generic class may not subclass java.lang.Throwable. It avoid
 * uncatchable generic throwable. This restriction is needed since the catch
 * mechanism of the Java virtual machine works only with non-generic classes.
 * </br> See the reference <a href=
 * "http://java.sun.com/docs/books/jls/third_edition/html/classes.html#8.1.2"
 * >8.1.2 Generic Classes and Type Parameters</a>.
 * 
 * @author timmy00274672
 * @see Throwable
 */
public class GenericTypeExtendThrowable {

    public static void main(String[] args) {

    }

}

/*
 * The generic class BB<T> may not subclass java.lang.Throwable
 */
// class BB<T> extends Throwable{}