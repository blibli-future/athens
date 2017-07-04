package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.LateCondition;
import com.blibli.future.model.EmployeeSubstitutionLeaveRight;
import com.blibli.future.repository.AttendanceRepository;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.EmployeeSubstitutionLeaveRightRepository;
import com.blibli.future.repository.EmployeeYearlyLeaveRepository;
import com.blibli.future.service.api.ReportService;
import com.blibli.future.vo.ReportResponseVo;
import com.blibli.future.vo.SingleReportVo;

@Service
public class ReportServiceImpl implements ReportService{
	EmployeeRepository employeeRepository;
	AttendanceRepository attendanceRepository;
	EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
	EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
	
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
	public List<SingleReportVo> reportParse(List<Object[]> objects) {
		if(objects!=null)
		{
			List<SingleReportVo> singleReportVos = new ArrayList<>();
			for(Object[] object : objects){
				SingleReportVo singleReportVo = new SingleReportVo(object);
				singleReportVos.add(singleReportVo);
			}
			return singleReportVos;
		}
		return null;
	}

	@Override
	public List<ReportResponseVo> fullReport(String dept, LocalDate startDate, LocalDate endDate) {
		List<ReportResponseVo> reports = employeeRepository.initReport(dept);
		List<Object[]> daysComingObject = attendanceRepository.countEmployeeAttendance(dept, startDate, endDate);
		List<Object[]> daysAbsenceObject = attendanceRepository.countNotAttendance(dept, startDate, endDate);
		List<Object[]> daysSickObject = employeeAbsencePermitRepository.countAbsencePermitEmployee(dept, startDate, endDate, AbsencePermit.SICK.ordinal());
		List<Object[]> daysUnpaidLeaveObject = employeeAbsencePermitRepository.countAbsencePermitEmployee(dept, startDate, endDate, AbsencePermit.UNPAID_LEAVE.ordinal());
		List<Object[]> daysYearlyLeaveObject = employeeYearlyLeaveRepository.sumEmployeeYearlyLeave(dept, startDate, endDate);
		List<Object[]> daysLeaveEarlyObject = attendanceRepository.countEmployeeEarlyLeaveHour(dept, startDate, endDate);
		List<Object[]> daysLateWithoutPermissionObject = attendanceRepository.countEmployeeLateCondition(dept, startDate, endDate, LateCondition.LATE.ordinal());
		List<Object[]> daysLateWithPermissionObject = attendanceRepository.countEmployeeLateCondition(dept, startDate, endDate, LateCondition.LATEWITHPERMISSION.ordinal());
		List<Object[]> daysHourlyLeaveObject = employeeAbsencePermitRepository.countAbsencePermitEmployee(dept, startDate, endDate, AbsencePermit.HOURLY.ordinal());
		List<Object[]> daysReplacementLeaveObject = employeeSubstitutionLeaveRightRepository.sumEmployeeSubtitutionLeaveRight(dept, startDate, endDate);
		List<Object[]> daysNoTapOutObject = attendanceRepository.countEmployeeNoTapOut(dept, startDate, endDate);
		
		List<SingleReportVo> daysComing = reportParse(daysComingObject);
		List<SingleReportVo> daysAbsence = reportParse(daysAbsenceObject);
		List<SingleReportVo> daysSick = reportParse(daysSickObject);
		List<SingleReportVo> daysUnpaidLeave = reportParse(daysUnpaidLeaveObject);
		List<SingleReportVo> daysYearlyLeave = reportParse(daysYearlyLeaveObject);
		List<SingleReportVo> daysLeaveEarly = reportParse(daysLeaveEarlyObject);
		List<SingleReportVo> daysLateWithoutPermission = reportParse(daysLateWithoutPermissionObject);
		List<SingleReportVo> daysLateWithPermission = reportParse(daysLateWithPermissionObject);
		List<SingleReportVo> daysHourlyLeave = reportParse(daysHourlyLeaveObject);
		List<SingleReportVo> daysReplacementLeave = reportParse(daysReplacementLeaveObject);
		List<SingleReportVo> daysNoTapOut = reportParse(daysNoTapOutObject);
		
		for(ReportResponseVo report : reports){
			for(SingleReportVo x : daysComing){
				if(report.getNik().equals(x.getNik())){
					report.setDaysComing(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysAbsence){
				if(report.getNik().equals(x.getNik())){
					report.setDaysAbsence(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysSick){
				if(report.getNik().equals(x.getNik())){
					report.setSick(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysUnpaidLeave){
				if(report.getNik().equals(x.getNik())){
					report.setUnpaidLeave(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysYearlyLeave){
				if(report.getNik().equals(x.getNik())){
					report.setYearlyLeave(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysLeaveEarly){
				if(report.getNik().equals(x.getNik())){
					report.setLeaveEarly(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysLateWithoutPermission){
				if(report.getNik().equals(x.getNik())){
					report.setLateWithoutPermission(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysLateWithPermission){
				if(report.getNik().equals(x.getNik())){
					report.setLateWithPermission(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysHourlyLeave){
				if(report.getNik().equals(x.getNik())){
					report.setHourlyLeave(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysReplacementLeave){
				if(report.getNik().equals(x.getNik())){
					report.setReplacementLeave(x.getNumberResult());
					break;
				}
			}
			
			for(SingleReportVo x : daysNoTapOut){
				if(report.getNik().equals(x.getNik())){
					report.setNoTapOutDay(x.getNumberResult());
					break;
				}
			}
		}
		
		return reports;
	}

}
