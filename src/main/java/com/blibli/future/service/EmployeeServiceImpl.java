package com.blibli.future.service;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.LateCondition;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.AttendanceRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.EmployeeSubstitutionLeaveRightRepository;
import com.blibli.future.repository.EmployeeYearlyLeaveRepository;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.vo.EmployeeVo;
import com.blibli.future.vo.SubReportVo;
import com.blibli.future.vo.SummariesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
    private final EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
    private final AttendanceRepository attendanceRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository, EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository, AttendanceRepository attendanceRepository){
        this.employeeRepository = employeeRepository;
        this.employeeYearlyLeaveRepository = employeeYearlyLeaveRepository;
        this.employeeSubstitutionLeaveRightRepository = employeeSubstitutionLeaveRightRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public Employee saveEmployee (EmployeeVo employeeVo){
        if(isEmployeeExist(employeeVo.getNik())){
            return null;
        }else{
            Employee emp = new Employee(employeeVo.getNik(), employeeVo.getFullName(), Gender.valueOf(employeeVo.getGender()), employeeVo.getPosition(), 
            		employeeVo.getLevel(), employeeVo.getOrganizationalUnitText(), MaritalStatus.valueOf(employeeVo.getMaritalStatus()), Religion.valueOf(employeeVo.getReligion()), employeeVo.getNameOfDept(),
            		employeeVo.getChiefNik(), employeeVo.getChiefName(), employeeVo.getChiefPosition(), employeeVo.getChiefPositionText(),
            		LocalDate.parse(employeeVo.getStartWorkingDate(), formatter), LocalDate.parse(employeeVo.getEndWorkingDate(), formatter), employeeVo.getStatus());
            employeeRepository.save(emp);
            return emp;
        }
    }

    public boolean isEmployeeExist(String nik){
        if (employeeRepository.findOneByNik(nik) !=null){
            return true;
        }
        return  false;
    }


    public Employee updateEmployee (EmployeeVo employeeVo){
    		Employee oldEmployee = employeeRepository.findOneByNik(employeeVo.getNik());
    	if(oldEmployee!=null){
    	   oldEmployee.setChiefName(employeeVo.getChiefName());
    	   oldEmployee.setChiefNik(employeeVo.getChiefNik());
    	   oldEmployee.setChiefPosition(employeeVo.getChiefPosition());
    	   oldEmployee.setChiefPositionText(employeeVo.getChiefPositionText());
    	   oldEmployee.setEndWorkingDate(LocalDate.parse(employeeVo.getEndWorkingDate(), formatter));
    	   oldEmployee.setFullName(employeeVo.getFullName());
    	   oldEmployee.setGender(Gender.valueOf(employeeVo.getGender()));
    	   oldEmployee.setLevel(employeeVo.getLevel());
    	   oldEmployee.setMaritalStatus(MaritalStatus.valueOf(employeeVo.getMaritalStatus()));
    	   oldEmployee.setNameOfDept(employeeVo.getNameOfDept());
    	   oldEmployee.setOrganizationalUnitText(employeeVo.getOrganizationalUnitText());
    	   oldEmployee.setReligion(Religion.valueOf(employeeVo.getReligion()));
    	   oldEmployee.setStartWorkingDate(LocalDate.parse(employeeVo.getStartWorkingDate(), formatter));
    	   oldEmployee.setStatus(employeeVo.getStatus());
           employeeRepository.save(oldEmployee);
           return oldEmployee;
       }
       return null;
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
	public List<Employee> getAllEmployees() {
		List<Employee> listEmployee = new ArrayList<>();
        listEmployee = employeeRepository.findAll();
        if(listEmployee!=null){
            return listEmployee;
        }
        return null;
	}

    @Override
    public SummariesVo generateSummaries(String nik) throws IdNotFoundException {
        Employee employee = employeeRepository.findOneByNik(nik);

        if(employee == null) {
            throw new IdNotFoundException("nik: " + nik + " is not found in the database");
        }

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

        Object[] employeeLateCountObject = this.attendanceRepository.countEmployeeLateConditionByNikDateBetween(
                LateCondition.LATE.ordinal(),
                nik,
                LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1),
                LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31)
        );

        SubReportVo yearlyLeaveCount = new SubReportVo(yearlyLeaveCountObject);
        SubReportVo substitutionLeaveRightCount = new SubReportVo(substitutionLeaveRightCountObject);
        SubReportVo employeeLateCount = new SubReportVo(employeeLateCountObject);

        //Todo: generate Max Count of Each Field
        return new SummariesVo(
                nik,
                yearlyLeaveCount.getNumberResult(),
                substitutionLeaveRightCount.getNumberResult(),
                employeeLateCount.getNumberResult(),
                0, //by department
                0, //"max substitutionLeaveRight" START with EmployeeSubstitutionLeaveRightRepository sumEmployeeYearlyLeaveByNikDateBetween END with SubstitutionLeaveRightRepository countSubstitutionLeaveRightAvaiableByNik
                5);
    }
}
