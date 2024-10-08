import java.util.ArrayList;

interface FullTimeEmployeeSalaryChange{
    void changeSalary(double newsalary);
}

interface PartTimeEmployeeSalaryChange{
    void changeSalary(double newCostPerHour);
}
abstract class Employee{
    private int id;
    private  String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public  String getName(){
        return name;
    }
    abstract double calculateSalary();

    @Override
    public String toString(){
        return "Employee[id= "+id+",name= "+name+" ,salary= "+calculateSalary()+"]" ;
    }
}

class FullTimeEmployee extends Employee implements FullTimeEmployeeSalaryChange{
    private double monthlysalary;
    public FullTimeEmployee(int id, String name,double monthlysalary) {
        super(id, name);
        this.monthlysalary = monthlysalary;
    }

    @Override
    double calculateSalary(){
        return monthlysalary;
    }

    @Override
     public void changeSalary(double newsalary){
        monthlysalary = newsalary;
        System.out.println("Successfully change the salary to Rs "+monthlysalary);
    }
}

class PartTimeEmployee extends Employee implements PartTimeEmployeeSalaryChange{
    private int hoursWorked;
    private double costPerHour;
    public PartTimeEmployee(int id,String name,int hoursWorked,double costPerHour){
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.costPerHour = costPerHour;
    }
    @Override
    double calculateSalary(){
        return hoursWorked*costPerHour;
    }
    @Override
    public void changeSalary(double newCostPerHour){
        costPerHour = newCostPerHour;
        System.out.println("Successfully change the salary.");
    }
}

class PayRoll{
    private ArrayList<Employee> list ;

    public PayRoll() {
        list = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        boolean isExist = false;
        for (Employee existingEmployee : list){
            if(existingEmployee.getName()==employee.getName() || existingEmployee.getId()==employee.getId()) {
                isExist = true;
                break;
            }
        }
        if(isExist){
            System.out.println("Employee already exists!.");
        }
        else{
            list.add(employee);
        }

    }
    public void removeEmployee(int id){
        Employee employeeToRemove = null;
        for(Employee employee : list){
            if(employee.getId()==id){
                employeeToRemove = employee;
                break;
            }
        }
        if(employeeToRemove!=null){
            list.remove(employeeToRemove);
        }
        else {
            System.out.println("Employee with id: "+id+" does not exist.");
        }
    }
    public void displayEmployees(){
        for(Employee employee:list){
            System.out.println(employee);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayRoll payRoll = new PayRoll();

        FullTimeEmployee emp1 = new FullTimeEmployee(1, "Srikanth", 70000.0);
        PartTimeEmployee emp2 = new PartTimeEmployee(2, "Shashank", 8, 400.0);

        payRoll.addEmployee(emp1);
        payRoll.addEmployee(emp2);

        payRoll.displayEmployees();
        System.out.println();
        emp1.changeSalary(50000.0);
        emp2.changeSalary(500.0);
        payRoll.removeEmployee(2);
        payRoll.displayEmployees();
    }
}
