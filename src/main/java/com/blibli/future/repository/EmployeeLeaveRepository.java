package com.blibli.future.repository;

import com.blibli.future.enums.Status;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.vo.PermissionResponseVo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long>{
	List<EmployeeLeave> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart, LocalDate dateEnd);
	EmployeeLeave findOneById(String id);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(el.id, el.employee.nik, e.fullName, el.startDate, el.endDate, el.status, 'leave', el.processedBy, el.reason) "
			+ "from EmployeeLeave el join Employee e on el.employee.nik = e.nik where e.chiefNik = (?1) and el.status = (?2)")
	List<PermissionResponseVo> getEmployeeLeaveByChiefNikStatus(String chiefNik, Status status);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(el.id, el.employee.nik, e.fullName, el.startDate, el.endDate, el.status, 'leave', el.processedBy, el.reason) "
			+ "from EmployeeLeave el join Employee e on el.employee.nik = e.nik where e.nik = (?1) and el.status = (?2)")
	List<PermissionResponseVo> getEmployeeLeaveByNikStatus(String nik, Status status);
}