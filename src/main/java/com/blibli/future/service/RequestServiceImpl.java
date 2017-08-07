package com.blibli.future.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.enums.Status;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.exception.TypeNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.model.EmployeeSubstitutionLeaveRight;
import com.blibli.future.model.EmployeeYearlyLeave;
import com.blibli.future.model.Leave;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.EmployeeSubstitutionLeaveRightRepository;
import com.blibli.future.repository.EmployeeYearlyLeaveRepository;
import com.blibli.future.repository.LeaveRepository;
import com.blibli.future.service.api.RequestService;
import com.blibli.future.vo.PermissionRequestVo;
import com.blibli.future.vo.PermissionResponseVo;

@Service
public class RequestServiceImpl implements RequestService{

	private EmployeeRepository employeeRepository;
	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	private EmployeeLeaveRepository employeeLeaveRepository;
	private EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
	private EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
	private LeaveRepository leaveRepository;
	
	@Autowired
	public RequestServiceImpl(EmployeeRepository employeeRepository, EmployeeAbsencePermitRepository employeeAbsencePermitRepository,
			EmployeeLeaveRepository employeeLeaveRepository, EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository,
			EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository, LeaveRepository leaveRepository){
		this.employeeRepository = employeeRepository;
		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
		this.employeeLeaveRepository = employeeLeaveRepository;
		this.employeeYearlyLeaveRepository = employeeYearlyLeaveRepository;
		this.employeeSubstitutionLeaveRightRepository = employeeSubstitutionLeaveRightRepository;
		this.leaveRepository = leaveRepository;
	}
	
	@Override
	public PermissionRequestVo createRequest(String nik, String type, PermissionRequestVo permissionRequestVo)
			throws IdNotFoundException, TypeNotFoundException {
		Employee employee = employeeRepository.findOneByNik(nik);
		if(employee==null)
			throw new IdNotFoundException("NIK: " + nik + " was not found");
		if(type.equals("absence")){
			EmployeeAbsencePermit employeeAbsencePermit = EmployeeAbsencePermit.convertToEmployeeAbsencePermit(permissionRequestVo, employee);
			employeeAbsencePermitRepository.save(employeeAbsencePermit);
		}
		else if(type.equals("leave")){
			Leave leave = leaveRepository.findOneById(permissionRequestVo.getRequestKey());
			EmployeeLeave employeeLeave = EmployeeLeave.convertToEmployeeLeave(permissionRequestVo, employee, leave);
			employeeLeaveRepository.save(employeeLeave);
		}
		else if(type.equals("yearly")){
			EmployeeYearlyLeave employeeYearlyLeave = EmployeeYearlyLeave.convertToEmployeeYearlyLeave(permissionRequestVo, employee);
			employeeYearlyLeaveRepository.save(employeeYearlyLeave);
		}
		else if(type.equals("subtitution")){
			EmployeeSubstitutionLeaveRight employeeSubstitutionLeaveRight = EmployeeSubstitutionLeaveRight.convertToEmployeeSubstitutionLeaveRight(permissionRequestVo, employee);
			employeeSubstitutionLeaveRightRepository.save(employeeSubstitutionLeaveRight);
		}
		else
			throw new TypeNotFoundException("Type: " + type + " was not found");
		return permissionRequestVo;
	}

