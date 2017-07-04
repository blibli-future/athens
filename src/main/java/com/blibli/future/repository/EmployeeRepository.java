
package com.blibli.future.repository;

import com.blibli.future.model.Employee;
import com.blibli.future.vo.ReportResponseVo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    public Employee findOneByNik (String nik);
    public Employee findOneByFullName (String fullName);
    public List <Employee> findByNameOfDept(String nameOfDept);
    @Query(value = "select new com.blibli.future.vo.ReportVo(nik, fullName, nameOfDept, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0) from Employee where nameOfDept = (?1)")
    public List<ReportResponseVo> initReport(String dept);
}
