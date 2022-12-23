package com.bbva.pisd.lib.r012;

import java.math.BigDecimal;
import java.util.Map;

public interface PISDR012 {

	Map<String, Object> executeGetProductInformation(String insuranceProductType);
	Map<String, Object> executeGetProductModalitiesInformation(Map<String, Object> arguments);
	Map<String, Object> executeGetProductModalitySelected(String insuranceModalityType);
	Map<String, Object> executeGetConsiderations(Map<String, Object> arguments);
	Map<String, Object> executeGetSimulationId();
	boolean executeSaveSimulation(Map<String, Object> arguments);
	void executeSaveSimulationProduct(Map<String, Object> arguments);
	void executeSaveSimulationVehicle(Map<String, Object> arguments);

	Map<String, Object> executeGetInsuranceQuotation(String quotationId);

	Map<String, Object> executeGetInsuranceSimulationIdAndExpiredDate(String quotationId);
	int executeSaveInsuranceQuotation(Map<String, Object> arguments);
	int executeSaveInsuranceQuotationMod(Map<String, Object> arguments);
	int executeSaveInsuranceQuotationVeh(Map<String, Object> arguments);

	Map<String, Object> executeGetPlansBBVA(Map<String, Object> arguments);
	Map<String, Object> executeQueryForDetailQuotationService(String policyQuotaInternalId);

	void executeRegisterAdditionalQuotationVeh(Map<String, Object> arguments);
	Map<String, Object> executeRegisterAdditionalCompanyQuotaId(String companyQuotaId);
	void executeRegisterAdditionalQuotationBranch(Map<String, Object> arguments);
	void executeRegisterAdditionalQuotationBranchMod(Map<String, Object> arguments);

	Map<String, Object> executeGetPolicyContract(Map<String, Object> arguments);
	boolean executeUpdateInsuranceContract(Map<String, Object> arguments);
	boolean executeUpdatePaymentSchedule(Map<String, Object> arguments);

	Map<String, Object> executeQueryForGerInsuranceCompanyQuotaId(Map<String, Object>  policyQuotaInternalId);

	Map<String, Object> executeGetInsuranceContractStartDate(Map<String, Object> arguments);

	Map<String,Object> executeGetInsuranceContractStatus();
	Boolean executeUpdateInsuranceContractDocument(Map<String,Object> arguments);
    Map<String,Object> executeGetOffer(Map<String,Object> arguments);
	//Inicio Open Market
	int executeUpdate(String nameProp, Map<String, Object> parameters);
	//Fin Open Market

	Map<String, Object> executeGetRolesByProductAndModality(BigDecimal productId, String modalityType);

	/**
	 * @author P030557
	 */
	Map<String, Object> executeGetRequiredFieldsForCreatedInsrcEvnt(String policyQuotaInternalId);

	/**
	 * @author P030557
	 * @param queryId query identificator from sql properties sheet
	 * @param arguments parameters to replace in query
	 * @param requiredParameters parameters whose should not be null values (if there aren't any parameters to evaluate, pass an empty array)
	 * @return rows number inserted or updated (it always must be 1 if everything went ok)
	 */
	int executeInsertSingleRow(String queryId, Map<String, Object> arguments, String... requiredParameters);

	/**
	 * @author P030557
	 * @param queryId query identificator from sql properties sheet
	 * @param arguments parameters to replace in query
	 * @return a row with its columns
	 */
	Map<String, Object> executeGetASingleRow(String queryId, Map<String, Object> arguments);

	/**
	 * @author P030557
	 * @param queryId query identificator from sql properties sheet
	 * @param argumentsArray parameters array to replace in each insert or update
	 * @return rows number inserted or updated (it should be more than 1 if everything went ok)
	 */
	int[] executeMultipleInsertionOrUpdate(String queryId, Map<String, Object>[] argumentsArray);

}
