package generic;

import java.awt.Color;

interface HasColor {
    Color getColor();
}

class Colored<T extends HasColor> {
    T item;

    public Colored(T item) {
	System.out.println("Name: " + item.getClass().getSimpleName());
	this.item = item;
    }

    T getItem() {
	return item;
    }

    Color color() {
	return item.getColor();
    }
}

class Dimension {
    int x, y, z;
}

class ColoredDimension<T extends Dimension & HasColor> {
    public ColoredDimension(T item) {
	System.out.println(item.getClass().getSimpleName());
    }

}

class Bounded extends Dimension implements HasColor {

    @Override
    public Color getColor() {
	return null;
    }

}

// The type Dimension is not an interface; it cannot be specified as a bounded
// parameter
// class ColoredDimension2<T extends HasColor & Dimension> {}

public class BasicBound {

    public static void main(String[] args) {
	new Colored<>(new Bounded()); // Name: Bounded
	new Colored<>(new HasColor() {

	    @Override
	    public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	    }
	});// Name: (none)

	new ColoredDimension<>(new Bounded());
    }

}