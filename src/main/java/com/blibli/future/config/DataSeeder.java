package com.blibli.future.config;

import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.repository.AthensCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataSeeder implements ApplicationRunner {
    private final AthensCredentialsRepository credentialsRepository;

    @Autowired
    public DataSeeder(AthensCredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        AthensCredential adminCredential = new AthensCredential("ADMIN", "admin", "000", Stream.of(Role.ADMIN).collect(Collectors.toSet()));

        credentialsRepository.save(adminCredential);
    }
}
