package core;

import java.util.*;
import java.util.stream.Collectors;

public class MaxSalary {

    public static void main(String[] args) {
        List<Employee1> empList = Arrays.asList(
                new Employee1(100,001,50000),
                new Employee1(101,001,80000),
                new Employee1(102,002,50000),
                new Employee1(103,002,90000),
                new Employee1(103,002,110000),
                new Employee1(103,002,120000)
        );

        Map<Integer, Optional<Employee1>> maxSalary = empList.stream()
                        .collect(Collectors.groupingBy(Employee1::getDeptId,
                                        Collectors.maxBy(Comparator.comparing(Employee1::getSalary)
                        )));

        Set<Map.Entry<Integer, Optional<Employee1>>> entrySet = maxSalary.entrySet();
        for (Map.Entry entry : entrySet){
            Employee1 e = (Employee1) ((Optional)entry.getValue()).get();
            System.out.println("Salary:-->"+e.getSalary());
        }
    }
}

class Employee1 {
    private int empId;
    private int deptId;

    public Employee1(int empId, int deptId, double salary) {
        this.empId = empId;
        this.deptId = deptId;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private double salary;
}
