package am.vector.model;

public class PaySlipModel {
    private String period;
    private String ssn;
    private int wage;
    private int bonus;
    private int it;
    private int ssp;
    private int army;
    private long absenceID;

    public PaySlipModel() {
    }

    public PaySlipModel(String period, String ssn, int wage, int bonus, int it, int ssp, int army, long absenceID) {

        this.period = period;
        this.ssn = ssn;
        this.wage = wage;
        this.bonus = bonus;
        this.it = it;
        this.ssp = ssp;
        this.army = army;
        this.absenceID = absenceID;
    }

    public String getPeriod() {

        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getIT() {
        return it;
    }

    public void setIT(int it) {
        this.it = it;
    }

    public int getSSP() {
        return ssp;
    }

    public void setSSP(int ssp) {
        this.ssp = ssp;
    }

    public int getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
    }

    public long getAbsenceID() {
        return absenceID;
    }

    public void setAbsenceID(long absenceID) {
        this.absenceID = absenceID;
    }
}
