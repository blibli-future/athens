
package com.blibli.future.repository;

import com.blibli.future.model.EmployeeShift;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, String> {
	public EmployeeShift findOneByNikAndIdshift(String nik, String idShift);
	public List<EmployeeShift> findByIdshift(String idShift);
}
