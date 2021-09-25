package com.mindex.challenge.data;

//TASK 1
public class ReportingStructure {
    private Employee employee;
    private int numberofReports;

    public ReportingStructure(){
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee e){
        this.employee = e;
    }

    public int getNumberofReports(){
        return numberofReports;
    }

    public void setNumberofReports(int n){
        this.numberofReports = n;
    }

}
