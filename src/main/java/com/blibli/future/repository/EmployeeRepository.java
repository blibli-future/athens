
package com.blibli.future.repository;

import com.blibli.future.model.Employee;
import com.blibli.future.vo.EmployeeResponseVo;
import com.blibli.future.vo.ReportResponseVo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findOneByNik (String nik);
    Employee findOneByFullName (String fullName);
    List<Employee> findByNameOfDept(String nameOfDept);

    @Query(value = "select new com.blibli.future.vo.ReportResponseVo(nik, fullName, nameOfDept) from Employee where nameOfDept = (?1)")
    List<ReportResponseVo> initReport(String dept);
    
    @Query(value = "select new com.blibli.future.vo.EmployeeResponseVo(e.nik, e.fullName, e.gender, e.position, e.organizationalUnitText, e.maritalStatus, e.religion, e.nameOfDept, e.chiefNik, (select fullName from Employee where nik = e.chiefNik), e.startWorkingDate) from Employee e")
    List<EmployeeResponseVo> findAllEmployee();
}
