package model.employees;

import model.payments.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Commissioned extends Employee{
    private double salary;
    private double percCommission;
    private List<SalesReport> salesReport;

    
    public Commissioned(String name, String address, UUID id, Syndicate syndicate, Payments paymentInfo, double salary, double percCommission) {
        super(name, address, id, syndicate, paymentInfo);
        this.salary = salary;
        this.percCommission = percCommission;
        this.salesReport = new ArrayList<SalesReport>();
    }

    public Commissioned(double salary, double percCommission) {
        this.salary = salary;
        this.percCommission = percCommission;
    }


    @Override
    public double calculatePayment(LocalDate date) {
        double total = this.getSalary()/2;

        List<SalesReport> salesReport;
        List<Paycheck> paycheck;
        Predicate<SalesReport> verifying;
        LocalDate lastDate;
        paycheck = getPayInfo().getPaycheck();
        int i = paycheck.size()-1;

        if(!paycheck.isEmpty() && paycheck != null){
            lastDate = paycheck.get(i).getDate();
            verifying = s -> s.getDate().isAfter(lastDate) && !s.getDate().isAfter(date);
        } else{
            verifying = s -> !s.getDate().isAfter(date);
        }

        salesReport = this.getSalesReport().stream().filter(verifying).collect(Collectors.toList());


        for( SalesReport sales : salesReport ){
            double totalC = sales.getValue() * (this.getPercCommission() / 100.0);

            total += totalC;
        }
        
        return total;
        
    }

    
    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public double getPercCommission() {
        return this.percCommission;
    }

    public void setPercCommission(double percCommission) {
        this.percCommission = percCommission;
    }

    public List<SalesReport> getSalesReport() {
        return this.salesReport;
    }

    public void setSalesReport(List<SalesReport> salesReport) {
        this.salesReport = salesReport;
    }


    @Override
    public String toString() {
        return "\n-> Type: 'Commissioned'\n{" +
            " Salary = $" + getSalary() +
            ", Percentage of Commission = " + getPercCommission() + "%" +
            ", Sales Report = '" + getSalesReport() + "'" +
            "}";
    }



}
