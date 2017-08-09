package com.blibli.future.service;

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.LateCondition;
import com.blibli.future.repository.*;
import com.blibli.future.service.api.ReportService;
import com.blibli.future.vo.ReportResponseVo;
import com.blibli.future.vo.SubReportVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService{
	private static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);
	private EmployeeRepository employeeRepository;
	private AttendanceRepository attendanceRepository;
	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	private EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
	private EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
	
	@Autowired 
	public ReportServiceImpl(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository, EmployeeAbsencePermitRepository employeeAbsencePermitRepository, 
			EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository, EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository){
		this.employeeRepository = employeeRepository;
		this.attendanceRepository = attendanceRepository;
		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
		this.employeeYearlyLeaveRepository = employeeYearlyLeaveRepository;
		this.employeeSubstitutionLeaveRightRepository = employeeSubstitutionLeaveRightRepository;
	}

	@Override
	public Map<String, SubReportVo> mapSubReportQueryResult(List<Object[]> objects) {
		if(objects == null) {
			return null;
		}

		Map<String, SubReportVo> singleReportVos = new HashMap<>();

		for(Object[] object : objects) {
			SubReportVo subReportVo = new SubReportVo(object);
			singleReportVos.put(subReportVo.getNik(), subReportVo);
		}

		return singleReportVos;
	}

	@Override
	public List<ReportResponseVo> generateFullReport(String dept, LocalDate startDate, LocalDate endDate) {
		List<ReportResponseVo> reports = employeeRepository.initReport(dept);

		List<Object[]> daysComingObj = attendanceRepository.countEachEmployeeAttendanceByDepartmentDateBetween(dept, startDate, endDate);
		List<Object[]> daysAbsenceObj = attendanceRepository.countEachEmployeeAbsenceByDepartmentDateBetween(dept, startDate, endDate);
		List<Object[]> daysLeaveEarlyObj = attendanceRepository.countEachEmployeeEarlyLeaveByDepartmentDateBetween(dept, startDate, endDate);
		List<Object[]> daysLateWithoutPermissionObj = attendanceRepository.countEachEmployeeLateConditionByDepartmentDateBetween(LateCondition.LATE.ordinal(), dept, startDate, endDate);
		List<Object[]> daysLateWithPermissionObj = attendanceRepository.countEachEmployeeLateConditionByDepartmentDateBetween(LateCondition.LATEWITHPERMISSION.ordinal(), dept, startDate, endDate);
		List<Object[]> daysNoTapOutObj = attendanceRepository.countEachEmployeeNoTapOutDateByDepartmentDateBetween(dept, startDate, endDate);
		List<Object[]> daysSickObj = employeeAbsencePermitRepository.countEachEmployeeAbsencePermitByDepartmentDateBetween(AbsencePermit.SICK.ordinal(), dept, startDate, endDate);
		List<Object[]> daysUnpaidLeaveObj = employeeAbsencePermitRepository.countEachEmployeeAbsencePermitByDepartmentDateBetween(AbsencePermit.UNPAID_LEAVE.ordinal(), dept, startDate, endDate);
		List<Object[]> daysHourlyLeaveObj = employeeAbsencePermitRepository.countEachEmployeeAbsencePermitByDepartmentDateBetween(AbsencePermit.HOURLY.ordinal(), dept, startDate, endDate);
		List<Object[]> daysYearlyLeaveObj = employeeYearlyLeaveRepository.sumEachEmployeeYearlyLeaveByDepartmentDateBetween(dept, startDate, endDate);
		List<Object[]> daysReplacementLeaveObj = employeeSubstitutionLeaveRightRepository.sumEachEmployeeSubstitutionLeaveRightByDepartmentDateBetween(dept, startDate, endDate);

		Map<String, SubReportVo>  daysComing = mapSubReportQueryResult(daysComingObj);
		Map<String, SubReportVo>  daysAbsence = mapSubReportQueryResult(daysAbsenceObj);
		Map<String, SubReportVo>  daysLeaveEarly = mapSubReportQueryResult(daysLeaveEarlyObj);
		Map<String, SubReportVo>  daysLateWithoutPermission = mapSubReportQueryResult(daysLateWithoutPermissionObj);
		Map<String, SubReportVo>  daysLateWithPermission = mapSubReportQueryResult(daysLateWithPermissionObj);
		Map<String, SubReportVo>  daysNoTapOut = mapSubReportQueryResult(daysNoTapOutObj);
		Map<String, SubReportVo>  daysSick = mapSubReportQueryResult(daysSickObj);
		Map<String, SubReportVo>  daysUnpaidLeave = mapSubReportQueryResult(daysUnpaidLeaveObj);
		Map<String, SubReportVo>  daysHourlyLeave = mapSubReportQueryResult(daysHourlyLeaveObj);
		Map<String, SubReportVo>  daysYearlyLeave = mapSubReportQueryResult(daysYearlyLeaveObj);
		Map<String, SubReportVo>  daysReplacementLeave = mapSubReportQueryResult(daysReplacementLeaveObj);


		for(ReportResponseVo report : reports){
			String nik = report.getNik();
			if(daysComing.get(nik)!=null)
				report.setDaysComing(daysComing.get(nik).getNumberResult());
			if(daysAbsence.get(nik)!=null)
				report.setDaysAbsence(daysAbsence.get(nik).getNumberResult());
			if(daysSick.get(nik)!=null)
				report.setdaysSick(daysSick.get(nik).getNumberResult());
			if(daysUnpaidLeave.get(nik)!=null)
				report.setUnpaidLeave(daysUnpaidLeave.get(nik).getNumberResult());
			if(daysYearlyLeave.get(nik)!=null)
				report.setYearlyLeave(daysYearlyLeave.get(nik).getNumberResult());
			if(daysLeaveEarly.get(nik)!=null)
				report.setLeaveEarly(daysLeaveEarly.get(nik).getNumberResult());
			if(daysLateWithoutPermission.get(nik)!=null)
				report.setLateWithoutPermission(daysLateWithoutPermission.get(nik).getNumberResult());
			if(daysLateWithPermission.get(nik)!=null)
				report.setLateWithPermission(daysLateWithPermission.get(nik).getNumberResult());
			if(daysHourlyLeave.get(nik)!=null)
				report.setHourlyLeave(daysHourlyLeave.get(nik).getNumberResult());
			if(daysReplacementLeave.get(nik)!=null)
				report.setReplacementLeave(daysReplacementLeave.get(nik).getNumberResult());
			if(daysNoTapOut.get(nik)!=null)
				report.setNoTapOutDay(daysNoTapOut.get(nik).getNumberResult());
		}
		LOG.info("Got Report");
		return reports;
	}

}
