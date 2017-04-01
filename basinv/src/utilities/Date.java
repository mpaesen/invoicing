package utilities;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Deze class representeert een datum object en voorziet elementaire testen
 * 
 * @author Mathy Paesen
 * @date 24 september 2009
 */
public class Date implements Comparable<Date> {
	public static final int LONG_JUL = 1000;
	// berekening van Julian datum
	public static final int LEAP_YEAR = 366;
	public static final int NORMAL_YEAR = 365;
	public static final int AANTAL_MAANDEN = 12;
	public static final int CORRECTIE_MAAND = 1;
	// maanden worden bewaard van 0 - 11
	public static final int EERSTE_MAAND = 0;
	public static final int LAATSTE_MAAND = 11;
	public static final int DATUM_CORRECT = 0;
	public static final int DAG_KLEINER = -1;
	public static final int MAAND_KLEINER = -2;
	public static final int JAAR_KLEINER = -3;
	public static final int DAG_GROTER = 1;
	public static final int MAAND_GROTER = 2;
	public static final int JAAR_GROTER = 3;
	public static final String SEPARATOR = "/";
	public static final String[] MAAND_TEXT = { "Januari", "Februari", "Maart",
			"April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober",
			"November", "December" };

	private int dag;
	private int maand;
	private int jaar;
	private long longDate;

	public Date() throws DatumException {
		GregorianCalendar gc;
		gc = new GregorianCalendar();
		this.setDag(gc.get(Calendar.DATE));
		this.setMaand(gc.get(Calendar.MONTH));
		this.setJaar(gc.get(Calendar.YEAR));
		testDatum();
	}
	
	public Date(long l)throws DatumException{
		Calendar cal = new GregorianCalendar();
		cal.setTime(new java.util.Date(l));
		this.setDag(cal.get(Calendar.DATE));
		this.setMaand(cal.get(Calendar.MONTH));
		this.setJaar(cal.get(Calendar.YEAR));

		testDatum();

	}

	public Date(int dag, int maand, int jaar) throws DatumException {
		super();
		this.setDag(dag);
		this.setMaand(maand);
		this.setJaar(jaar);
		testDatum();
	}

	public Date(Date date) throws DatumException {
		this(date.getDag(), date.getMaand() , date
				.getJaar());
		testDatum();
	}

