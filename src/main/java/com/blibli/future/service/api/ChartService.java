package com.blibli.future.service.api;

import com.blibli.future.exception.TypeNotFoundException;
import com.blibli.future.vo.ChartResponse;

public interface ChartService {
	public ChartResponse generateChart(String dept, String type) throws TypeNotFoundException;
}
