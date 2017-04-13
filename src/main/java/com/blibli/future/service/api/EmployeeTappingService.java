package com.blibli.future.service.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blibli.future.model.Attendance;

public interface EmployeeTappingService {
	public boolean addTapMachineFile(MultipartFile tapMachineFile);
	public boolean processUpdateTapping(String type, String nik, LocalDate dateTap, LocalTime tapTime);
	public boolean processTapping(String type, String nik, LocalDate dateTap, LocalTime tapTime);
	public List<Attendance> processGetTapping(LocalDate dateStart, LocalDate dateEnd);
}
