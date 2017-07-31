package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.enums.Status;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.service.api.ApprovalService;
import com.blibli.future.vo.ApprovalResponseVo;

@Service
public class ApprovalServiceImpl implements ApprovalService{

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
			//TODO log sukses dudeh
			return employeeAbsencePermitRepository.save(employeeAbsencePermit);
		}
		else{
			throw new IdNotFoundException("Absence Permit with ID: " + idAbsencePermit + " was not found in the database");
		}
	}

	@Override
	public EmployeeLeave processLeave(String idLeave, String chiefNik, boolean isApproved) throws IdNotFoundException{
		EmployeeLeave employeeLeave = employeeLeaveRepository.findOneById(idLeave);
		if(employeeLeave!=null)
		{
			employeeLeave.setApprovedBy(chiefNik);
			employeeLeave.setApprovedDate(LocalDate.now());
			if(isApproved)
				employeeLeave.setStatus(Status.APPROVED);
			else
				employeeLeave.setStatus(Status.REJECTED);
			//TODO Log sukses dudeh
			return employeeLeaveRepository.save(employeeLeave);
		}
		else{
			throw new IdNotFoundException("Absence Permit with ID: " + idLeave + " was not found in the database");
		}
		//TODO Log null dudeh
	}
	
	@Override
	public List<ApprovalResponseVo> getUnapprovedRequests(String chiefNik) {
		List<ApprovalResponseVo> approvalResponseVoList = new ArrayList<>();
		List<ApprovalResponseVo> employeeAbsencePermitList = new ArrayList<>();
		List<ApprovalResponseVo> employeeLeaveList = new ArrayList<>();
		employeeAbsencePermitList = employeeAbsencePermitRepository.getEmployeeAbsencePermitByChiefNikStatus(chiefNik, Status.WAITING);
		employeeLeaveList = employeeLeaveRepository.getEmployeeLeaveByChiefNikStatus(chiefNik, Status.WAITING);
		if(employeeAbsencePermitList!=null)
			approvalResponseVoList.addAll(employeeAbsencePermitList);
		if(employeeLeaveList!=null)
			approvalResponseVoList.addAll(employeeLeaveList);
		return approvalResponseVoList;
	}

	@Override
	public List<ApprovalResponseVo> getRequestHistories(String chiefNik) {
		List<ApprovalResponseVo> approvalResponseVoList = new ArrayList<>();
		List<ApprovalResponseVo> approvedAbsencePermitList = new ArrayList<>();
		List<ApprovalResponseVo> approvedLeaveList = new ArrayList<>();
		List<ApprovalResponseVo> rejectedAbsencePermitList = new ArrayList<>();
		List<ApprovalResponseVo> rejectedLeaveList = new ArrayList<>();
		
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

		return approvalResponseVoList;
	}
}
