package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.LateCondition;
import com.blibli.future.enums.Status;
import com.blibli.future.exception.TypeNotFoundException;
import com.blibli.future.repository.AttendanceRepository;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.repository.EmployeeSubstitutionLeaveRightRepository;
import com.blibli.future.repository.EmployeeYearlyLeaveRepository;
import com.blibli.future.service.api.ChartService;
import com.blibli.future.vo.ChartResponse;
import com.blibli.future.vo.DailyChartVo;
import com.blibli.future.vo.SubReportVo;

@Service
public class ChartServiceImpl implements ChartService{
	private static final Logger LOG = LoggerFactory.getLogger(ChartServiceImpl.class);
	private AttendanceRepository attendanceRepository;
	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	private EmployeeLeaveRepository employeeLeaveRepository;
	private EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
	private EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
	
	@Autowired
	public ChartServiceImpl(AttendanceRepository attendanceRepository, EmployeeAbsencePermitRepository employeeAbsencePermitRepository,
			EmployeeLeaveRepository employeeLeaveRepository, EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository,
			EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository){
		this.attendanceRepository = attendanceRepository;
		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
		this.employeeLeaveRepository = employeeLeaveRepository;
		this.employeeSubstitutionLeaveRightRepository = employeeSubstitutionLeaveRightRepository;
		this.employeeYearlyLeaveRepository = employeeYearlyLeaveRepository;
	}
	
	@Override
	public ChartResponse generateChart(String dept, String type) throws TypeNotFoundException {
		List<DailyChartVo> dailyChart = new ArrayList<>();
		List<SubReportVo> top10Report =new ArrayList<>();
		if(type.equals("late")){
			List<Object[]> dailyObject = attendanceRepository.countOneDepartmentLateConditionByDepartmentDateBetween(LateCondition.LATE.ordinal(), dept, LocalDate.now().minusWeeks(1), LocalDate.now());
			List<Object[]> top10Object = attendanceRepository.countTop10EmployeeLateConditionByDepartmentDateBetween(LateCondition.LATE.ordinal(), dept, LocalDate.now().minusWeeks(1), LocalDate.now());
			for(Object[] object : dailyObject){
				dailyChart.add(new DailyChartVo(object));
			}
			for(Object[] object : top10Object){
				top10Report.add(new SubReportVo(object));
			}
			LOG.info("Got Employee Late Chart");
			return new ChartResponse(dailyChart, top10Report);
		}
		else if(type.equals("hourly")){
			List<Object[]> dailyObject = employeeAbsencePermitRepository.countOneDepartmentAbsencePermitByAbsencePermitStatusDepartmentDate(AbsencePermit.HOURLY.ordinal(), Status.APPROVED.ordinal(), dept, LocalDate.now(), 7);
			List<Object[]> top10Object = attendanceRepository.countTop10EmployeeLateConditionByDepartmentDateBetween(AbsencePermit.HOURLY.ordinal(), dept, LocalDate.now().minusWeeks(1), LocalDate.now());
			for(Object[] object : dailyObject){
				dailyChart.add(new DailyChartVo(object));
			}
			for(Object[] object : top10Object){
				top10Report.add(new SubReportVo(object));
			}
			LOG.info("Got Hourly Leave Chart");
			return new ChartResponse(dailyChart, top10Report);
		}
		else if(type.equals("sick")){
			List<Object[]> dailyObject = employeeAbsencePermitRepository.countOneDepartmentAbsencePermitByAbsencePermitStatusDepartmentDate(AbsencePermit.SICK.ordinal(), Status.APPROVED.ordinal(), dept, LocalDate.now(), 7);
			List<Object[]> top10Object = attendanceRepository.countTop10EmployeeLateConditionByDepartmentDateBetween(AbsencePermit.SICK.ordinal(), dept, LocalDate.now().minusWeeks(1), LocalDate.now());
			for(Object[] object : dailyObject){
				dailyChart.add(new DailyChartVo(object));
			}
			for(Object[] object : top10Object){
				top10Report.add(new SubReportVo(object));
			}
			LOG.info("Got Employee Sick Chart");
			return new ChartResponse(dailyChart, top10Report);
		}
		else if(type.equals("substitution")){
			List<Object[]> dailyObject = employeeSubstitutionLeaveRightRepository.countOneDepartmentSubstitutionLeaveByStatusDepartmentDate(Status.APPROVED.ordinal(), dept, LocalDate.now(), 7);
			List<Object[]> top10Object = employeeSubstitutionLeaveRightRepository.countTop10EmployeeSubstitutionLeaveByStatusDepartmentDateBetween(Status.APPROVED.ordinal(), dept, LocalDate.now().minusWeeks(1), LocalDate.now());
			for(Object[] object : dailyObject){
				dailyChart.add(new DailyChartVo(object));
			}
			for(Object[] object : top10Object){
				top10Report.add(new SubReportVo(object));
			}
			LOG.info("Got Substitution Leave Chart");
			return new ChartResponse(dailyChart, top10Report);
		}
		else if(type.equals("yearly")){
			List<Object[]> dailyObject = employeeYearlyLeaveRepository.countOneDepartmentYearlyLeaveByStatusDepartmentDate(Status.APPROVED.ordinal(), dept, LocalDate.now(), 7);
			List<Object[]> top10Object = employeeYearlyLeaveRepository.countTop10EmployeeSubstitutionLeaveByStatusDepartmentDateBetween(Status.APPROVED.ordinal(), dept, LocalDate.now().minusWeeks(1), LocalDate.now());
			for(Object[] object : dailyObject){
				dailyChart.add(new DailyChartVo(object));
			}
			for(Object[] object : top10Object){
				top10Report.add(new SubReportVo(object));
			}
			LOG.info("Got Yearly Leave Chart");
			return new ChartResponse(dailyChart, top10Report);
		}
		else if(type.equals("special")){
			List<Object[]> dailyObject = employeeLeaveRepository.countOneDepartmentSpecialLeaveByStatusDepartmentDate(Status.APPROVED.ordinal(), dept, LocalDate.now(), 7);
			List<Object[]> top10Object = employeeLeaveRepository.countTop10EmployeeSpecialLeaveByStatusDepartmentDateBetween(Status.APPROVED.ordinal(), dept, LocalDate.now().minusWeeks(1), LocalDate.now());
			for(Object[] object : dailyObject){
				dailyChart.add(new DailyChartVo(object));
			}
			for(Object[] object : top10Object){
				top10Report.add(new SubReportVo(object));
			}
			LOG.info("Got Special Leave Chart");
			return new ChartResponse(dailyChart, top10Report);
		}
		LOG.error("Type:"+type+" not found");
		throw new TypeNotFoundException("Type: " + type + " was not found");
	}

}
