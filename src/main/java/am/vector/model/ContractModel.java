package am.vector.model;

import am.vector.util.SafeSet;

import java.time.LocalDate;

public class ContractModel {
    private long id;
    private String ssn;
    private long roleId;
    private LocalDate startDate;
    private LocalDate endDate = LocalDate.parse("1900-01-01");
    private int salary;

    public ContractModel() {
    }

    public ContractModel(String ssn, long roleId, LocalDate startDate, int salary) {
        this.ssn = ssn;
        this.roleId = roleId;
        this.startDate = startDate;
        this.salary = salary;
    }

    public ContractModel(String ssn, long roleId, LocalDate startDate, LocalDate endDate, int salary) {
        this.ssn = ssn;
        this.roleId = roleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
