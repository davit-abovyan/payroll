package am.vector.model;

import am.vector.constants.LeaveType;

import java.time.LocalDate;

public class AbsenceModel {
    private long id;
    private String ssn;
    private String period;
    private LeaveType leaveType;
    private int amount;
    private LocalDate startDate;
    private LocalDate endDate;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public AbsenceModel() {
    }

    public AbsenceModel(LeaveType leaveType, String ssn, String period, LocalDate startDate, LocalDate endDate) {
        this.leaveType = leaveType;
        this.ssn = ssn;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AbsenceModel(LeaveType leaveType, String ssn, String period, int amount, LocalDate startDate, LocalDate endDate) {
        this.leaveType = leaveType;
        this.ssn = ssn;
        this.period = period;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
