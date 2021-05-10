package model.payments;

import java.util.*;


public class PaymentSchedule {
    
    private List<String> schedule;

    public PaymentSchedule(){
        this.schedule = new ArrayList<String>();

        this.schedule.add("monthly");
        this.schedule.add("biweekly");
        this.schedule.add("weekly");
    }


    public List<String> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    public void addSchedule(String schedule){
        this.schedule.add(schedule);
    }

}
