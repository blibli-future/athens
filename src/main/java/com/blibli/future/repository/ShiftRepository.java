package com.blibli.future.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, String> {
	public Shift findOneById(String id);
}
