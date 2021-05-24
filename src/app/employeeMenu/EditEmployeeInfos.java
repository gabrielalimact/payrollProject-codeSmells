package app.employeeMenu;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import app.*;
import model.employees.Commissioned;
import model.employees.Employee;
import model.employees.Hourly;
import model.employees.Salaried;
import model.employees.Syndicate;
import model.payments.PayMethods;

public class EditEmployeeInfos {
    public static void editEmployee(Scanner input, List<Employee> employeeList){
	    int op = 12;
	    Employee e = employeeList.get(SystemInputs.getIndiceDaLista(input, employeeList));
	
	    while(op!=0){
	        System.out.println("\nPlease, choose a option to change: ");
	        System.out.println("1 - Name");
	        System.out.println("2 - Address");
	        System.out.println("3 - Type");
	        System.out.println("4 - Payment Method");
	        System.out.println("5 - Syndicate Status");
	        System.out.println("6 - Syndicate ID");
	        System.out.println("7 - Syndicate Fee");
	        System.out.println("0 - Close menu.\n");
	        System.out.print("  -> ");
	
	
	        op = SystemInputs.readInt(input, "");
	
	
	        switch(op){
	        case 1:
	            editName(input, e);
	            break;

	        case 2:
	            editAddress(input, e);
	            break;

	        case 3:
	            System.out.println("Select the new type of employee: ");
	            System.out.println("1 - Salaried\n2 - Commissioned\n3 - Hourly\nPress any other key to back to menu");
	            editType(input, e, SystemInputs.readInt(input, ""));
	            break;

	        case 4:
	            int a = 1;
	            System.out.println("What's the payment method?");
	            for(PayMethods method : PayMethods.values()){
	                System.out.println(a + " - " + method.getMethod());
	                a++;
	            }
                editPaymentMethod(input, e, input.nextInt());
	            break;

	        case 5: 
	            editSyndicateInfo(input, e);
	            break;

	        case 6:
	            if(e.getSyndicate()!=null){
	                e.getSyndicate().setIdEmployee(UUID.randomUUID());
	                System.out.println(e.toString());                    
	            }else{
	                System.out.println("The employee isn't a syndicalist.");
	            }
	            break;

	        case 7:
	            if(e.getSyndicate()!=null){
	                double syndFees = SystemInputs.readDouble(input, "Enter the new value: ");
	                e.getSyndicate().setFees(syndFees);
	                System.out.println(e.toString());                  
	            }else{
	                System.out.println("The employee isn't a syndicalist.");
	            }
	            
	        }
	    }
	}    
    
    public static void editName(Scanner input, Employee employee){
        String name = SystemInputs.readString(input, "Employee's Name: ");
        employee.setName(name);
    }

    public static void editAddress(Scanner input, Employee employee){
        String address = SystemInputs.readString(input, "Employee's Address: '");
	    employee.setAddress(address);
    }

    public static void editType(Scanner input, Employee employee, int i){
        switch(i){
            case 1:
                if(!(employee instanceof Salaried)){
                    double salary = SystemInputs.readDouble(input, "Salary Value: ");
                    employee = new Salaried(salary);
                }else{
                    System.out.println("The employee's already salaried.");
                }
                break;
        
            case 2:
                if(!(employee instanceof Commissioned)){ 
                    double salaryC = SystemInputs.readDouble(input, "Salary Value: ");
                    double commissionPay = SystemInputs.readDouble(input, "Percentage of Commission: ");
                    employee = new Commissioned(salaryC, commissionPay);
                }else{
                    System.out.println("The employee's already commissioned.");
                }
                break;

            case 3:
                if(!(employee instanceof Hourly)){
                    double hourPay = SystemInputs.readDouble(input, "Value for Work Hour: ");
                    employee = new Hourly(hourPay);
                }else{
                    System.out.println("The employee's already hourly.");
                }
                break;

            default:
                break;
        }

    }
	
    public static void editPaymentMethod(Scanner input, Employee employee, int i){
        switch(i){
            case 1:
                employee.getPayInfo().setPayMethod(PayMethods.values()[i-1]);
                System.out.println("Method was changed.\n" + employee.toString());
                break;
            case 2:
                employee.getPayInfo().setPayMethod(PayMethods.values()[i-1]);
                System.out.println("Method was changed.\n" + employee.toString());
                break;
            case 3:
                employee.getPayInfo().setPayMethod(PayMethods.values()[i-1]);
                System.out.println("Method was changed.\n" + employee.toString());
                break;
            default:
                System.out.println("Invalid option.\n");
                break;
        }
    }
    
    public static void editSyndicateInfo(Scanner input, Employee employee){
        if(employee.getSyndicate()==null || !employee.getSyndicate().getActive()){
            System.out.println("Employee isn't a syndicalist. Want to register?\n1 - Yes\n2 - No\n");
            int o = SystemInputs.readInt(input, "");
            if(o == 1){                        
                double syndFees = SystemInputs.readDouble(input, "Price for syndicate membership: ");
                Syndicate syndicateMember = new Syndicate(employee.getId(), syndFees, true);
                employee.setSyndicate(syndicateMember);
            }else{
                return;
            }
        }else{            
            System.out.println("Employee is a syndicalist. Do you want to disable?\n1 - Yes\n2 - No");
            int o = SystemInputs.readInt(input, "");
            if(o == 1){                        
                employee.getSyndicate().setActive(false);
            }else{
                return;
            }

        }
        
    }
    
}
