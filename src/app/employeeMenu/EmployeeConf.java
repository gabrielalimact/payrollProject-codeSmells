package app.employeeMenu;

import model.payments.*;
import model.employees.*;
import model.employees.Salaried;
import app.*;


import java.time.*;
import java.util.*;

public class EmployeeConf{

    public static Employee newEmployee(Scanner input, PaymentSchedule schedule){
        Employee employee = null;
        Payments payment = null;
        Syndicate syndicateMember = null;

        int op;
        String optionSchedule = "";
        String name = SystemInputs.readString(input, "Employee's Name: ");
        String address = SystemInputs.readString(input, "Employee's Address: ");

        System.out.println("Select the type of employee: ");
        System.out.println("1 - Salaried\n2 - Commissioned\n3 - Hourly");

        op = input.nextInt();
        System.out.println();
        optionSchedule = schedule.getSchedule().get(op-1);
        switch(op){
            case 1:
            double salary = SystemInputs.readDouble(input, "Salary Value: ");
            // employee = new Salaried("Gabriela", "Maceio", UUID.randomUUID(), syndicateMember, payment, salary);
            employee = new Salaried(name, address, UUID.randomUUID(), syndicateMember, payment, salary);
            break;
            
            case 2:
            double salaryC = SystemInputs.readDouble(input, "Salary Value: ");
            double commissionPay = SystemInputs.readDouble(input, "Percentage of Commission: ");
            // employee = new Commissioned("Leticia", "Arapiraca", UUID.randomUUID(), syndicateMember, payment, salaryC, commissionPay);
            employee = new Commissioned(name, address, UUID.randomUUID(), syndicateMember, payment, salaryC, commissionPay);
            break;
            
            case 3:
            double hourPay = SystemInputs.readDouble(input, "Value for Work Hour: ");
            // employee = new Hourly("Sophia", "Maceio", UUID.randomUUID(), syndicateMember, payment, hourPay);
            employee = new Hourly(name, address, UUID.randomUUID(), syndicateMember, payment, hourPay);
            break;
        }
        
        
        payment = PaymentConf.getPayInfo(input, optionSchedule);
        employee.setPayInfo(payment);

        System.out.println("Is a Syndicate Member?\n1 - Yes\n2 - No");
        int synd = input.nextInt();
        if(synd == 1){
            double syndFees = SystemInputs.readDouble(input, "Price for syndicate membership: ");
            syndicateMember = new Syndicate(employee.getId(), syndFees, true);
            employee.setSyndicate(syndicateMember);
        }else if (synd == 2){
            double syndFees = 0;
            syndicateMember = new Syndicate(employee.getId(),syndFees, false);
        }
        System.out.println("\nEMPLOYEE SUCCESSFULLY REGISTERED!");
        System.out.println(employee.toString() + "\n\n\n");
        return employee;
    }

    

    public static void removeEmployee(Scanner input, List<Employee> employeeList){
        if(!employeeList.isEmpty()){
            employeeList.remove(SystemInputs.getIndiceDaLista(input, employeeList));
            System.out.println("Employee Removed!");
        }else{
            System.out.println("This employee list is empty.");
        }
       
       
    }

    public static void addTimeCard(Scanner input, List<Employee> hourlyList){
        LocalDate date = SystemInputs.readDate(input, "Enter the date: ");
        Hourly employee = (Hourly) hourlyList.get(SystemInputs.getIndiceDaLista(input, hourlyList));

        System.out.println("Please, enter the hours and minutes of IN");
        int hIN = SystemInputs.readInt(input, "Hour of IN: ");
        int mIN = SystemInputs.readInt(input, "Minutes of IN: ");
        LocalTime tIN = LocalTime.of(hIN, mIN);

        System.out.println("Please, enter the hours and minutes of OUT");
        int hOUT = SystemInputs.readInt(input, "Hour of OUT: ");
        int mOUT = SystemInputs.readInt(input, "Minutes of OUT: ");
        LocalTime tOUT = LocalTime.of(hOUT, mOUT);

        TimeCard tc = new TimeCard(date, tIN, tOUT);
        employee.getTimeCard().add(tc);

        System.out.println("Time Card added with success.");
        }
        

    public static void addSaleReport(Scanner input, List<Employee> commissionedList){
        LocalDate date = SystemInputs.readDate(input, "Enter the date: ");
        Commissioned employee = (Commissioned) commissionedList.get(SystemInputs.getIndiceDaLista(input, commissionedList));

        double saleValue = SystemInputs.readDouble(input, "Please, enter the value of sale: ");

        SalesReport sales = new SalesReport(saleValue, date);
        employee.getSalesReport().add(sales);
        System.out.println("The sale report was added successfully");
    }

    
    public static void addServiceFee(Scanner input, List<Employee> syndicateList){
        LocalDate date = SystemInputs.readDate(input, "Enter the date: ");
        Employee employee = syndicateList.get(SystemInputs.getIndiceDaLista(input, syndicateList));
        double feesValue = SystemInputs.readDouble(input, "Please, enter the value of fees: ");

        ServicesFees fees = new ServicesFees(feesValue, date);
        employee.getSyndicate().getServiceFees().add(fees);
        System.out.println("Service Fees added.");
        
    }


}
