package generic;

import java.lang.reflect.Method;

/**
 * @author timmy00274672
 * 
 */
public class LatenReflection {

    public static void main(String[] args) {
	CommunicateReflectively.perform(new SmartDog());
	CommunicateReflectively.perform(new Mime());
    }

}

class Mime {
    public void walkAgainstTheWind() {
    }

    public void sit() {
	System.out.println("Pretending to sit");
    }

    public void pushInvisibleWalls() {
    }

    @Override
    public String toString() {
	return Mime.class.getSimpleName();
    }
}

class SmartDog {
    public void speak() {
	System.out.println("Woof!");
    }

    public void sit() {
	System.out.println("Sitting");
    }

    public void reproduce() {
    }
}

class CommunicateReflectively {
    public static void perform(Object speaker) {
	Class<?> spkr = speaker.getClass();
	try {
	    try {
		Method speak = spkr.getMethod("speak");
		speak.invoke(speaker);
	    } catch (NoSuchMethodException e) {
		System.out.println(speaker + " cannot speak");
	    }

	    try {
		Method sit = spkr.getMethod("sit");
		sit.invoke(speaker);
	    } catch (NoSuchMethodException e) {
		System.out.println(speaker + " cannot sit");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
