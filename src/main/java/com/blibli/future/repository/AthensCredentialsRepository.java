package com.blibli.future.repository;

import com.blibli.future.model.AthensCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthensCredentialsRepository extends JpaRepository<AthensCredential, String>{
    AthensCredential findByNikAndPassword(String nik, String password);
}