	@Override
	public List<PermissionResponseVo> getEmployeeRequestHistories(String nik) throws IdNotFoundException {
		if(employeeRepository.findOneByNik(nik)==null)
			throw new IdNotFoundException("NIK: " + nik + " was not found");
		List<PermissionResponseVo> requestsResponseVoList = new ArrayList<>();
		
		List<PermissionResponseVo> approvedAbsencePermitList = new ArrayList<>();
		List<PermissionResponseVo> approvedLeaveList = new ArrayList<>();
		List<PermissionResponseVo> approvedSubstitutionLeaveRightList = new ArrayList<>();
		List<PermissionResponseVo> approvedYearlyLeaveList = new ArrayList<>();
		
		List<PermissionResponseVo> waitingAbsencePermitList = new ArrayList<>();
		List<PermissionResponseVo> waitingLeaveList = new ArrayList<>();
		List<PermissionResponseVo> waitingSubstitutionLeaveRightList = new ArrayList<>();
		List<PermissionResponseVo> waitingYearlyLeaveList = new ArrayList<>();
		
		List<PermissionResponseVo> rejectedAbsencePermitList = new ArrayList<>();
		List<PermissionResponseVo> rejectedLeaveList = new ArrayList<>();
		List<PermissionResponseVo> rejectedSubstitutionLeaveRightList = new ArrayList<>();
		List<PermissionResponseVo> rejectedYearlyLeaveList = new ArrayList<>();
		
		approvedAbsencePermitList = employeeAbsencePermitRepository.getEmployeeAbsencePermitByNikStatus(nik, Status.APPROVED);
		approvedLeaveList = employeeLeaveRepository.getEmployeeLeaveByNikStatus(nik, Status.APPROVED);
		approvedSubstitutionLeaveRightList = employeeSubstitutionLeaveRightRepository.getEmployeeSubstitutionLeaveRightByNikStatus(nik, Status.APPROVED);
		approvedYearlyLeaveList = employeeYearlyLeaveRepository.getEmployeeYearlyLeaveByNikStatus(nik, Status.APPROVED);
		
		waitingAbsencePermitList = employeeAbsencePermitRepository.getEmployeeAbsencePermitByNikStatus(nik, Status.APPROVED);
		waitingLeaveList = employeeLeaveRepository.getEmployeeLeaveByNikStatus(nik, Status.APPROVED);
		waitingSubstitutionLeaveRightList = employeeSubstitutionLeaveRightRepository.getEmployeeSubstitutionLeaveRightByNikStatus(nik, Status.APPROVED);
		waitingYearlyLeaveList = employeeYearlyLeaveRepository.getEmployeeYearlyLeaveByNikStatus(nik, Status.APPROVED);
		
		rejectedAbsencePermitList = employeeAbsencePermitRepository.getEmployeeAbsencePermitByNikStatus(nik, Status.REJECTED);
		rejectedLeaveList = employeeLeaveRepository.getEmployeeLeaveByNikStatus(nik, Status.REJECTED);
		rejectedSubstitutionLeaveRightList = employeeSubstitutionLeaveRightRepository.getEmployeeSubstitutionLeaveRightByNikStatus(nik, Status.REJECTED);
		rejectedYearlyLeaveList = employeeYearlyLeaveRepository.getEmployeeYearlyLeaveByNikStatus(nik, Status.REJECTED);

		if(approvedAbsencePermitList!=null)
			requestsResponseVoList.addAll(approvedAbsencePermitList);
		if(approvedLeaveList!=null)
			requestsResponseVoList.addAll(approvedLeaveList);
		if(approvedSubstitutionLeaveRightList!=null)
			requestsResponseVoList.addAll(approvedSubstitutionLeaveRightList);
		if(approvedYearlyLeaveList!=null)
			requestsResponseVoList.addAll(approvedYearlyLeaveList);
		
		if(waitingAbsencePermitList!=null)
			requestsResponseVoList.addAll(waitingAbsencePermitList);
		if(waitingLeaveList!=null)
			requestsResponseVoList.addAll(waitingLeaveList);
		if(waitingSubstitutionLeaveRightList!=null)
			requestsResponseVoList.addAll(waitingSubstitutionLeaveRightList);
		if(waitingYearlyLeaveList!=null)
			requestsResponseVoList.addAll(waitingYearlyLeaveList);
		
		if(rejectedAbsencePermitList!=null)
			requestsResponseVoList.addAll(rejectedAbsencePermitList);
		if(rejectedLeaveList!=null)
			requestsResponseVoList.addAll(rejectedLeaveList);
		if(rejectedSubstitutionLeaveRightList!=null)
			requestsResponseVoList.addAll(rejectedSubstitutionLeaveRightList);
		if(rejectedYearlyLeaveList!=null)
			requestsResponseVoList.addAll(rejectedYearlyLeaveList);

		return requestsResponseVoList;
	}

}