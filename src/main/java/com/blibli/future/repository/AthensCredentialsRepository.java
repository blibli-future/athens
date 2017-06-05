package com.blibli.future.repository;

import com.blibli.future.model.AthensCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthensCredentialsRepository extends JpaRepository<AthensCredentials, String>{

}
