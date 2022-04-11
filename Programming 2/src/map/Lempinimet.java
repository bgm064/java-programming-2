package map;

import java.util.TreeMap;

public class Lempinimet {

	public static void main(String[] args) {

		TreeMap<String, String> nimet = new TreeMap<>();

		nimet.put("Teemu", "The Finnish Flash");
		nimet.put("Jari", "Litti");
		nimet.put("Kaisa-Leena", "Kappa");

		System.out.println(nimet);

	}

}
