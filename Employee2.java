import java.util.Date;

public class Employee2 {

    private int employeeIdNumber;
    private String firstName;
    private String lastName;
    private int annualSalary;
    private Date startDate;

    public Employee2(int id, String firstName, String lastName, int salary, Date startDate) {
        this.employeeIdNumber = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = salary;
        this.startDate = startDate;
    }

    public int getId() {
        return employeeIdNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getSalary() {
        return annualSalary;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getStartDate() {
        return startDate;
    }

}