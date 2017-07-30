package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.model.Leave;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.LeaveRepository;
import com.blibli.future.service.api.EmployeeLeaveService;
import com.blibli.future.vo.EmployeeLeaveVo;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService{
	private EmployeeLeaveRepository employeeLeaveRepository;
	private LeaveRepository leaveRepository;
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeLeaveServiceImpl(EmployeeLeaveRepository employeeLeaveRepository, LeaveRepository leaveRepository, EmployeeRepository employeeRepository){
		this.employeeLeaveRepository = employeeLeaveRepository;
		this.leaveRepository = leaveRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeLeave sentLeaveRequest(EmployeeLeaveVo employeeLeaveVo) {
		if(employeeLeaveVo!=null)
		{
			Employee emp = employeeRepository.findOneByNik(employeeLeaveVo.getNik());
			Leave lea = leaveRepository.findOneById(employeeLeaveVo.getIdLeave());
			if(emp!=null && lea!=null){
				EmployeeLeave employeeLeave = EmployeeLeave.convertToEmployeeLeave(employeeLeaveVo, emp, lea);
				EmployeeLeave savedEmployeeLeave = employeeLeaveRepository.save(employeeLeave);
				if(savedEmployeeLeave!=null){
					//Log EmployeeLeave saved dudeh
					return savedEmployeeLeave;
				}
				//Log save fail dudeh
				return null;
			}
			//Log employee or leave not found dudeh
			return null;
		}
		//Log VO null dudeh
		return null;
	}

	@Override
	public List<EmployeeLeave> getLeaveRequest(String nik) {
		if(nik!=null)
		{
			Employee emp = employeeRepository.findOneByNik(nik);
			if(emp!=null){
				//Get First Date Of Year
				int year = LocalDate.now().getYear();
				int month = 1;
				int dayOfMonth = 1;
				LocalDate dateStart = LocalDate.of(year, month, dayOfMonth);
				//Get End Date Of Year
				year = LocalDate.now().getYear();
				month = 12;
				dayOfMonth = 31;
				LocalDate dateEnd = LocalDate.of(year, month, dayOfMonth);
						
				List<EmployeeLeave> listEmployeeLeave = new ArrayList<>();
				listEmployeeLeave = employeeLeaveRepository.findByEmployeeAndRequestDateBetween(emp, dateStart, dateEnd);
				if(listEmployeeLeave!=null){
					//Log list parsed dudeh
					return listEmployeeLeave;
				}
				//Log null dudeh
				return null;
			}
			//Log employee null dudeh
			return null;
		}
		//Log nik null dudeh
		return null;
	}

	@Override
	public EmployeeLeave updateLeaveRequest(EmployeeLeaveVo employeeLeaveVo) {
		if(employeeLeaveVo!=null)
		{
			EmployeeLeave oldEmployeeLeave = employeeLeaveRepository.findOneById(employeeLeaveVo.getId());
			Leave leave = leaveRepository.findOneById(employeeLeaveVo.getIdLeave());
			if(oldEmployeeLeave!=null)
			{
				oldEmployeeLeave.updateEmployeeLeave(employeeLeaveVo, leave);
				EmployeeLeave savedEmployeeLeave = employeeLeaveRepository.save(oldEmployeeLeave);
				if(savedEmployeeLeave!=null)
				{
					//Log EmployeeLeave updated dudeh
					return savedEmployeeLeave;
				}
				//Log update fail dudeh
				return null;
			}
			//Log employee leave not found dudeh
			return null;
		}
		//Log Vo null dudeh
		return null;
	}
}
