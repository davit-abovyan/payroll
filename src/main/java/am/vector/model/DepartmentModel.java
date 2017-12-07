package am.vector.model;

public class DepartmentModel {
    public String getCode() {
        return code;
    }

    public DepartmentModel() {
    }

    public DepartmentModel(String code, String department_name) {

        this.code = code;
        this.department_name = department_name;
    }

    public void setCode(String code) {
        this.code = code;

    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getCost_center() {
        return cost_center;
    }

    public void setCost_center(String cost_center) {
        this.cost_center = cost_center;
    }

    public DepartmentModel(String code, String department_name, String cost_center) {

        this.code = code;
        this.department_name = department_name;
        this.cost_center = cost_center;
    }

    private String code;
    private String department_name;
    private String cost_center;
}
