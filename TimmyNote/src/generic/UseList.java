package generic;

import java.util.List;

/**
 * 
 * @author timmy00274672
 * 
 */
public class UseList<K, V> implements Exception {
    void f(List<K> l) {
    }

    // won't compile
    // void f(List<V> l){}
    public static void main(String[] args) {

    }

}
