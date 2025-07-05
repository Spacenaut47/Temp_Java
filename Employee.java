import java.util.*;

// Custom Exception 1: NoEmployeesFoundException
class NoEmployeesFoundException extends Exception {
    public NoEmployeesFoundException(String message) {
        super(message);
    }
}

// Custom Exception 2: NegativePercentageException
class NegativePercentageException extends Exception {
    public NegativePercentageException(String message) {
        super(message);
    }
}

// Employee Class with Getters, Setters, Constructor
class Employee {
    private int id;
    private String name;
    private double salary;
    private int noOfBenefits;
    private List<String> benefits;

    public Employee(int id, String name, double salary, int noOfBenefits, List<String> benefits) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.noOfBenefits = noOfBenefits;
        this.benefits = benefits;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getNoOfBenefits() {
        return noOfBenefits;
    }

    public List<String> getBenefits() {
        return benefits;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setNoOfBenefits(int noOfBenefits) {
        this.noOfBenefits = noOfBenefits;
    }

    public void setBenefits(List<String> benefits) {
        this.benefits = benefits;
    }
}

// Service class with required methods
class EmployeeService {

    // Task 1: Fetch list of employees with a specific benefit
    public List<Employee> fetchEmployeesWithBenefit(List<Employee> empList, String benefit)
            throws NoEmployeesFoundException {
        List<Employee> result = new ArrayList<>();
        for (Employee e : empList) {
            for (String b : e.getBenefits()) {
                if (b.equalsIgnoreCase(benefit)) {
                    result.add(e);
                    break;
                }
            }
        }
        if (result.isEmpty()) {
            throw new NoEmployeesFoundException("No Employees Found");
        }
        return result;
    }

    // Task 2: Update salaries by percentage
    public List<Double> updateSalariesByPercentage(List<Employee> empList, int percentage)
            throws NegativePercentageException {
        if (percentage < 0) {
            throw new NegativePercentageException("Percentage cannot be negative");
        }

        List<Double> updatedSalaries = new ArrayList<>();
        for (Employee e : empList) {
            for (int i = 0; i < e.getBenefits().size(); i++) {
                double updated = e.getSalary() + (e.getSalary() * percentage / 100.0);
                updatedSalaries.add(updated);
            }
        }
        return updatedSalaries;
    }
}

// Main Tester Class
public class EmployeeTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        int n = Integer.parseInt(sc.nextLine()); // number of employees
        for (int i = 0; i < n; i++) {
            int id = Integer.parseInt(sc.nextLine());
            String name = sc.nextLine();
            double salary = Double.parseDouble(sc.nextLine());
            int noOfBenefits = Integer.parseInt(sc.nextLine());
            List<String> benefits = new ArrayList<>();
            for (int j = 0; j < noOfBenefits; j++) {
                benefits.add(sc.nextLine());
            }
            employees.add(new Employee(id, name, salary, noOfBenefits, benefits));
        }

        String searchBenefit = sc.nextLine();
        int percentage = Integer.parseInt(sc.nextLine());

        EmployeeService service = new EmployeeService();

        try {
            List<Employee> filtered = service.fetchEmployeesWithBenefit(employees, searchBenefit);
            for (Employee e : filtered) {
                System.out.println(e.getId());
                System.out.println(e.getName());
                System.out.println(e.getSalary());
                for (String b : e.getBenefits()) {
                    System.out.println(b);
                }
            }

            List<Double> updatedSalaries = service.updateSalariesByPercentage(filtered, percentage);
            for (Double s : updatedSalaries) {
                System.out.println(s);
            }

        } catch (NoEmployeesFoundException | NegativePercentageException e) {
            System.out.println(e.getMessage());
        }
    }
}
