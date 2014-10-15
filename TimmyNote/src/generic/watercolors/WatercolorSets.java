package generic.watercolors;

import java.util.EnumSet;

import timmy.util.Sets;

public class WatercolorSets {

    public static void main(String[] args) {
	EnumSet<Watercolors> set1 = EnumSet.range(Watercolors.BRILLIANT_RED,
		Watercolors.VIRIDIAN_HUE);
	EnumSet<Watercolors> set2 = EnumSet.range(Watercolors.CERULEAN_BLUE_HUE,
		Watercolors.BURNT_UMBER);

	System.out.println(Sets.union(set1, set2));
	System.out.println(Sets.difference(set1, set2));
    }

}
