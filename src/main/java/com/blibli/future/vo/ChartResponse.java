package com.blibli.future.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChartResponse implements Serializable{
	private static final long serialVersionUID = -5740697559286666084L;
	private List<DailyChartVo> dailyChart;
	private List<SubReportVo> top10Report;
	
	public ChartResponse(){}
	
	public ChartResponse(List<DailyChartVo> dailyChart, List<SubReportVo> top10Report){
		this.dailyChart = dailyChart;
		this.top10Report = top10Report;
	}

	public List<DailyChartVo> getDailyChart() {
		return dailyChart;
	}

	public void setDailyChart(List<DailyChartVo> dailyChart) {
		this.dailyChart = dailyChart;
	}

	public List<SubReportVo> getTop10Report() {
		return top10Report;
	}

	public void setTop10Report(List<SubReportVo> top10Report) {
		this.top10Report = top10Report;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dailyChart == null) ? 0 : dailyChart.hashCode());
		result = prime * result + ((top10Report == null) ? 0 : top10Report.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChartResponse other = (ChartResponse) obj;
		if (dailyChart == null) {
			if (other.dailyChart != null)
				return false;
		} else if (!dailyChart.equals(other.dailyChart))
			return false;
		if (top10Report == null) {
			if (other.top10Report != null)
				return false;
		} else if (!top10Report.equals(other.top10Report))
			return false;
		return true;
	}
}
