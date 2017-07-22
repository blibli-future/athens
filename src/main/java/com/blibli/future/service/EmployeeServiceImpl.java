package com.blibli.future.service;
import com.blibli.future.enums.Gender;
import com.blibli.future.enums.LateCondition;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.*;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.vo.SubReportVo;
import com.blibli.future.vo.SummariesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
    private final EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
    private final AttendanceRepository attendanceRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository, EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository, AttendanceRepository attendanceRepository){
        this.employeeRepository = employeeRepository;
        this.employeeYearlyLeaveRepository = employeeYearlyLeaveRepository;
        this.employeeSubstitutionLeaveRightRepository = employeeSubstitutionLeaveRightRepository;
        this.attendanceRepository = attendanceRepository;
    }



    public boolean saveEmployee (String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
                                 MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
                                 String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status){
        if(isEmployeeExist(nik)){
            return false;
        }else{
            Employee emp = new Employee(nik, fullName,gender,position,level,
                    organizationalUnitText,maritalStatus,religion,nameOfDept,chiefNik,
                    chiefName,chiefPosition,chiefPositionText,
                    startWorkingDate,endWorkingDate,status);
            employeeRepository.save(emp);
            return true;
        }


    }

    public boolean isEmployeeExist(String nik){
        if (employeeRepository.findOneByNik(nik) !=null){
            return true;
        }
        return  false;
    }


    public boolean updateEmployee (String nik, String fullName, Gender gender, String position, String level, String organizationalUnitText,
            MaritalStatus maritalStatus, Religion religion, String nameOfDept, String chiefNik, String chiefName, String chiefPosition,
            String chiefPositionText, LocalDate startWorkingDate, LocalDate endWorkingDate, Boolean status){
    		Employee oldEmployee = employeeRepository.findOneByNik(nik);
    	if(oldEmployee!=null){
    	   oldEmployee.setChiefName(chiefName);
    	   oldEmployee.setChiefNik(chiefNik);
    	   oldEmployee.setChiefPosition(chiefPosition);
    	   oldEmployee.setChiefPositionText(chiefPositionText);
    	   oldEmployee.setEndWorkingDate(endWorkingDate);
    	   oldEmployee.setFullName(fullName);
    	   oldEmployee.setGender(gender);
    	   oldEmployee.setLevel(level);
    	   oldEmployee.setMaritalStatus(maritalStatus);
    	   oldEmployee.setNameOfDept(nameOfDept);
    	   oldEmployee.setOrganizationalUnitText(organizationalUnitText);
    	   oldEmployee.setReligion(religion);
    	   oldEmployee.setStartWorkingDate(startWorkingDate);
    	   oldEmployee.setStatus(status);
           employeeRepository.save(oldEmployee);
           return true;
       }
       return false;
    }


    public List<Employee> getEmployeesByDept(String nameOfDept){
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeRepository.findByNameOfDept(nameOfDept);
        if(listEmployee!=null){
            return listEmployee;
        }
        return null;

    }

    @Override
    public SummariesVo generateSummaries(String nik) { //Todo: apply error handling by throwing exception where nik is not found
        Object[] yearlyLeaveCountObject = this.employeeYearlyLeaveRepository.sumEmployeeYearlyLeaveByNikDateBetween(
                nik,
                LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1),
                LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31)
        );

        Object[] substitutionLeaveRightCountObject = this.employeeSubstitutionLeaveRightRepository.sumEmployeeSubstitutionLeaveRightByNikDateBetween(
                nik,
                LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1),
                LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31)
        );

        Object[] employeeLeaveCountObject = this.attendanceRepository.countEmployeeLateConditionByNikDateBetween(
                LateCondition.LATE.ordinal(),
                nik,
                LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1),
                LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31)
        );

        SubReportVo yearlyLeaveCount = new SubReportVo(yearlyLeaveCountObject);
        SubReportVo substitutionLeaveRightCount = new SubReportVo(substitutionLeaveRightCountObject);
        SubReportVo employeeLeaveCount = new SubReportVo(employeeLeaveCountObject);


        //TODO Ariel: create and fully populate SummariesVo data
        //TODO Bastian: create query for calculating max yearlyLeave, max substitutionLeaveRight and employeeLeaveCount
        return new SummariesVo();
    }

}
