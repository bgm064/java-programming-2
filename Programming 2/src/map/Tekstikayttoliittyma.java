package map;

import java.util.Scanner;

public class Tekstikayttoliittyma {

	public static void main(String[] args) {

		Sanakirja sanakirja = new Sanakirja();
		Scanner input = new Scanner(System.in);

		System.out.println("Komennot:");
		System.out.println("  lisaa - lisää sanaparin sanakirjaan");
		System.out.println("  kaanna - kysyy sanan ja tulostaa sen käännöksen");
		System.out.println("  lopeta - poistuu käyttöliittymästä");

		while (true) {
			System.out.print("\nKomento: ");
			String komento = input.nextLine();

			switch (komento) {

			case "lisaa":
				System.out.print("Suomeksi: ");
				String sana = input.nextLine();

				System.out.print("Anna käännös: ");
				String kaannos = input.nextLine();

				sanakirja.lisaa(sana, kaannos);
				break;

			case "kaanna":
				System.out.print("Anna sana: ");
				sana = input.nextLine();

				System.out.println("Käännös: " + sanakirja.kaanna(sana));
				break;

			case "lopeta":
				input.close();
				System.out.println("Hei hei!");

				System.exit(0);
				break;

			default:
				System.out.println("Tuntematon komento.");
				break;
			}
		}

	}

}