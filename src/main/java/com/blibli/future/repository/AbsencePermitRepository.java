package com.blibli.future.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.AbsencePermit;

@Repository
public interface AbsencePermitRepository extends JpaRepository<AbsencePermit, String> {

}
