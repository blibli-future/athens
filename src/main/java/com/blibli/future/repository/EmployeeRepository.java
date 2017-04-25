
package com.blibli.future.repository;

import com.blibli.future.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    public Employee findOneByNik (String nik);
    public Employee findOneByFullName (String fullName);
    public List <Employee> findByNameOfDept(String nameOfDept);
}
