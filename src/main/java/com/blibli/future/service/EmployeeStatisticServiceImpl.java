package com.blibli.future.service;

import com.blibli.future.enums.LateCondition;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.*;
import com.blibli.future.service.api.EmployeeStatisticService;
import com.blibli.future.vo.SubReportVo;
import com.blibli.future.vo.SummariesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
public class EmployeeStatisticServiceImpl implements EmployeeStatisticService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
    private final EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
    private final AttendanceRepository attendanceRepository;
    private final SubstitutionLeaveRightRepository substitutionLeaveRightRepository;

    @Autowired
    public EmployeeStatisticServiceImpl(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository, EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository, EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository, SubstitutionLeaveRightRepository substitutionLeaveRightRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.employeeSubstitutionLeaveRightRepository = employeeSubstitutionLeaveRightRepository;
        this.employeeYearlyLeaveRepository = employeeYearlyLeaveRepository;
        this.substitutionLeaveRightRepository = substitutionLeaveRightRepository;
    }

    @Override
    public SummariesVo generateSummaries(String nik) throws IdNotFoundException {
        Employee employee = employeeRepository.findOneByNik(nik);

        if(employee == null) {
            throw new IdNotFoundException("nik: " + nik + " is not found in the database");
        }

        SummariesVo summaries = new SummariesVo();

        summaries.setNik(nik);
        summaries.setYearlyLeaveUsed(countYearlyLeaveUsed(nik));
        summaries.setSubstitutionLeaveRightUsed(countSubstitutionLeaveRightUsed(nik));
        summaries.setLateUsed(countLate(nik));
        summaries.setMaxYearlyLeave(countMaxYearlyLeaveUsed(employee));
        summaries.setMaxSubstitutionLeaveRight(countMaxSubstitutionLeaveRight(nik));
        summaries.setMaxEmployeeLate(countMaxLate());

        return summaries;
    }

    private long countYearlyLeaveUsed(String nik) {
        long result = 0;
    	Object[] yearlyLeaveCountObject = this.employeeYearlyLeaveRepository.sumEmployeeYearlyLeaveByNikDateBetween(
                nik,
                LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1),
                LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31)
        );

        if(yearlyLeaveCountObject.length!=0){
        	SubReportVo yearlyLeaveCount = new SubReportVo(yearlyLeaveCountObject);
        	result = yearlyLeaveCount.getNumberResult();
        }
        return result;
    }

    private long countSubstitutionLeaveRightUsed(String nik) {
    	long result = 0;
    	Object[] substitutionLeaveRightCountObject =
                this.employeeSubstitutionLeaveRightRepository.sumEmployeeSubstitutionLeaveRightByNikDateBetween(
                nik,
                LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1),
                LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31)
        );
        if(substitutionLeaveRightCountObject.length!=0){
        	SubReportVo substitutionLeaveRightCount = new SubReportVo(substitutionLeaveRightCountObject);
        	result = substitutionLeaveRightCount.getNumberResult();
        }
        return result;
    }

    private long countLate(String nik) {
    	long result = 0;
        Object[] employeeLateCountObject = this.attendanceRepository.countEmployeeLateConditionByNikDateBetween(
                LateCondition.LATE.ordinal(),
                nik,
                LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1),
                LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31)
        );
        if(employeeLateCountObject.length!=0){
        	SubReportVo employeeLateCount = new SubReportVo(employeeLateCountObject);
        	result = employeeLateCount.getNumberResult();
        }
        return result;
    }

    private int countMaxYearlyLeaveUsed(Employee employee) {
        String department = employee.getNameOfDept();
        String level = employee.getLevel();

        if(level.equals("1")) {
            if(department.equals("operation")) {
                return 24;
            }
            return 20;
        } else if(level.equals("2")) {
            if(department.equals("operation")) {
                return 18;
            }

            return 15;
        } else if(level.equals("3")) {
            if(department.equals("operation")) {
                return 15;
            }

            return 13;
        } else if(level.equals("4")) {
            if(department.equals("operation")) {
                return 12;
            }

            return 10;
        }

        return 0;
    }

    private int countMaxSubstitutionLeaveRight(String nik) {
        int result = 0;
    	Object[] maxSubstitutionLeaveRightObject =
                substitutionLeaveRightRepository.countSubstitutionLeaveRightAvailableByNik(nik);
        result = Integer.parseInt(maxSubstitutionLeaveRightObject[0].toString());
        return result;
    }

    private int countMaxLate() {
        return 5;
    }
}

