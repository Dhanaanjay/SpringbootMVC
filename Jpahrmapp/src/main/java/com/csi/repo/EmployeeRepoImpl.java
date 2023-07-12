package com.csi.repo;

import com.csi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepoImpl extends JpaRepository<Employee,Integer> {

    public List<Employee> findByEmpName(String empName);

    public Employee findByEmpEmailIdAndempPassword(String empEmailId,String empPassword);
}
