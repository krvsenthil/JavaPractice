package core;

import java.util.Arrays;

public class PassByValue {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setEmpId(1000);
        PassByValue passByValue = new PassByValue();
        passByValue.updateEmployee(employee);
        System.out.println("Emp Id "+employee.getEmpId());

        int sum = 10;
        passByValue.addSum(sum);
        System.out.println("Sum After "+sum);

        int[] arr = {1,2};
        add(arr);
        System.out.println("Array-->"+ Arrays.toString(arr));
    }

    private static void add(int[] arr){
        arr[1]=200;
    }

    public void updateEmployee(Employee employee){
        employee.setEmpId(1001);
    }

    public void addSum(int sum){
        sum += 10;
        System.out.println(sum);
    }
}
