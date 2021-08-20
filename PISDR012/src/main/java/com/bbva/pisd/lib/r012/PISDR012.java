package com.bbva.pisd.lib.r012;

import java.math.BigDecimal;
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

	Map<String, Object> executeGetInsuranceQuotation(String quotationId);

	Map<String, Object> executeGetInsuranceSimulationIdAndExpiredDate(String quotationId);
	void executeSaveInsuranceQuotation(Map<String, Object> arguments);
	void executeSaveInsuranceQuotationMod(Map<String, Object> arguments);
	void executeSaveInsuranceQuotationVeh(Map<String, Object> arguments);
	Map<String, Object> executeGetCompanyDescById(BigDecimal companyId);

	Map<String, Object> executeGetPlansBBVA(Map<String, Object> arguments);
	Map<String, Object> executeQueryForDetailQuotationService(String policyQuotaInternalId);

	void executeRegisterAdditionalQuotationVeh(Map<String, Object> arguments);
	Map<String, Object> executeRegisterAdditionalCompanyQuotaId(String companyQuotaId);
	void executeRegisterAdditionalQuotationBranch(Map<String, Object> arguments);
	void executeRegisterAdditionalQuotationBranchMod(Map<String, Object> arguments);

	Map<String, Object> executeGetRequiredFieldsForEmissionService(String policyQuotaInternalId);
	Map<String, Object> executeGetPaymentPeriod(String frequencyType);
	int executeSaveContract(Map<String, Object> arguments);
	int[] executeSaveReceipts(Map<String, Object>[] firstReceiptMap);
	int executeSaveContractMove(Map<String, Object> arguments);
	Map<String, Object> executeGetRolesByProductAndModality(BigDecimal productId, String modalityType);
	int[] executeSaveParticipants(Map<String, Object>[] participantsMap);

	Map<String, Object> executeGetPolicyContract(Map<String, Object> arguments);
	boolean executeUpdateInsuranceContract(Map<String, Object> arguments);
	boolean executeUpdatePaymentSchedule(Map<String, Object> arguments);

	Map<String, Object> executeQueryForGerInsuranceCompanyQuotaId(Map<String, Object>  policyQuotaInternalId);

	Map<String, Object> executeGetInsuranceContractStartDate(Map<String, Object> arguments);

	Map<String,Object> executeGetInsuranceContractStatus();
	Boolean executeUpdateInsuranceContractDocument(Map<String,Object> arguments);
}
