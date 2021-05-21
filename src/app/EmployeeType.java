package app;

import model.employees.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeType {
    public static List<Employee> isHourly(Scanner input, List<Employee> employeeList){
        List<Employee> hourlyList = new ArrayList<Employee>();
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
        List<Employee> salariedList = new ArrayList<Employee>();
        if(!employeeList.isEmpty()){
            for(Employee employee : employeeList){
                if(employee instanceof Salaried){
                    salariedList.add(employee);
                }
            }
        }
        return salariedList;
    }

    public static List<Employee> isCommissioned(Scanner input, List<Employee> employeeList){
        List<Employee> commissionedList = new ArrayList<Employee>();
        if(!employeeList.isEmpty()){
            for(Employee employee : employeeList){
                if(employee instanceof Commissioned){
                    commissionedList.add(employee);
                }
            }
        }
        if(commissionedList.isEmpty()){
            System.out.println("\nThere are no commissioned employees on the list.\n");
        }
        return commissionedList;
    }

    public static List<Employee> isSyndicateMember(Scanner input, List<Employee> employeeList){
        Predicate<Employee> isSynd = syndM -> syndM.getSyndicate() != null && syndM.getSyndicate().getActive();
        List<Employee> syndicateList = employeeList.stream().filter(isSynd).collect(Collectors.toList());
        
        return syndicateList;
    }
}
