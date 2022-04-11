package map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EtunimiTilasto {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Map<String, Integer> mappi = luoTilasto();

		while (true) {
			System.out.print("Anna etunimi: ");
			String nimi = input.nextLine();

			if (nimi.equalsIgnoreCase("lopeta")) {
				input.close();
				System.exit(0);
			}

			int lkm = mappi.getOrDefault(nimi, 0);

			System.out.println(nimi + ": " + lkm + " kappaletta\n");
		}
	}

	public static Map<String, Integer> luoTilasto() {

		TiedostonLukija lukija = new TiedostonLukija("src/map/etunimet.csv");
		List<String> rivit = lukija.lueRivit();
		HashMap<String, Integer> tilasto = new HashMap<String, Integer>();

		for (String rivi : rivit) {
			String[] osat = rivi.split(";");

			String nimi = osat[0];
			Integer lukumaara = Integer.parseInt(osat[1].replaceAll(" ", ""));

			if (tilasto.containsKey(nimi)) {
				int edellinenLuku = tilasto.get(nimi);
				tilasto.put(nimi, edellinenLuku + lukumaara);
			} else {
				tilasto.put(nimi, lukumaara);
			}
		}
		
		return tilasto;
	}
	
}
