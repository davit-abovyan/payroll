package am.vector.model;

import java.time.LocalDate;

public class EmployeeModel {
    private String ssn;
    private String fullName;
    private String email;
    private LocalDate birthDay = LocalDate.parse("1900-01-01");
    private String departmentCode;
    private LocalDate hireDate;
    private LocalDate terminationDate = LocalDate.parse("1900-01-01");

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public EmployeeModel() {
    }

    public EmployeeModel(String ssn, String fullName, String departmentCode) {

        this.ssn = ssn;
        this.fullName = fullName;
        this.departmentCode = departmentCode;
    }

    public EmployeeModel(String ssn, String fullName, String email, LocalDate birthDay,
                         String departmentCode, LocalDate hireDate) {
        this.ssn = ssn;
        this.fullName = fullName;
        this.email = email;
        this.birthDay = birthDay;
        this.departmentCode = departmentCode;
        this.hireDate = hireDate;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
