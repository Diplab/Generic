Generic
=======

## Outline

- [前言](#前言)
- [泛型的定義](#泛型的定義)
- [泛型入門](#泛型入門)
    + [沒有泛型](#沒有泛型)
    + [有泛型](#有泛型)
    + [有序表(tuple)](#有序表(tuple))
- [泛型介面](#泛型介面)
- [擦拭(erasure)](#擦拭(erasure))
- [萬用字元(wildcards)](#萬用字元(wildcards))
	+ [< ? extends T >](#< ? extends T >)
	+ [< ? super T >](#< ? super T >)
- [議題(Issues)](#議題(Issues))
- [動態型別安全(Dynamic type safety)](#動態型別安全(Dynamic type safety))
- [異常(Exception)](#異常(Exception))
- [混成型別(Mixins)](#混成型別(Mixins))
	+ [混成介面](#混成介面)

## 前言
Java單一繼承體系的架構過於侷限。如果是interface而非class，便可讓這個限制鬆綁。但interface在使用上必須實作特定的interface，因此仍過於受限。如果能讓程式碼操作"某些未確定型別"，就可不再受限於特定的interface或是class。這就是"泛型"的概念，他是Java SE5中重要的改變。

##泛型的定義

- 泛型實做了參數化型別的概念，讓你撰寫容易使用多種型別的物件，尤其是容器。
- 撰寫程式時，我們可能無法再編譯期間確定程式碼的撰寫方式，因此，將主要的核心演算法與邏輯獨立出來，不再牽制於資料型別的限制。
- 例如：堆疊結構需要Pop()、Push()等方法，但堆入的元素型別可能是String、double、float...等，與其為每個型別都撰寫一種堆疊類別來處理來實現多型，不如用泛型。
- 泛型可以讓類別的型別參數化，也就是說我們可以定義完堆疊後，再告訴編譯器我們的堆疊會使用甚麼樣的資料型別。如此一來同樣達成了「一種介面，多種方法」的技巧。

## 泛型入門

### 沒有泛型

**範例1 BooleanFoo.java**
```java
public class BooleanFoo {
    private Boolean foo;
 
    public void setFoo(Boolean foo) {
        this.foo = foo;
    }
 
    public Boolean getFoo() {
        return foo;
    }
}
```

**範例2 IntegerFoo.java**
```java
public class IntegerFoo {
    private Integer foo;
 
    public void setFoo(Integer foo) {
        this.foo = foo;
    }
 
    public Integer getFoo() {
        return foo;
    }
}
```

觀察範例兩個範例，其中除了宣告成員的型態、參數列的型態與方法返回值的型態不同之外，剩下的程式碼完全相同。我們不希望對每一個遇到的型別都重寫一個新的工具。泛型（Generics）的需求就在此產生。


由於 Java 中所有的類別最上層都繼承自 Object 類別，如範例3 的類別來取代範例1 與範例2 的類別。

**範例3 ObjectFoo.java**
```java
public class ObjectFoo {
    private Object foo;
 
    public void setFoo(Object foo) {
        this.foo = foo;
    }
 
    public Object getFoo() {
        return foo;
    }
}
```

由於 Java 中所有定義的類別，都以 Object 為最上層的父類別，所以在J2SE1.4或之前版本上，只要撰寫如範例3 的類別，然後可以如下的使用它：

```java
    ObjectFoo foo1 = new ObjectFoo();
    ObjectFoo foo2 = new ObjectFoo();
    
    foo1.setFoo(new Boolean(true));
    // 記得轉換操作型態
    Boolean b = (Boolean) foo1.getFoo();

    foo2.setFoo(new Integer(10));
    // 記得轉換操作型態
    Integer i = (Integer) foo2.getFoo();
```
但是設定至 foo1 或 foo2 的 Integer 或 Boolean 實例會失去其型態資訊，從 getFoo() 傳回的是 Object 型態的實例，必須轉換它的操作型態。

###有泛型

在 J2SE 5.0 之後，提出了針對泛型（Generics）設計的解決方法，定義一個簡單的泛型類別，範例4 取代範例3 。

**範例4 GenericFoo.java**
```java
public class GenericFoo<T> {
    private T foo;
 
    public void setFoo(T foo) {
        this.foo = foo;
    }
 
    public T getFoo() {
        return foo;
    }
}
```

在範例4 中，使用 `<T>` 用來宣告一個型態持有者（Holder）名稱 T，之後您可以用 T 這個名稱作為型態代表來宣告成員、參數或返回值型態，然後可以如範例5 來使用這個類別。

**範例5 GenericFooDemo.java**
```java
public class GenericFooDemo {
    public static void main(String[] args) {
        GenericFoo<Boolean> foo1 = new GenericFoo<Boolean>();
        GenericFoo<Integer> foo2 = new GenericFoo<Integer>();
 
        foo1.setFoo(new Boolean(true));
        Boolean b = foo1.getFoo(); // 不需要再轉換型態
        System.out.println(b);

        foo2.setFoo(new Integer(10));
        Integer i = foo2.getFoo(); // 不需要再轉換型態
        System.out.println(i);
    }
}
```

###有序表(tuple)

有序表是一種直接將一群物件包覆在一起而成的單一物件。每一個在有序表中的物件都可以是不同型別。在使用有序表時，只需要直接定義適當長度的有序表作為 method 的回傳值，並在 return 時傳回。

**範例6 GenericFoo2.java**
```java
public class GenericFoo2<T1, T2> {
    private T1 foo1;
    private T2 foo2;
 
    public void setFoo1(T1 foo1) {
        this.foo1 = foo1;
    }
 
    public T1 getFoo1() {
        return foo1;
    }
 
    public void setFoo2(T2 foo2) {
        this.foo2 = foo2;
    }
 
    public T2 getFoo2() {
        return foo2;
    }
}
```
使用 GenericFoo2 類別，分別以 Integer 與 Boolean 設定 T1 與 T2 ：
```java
    GenericFoo<Integer, Boolean> foo = new GenericFoo<Integer, Boolean>();

```

**Map應用**
```java
public class test {
	public static void main(String[] args) {
        Map<String, String> map =  new TreeMap<String, String>();
        map.put("dog", "DOG");
        map.put("cat", "CAT");
        map.put("bird", "BIRD");

        for(String value : map.values()) {
            System.out.println(value);    
        }
    }	
}
```




再如範例6-1 寫一個子類別擴充範例6 的父類別。

#### **範例6-1 SubGenericFoo4.java**
```java
public class SubGenericFoo2<T1, T2, T3> 
                extends GenericFoo2<T1, T2> {
    private T3 foo3;
  
    public void setFoo3(T3 foo3) {
        this.foo3 = foo3;
    }
 
    public T3 getFoo3() {
        return foo3;
    }
}
```

如果決定要保留型態持有者，則父類別上宣告的型態持有者數目在繼承下來時必須寫齊全，也就是說在範例6-1 中，父類別上 GenericFoo2 上出現的 T1 與 T2 在 SubGenericFoo2 中都要出現，如果不保留型態持有者，則繼承下來的 T1 與 T2 自動變為 Object，建議是父類別的型態持有者都要保留。

##泛型介面

- 定義：interface interface-name<type-param-list>{//...
- 實作：class class-name<type-param-list> implements interface-name<type-arg-list>{//...

泛型也可施用於 interfaces 。產生器(generator)是種能建立物件的 class 。產生器只定義 method ， 而此 method 能產生新物件。在此，我們將它稱為 next()，並包裝在標準的公用函式中。
```java
package net.mindview.util;

public interface Generator<T> {
	T next();
}
```
next() 的回傳型別被參數化為 T 。由此可知，在 interfaces 中使用泛型跟在 class 的泛型是一樣的。

以下是 Generator<T> 的實作，以費式數列為例：

**範例7 Fibonacci.java**
```java
package net.mindview.util;

public class Fibonacci implements Generator<Integer>{
	private int count = 0;
	public Integer next(){ 
		return fib(count++);
	}
	private int fib (int n){
		if(n < 2) 
			return 1;
		return fib(n-2) + fib(n-1);
	}
	
	public static void main(String[] args) {
		Fibonacci gen = new Fibonacci();
		for(int i = 0; i<10; i++)
			System.out.print(gen.next() + " ");
	}
}
```
這邊帶出一項 Java 泛型的限制：當宣告一個泛型類別的實體時，傳入的型別參數必須是類別型別，你不能使用基本型別，像是int或char：

Gen<int> str = new Gen<int>(53); //錯誤，不能使用基本型別。 

##擦拭(erasure)

Java 泛型是利用擦拭來實作。也意味著使用泛型時，任何特殊型別的資訊都會被擦拭掉。

例如：
- **參數化的型別**，經過擦拭後應去除參數： List<T> 被擦拭為 List
- **未被參數化的型別**，經過擦拭後獲得型別本身： Byte 被擦拭為 Byte
- **型別參數**，經過擦拭後得 object ： T 被擦拭為 object

**範例8 Erased.java**
```java
import java.util.*;

public class Erased {

	public static void main(String[] args) {
		Class c1 = new ArrayList<String>().getClass();
		Class c2 = new ArrayList<Integer>().getClass();
		System.out.println(c1 == c2);
	}

}
```

##萬用字元(wildcards)

###< ? extends T >

例如，有一個 foo 名稱參考的對象，其型態持有者實例化的對象是實作 List 介面的類別或其子類別，要宣告一個參考名稱，可以使用 '?'「萬用字元」（Wildcard），'?' 代表未知型態，並使用 "extends" 來作限定，例如：
```java
    GenericFoo<? extends List> foo = null;
    foo = new GenericFoo<ArrayList>();
    foo = new GenericFoo<LinkedList>();
```

`<? extends List>` 表示型態未知，只知會是實作 List 介面的類別，所以如果型態持有者實例化的對象不是實作 List 介面的類別，則編譯器會回報錯誤，例如以下這行無法通過編譯：
```java
    GenericFoo<? extends List> foo = new GenericFoo<HashMap>();
```

使用 '?' 來作限定，例如若想要自訂一個 showFoo() 方法，方法的內容實作是針對 String 或其子類的實例而制定的，例如：
```java
    public void showFoo(GenericFoo foo) {
    }
```

如果不希望任何的型態都可以傳入showFoo() 方法中，可以使用以下的方式來限定：
```java
    public void showFoo(GenericFoo<? extends String> foo) {
        // 針對String或其子類而制定的內容
        System.out.println(foo.getFoo());
    }
```

在宣告名稱時如果指定了 `<?>` 而不使用 "extends"，則預設是允許 Object 及其下的子類。

那為什麼不直接使用 `GenericFoo` 宣告就好了，何必要用`GenericFoo<?>`來宣告？

例如：
```java
    GenericFoo<String> foo = new GenericFoo<String>();
    foo.setFoo("caterpillar");
    
    GenericFoo<?> immutableFoo = foo;
    // 取得資訊
    System.out.println(immutableFoo.getFoo());

    // 透過immutableFoo來移去foo所參考實例內的資訊
    immutableFoo.setFoo(null);

    // 不可透過immutableFoo來設定新的資訊給foo所參考的實例
    // 所以下面這行無法通過編譯
    //  immutableFoo.setFoo("123");
```

使用 `<?>` 或是 `<? extends SomeClass>` 的宣告方式，只能透過該名稱來取得所參考實例的資訊，或是移除某些資訊，但不能增加它的資訊，因為不知道 `<?>` 或是 `<? extends SomeClass>` 宣告的參考名稱，實際上參考的物件，當中確實儲存的是什麼類型的資訊。因為若能加入，被加入的物件同樣也會有失去型態資訊的問題。


###< ? super T >

除了 extends ，也可使用 "super" ，例如：

    GenericFoo<? super StringBuilder> foo = null;

如此一來， foo 就只接受 StringBuilder 及其上層的父類型態，也就是只能接受 `GenericFoo<StringBuilder>` 與 `GenericFoo<Object>` 的實例。

##議題(Issues)
Java 泛型時所會遇到的問題

- 不能使用基礎型別作為參數
	+ 像是int或char：ArrayList<int>
	
- 實作參數化的界面
	+ class 不能同時實作同一泛型 interface 的兩種變型，因為擦拭關係，他們會變成相同的 interface 。例如：List<String> 和 List<Integer> 都會因為擦拭變成 List

- 轉型與警告
	+ 對泛型型別使用轉型並不會有任何效果。
	+ 因為擦拭關係，無法得知該轉型是否安全，因此，使用 @SuppressWarnings() ，可使編譯器將警告忽略掉。

- 重載
	+ 由於擦拭，重載方法會產生完全相同的型別
	```java
	class UseList<W,T> {
    // 錯誤：Method f(List<T>) has the same erasure f(List<E>) as another method in type UseList<W,T>
    void f(List<T> v) {}
    void f(List<W> v) {}
}
```
- 劫持 interface 的 base class
	+假設有一個Pet類別，對其他Pet物件而言為ComparablePet
```java
class ComparablePet implements Comparable<ComparablePet> {
    public int compareTo(ComparablePet arg) { return 0; }
}
 
// Comparable<ComparablePet> and Comparable<Cat>
// ComparablePet劫持Comparable，只能進行ComparablePet的比較，而不能進行Cat的比較
class Cat extends ComparablePet implements Comparable<Cat>{
    // Error: Comparable cannot be inherited with
    // different arguments: <Cat> and <Pet>
    public int compareTo(Cat arg) { return 0; }
}

```

##動態型別安全(Dynamic type safety)

因為可將泛型容器傳入 Java SE5 之前的程式碼，所以就是程式碼可能破壞傳入的容器。因此，Java SE5 中提供 static method 檢查問題：checkedCollection(), checkedList(), checkedMap(), checkedSet(), checkedSortedMap(), checkedSortedSet()

```java
interface Pet{}
class Dog implements Pet{}
class Cat implements Pet{}
public class CheckList {

    // oldStyleMethod()是舊友程式碼
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    } 

    public static void main(String[] args) {
        // 沒有check之前是可以的
        List<Dog> dogs1 = new ArrayList<Dog>();
        oldStyleMethod(dogs1); // Quietly accepts a Cat
        // check之後抛出 ClassCastException
        List<Dog> dogs2 = Collections.checkedList(
                new ArrayList<Dog>(), Dog.class);
        try {
            oldStyleMethod(dogs2); // Throws an exception
        } catch(Exception e) {
            System.out.println(e);
        }
        // Derived types work fine:
        List<Pet> pets = Collections.checkedList(
                new ArrayList<Pet>(), Pet.class);
        pets.add(new Dog());
        pets.add(new Cat());
    }
}
```
##異常(Exception)

因為擦拭關係，泛型機制搭配異常使用會受限制。catch 不能捕捉泛型型別的異常，因為異常的確切型別再編譯時期與執行時期都必須是確知的。不過，在 method 定義時的 throws 可使用型別參數。

```java
interface Processor<T,E extends Exception> {
    void process(List<T> resultCollector) throws E; // 抛出泛型異常
}
```
##混成型別(Mixins)

基本概念是混合來自多個classes的能力，以得到表示混合所有型別的class。混成型別價值之一在於，能套用多個classes的特性及行為。

###混成介面

Mixin 類別基本上是利用委任(delegation)，所以每個混進的型別都需要一個欄位，而且必須撰寫所有必要的 methods，將方法傳遞至合適物件。
- 缺點是隨著混入更複雜的class，程式碼便會增長迅速。

```java
interface TimeStamped { long getStamp(); }

class TimeStampedImp implements TimeStamped {
    private final long timeStamp;
    public TimeStampedImp() {
        timeStamp = new Date().getTime();
    }
    public long getStamp() { return timeStamp; }
}

interface SerialNumbered { long getSerialNumber(); }

class SerialNumberedImp implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public long getSerialNumber() { return serialNumber; }
}

interface Basic {
    public void set(String val);
    public String get();
}

class BasicImp implements Basic {
    private String value;
    public void set(String val) { value = val; }
    public String get() { return value; }
}

```
每個混進的型別都需要一個欄位
```java
class Mixin extends BasicImp
implements TimeStamped, SerialNumbered {
    // 混入class對應的欄位
    private TimeStamped timeStamp = new TimeStampedImp();
    // 混入class對應的欄位
    private SerialNumbered serialNumber =
            new SerialNumberedImp();
    // 在Mixin中撰寫所有必须的方法，將方法傳遞至合適物件:
    public long getStamp() { return timeStamp.getStamp(); }
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }
}

public class Mixin {

    public static void main(String[] args) {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " +
          mixin1.getStamp() +  " " + mixin1.getSerialNumber());
        System.out.println(mixin2.get() + " " +
          mixin2.getStamp() +  " " + mixin2.getSerialNumber());
    }
}
```


