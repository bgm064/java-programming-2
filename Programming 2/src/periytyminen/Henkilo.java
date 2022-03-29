package periytyminen;

public class Henkilo {

	private String name;
	private String addr;
	
	public static void main(String[] args) {

		Henkilo ada = new Henkilo("Ada Lovelace", "Korsontie 1 03100 Vantaa");
		Henkilo esko = new Henkilo("Esko Ukkonen", "Mannerheimintie 15 00100 Helsinki");
		
		System.out.println(ada);
		System.out.println(esko);

	}

	public Henkilo(String name, String addr) {
		this.name = name;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return this.name + "\n  " + this.addr;
	}

}
