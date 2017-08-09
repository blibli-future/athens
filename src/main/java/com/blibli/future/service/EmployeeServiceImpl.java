package com.blibli.future.service;
import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.enums.Role;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.AthensCredentialsRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.vo.EmployeeResponseVo;
import com.blibli.future.vo.EmployeeEditRequestVo;
import com.blibli.future.vo.EmployeeRequestVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private AthensCredentialsRepository  athensCredentialsRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Autowired
    public EmployeeServiceImpl (EmployeeRepository employeeRepository, AthensCredentialsRepository  athensCredentialsRepository){
        this.employeeRepository = employeeRepository;
        this.athensCredentialsRepository = athensCredentialsRepository;
    }

    public Employee saveEmployee (EmployeeRequestVo employeeVo){
        if(isEmployeeExist(employeeVo.getNik())){
            return null;
        }else{
            Employee emp = new Employee(employeeVo.getNik(), employeeVo.getFullName(), Gender.valueOf(employeeVo.getGender()), employeeVo.getPosition(), 
            		employeeVo.getLevel(), employeeVo.getOrganizationalUnitText(), MaritalStatus.valueOf(employeeVo.getMaritalStatus()), Religion.valueOf(employeeVo.getReligion()), employeeVo.getNameOfDept(),
            		employeeVo.getChiefNik(), LocalDate.parse(employeeVo.getStartWorkingDate(), formatter), true);
            employeeRepository.save(emp);
            AthensCredential credential = new AthensCredential(employeeVo.getNik(), "123456", employeeVo.getNik(), Stream.of(Role.ADMIN).collect(Collectors.toSet()));
            athensCredentialsRepository.save(credential);
            LOG.info("Employee with NIK: "+employeeVo.getNik()+" Saved");
            return emp;
        }
    }
    public boolean isEmployeeExist(String nik){
        if (employeeRepository.findOneByNik(nik) !=null){
            return true;
        }
        return  false;
    }


    public Employee updateEmployee (EmployeeEditRequestVo employeeVo) throws IdNotFoundException{
    	Employee oldEmployee = employeeRepository.findOneByNik(employeeVo.getNik());
    	if(oldEmployee == null){
    		LOG.error("NIK: " + employeeVo.getNik() + " was not found");
    		throw new IdNotFoundException("NIK: " + employeeVo.getNik() + " was not found");
    	}
    	oldEmployee.setChiefNik(employeeVo.getChiefNik());
    	oldEmployee.setFullName(employeeVo.getFullName());
    	oldEmployee.setGender(Gender.valueOf(employeeVo.getGender()));
    	oldEmployee.setLevel(employeeVo.getLevel());
    	oldEmployee.setMaritalStatus(MaritalStatus.valueOf(employeeVo.getMaritalStatus()));
    	oldEmployee.setNameOfDept(employeeVo.getNameOfDept());
    	oldEmployee.setOrganizationalUnitText(employeeVo.getOrganizationalUnitText());
    	oldEmployee.setReligion(Religion.valueOf(employeeVo.getReligion()));
    	oldEmployee.setStartWorkingDate(LocalDate.parse(employeeVo.getStartWorkingDate(), formatter));
    	oldEmployee.setStatus(employeeVo.getStatus());
    	if(!oldEmployee.getStatus())
    		oldEmployee.setEndWorkingDate(LocalDate.parse(employeeVo.getEndWorkingDate(), formatter));
    	Employee newEmployee = employeeRepository.save(oldEmployee);
    	LOG.info("NIK: " + employeeVo.getNik() + " Updated");
    	return newEmployee;
    }
    
	@Override
	public List<EmployeeResponseVo> getAllEmployees() {
		List<EmployeeResponseVo> listEmployee = new ArrayList<>();
        listEmployee = employeeRepository.findAllEmployee();
        if(listEmployee!=null){
        	LOG.info("Got Employee List");
            return listEmployee;
        }
        LOG.error("Employee List is Null");
        return null;
	}

	@Override
	public EmployeeResponseVo getEmployeeByNik(String nik) throws IdNotFoundException {
		Employee employee = employeeRepository.findOneByNik(nik);
		if(employee == null){
			LOG.error("NIK: " + nik + " was not found");
			throw new IdNotFoundException("NIK: " + nik + " was not found");
		}
		EmployeeResponseVo employeeResponse = new EmployeeResponseVo(employee.getNik(), employee.getFullName(), employee.getGender(), employee.getPosition(), employee.getOrganizationalUnitText(), employee.getMaritalStatus(), employee.getReligion(), employee.getNameOfDept(), employee.getChiefNik(), employee.getStartWorkingDate(), employee.getLevel());
		LOG.info("Got Employee Nik: " + nik);
		return employeeResponse;
	}

}