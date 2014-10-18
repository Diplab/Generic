package generic;


/**
 * It show that there is not covariant argument types
 * @author timmy00274672
 *
 */
public class OrdinaryArguments {

    public static void main(String[] args) {
	new OrdinarySetter().set(new Base());
	new DerivedSetter().set(new Base());
	new DerivedSetter().set(new Derived());
    }
}

class Base{}
class Derived extends Base{}
class OrdinarySetter{
    void set(Base base){
	System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends OrdinarySetter{
    void set(Derived derived){
	System.out.println("DerivedSetter.set(Derived)");
    }
    
    @Override
    void set(Base base) {
	System.out.println("DerivedSetter.set(Base)");
    }
}