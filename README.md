Generic
=======

## Outline

- [前言](#前言)
- [泛型的定義](#泛型的定義)
- [泛型入門](#泛型入門)
    + [沒有泛型](#沒有泛型)
    + [有泛型](#有泛型)

## 前言
Java單一繼承體系的架構過於侷限。如果是interface而非class，便可讓這個限制鬆綁。但interface在使用上必須實作特定的interface，因此仍過於受限。如果能讓程式碼操作"某些位確定型別"，就可不再受限於特定的interface或是class。這就是"泛型"的概念，他是Java SE5中重要的改變。

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
