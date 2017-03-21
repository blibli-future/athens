
package com.blibli.future.repository;

import com.blibli.future.model.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, String> {

}
