package timmy.util;

import java.util.HashSet;
import java.util.Set;

public class Sets {
    private Sets() {
    }

    public static <E> Set<E> union(Set<E> set1, Set<E> set2) {
	Set<E> resultSet = new HashSet<>(set1);
	resultSet.addAll(set2);
	return resultSet;
    }

    public static <E> Set<E> intersection(Set<E> set1, Set<E> set2) {
	Set<E> resultSet = new HashSet<>(set1);
	resultSet.retainAll(set2);
	return resultSet;
    }

    public static <E> Set<E> difference(Set<E> set1, Set<E> set2) {
	Set<E> resultSet = new HashSet<>(set1);
	resultSet.removeAll(set2);
	return resultSet;
    }

    public static <E> Set<E> complement(Set<E> set1, Set<E> set2) {
	return difference(union(set1, set2), intersection(set1, set2));
    }
}
