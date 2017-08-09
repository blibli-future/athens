package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.enums.Status;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.service.api.ApprovalService;
import com.blibli.future.vo.PermissionResponseVo;

@Service
public class ApprovalServiceImpl implements ApprovalService{
	private static final Logger LOG = LoggerFactory.getLogger(ApprovalServiceImpl.class);
	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	private EmployeeLeaveRepository employeeLeaveRepository;
	
	@Autowired
	ApprovalServiceImpl(EmployeeAbsencePermitRepository employeeAbsencePermitRepository, EmployeeLeaveRepository employeeLeaveRepository){
		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
		this.employeeLeaveRepository = employeeLeaveRepository;
	}

	@Override
	public EmployeeAbsencePermit processAbsencePermit(String idAbsencePermit, String chiefNik, boolean isApproved) throws IdNotFoundException{
		EmployeeAbsencePermit employeeAbsencePermit = employeeAbsencePermitRepository.findOneById(idAbsencePermit);
		if(employeeAbsencePermit!=null)
		{
			employeeAbsencePermit.setProcessedBy(chiefNik);
			employeeAbsencePermit.setProcessedDate(LocalDate.now());
			if(isApproved)
				employeeAbsencePermit.setStatus(Status.APPROVED);
			else
				employeeAbsencePermit.setStatus(Status.REJECTED);
			LOG.info("Absence Permit:"+idAbsencePermit+" Processed By: "+chiefNik);
			return employeeAbsencePermitRepository.save(employeeAbsencePermit);
		}
		else{
			LOG.error("Absence Permit ID Not Found");
			throw new IdNotFoundException("Absence Permit with ID: " + idAbsencePermit + " was not found in the database");
		}
	}

	@Override
	public EmployeeLeave processLeave(String idLeave, String chiefNik, boolean isApproved) throws IdNotFoundException{
		EmployeeLeave employeeLeave = employeeLeaveRepository.findOneById(idLeave);
		if(employeeLeave!=null)
		{
			employeeLeave.setProcessedBy(chiefNik);
			employeeLeave.setProcessedDate(LocalDate.now());
			if(isApproved)
				employeeLeave.setStatus(Status.APPROVED);
			else
				employeeLeave.setStatus(Status.REJECTED);
			LOG.info("Leave with ID: "+idLeave+" Processed By "+chiefNik);
			return employeeLeaveRepository.save(employeeLeave);
		}
		else{
			LOG.error("Leave with ID: "+idLeave+" Not Found");
			throw new IdNotFoundException("Leave with ID: " + idLeave + " was not found in the database");
		}
	}
	
	@Override
	public List<PermissionResponseVo> getUnapprovedRequests(String chiefNik) {
		List<PermissionResponseVo> approvalResponseVoList = new ArrayList<>();
		List<PermissionResponseVo> employeeAbsencePermitList = new ArrayList<>();
		List<PermissionResponseVo> employeeLeaveList = new ArrayList<>();
		employeeAbsencePermitList = employeeAbsencePermitRepository.getEmployeeAbsencePermitByChiefNikStatus(chiefNik, Status.WAITING);
		employeeLeaveList = employeeLeaveRepository.getEmployeeLeaveByChiefNikStatus(chiefNik, Status.WAITING);
		if(employeeAbsencePermitList!=null)
			approvalResponseVoList.addAll(employeeAbsencePermitList);
		if(employeeLeaveList!=null)
			approvalResponseVoList.addAll(employeeLeaveList);
		LOG.info("Got Unapproved Requests");
		return approvalResponseVoList;
	}

	@Override
	public List<PermissionResponseVo> getRequestHistories(String chiefNik) {
		List<PermissionResponseVo> approvalResponseVoList = new ArrayList<>();
		List<PermissionResponseVo> approvedAbsencePermitList = new ArrayList<>();
		List<PermissionResponseVo> approvedLeaveList = new ArrayList<>();
		List<PermissionResponseVo> rejectedAbsencePermitList = new ArrayList<>();
		List<PermissionResponseVo> rejectedLeaveList = new ArrayList<>();
		
		approvedAbsencePermitList = employeeAbsencePermitRepository.getEmployeeAbsencePermitByChiefNikStatus(chiefNik, Status.APPROVED);
		approvedLeaveList = employeeLeaveRepository.getEmployeeLeaveByChiefNikStatus(chiefNik, Status.APPROVED);
		rejectedAbsencePermitList = employeeAbsencePermitRepository.getEmployeeAbsencePermitByChiefNikStatus(chiefNik, Status.REJECTED);
		rejectedLeaveList = employeeLeaveRepository.getEmployeeLeaveByChiefNikStatus(chiefNik, Status.REJECTED);
		
		if(approvedAbsencePermitList!=null)
			approvalResponseVoList.addAll(approvedAbsencePermitList);
		if(approvedLeaveList!=null)
			approvalResponseVoList.addAll(approvedLeaveList);
		if(rejectedAbsencePermitList!=null)
			approvalResponseVoList.addAll(rejectedAbsencePermitList);
		if(rejectedLeaveList!=null)
			approvalResponseVoList.addAll(rejectedLeaveList);

		LOG.info("Got Request Histories");
		return approvalResponseVoList;
	}
}
