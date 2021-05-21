package app;

import model.employees.*;

import java.util.*;

public class EmployeeType {
    public static List<Employee> isHourly(Scanner input, List<Employee> employeeList){
        List<Employee> hourlyList = new ArrayList<Employee>();;
        if(!employeeList.isEmpty()){
            for(Employee employee : employeeList){
                if(employee instanceof Hourly){
                    hourlyList.add(employee);
                }
            }
        }
        return hourlyList;
    }
    public static List<Employee> isSalaried(Scanner input, List<Employee> employeeList){
        List<Employee> salariedList = new ArrayList<Employee>();;
        if(!employeeList.isEmpty()){
            for(Employee employee : employeeList){
                if(employee instanceof Hourly){
                    salariedList.add(employee);
                }
            }
        }
        return salariedList;
    }

    public static List<Employee> isCommissioned(Scanner input, List<Employee> employeeList){
        List<Employee> commissionedList = new ArrayList<Employee>();;
        if(!employeeList.isEmpty()){
            for(Employee employee : employeeList){
                if(employee instanceof Hourly){
                    commissionedList.add(employee);
                }
            }
        }
        return commissionedList;
    }

    public static List<Employee> isSyndicateMember(Scanner input, List<Employee> employeeList){
        List<Employee> syndicateList = new ArrayList<Employee>();;
        if(!employeeList.isEmpty()){
            for(Employee employee : employeeList){
                if(employee instanceof Hourly){
                    syndicateList.add(employee);
                }
            }
        }
        return syndicateList;
    }
}
