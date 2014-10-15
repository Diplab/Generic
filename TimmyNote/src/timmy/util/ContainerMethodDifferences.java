package timmy.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class ContainerMethodDifferences {

    private static final Set<String> OBJECTS_METHOD;
    static{
	OBJECTS_METHOD = methodSet(Object.class);
	OBJECTS_METHOD.add("clone");
    }

    static Set<String> methodSet(Class<?> type) {
	Set<String> methodSet = new HashSet<>();
	for (Method method : type.getMethods()) {
	    methodSet.add(method.getName());
	}
	return methodSet;
    }

    static Set<String> interfaceSet(Class<?> type) {
	Set<String> interfaceSet = new HashSet<>();
	for (Class<?> inter : type.getInterfaces()) {
	    interfaceSet.add(inter.getName());
	}
	return interfaceSet;
    }

    static void showDifference(Class<?> aClass, Class<?> bClass) {
	System.out.println(String.format(Locale.TAIWAN,
		"difference between %s and %s", aClass.getSimpleName(),
		bClass.getSimpleName()));
	Set<String> difference = Sets.difference(methodSet(aClass), methodSet(bClass));
	difference = Sets.difference(difference, OBJECTS_METHOD);
	System.out.println(String.format(Locale.TAIWAN, "method: %s\n",
		difference));
//	System.out.println(String.format(Locale.TAIWAN, "interface: %s\n",
//		Sets.difference(interfaceSet(aClass), interfaceSet(bClass))));

    }

    public static void main(String[] args) {
	System.out.println(methodSet(Object.class));
	
	showDifference(Set.class, Collection.class);
	{
	    showDifference(HashSet.class, Set.class);
	    {
		showDifference(LinkedHashSet.class, HashSet.class);
	    }
	    showDifference(TreeSet.class,Set.class);
	}
	
	showDifference(List.class, Collection.class);
	{
	    showDifference(ArrayList.class, List.class);
	    showDifference(LinkedList.class, List.class);
	}
	
	showDifference(Queue.class, Collection.class);
	{
	    showDifference(PriorityQueue.class, Queue.class);
	}
    }

}
