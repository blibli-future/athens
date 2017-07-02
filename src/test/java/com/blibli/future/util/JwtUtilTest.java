package com.blibli.future.util;


import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtUtilTest {
    @Test
    public void createAndVerifyTest() throws Exception{
        AthensCredential adminCredential = new AthensCredential("ADMIN", "PASSWORD", "ADMIN-NIK", Stream.of(Role.ADMIN).collect(Collectors.toSet()));
        String adminToken = JwtUtil.createTokenFor(adminCredential);

        Assert.assertNotNull(JwtUtil.verify(adminToken));
    }
}
