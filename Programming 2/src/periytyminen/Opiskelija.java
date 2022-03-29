package periytyminen;

public class Opiskelija extends Henkilo {

	private int opintopisteet;

	public static void main(String[] args) {
		Opiskelija olli = new Opiskelija("Olli", "Ida Albergintie 1 00400 Helsinki");
		System.out.println(olli);
		System.out.println("opintopisteitä " + olli.opintopisteita());

		olli.opiskele();

		System.out.println("opintopisteitä " + olli.opintopisteita());
	}

	public Opiskelija(String name, String addr) {
		super(name, addr);
		this.opintopisteet = 0;
	}

	public int opintopisteita() {
		return this.opintopisteet;
	}

	public void opiskele() {
		this.opintopisteet += 1;

	}

	@Override
	public String toString() {
		return super.toString() + "\n  opintopisteitä " + this.opintopisteet;
	}

}