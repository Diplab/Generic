import java.util.*;

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
