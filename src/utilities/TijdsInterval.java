package utilities;

import java.util.GregorianCalendar;

/**
 * @author Mathy Paesen
 * @since 26 sept 2009
 */
public class TijdsInterval implements Comparable<TijdsInterval> {
    private GregorianCalendar vanMoment;
    private GregorianCalendar totMoment;

    public TijdsInterval() {
        this(new GregorianCalendar(), new GregorianCalendar());
    }

    public TijdsInterval(GregorianCalendar van, GregorianCalendar tot) {
        vanMoment = van;
        totMoment = tot;
    }

    public GregorianCalendar getVanMoment() {
        return vanMoment;
    }

    public void setVanMoment(GregorianCalendar vanMoment) {
        this.vanMoment = vanMoment;
    }

    public GregorianCalendar getTotMoment() {
        return totMoment;
    }

    public void setTotMoment(GregorianCalendar totMoment) {
        this.totMoment = totMoment;
    }

    /**
     * @return Voor= -2, Achter = +2, Overlapt = -1, Gelijk = 0
     */
    @Override
    public int compareTo(TijdsInterval tot) {
        // Voor = -2
        // Achter = +2
        // overlapt = -1
        // gelijk = 0
        if (this.getTotMoment().before(tot.getVanMoment())) {
            return -2;
        }
        if (this.getVanMoment().after(tot.getTotMoment())) {
            return +2;
        }
        if (this.overlapt(tot)) {
            return -1;
        }
        return 0;
    }

    /**
     * @return String representatie van een Tijdsinterval
     */
    public String toString() {
        return "Interval van " + vanMoment.toString() + " tot "
                + totMoment.toString();
    }

    /**
     * @return boolean
     */
    public boolean equals(TijdsInterval interval) {
        return this.getVanMoment() == interval.getVanMoment()
                && this.getVanMoment() == interval.getTotMoment();
    }

    /**
     * Berekening van het tijdsinterval uitgedrukt in dagen
     *
     * @return int
     */
    public int getLengteInDagen() {
        long verschil = totMoment.getTimeInMillis()
                - vanMoment.getTimeInMillis();
        int aantalDagen = (int) verschil / (24 * 60 * 60 * 1000);

        return aantalDagen;
    }

    /**
     * Berekening van het tijdsinterval uitgedrukt in uren
     *
     * @return int
     */
    public int getLengteInUren() {
        long verschil = totMoment.getTimeInMillis()
                - vanMoment.getTimeInMillis();
        int aantalUren = (int) verschil / (60 * 60 * 1000);

        return aantalUren;
    }

    /**
     * Berekening van het tijdsinterval uitgedrukt in minuten
     *
     * @return int
     */
    public int getLengteInMinuten() {
        long verschil = totMoment.getTimeInMillis()
                - vanMoment.getTimeInMillis();
        int aantalMinuten = (int) verschil / (60 * 1000);

        return aantalMinuten;
    }

    /**
     * Berekening van het tijdsinterval uitgedrukt in seconden
     *
     * @return int
     */
    public int getLengteInSeconden() {
        long verschil = totMoment.getTimeInMillis()
                - vanMoment.getTimeInMillis();
        int aantalSeconden = (int) verschil / (1000);

        return aantalSeconden;
    }

    /**
     * Evalueert of 2 tijdsintervallen mekaar overlappen
     *
     * @param Tijdsinterval
     * @return boolean
     */
    public boolean overlapt(TijdsInterval second) {
        if (this.getVanMoment().before(second.getVanMoment())
                && (second.getVanMoment().before(this.getTotMoment()))) {
            return true;
        }
        if (this.getVanMoment().before(second.getVanMoment())
                && (second.getTotMoment().before(this.getTotMoment()))) {
            return true;
        }
        if (second.getVanMoment().before(this.getVanMoment())
                && (second.getTotMoment().before(this.getTotMoment()))) {
            return true;
        }
        return second.getVanMoment().before(this.getVanMoment())
                && (this.getTotMoment().before(second.getTotMoment()));
    }
}
