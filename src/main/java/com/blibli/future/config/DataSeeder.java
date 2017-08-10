package com.blibli.future.config;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.model.Employee;
import com.blibli.future.repository.AthensCredentialsRepository;
import com.blibli.future.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataSeeder implements ApplicationRunner {
    private final AthensCredentialsRepository credentialsRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public DataSeeder(AthensCredentialsRepository credentialsRepository, EmployeeRepository employeeRepository) {
        this.credentialsRepository = credentialsRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        AthensCredential adminCredential = new AthensCredential("ADMIN", "admin", "000", Stream.of(Role.ADMIN).collect(Collectors.toSet()));
        credentialsRepository.save(adminCredential);
        Employee emp = new Employee("000", "Yogie", Gender.MALE, "Backend Developer", "4", "Developer", MaritalStatus.LAJANG, Religion.KRISTEN, "Technology", "---", LocalDate.now().minusMonths(2), true);
        employeeRepository.save(emp);
    }
}
