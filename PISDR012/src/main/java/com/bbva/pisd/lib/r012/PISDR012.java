package com.bbva.pisd.lib.r012;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PISDR012 {

	Map<String, Object> executeInsuranceProduct(Map<String, Object> arguments);
	Map<String, Object> executeGetProductIdForRimac(Map<String, Object> arguments);
	Map<String, Object> executeInsuranceProductModality(Map<String, Object> arguments);
	Map<String, Object> executeGetConsiderations(Map<String, Object> arguments);
	Map<String, Object> executeGetSimulationId();
	boolean executeSaveSimulation(Map<String, Object> arguments);
	void executeSaveSimulationProduct(Map<String, Object> arguments);
	void executeSaveSimulationVehicle(Map<String, Object> arguments);
	List<Map<String, Object>> executeGetInsuranceQuotation(String quotationId);
	Map<String, Object> executeGetInsuranceSimulationIdAndExpiredDate(String quotationId);
	void executeSaveInsuranceQuotation(Map<String, Object> arguments);
	void executeSaveInsuranceQuotationMod(Map<String, Object> arguments);
	void executeSaveInsuranceQuotationVeh(Map<String, Object> arguments);
	Map<String, Object> executeGetCompanyDescById(BigDecimal companyId);

}
