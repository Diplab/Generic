package generic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Shape {
    public void rotate() {
	System.out.println(this + " rotate");
    }

    public void resize(int newSize) {
	System.out.println(String.format("%s resize %d", this, newSize));
    }
}

class Square extends Shape {
}

@SuppressWarnings("serial")
class FilledList<T> extends ArrayList<T> {
    public FilledList(Class<? extends T> type, int size) {
	for (int i = 0; i < size; i++) {
	    try {
		add(type.newInstance());
	    } catch (InstantiationException e) {
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    }
	}
    }
}

/**
 * @author timmy00274672
 * 
 */
public class Apply {
    public static <T, S extends Iterable<? extends T>> void apply(S seq,
	    Method f, Object... args) {
	try {
	    for (T t : seq) {
		f.invoke(t, args);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) throws Exception {
	List<Shape> shapesList = new ArrayList<>();
	for (int i = 0; i < 2; i++) {
	    shapesList.add(new Shape());
	}

	Apply.apply(shapesList, Shape.class.getMethod("rotate"));
	Apply.apply(shapesList, Shape.class.getMethod("resize", int.class), 5);
	
	shapesList.clear();
	
	for (int i = 0; i < 2; i++) {
	    shapesList.add(new Square());
	}

	Apply.apply(shapesList, Shape.class.getMethod("rotate"));
	Apply.apply(shapesList, Shape.class.getMethod("resize", int.class), 5);
	
	Apply.apply(new FilledList<>(Shape.class, 2), Shape.class.getMethod("rotate"));
	Apply.apply(new FilledList<>(Square.class, 2), Shape.class.getMethod("rotate"));
    }
    

}
