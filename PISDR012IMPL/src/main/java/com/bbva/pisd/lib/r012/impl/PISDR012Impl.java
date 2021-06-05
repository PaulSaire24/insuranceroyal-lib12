package com.bbva.pisd.lib.r012.impl;

import com.bbva.apx.exception.db.NoResultException;

import com.bbva.pisd.dto.insurance.utils.PISDErrors;
import com.bbva.pisd.dto.insurance.utils.PISDProperties;

import java.math.BigDecimal;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Objects;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PISDR012Impl extends PISDR012Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR012Impl.class);

	@Override
	public Map<String, Object> executeInsuranceProduct(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeInsuranceProduct START *****");
		Map<String, Object> response = null;
		if(parametersEvaluation(arguments, PISDProperties.FILTER_INSURANCE_PRODUCT_TYPE.getValue())) {
			try {
				LOGGER.info("***** PISDR012Impl - executeInsuranceProduct PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSRC_PRODUCT.getValue(), arguments);
				response.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_PRODUCT] Result -> Key {} with value: {}", key, value));
			} catch(NoResultException ex) {
				LOGGER.info("executeInsuranceProduct - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_PRODUCT]");
				this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		}
		LOGGER.info("***** PISDR012Impl - executeInsuranceProduct END *****");
		return response;
	}

	@Override
	public Map<String, Object> executeGetProductIdForRimac(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetProductIdForRimac START *****");
		Map<String, Object> response = null;
		if(parametersEvaluation(arguments, PISDProperties.FIELD_OR_FILTER_INSURANCE_RISK_BUSINESS_ID.getValue())) {
			try {
				LOGGER.info("***** PISDR012Impl - executeGetProductIdForRimac PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSRC_PRODUCT_FOR_RIMAC.getValue(), arguments);
				response.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_BUSINESS] Result -> Key {} with value: {}", key, value));
			} catch(NoResultException ex) {
				LOGGER.info("executeGetProductIdForRimac - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_BUSINESS]");
				this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		}
		LOGGER.info("***** PISDR012Impl - executeGetProductIdForRimac END *****");
		return response;
	}

	@Override
	public Map<String, Object> executeInsuranceProductModality(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeInsuranceProductModality START *****");
		List<Map<String, Object>> response = null;
		if(parametersEvaluation(arguments, PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())) {
			try {
				LOGGER.info("***** PISDR012Impl - executeInsuranceProductModality PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRNC_PRD_MODALITY.getValue(), arguments);
				response.stream().forEach((map -> map.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSRNC_PRD_MODALITY] Result -> Key {} with value: {}", key, value)))
				);
			} catch(NoResultException ex) {
				LOGGER.info("executeInsuranceProductModality - RESPONSE EMPTY [PISD.SELECT_INSRNC_PRD_MODALITY]");
				this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		}
		LOGGER.info("***** PISDR012Impl - executeInsuranceProductModality END *****");
		return buildResult(response);
	}

	@Override
	public Map<String, Object> executeGetConsiderations(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetConsiderations START *****");
		List<Map<String, Object>> response = null;
		if(parametersEvaluation(arguments, PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())) {
			try {
				LOGGER.info("***** PISDR012Impl - executeGetConsiderations PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_CONSIDERATIONS.getValue(), arguments);
			} catch(NoResultException ex) {
				LOGGER.info("executeConsiderationsIds - RESPONSE EMPTY [PISD.SELECT_CONSIDERATIONS_IDS]");
				this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		}
		LOGGER.info("***** PISDR012Impl - executeGetConsiderations END *****");
		return buildResult(response);
	}

	@Override
	public Map<String, Object> executeGetSimulationId() {
		LOGGER.info("***** PISDR012Impl - executeGetSimulationId START *****");
		Map<String, Object> response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_SIMULATION_ID.getValue());
		response.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_SIMULATION_ID] Result -> Key {} with value: {}", key, value));
		LOGGER.info("***** PISDR012Impl - executeGetSimulationId END *****");
		return response;
	}


	@Override
	public boolean executeSaveSimulation(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveSimulation START *****");
		if(parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(),
				PISDProperties.FIELD_CUSTOMER_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveSimulation - PARAMETER OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_SIMULATION.getValue(), arguments);
		} else {
			LOGGER.info("executeSaveSimulation - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_SIMULATION]");
			return false;
		}

		LOGGER.info("***** PISDR012Impl - executeSaveSimulation END *****");
		return true;
	}

	@Override
	public void executeSaveSimulationProduct(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveSimulationProduct START *****");
		if(parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(), PISDProperties.FIELD_SALE_CHANNEL_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveSimulationProduct - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSRNC_SIMLT_PRD.getValue(), arguments);
		} else {
			LOGGER.info("executeSaveSimulationProduct - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSRNC_SIMLT_PRD]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveSimulationProduct END *****");
	}

	@Override
	public void executeSaveSimulationVehicle(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveSimulationVehicle START *****");
		if(parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(), PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveSimulationVehicle - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSRNC_SIMLT_VEHICLE.getValue(), arguments);
		} else {
			LOGGER.info("executeSaveSimulationVehicle - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSRNC_SIMLT_VEHICLE]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveSimulationVehicle END *****");
	}

	@Override
	public Map<String, Object> executeGetInsuranceQuotation(String quotationId) {
		LOGGER.info("***** PISDR012Impl - executeGetInsuranceQuotation START *****");
		List<Map<String, Object>> response = null;

		try {
			response = this.jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRC_QUOTATION.getValue(), quotationId);
			response.forEach(map -> map.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_QUOTATION] Result -> Key {} with value: {}", key, value)));
		} catch (NoResultException ex) {
			LOGGER.info("executeGetInsuranceQuotation - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_QUOTATION]");
			this.addAdvice(PISDErrors.ERROR_NO_RESULT_JDBC_INSRC_QUOTATION.getAdviceCode());
		}

		LOGGER.info("***** PISDR012Impl - executeGetInsuranceQuotation END *****");
		return buildResult(response);
	}

	@Override
	public Map<String, Object> executeGetInsuranceSimulationIdAndExpiredDate(String quotationId) {
		LOGGER.info("***** PISDR012Impl - executeGetIdAndExpiredDateInsuranceSimulation START *****");
		Map<String, Object> response = null;
		try {
			response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_SIMULATION_BY_QUOTATIONID.getValue(), quotationId);
			response.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_SIMULATION_BY_QUOTATIONID] Result -> Key {} with value: {}", key, value));
		} catch(NoResultException ex) {
			LOGGER.info("executeGetInsuranceSimulationIdAndExpiredDate - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_SIMULATION_BY_QUOTATIONID]");
			this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
		}
		LOGGER.info("***** PISDR012Impl - executeGetInsuranceSimulationIdAndExpiredDate END *****");
		return response;
	}

	@Override
	public void executeSaveInsuranceQuotation(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotation START *****");
		if(parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),
				PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(), PISDProperties.FIELD_INSURANCE_COMPANY_QUOTA_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotation - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_QUOTATION.getValue(), arguments);
		} else {
			LOGGER.info("executeSaveInsuranceQuotation - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_QUOTATION]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotation END *****");
	}

	@Override
	public void executeSaveInsuranceQuotationMod(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationMod START *****");
		if(parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(), PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue(),
				PISDProperties.FIELD_SALE_CHANNEL_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationMod - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_QUOTATION_MOD.getValue(), arguments);
		} else {
			LOGGER.info("executeSaveInsuranceQuotationMod - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_QUOTATION_MOD]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationMod END *****");
	}

	@Override
	public void executeSaveInsuranceQuotationVeh(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationVeh START *****");
		if(parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(), PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue(),
				PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationVeh - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_QUOTATION_VEH.getValue(), arguments);
		} else {
			LOGGER.info("executeSaveInsuranceQuotationVeh - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_QUOTATION_VEH]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationVeh END *****");
	}

	@Override
	public Map<String, Object> executeGetCompanyDescById(BigDecimal companyId) {
		LOGGER.info("***** PISDR012Impl - executeGetCompanyDescById START *****");
		Map<String, Object> response = null;
		try {
			response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_COMPANY_BY_COMPANYID.getValue(), companyId);
			response.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_COMPANY] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.info("executeGetCompanyDescById - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_COMPANY]");
			this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
		}
		LOGGER.info("***** PISDR012Impl - executeGetCompanyDescById END *****");
		return response;
	}

	@Override
	public Map<String, Object> executeGetPlansBBVA(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetPlansBBVA START *****");
		List<Map<String, Object>> response = null;

		if(parametersEvaluation(arguments, PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_INSURANCE_COMPANY_MODALITY_ID.getValue())) {
			try {
				LOGGER.info("***** PISDR012Impl - executeGetPlansBBVA PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRNC_PRD_MODALITY_BY_RIMAC_IDS.getValue(), arguments);
				response.forEach(map -> map.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSRNC_PRD_MODALITY_BY_COMPANY_MODALITY_ID] Result -> Key {} with value: {}", key, value)));
			} catch (NoResultException ex) {
				LOGGER.info("executeGetPlansBBVA - QUERY EMPTY RESULT [PISD.SELECT_INSRNC_PRD_MODALITY_BY_COMPANY_MODALITY_ID]");
				this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		}
		LOGGER.info("***** PISDR012Impl - executeGetPlansBBVA END *****");
		return buildResult(response);
	}

	@Override
	public Map<String, Object> executeQueryForDetailQuotationService(String policyQuotaInternalId) {
		LOGGER.info("***** PISDR012Impl - executeQueryForDetailQuotationService START *****");
		Map<String, Object> response = null;
		try {
			response = this.jdbcUtils.queryForMap(PISDProperties.ID_QUERY_GET_QUOTATION_DETAIL.getValue(), policyQuotaInternalId);
			response.forEach((key, value) -> LOGGER.info("[PISD.QUERY_FOR_GET_QUOTATION_SERVICE] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.info("executeQueryForDetailQuotationService - QUERY EMPTY RESULT [PISD.QUERY_FOR_GET_QUOTATION_SERVICE]");
			this.addAdvice(PISDErrors.NON_EXISTENT_QUOTATION.getAdviceCode());
		}
		LOGGER.info("***** PISDR012Impl - executeQueryForDetailQuotationService END *****");
		return response;
	}

	@Override
	public void executeRegisterAdditionalQuotationVeh(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationVeh START *****");

		if(parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())) {

			LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationVeh - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_UPDATE_QUOTATION_REGISTER_ADDITIONAL_VEH.getValue(), arguments);
		} else {

			LOGGER.info("executeRegisterAdditionalQuotationVeh - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSURANCE_QUOTATION_VEH]");
		}

		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationVeh END *****");
	}


	@Override
	public Map<String, Object> executeRegisterAdditionalCompanyQuotaId(String companyQuotaId) {
		LOGGER.info("***** PISDR012Impl - executeRegisterAdditionalCompanyQuotaId START *****");
		Map<String, Object> response = null;
		try {
			response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_COMPANY_BY_COMPANY_REGISTER.getValue(), companyQuotaId);

			response.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_QUOTATION_REGISTER] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.info("executeRegisterAdditionalCompanyQuotaId - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_QUOTATION_REGISTER]");
			this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
		}
		LOGGER.info("***** PISDR012Impl - executeRegisterAdditionalCompanyQuotaId END *****");
		return response;
	}

	@Override
	public void executeRegisterAdditionalQuotationBranch(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranch START *****");

		if(parametersEvaluation(arguments, PISDProperties.FIELD_LAST_CHANGE_BRANCH_ID.getValue())) {

			LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranch - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSURANCE_QUOTATION_REGISTER.getValue(), arguments);
		} else {

			LOGGER.info("executeRegisterAdditionalQuotationBranch - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSURANCE_QUOTATION]");
		}

		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranch END *****");
	}

	@Override
	public void executeRegisterAdditionalQuotationBranchMod(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranchMod START *****");

		if(parametersEvaluation(arguments, PISDProperties.FIELD_LAST_CHANGE_BRANCH_ID.getValue())) {

			LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranchMod - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSRNC_QUOTATION_MOD_REGISTER.getValue(), arguments);
		} else {

			LOGGER.info("executeRegisterAdditionalQuotationBranchMod - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSRNC_QUOTATION_MOD]");
		}

		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranchMod END *****");
	}

	@Override
	public int executeSaveContract(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveContract START *****");
		int affectedRows = 0;
		if(parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(), PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue(),
				PISDProperties.FIELD_INSURANCE_COMPANY_ID.getValue(), PISDProperties.FIELD_POLICY_ID.getValue(),
				PISDProperties.FIELD_INSURANCE_CONTRACT_START_DATE.getValue(), PISDProperties.FIELD_INSURANCE_CONTRACT_END_DATE.getValue(),
				PISDProperties.FIELD_CUSTOMER_ID.getValue(), PISDProperties.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE.getValue(),
				PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveContract - PARAMETERS OK ... EXECUTING *****");
			try {
				affectedRows = this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_CONTRACT.getValue(), arguments);
			} catch (NoResultException ex) {
				LOGGER.info("***** PISDR012Impl - executeSaveContract - Database exception: {} *****", ex.getMessage());
				affectedRows = -1;
			}
		} else {
			LOGGER.info("executeSaveContract - MISSING MANDATORY PARAMETERS [PISD.INSERT_CONTRACT]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveContract END *****");
		return affectedRows;
	}

	@Override
	public Map<String, Object> executeGetPolicyContract(Map<String, Object> arguments) {
		Map<String, Object> response = null;
		if(parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_ID.getValue())) {
			try {
				response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue(), arguments);	
			} catch(NoResultException ex) {
				this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		}
		return response;
	}

	private boolean parametersEvaluation(Map<String, Object> arguments, String... keys) {
		return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
	}

	private Map<String, Object> buildResult(List<Map<String, Object>> response) {
		Map<String, Object> result = new HashMap<>();
		result.put(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue(), response);
		return result;
	}

	

}