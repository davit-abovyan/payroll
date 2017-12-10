package am.vector.model;

public class RoleModel {
    private long id;
    private String positionName;
    private String description;
    private int salaryRangeTop;
    private int salaryRangeBottom;

    public RoleModel() {
    }

    public RoleModel(String positionName, String description, int salaryRangeTop, int salaryRangeBottom) {
        this.positionName = positionName;
        this.description = description;
        this.salaryRangeTop = salaryRangeTop;
        this.salaryRangeBottom = salaryRangeBottom;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalaryRangeTop() {
        return salaryRangeTop;
    }

    public void setSalaryRangeTop(int salaryRangeTop) {
        this.salaryRangeTop = salaryRangeTop;
    }

    public int getSalaryRangeBottom() {
        return salaryRangeBottom;
    }

    public void setSalaryRangeBottom(int salaryRangeBottom) {
        this.salaryRangeBottom = salaryRangeBottom;
    }
}
