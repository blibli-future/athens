package com.blibli.future.service.api;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.vo.SummariesVo;

public interface EmployeeStatisticService {
    SummariesVo generateSummaries(String nik) throws IdNotFoundException;
}
