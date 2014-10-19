package generic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

interface Combine<T> {
    T combine(T x, T y);
}

interface UnaryFunction<R, T> {
    R function(T x);
}

interface Collector<T> extends UnaryFunction<T, T> {
    T result();
}

interface UnaryPredicate<T> {
    boolean test(T x);
}

/**
 * It's like what JGA does. 
 * @author timmy00274672
 * 
 */
public class Functional {

    public static <T> T reduce(Iterable<T> seq, Combine<T> combiner) {
	Iterator<T> iterable = seq.iterator();
	if (iterable.hasNext()) {
	    T result = iterable.next();
	    while (iterable.hasNext())
		result = combiner.combine(result, iterable.next());
	    return result;
	}
	return null;
    }

    public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
	for (T t : seq) {
	    func.function(t);
	}
	return func;
    }

    public static <R, T> List<R> transform(Iterable<T> seq,
	    UnaryFunction<R, T> func) {
	List<R> result = new ArrayList<>();
	for (T t : seq) {
	    result.add(func.function(t));
	}
	return result;
    }

    public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pred) {
	List<T> result = new ArrayList<>();
	for (T t : seq) {
	    if (pred.test(t))
		result.add(t);
	}
	return result;
    }

    public static class GreaterThan<T extends Comparable<T>> implements
	    UnaryPredicate<T> {
	private T base;

	public GreaterThan(T base) {
	    this.base = base;
	}

	@Override
	public boolean test(T x) {
	    return x.compareTo(base) > 0;
	}
    }

    public static class MultiplyingIntegerCollector implements
	    Collector<Integer> {
	private Integer val = 1;

	@Override
	public Integer function(Integer x) {
	    val *= x;
	    return val;
	}

	@Override
	public Integer result() {
	    return val;
	}
    }

    public static void main(String[] args) {
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	Integer result = reduce(list, new Combine<Integer>() {

	    @Override
	    public Integer combine(Integer x, Integer y) {
		return x + y;
	    }
	});

	System.out.println(result);

	result = reduce(list, new Combine<Integer>() {

	    @Override
	    public Integer combine(Integer x, Integer y) {
		return x - y;
	    }
	});
	System.out.println(result);

	System.out.println(filter(list, new GreaterThan<Integer>(4)));

	System.out.println(forEach(list, new MultiplyingIntegerCollector())
		.result());

	System.out.println(forEach(filter(list, new GreaterThan<Integer>(4)),
		new MultiplyingIntegerCollector()).result());

	final MathContext mContext = new MathContext(7);
	Iterable<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3, 4.4);
	List<BigDecimal> bigDecimalList = Functional
		.<BigDecimal, Double> transform(doubleList,
			new UnaryFunction<BigDecimal, Double>() {

			    @Override
			    public BigDecimal function(Double x) {
				return new BigDecimal(x, mContext);
			    }
			});
	System.out.println(bigDecimalList);
	

    }
}
