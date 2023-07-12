package com.csi.service;


import com.csi.model.Employee;
import com.csi.repo.EmployeeRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepoImpl employeeRepoImpl;

    public Employee signUp(Employee employee){
        return employeeRepoImpl.save(employee);
    }

    public Boolean signIn(String empEmailId,String empPassword){

        Employee employee=employeeRepoImpl.findByEmpEmailIdAndempPassword(empEmailId, empPassword);
        boolean flag=false;

       if (employee!=null&&employee.getEmpEmailId().equals(empEmailId)
       &&employee.getEmpPassword().equals(empPassword)){
           flag=true;
       }

        return flag;
    }
    public List<Employee> getAllData(){
        return employeeRepoImpl.findAll();
    }

    public List<Employee> saveAllData(List<Employee> employeeList){
        return employeeRepoImpl.saveAll(employeeList);

    }
    public Optional<Employee> getDataById(int empId){
        return employeeRepoImpl.findById(empId);
    }
    public List<Employee> getDataByName(String empName){
        return employeeRepoImpl.findByEmpName(empName);
    }
    public Employee getDataByContactNumber(long empContactNumber){
        return getAllData().stream().filter(employee->employee.getEmpContactNumber()==empContactNumber).collect(Collectors.toList()).get(0);
    }
    public Employee getDataByEmailId(String empEmailId){
        return getAllData().stream().filter(employee -> employee.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()).get(0);

    }
    public List<Employee> getDataByDOB(String empDOB){


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : getAllData()) {

            String empDOBDB = simpleDateFormat.format(employee.getEmpDOB());

            if (empDOBDB.equals(empDOB)) {
                employeeList.add(employee);
            }


        }

        return employeeList;

    }
    public List<Employee> getDataByAnyInput(String input){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : getAllData()) {

            String empDOBDB = simpleDateFormat.format(employee.getEmpDOB());

            if (empDOBDB.equals(input)
                    || String.valueOf(employee.getEmpId()).equals(input)
                    || employee.getEmpName().equals(input)
                    || employee.getEmpEmailId().equals(input)
                    || String.valueOf(employee.getEmpContactNumber()).equals(input)) {
                employeeList.add(employee);
            }


        }

        return employeeList;


    }
    public Employee sortById(){
        return getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()).get(0);
    }

    public List<Employee> sortByName(){
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList());
    }

}







