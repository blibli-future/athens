package com.blibli.future.service.api;

import java.time.LocalDate;

public interface RequestService {
	public boolean sentRequest(String nik, String idLeave, LocalDate startDate, LocalDate endDate, String reason);
}