	public Date(String datum) throws DatumException {
		/* DD/MM/JJJJ */
		StringTokenizer tokenizer = new StringTokenizer(datum, SEPARATOR);
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			switch (i) {
			case 0:
				this.setDag(Integer.parseInt((String) tokenizer.nextElement()));
				break;
			case 1:
				this.setMaand(Integer.parseInt((String) tokenizer.nextElement())
						);
				// maanden worden bewaard van 0 - 11
				break;
			case 2:
				this.setJaar(Integer.parseInt((String) tokenizer.nextElement()));
				break;
			default:
				this.setDag(0);
				this.setMaand(0);
				this.setJaar(0);
			}
			i++;
		}
		testDatum();
	}

	/**
	 * Indien een datum object niet correct is wordt een DatumException
	 * gegenereerd
	 * 
	 * @throws DatumException
	 */
	private void testDatum() throws DatumException {
		// maand 0-11

		GregorianCalendar testDatum = new GregorianCalendar(this.jaar,
				this.maand, this.dag);
		testDatum.setLenient(false);// enkel correcte datums toegelaten (geen
		// omrekening)
		try {
			testDatum.get(Calendar.YEAR);
			testDatum.get(Calendar.MONTH);
			testDatum.get(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			throw new DatumException(this);
		}
		longDate = testDatum.getTime().getTime();
	}

	public int getDag() {
		return dag;
	}

	public void setDag(int dag) {
		this.dag = dag;
	}

	public int getMaand() {
		return maand; // maanden worden bewaard van 0 - 11
	}

	public void setMaand(int maand) {
		this.maand = maand;
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	/**
	 * Enkel correcte data mogen toegelaten worden.
	 * 
	 * @param jaar
	 * @param maand
	 * @param dag
	 * @return true, false
	 */
	public boolean setDatum(int dag, int maand, int jaar) {
		boolean correct = false;
		try {
			new Date(dag, maand, jaar);
			this.setDag(dag);
			this.setMaand(maand);
			this.setJaar(jaar);
			correct = true;
		} catch (DatumException e) {
			correct = false;
			System.err.println("[" + jaar + SEPARATOR + maand + SEPARATOR + dag
					+ "]" + e);
		}
		return correct;
	}

	/**
	 * Date in USA formaat
	 * 
	 * @return MM/DD/YYYY
	 */
	public String getDatumInAmerikaansFormaat() {
		return String.format("%s%s%s", getMaand()+CORRECTIE_MAAND + SEPARATOR, getDag()
				+ SEPARATOR, getJaar());

	}

	/**
	 * Date in EUR formaat
	 * 
	 * @return DD/MM/YYYY
	 */
	public String getDatumInEuropeesFormaat() {
		return String.format("%s%s%s", getDag() + SEPARATOR, getMaand()+CORRECTIE_MAAND
				+ SEPARATOR, getJaar());

	}

	/**
	 * @return Januari, ..., December
	 */
	public String getMaandText() {
		if (getMaand() > LAATSTE_MAAND || getMaand() < EERSTE_MAAND) {
			return "Verkeerde maand";
		}
		return MAAND_TEXT[getMaand()];
	}

	/*
	 * String representatie van deze datum
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getDag() + " " + getMaandText() + " " + getJaar();
	}

	public String toString(String sep) {
		return getDag() + sep + getMaand() + sep + getJaar();
	}

	/**
	 * Is deze datum gelijk aan een ander datum object
	 * 
	 * @param date
	 * @return
	 */
	public boolean equals(Date date) {
		if (this.getDag() != date.getDag()) {
			return false;
		} else if (this.getMaand() != date.getMaand()) {
			return false;
		} else if (this.getJaar() != date.getJaar()) {
			return false;
		}
		return true;
	}

	/**
	 * 0 Deze datum en de parameter datum zijn gelijk -1 Als de dag van de maand
	 * van deze datum kleiner is dan die van een ander datum object -2 Als de
	 * maand van deze datum kleiner is dan die van een ander datum object -3 Als
	 * het jaar van deze datum kleiner is dan die van een ander datum object
	 * 
	 * @param date
	 * @return -1, -2, -3, 0
	 */
	@Override
	public int compareTo(Date date) {
		if (this.getJaar() < date.getJaar()) {
			return JAAR_KLEINER;
		}
		if (this.getJaar() > date.getJaar()) {
			return JAAR_GROTER;
		}
		if (this.getMaand() < date.getMaand()) {
			return MAAND_KLEINER;
		}
		if (this.getMaand() > date.getMaand()) {
			return MAAND_GROTER;
		}
		if (this.getDag() < date.getDag()) {
			return DAG_KLEINER;
		}
		if (this.getDag() > date.getDag()) {
			return DAG_GROTER;
		}
		return DATUM_CORRECT;
	}

	/**
	 * Is deze datum kleiner dan een ander datum object
	 * 
	 * @param date
	 * @return
	 */
	public boolean kleinerDan(Date date) {
		if (this.compareTo(date) < DATUM_CORRECT) {
			return true;
		}
		return false;
	}

	/**
	 * Bereken het verschil in jaren tussen deze datum en een ander datum object
	 * 
	 * @param date
	 * @return
	 */
	public int verschilInJaren(Date date) {
		return this.getJaar() - date.getJaar();
	}

	/**
	 * Bereken het verschil in maanden tussen deze datum en een ander datum
	 * object
	 * 
	 * @param date
	 * @return
	 */
	public int verschilInMaanden(Date date) {
		int verschil = this.verschilInJaren(date);
		verschil *= AANTAL_MAANDEN;
		verschil += (this.getMaand() - date.getMaand());
		return verschil;
	}

	/**
	 * Bereken het verschil in dagen tussen deze datum en een ander datum object
	 * 
	 * @param date
	 * @return
	 */
	public int verschilInDagen(Date date) {
		int verschil = 0;
		Date test;
		try {
			test = new Date(this);
			for (int i = 0; i < this.verschilInJaren(date); i++) {
				test.setJaar(test.getJaar() - 1);
				verschil += test.aantalDagen();
			}
		} catch (DatumException e) {
			e.printStackTrace();
		}
		verschil += this.dagVanHetJaar() - date.dagVanHetJaar();
		return verschil;
	}

	/**
	 * Schrikkeljaar of niet
	 * 
	 * @return true, false
	 */
	public boolean isLeapYear() {
		if ((this.getJaar() % 400 == 0)
				|| ((this.getJaar() % 4 == 0) && (this.getJaar() % 100 != 0))) {
			return true;
		}
		return false;
	}

	/**
	 * Bereken aantal dagen van een maand
	 * 
	 * @return { 31, 28/29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }
	 */
	public int dagVanHetJaar() {
		int dagen[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int totaal = 0;
		for (int maand = 0; maand < this.getMaand(); maand++) {
			// huidige maand nog niet meetellen
			totaal += dagen[maand];
			if (this.isLeapYear() && maand == 1) {
				totaal += 1; // 29 ipv 28 dagen
			}
		}
		totaal += this.getDag();
		return totaal;
	}

	/**
	 * Dag van het jaar
	 * 
	 * @return xxx/1-366
	 */
	public int julianDatum() {
		int totaal = this.getJaar() * LONG_JUL;
		return totaal += dagVanHetJaar();
	}

	/**
	 * Aantal dagen van het jaar
	 * 
	 * @return 365 of 366
	 */
	public int aantalDagen() {
		if (this.isLeapYear()) {
			return LEAP_YEAR;
		}
		return NORMAL_YEAR;
	}

	/**
	 * Wijzig een datum met een aantal dagen (+/-)
	 * 
	 * @param aantalDagen
	 */
	public void veranderDatum(int aantalDagen) {
		this.setDag(this.getDag() + aantalDagen);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setLenient(true);
		gc.set(this.getJaar(), this.getMaand(), this.getDag());
		this.setDag(gc.get(Calendar.DAY_OF_MONTH));
		this.setMaand(gc.get(Calendar.MONTH));
		this.setJaar(gc.get(Calendar.YEAR));
	}

	/**
	 * Berekening van een Unieke int Wordt gebruikt door de equals method
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		return julianDatum();
	}

	/**
	 * Geeft steeds de laatste dag van de maand
	 * 
	 * @param Date
	 * @return Date
	 * @throws DatumException
	 */
	public static Date laatsteDagVanDeMaand(Date date) throws DatumException {
		GregorianCalendar gc = new GregorianCalendar(date.jaar, date.maand,
				date.getDag());
		int dagVanDeMaand = gc.get(Calendar.DAY_OF_MONTH);
		gc.add(Calendar.MONTH, 1);
		gc.add(Calendar.DAY_OF_MONTH, -dagVanDeMaand);
		return new Date(gc.get(Calendar.DATE), gc.get(Calendar.MONTH)
				, gc.get(Calendar.YEAR));
		// GregorianCalendar kent enkel maanden tussen 0-11
	}

	/**
	 * @param Date
	 *            []
	 * @return Date[]
	 */
	public static Date[] sorteerDatums(Date[] datums) {
		Date hulp;
		for (int i = 0; i < datums.length - 1; i++) {
			for (int j = 0; j < datums.length - 1; j++) {
				if (datums[j + 1].kleinerDan(datums[j])) {
					hulp = datums[j];
					datums[j] = datums[j + 1];
					datums[j + 1] = hulp;
				}
			}
		}
		return datums;
	}
	
	public long getTimeInMilliSeconds(){
		return longDate; //
	}
}