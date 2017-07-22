package com.blibli.future.service.api;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.vo.SummariesVo;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
	boolean saveEmployee (String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
								 MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
								 String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status);
	boolean isEmployeeExist(String nik);
	boolean updateEmployee (String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
            MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
            String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status);
	List<Employee> getEmployeesByDept (String nameOfDept);

    SummariesVo generateSummaries(String nik);
}
