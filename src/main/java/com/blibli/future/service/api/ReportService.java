package com.blibli.future.service.api;

import com.blibli.future.vo.ReportResponseVo;
import com.blibli.future.vo.SingleReportVo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReportService {
//	public void Top10LateEmployee();
//	public void Top10HourlyAbsence();
//	public void Top10SickAbsence();
//	public void Top10SubtitutionalLeave();
//	public void Top10YearlyLeave();
//	public void Top10SpecialLeave();
	public List<ReportResponseVo> fullReport(String dept, LocalDate startDate, LocalDate endDate);
	public Map<String, SingleReportVo> parseSingleReportObject(List<Object[]> objects);
}
