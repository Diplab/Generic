
public class ObjectFoo2 {

	public static void main(String[] args) {
		ObjectFoo foo1 = new ObjectFoo();
	    ObjectFoo foo2 = new ObjectFoo();

	    foo1.setFoo(new Boolean(true));
	    // �O�o�ഫ�ާ@���A
	    Boolean b = (Boolean) foo1.getFoo();
	    System.out.println(b);

	    foo2.setFoo(new Integer(10));
	    // �O�o�ഫ�ާ@���A
	    Integer i = (Integer) foo2.getFoo();
	    System.out.println(i);

	}

}
