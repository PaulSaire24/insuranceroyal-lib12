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

import com.bbva.rbvd.dto.insrncsale.utils.RBVDErrors;
import com.bbva.rbvd.dto.insrncsale.utils.RBVDProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PISDR012Impl extends PISDR012Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR012Impl.class);

	@Override
	public Map<String, Object> executeGetProductInformation(String insuranceProductType) {
		LOGGER.info("***** PISDR012Impl - executeGetProductInformation START *****");
		Map<String, Object> response = null;
		try {
			response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_GET_PRODUCT_INFORMATION.getValue(), insuranceProductType);
			response.forEach((key, value) ->
					LOGGER.info("[PISD.GET_PRODUCT_INFORMATION] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.debug("[PISD.GET_PRODUCT_INFORMATION] - NON EXISTENT PRODUCT TYPE");
		}
		LOGGER.info("***** PISDR012Impl - executeGetProductInformation END *****");
		return response;
	}

	@Override
	public Map<String, Object> executeGetProductModalitiesInformation(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetProductModalitiesInformation START *****");
		List<Map<String, Object>> response = null;
		if(parametersEvaluation(arguments, PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue(), PISDProperties.FIELD_SALE_CHANNEL_ID.getValue())) {
			try {
				response = this.jdbcUtils.queryForList(PISDProperties.QUERY_GET_PRODUCT_MODALITIES_INFORMATION.getValue(), arguments);
				response.stream().forEach(map -> map.
						forEach((key, value) -> LOGGER.info("[PISD.GET_PRODUCT_MODALITIES_INFORMATION] Result -> Key {} with value: {}", key,value)));
			} catch (NoResultException ex) {
				LOGGER.debug("[PISD.GET_PRODUCT_MODALITIES_INFORMATION] - EMPTY RESULT");
			}
		}
		LOGGER.info("***** PISDR012Impl - executeGetProductModalitiesInformation END *****");
		return buildResult(response);
	}

	@Override
	public Map<String, Object> executeGetProductModalitySelected(String insuranceModalityType) {
		LOGGER.info("***** PISDR012Impl - executeGetProductModalitySelected START *****");
		Map<String, Object> response = null;
		try {
			response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_GET_PRODUCT_MODALITY_SELECTED.getValue(), insuranceModalityType);
			response.forEach((key, value) -> LOGGER.info("[PISD.GET_PRODUCT_MODALITY_SELECTED] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.debug("[PISD.GET_PRODUCT_MODALITY_SELECTED] - NON EXISTENT MODALITY");
		}
		LOGGER.info("***** PISDR012Impl - executeGetProductModalitySelected END *****");
		return response;
	}


	@Override
	public Map<String, Object> executeGetConsiderations(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetConsiderations START *****");
		List<Map<String, Object>> response = null;
		if (parametersEvaluation(arguments, PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())) {
			try {
				LOGGER.info("***** PISDR012Impl - executeGetConsiderations PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_CONSIDERATIONS.getValue(),
						arguments);
			} catch (NoResultException ex) {
				LOGGER.debug("executeConsiderationsIds - RESPONSE EMPTY [PISD.SELECT_CONSIDERATIONS_IDS]");
			}
		}
		LOGGER.info("***** PISDR012Impl - executeGetConsiderations END *****");
		return buildResult(response);
	}

	@Override
	public Map<String, Object> executeGetSimulationId() {
		LOGGER.info("***** PISDR012Impl - executeGetSimulationId START *****");
		Map<String, Object> response = this.jdbcUtils
				.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_SIMULATION_ID.getValue());
		response.forEach((key, value) -> LOGGER
				.info("[PISD.SELECT_INSURANCE_SIMULATION_ID] Result -> Key {} with value: {}", key, value));
		LOGGER.info("***** PISDR012Impl - executeGetSimulationId END *****");
		return response;
	}

	@Override
	public boolean executeSaveSimulation(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveSimulation START *****");
		if (parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(),
				PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {//Open Market
			LOGGER.info("***** PISDR012Impl - executeSaveSimulation - PARAMETER OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_SIMULATION.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveSimulation - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_SIMULATION]");
			return false;
		}

		LOGGER.info("***** PISDR012Impl - executeSaveSimulation END *****");
		return true;
	}

	@Override
	public void executeSaveSimulationProduct(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveSimulationProduct START *****");
		if (parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_SALE_CHANNEL_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveSimulationProduct - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSRNC_SIMLT_PRD.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveSimulationProduct - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSRNC_SIMLT_PRD]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveSimulationProduct END *****");
	}

	@Override
	public void executeSaveSimulationVehicle(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveSimulationVehicle START *****");
		if (parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveSimulationVehicle - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSRNC_SIMLT_VEHICLE.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveSimulationVehicle - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSRNC_SIMLT_VEHICLE]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveSimulationVehicle END *****");
	}

	@Override
	public Map<String, Object> executeGetInsuranceQuotation(String quotationId) {
		LOGGER.info("***** PISDR012Impl - executeGetInsuranceQuotation START *****");
		List<Map<String, Object>> response = null;

		try {
			response = this.jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRC_QUOTATION.getValue(), quotationId);
			response.forEach(map -> map.forEach((key, value) -> LOGGER
					.info("[PISD.SELECT_INSURANCE_QUOTATION] Result -> Key {} with value: {}", key, value)));
		} catch (NoResultException ex) {
			LOGGER.debug("executeGetInsuranceQuotation - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_QUOTATION]");
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
			response = this.jdbcUtils.queryForMap(
					PISDProperties.QUERY_SELECT_INSURANCE_SIMULATION_BY_QUOTATIONID.getValue(), quotationId);
			response.forEach((key, value) -> LOGGER.info(
					"[PISD.SELECT_INSURANCE_SIMULATION_BY_QUOTATIONID] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.debug("executeGetInsuranceSimulationIdAndExpiredDate - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_SIMULATION_BY_QUOTATIONID]");
		}
		LOGGER.info("***** PISDR012Impl - executeGetInsuranceSimulationIdAndExpiredDate END *****");
		return response;
	}

	@Override
	public int executeSaveInsuranceQuotation(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotation START *****");
		int affectedRows = 0;
		if (parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),
				PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue(),
				PISDProperties.FIELD_INSURANCE_COMPANY_QUOTA_ID.getValue(),
				PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotation - PARAMETERS OK ... EXECUTING *****");
			affectedRows = this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_QUOTATION.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceQuotation - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_QUOTATION]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotation | Number of inserted rows: {} *****", affectedRows);
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotation END *****");
		return affectedRows;
	}

	@Override
	public int executeSaveInsuranceQuotationMod(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationMod START *****");
		int affectedRows = 0;
		if (parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue(),
				PISDProperties.FIELD_SALE_CHANNEL_ID.getValue(), PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationMod - PARAMETERS OK ... EXECUTING *****");
			affectedRows = this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_QUOTATION_MOD.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceQuotationMod - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_QUOTATION_MOD]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationMod | Number of inserted rows: {} *****", affectedRows);
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationMod END *****");
		return affectedRows;
	}

	@Override
	public int executeSaveInsuranceQuotationVeh(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationVeh START *****");
		int affectedRows = 0;
		if (parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue(),
				PISDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationVeh - PARAMETERS OK ... EXECUTING *****");
			affectedRows = this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_QUOTATION_VEH.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceQuotationVeh - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_QUOTATION_VEH]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationVeh | Number of inserted rows: {} *****", affectedRows);
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceQuotationVeh END *****");
		return affectedRows;
	}

	@Override
	public Map<String, Object> executeGetPlansBBVA(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetPlansBBVA START *****");
		List<Map<String, Object>> response = null;

		if (parametersEvaluation(arguments, PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue(),
				PISDProperties.FIELD_INSURANCE_COMPANY_MODALITY_ID.getValue())) {
			try {
				LOGGER.info("***** PISDR012Impl - executeGetPlansBBVA PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForList(
						PISDProperties.QUERY_SELECT_INSRNC_PRD_MODALITY_BY_RIMAC_IDS.getValue(), arguments);
				response.forEach(map -> map.forEach((key, value) -> LOGGER.info(
						"[PISD.SELECT_INSRNC_PRD_MODALITY_BY_COMPANY_MODALITY_ID] Result -> Key {} with value: {}", key,
						value)));
			} catch (NoResultException ex) {
				LOGGER.info(
						"executeGetPlansBBVA - QUERY EMPTY RESULT [PISD.SELECT_INSRNC_PRD_MODALITY_BY_COMPANY_MODALITY_ID]");
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
			response = this.jdbcUtils.queryForMap(PISDProperties.ID_QUERY_GET_QUOTATION_DETAIL.getValue(),
					policyQuotaInternalId);
			response.forEach((key, value) -> LOGGER
					.info("[PISD.QUERY_FOR_GET_QUOTATION_SERVICE] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.info(
					"executeQueryForDetailQuotationService - QUERY EMPTY RESULT [PISD.QUERY_FOR_GET_QUOTATION_SERVICE]");
			this.addAdvice(PISDErrors.NON_EXISTENT_QUOTATION.getAdviceCode());
		}
		LOGGER.info("***** PISDR012Impl - executeQueryForDetailQuotationService END *****");
		return response;
	}

	@Override
	public void executeRegisterAdditionalQuotationVeh(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationVeh START *****");

		if (parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())) {

			LOGGER.info(
					"***** PISDR0012Impl - executeRegisterAdditionalQuotationVeh - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_UPDATE_QUOTATION_REGISTER_ADDITIONAL_VEH.getValue(), arguments);
		} else {

			LOGGER.info(
					"executeRegisterAdditionalQuotationVeh - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSURANCE_QUOTATION_VEH]");
		}

		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationVeh END *****");
	}

	@Override
	public Map<String, Object> executeRegisterAdditionalCompanyQuotaId(String companyQuotaId) {
		LOGGER.info("***** PISDR012Impl - executeRegisterAdditionalCompanyQuotaId START *****");
		Map<String, Object> response = null;
		try {
			response = this.jdbcUtils.queryForMap(
					PISDProperties.QUERY_SELECT_INSURANCE_COMPANY_BY_COMPANY_REGISTER.getValue(), companyQuotaId);

			response.forEach((key, value) -> LOGGER
					.info("[PISD.SELECT_INSURANCE_QUOTATION_REGISTER] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.info(
					"executeRegisterAdditionalCompanyQuotaId - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_QUOTATION_REGISTER]");
			this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
		}
		LOGGER.info("***** PISDR012Impl - executeRegisterAdditionalCompanyQuotaId END *****");
		return response;
	}

	@Override
	public void executeRegisterAdditionalQuotationBranch(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranch START *****");

		if (parametersEvaluation(arguments, PISDProperties.FIELD_LAST_CHANGE_BRANCH_ID.getValue())) {

			LOGGER.info(
					"***** PISDR0012Impl - executeRegisterAdditionalQuotationBranch - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSURANCE_QUOTATION_REGISTER.getValue(), arguments);
		} else {

			LOGGER.info(
					"executeRegisterAdditionalQuotationBranch - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSURANCE_QUOTATION]");
		}

		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranch END *****");
	}

	@Override
	public void executeRegisterAdditionalQuotationBranchMod(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranchMod START *****");

		if (parametersEvaluation(arguments, PISDProperties.FIELD_LAST_CHANGE_BRANCH_ID.getValue())) {

			LOGGER.info(
					"***** PISDR0012Impl - executeRegisterAdditionalQuotationBranchMod - PARAMETERS OK ... EXECUTING *****");
			this.jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSRNC_QUOTATION_MOD_REGISTER.getValue(), arguments);
		} else {

			LOGGER.info(
					"executeRegisterAdditionalQuotationBranchMod - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSRNC_QUOTATION_MOD]");
		}

		LOGGER.info("***** PISDR0012Impl - executeRegisterAdditionalQuotationBranchMod END *****");
	}

	@Override
	public Map<String, Object> executeGetRequiredFieldsForEmissionService(String policyQuotaInternalId) {
		LOGGER.info("***** PISDR012Impl - executeGetRequiredFieldsForEmissionService START *****");
		try {
			Map<String, Object> response = this.jdbcUtils.queryForMap(RBVDProperties.DYNAMIC_QUERY_FOR_INSURANCE_CONTRACT.getValue(), policyQuotaInternalId);
			response.forEach((key, value) ->
					LOGGER.info("[PISD.SELECT_REQUIRED_FIELDS_FOR_INSURANCE_CONTRACT] Result -> Key {} with value: {}", key, value));
			LOGGER.info("***** PISDR012Impl - executeGetRequiredFieldsForEmissionService END *****");
			return response;
		} catch (NoResultException ex) {
			LOGGER.debug("executeGetRequiredFieldsForEmissionService - NON_EXISTENT_QUOTATION: {}", RBVDErrors.NON_EXISTENT_QUOTATION.getMessage());
			return null;
		}
	}

	@Override
	public Map<String, Object> executeGetPaymentPeriod(String frequencyType) {
		LOGGER.info("***** PISDR012Impl - executeGetPaymentPeriod START *****");
		try {
			Map<String, Object> response = this.jdbcUtils.queryForMap(RBVDProperties.QUERY_SELECT_PAYMENT_PERIOD.getValue(), frequencyType);
			response.forEach((key, value) ->
					LOGGER.info("[PISD.SELECT_PAYMENT_PERIOD] Result -> Key {} with value: {}", key, value));
			LOGGER.info("***** PISDR012Impl - executeGetPaymentPeriod END *****");
			return response;
		} catch (NoResultException ex) {
			LOGGER.debug("executeGetPaymentPeriod - Incorrect frequency type");
			return null;
		}
	}

	@Override
	public int executeSaveContract(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveContract START *****");
		int affectedRows = 0;
		if (parametersEvaluation(arguments, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(),
				RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), RBVDProperties.FIELD_INSURANCE_PRODUCT_ID.getValue(),
				RBVDProperties.FIELD_INSURANCE_MODALITY_TYPE.getValue(), RBVDProperties.FIELD_INSURANCE_COMPANY_ID.getValue(), RBVDProperties.FIELD_POLICY_ID.getValue(),
				RBVDProperties.FIELD_INSURANCE_CONTRACT_START_DATE.getValue(), RBVDProperties.FIELD_INSURANCE_CONTRACT_END_DATE.getValue(),
				RBVDProperties.FIELD_CUSTOMER_ID.getValue(), RBVDProperties.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE.getValue(),
				RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), RBVDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveContract - PARAMETERS OK ... EXECUTING *****");
			try {
				affectedRows = this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_CONTRACT.getValue(),
						arguments);
			} catch (NoResultException ex) {
				LOGGER.info("***** PISDR012Impl - executeSaveContract - Database exception: {} *****", ex.getMessage());
				affectedRows = -1;
			}
		} else {
			LOGGER.info("executeSaveContract - MISSING MANDATORY PARAMETERS [PISD.INSERT_CONTRACT]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveContract | Number of inserted rows: {} *****", affectedRows);
		LOGGER.info("***** PISDR012Impl - executeSaveContract END *****");
		return affectedRows;
	}

	@Override
	public int executeSaveContractEndoserment(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveContractEndoserment START *****");
		int affectedRows = 0;
		if (parametersEvaluation(arguments, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(),
				RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(),
				RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveContractEndoserment - PARAMETERS OK ... EXECUTING *****");
			try {
				affectedRows = this.jdbcUtils.update(RBVDProperties.QUERY_INSERT_POLICY_ENDORSEMENT.getValue(),
						arguments);
			} catch (NoResultException ex) {

				LOGGER.info("***** PISDR012Impl - executeSaveContractEndoserment - Database exception: {} *****", ex.getMessage());
				affectedRows = -1;
			}
		} else {

			LOGGER.info("executeSaveContractEndoserment - MISSING MANDATORY PARAMETERS [PISD.INSERT_CONTRACT]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveContractEndoserment | Number of inserted rows: {} *****", affectedRows);
		LOGGER.info("***** PISDR012Impl - executeSaveContractEndoserment END *****");
		return affectedRows;
	}

	@Override
	public int[] executeSaveReceipts(Map<String, Object>[] receipts) {
		LOGGER.info("***** PISDR012Impl - executeSaveFirstReceipt START *****");
		int[] affectedRows = null;
		if(Arrays.stream(receipts).
				allMatch(map -> parametersEvaluation(map, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(),
						RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(),
						RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue(),RBVDProperties.FIELD_USER_AUDIT_ID.getValue()))) {
			LOGGER.info("***** PISDR012Impl - executeSaveFirstReceipt - PARAMETERS OK ... EXECUTING *****");
			try {
				affectedRows = this.jdbcUtils.batchUpdate(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(),
						receipts);
			} catch (NoResultException ex) {
				LOGGER.info("***** PISDR012Impl - executeSaveFirstReceipt - Database exception: {} *****", ex.getMessage());
				affectedRows = new int[0];
			}
		} else {
			LOGGER.info("executeSaveFirstReceipt - MISSING MANDATORY PARAMETERS [PISD.INSERT_CTR_RECEIPTS]");
		}
		LOGGER.info("***** PISDR012Impl - executeSaveFirstReceipt | Number of inserted rows: {} *****", Objects.nonNull(affectedRows) ? affectedRows.length : null);
		LOGGER.info("***** PISDR012Impl - executeSaveFirstReceipt END *****");
		return affectedRows;
	}

	@Override
	public int executeSaveContractMove(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeSaveContractMove START *****");
		int affectedRows = 0;
		if(parametersEvaluation(arguments, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(),
				RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(),
				RBVDProperties.FIELD_POLICY_MOVEMENT_NUMBER.getValue(), RBVDProperties.FIELD_USER_AUDIT_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveContractMove - PARAMETERS OK ... EXECUTING *****");
			try {
				affectedRows = this.jdbcUtils.update(RBVDProperties.QUERY_INSERT_INSRNC_CONTRACT_MOV.getValue(), arguments);
			} catch (NoResultException ex) {
				LOGGER.info("***** PISDR012Impl - executeSaveContractMove - Database exception: {} *****", ex.getMessage());
				affectedRows = -1;
			}
		} else {
			LOGGER.info("executeSaveContractMove - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSRNC_CONTRACT_MOV]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveContractMove | Number of inserted rows: {} *****", affectedRows);
		LOGGER.info("***** PISDR012Impl - executeSaveContractMove END *****");
		return affectedRows;
	}

	@Override
	public Map<String, Object> executeGetRolesByProductAndModality(BigDecimal productId, String modalityType) {
		LOGGER.info("***** PISDR012Impl - executeGetRolesByProductAndModality START *****");
		List<Map<String, Object>> response = null;
		if(Objects.nonNull(productId) && Objects.nonNull(modalityType)) {
			try {
				LOGGER.info("***** PISDR012Impl - executeGetRolesByProductAndModality PARAMETERS OK ... EXECUTING *****");
				response = this.jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_INSRNC_ROLE_MODALITY.getValue(), productId, modalityType);
				response.forEach(map -> map.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSRNC_ROLE_MODALITY] Result -> Key {} with value: {}", key, value)));
			} catch (NoResultException ex) {
				LOGGER.info("executeGetRolesByProductAndModality - NO ROLES ERROR: {}", RBVDErrors.NO_ROLES.getMessage());
			}
		} else {
			LOGGER.info("executeGetRolesByProductAndModality - MISSING MANDATORY PARAMETERS [PISD.SELECT_INSRNC_ROLE_MODALITY]");
		}
		LOGGER.info("***** PISDR012Impl - executeGetRolesByProductAndModality END *****");
		return buildResult(response);
	}

	@Override
	public int[] executeSaveParticipants(Map<String, Object>[] participantsMap) {
		LOGGER.info("***** PISDR012Impl - executeSaveParticipants START *****");
		int[] affectedRows = null;
		if(Arrays.stream(participantsMap).
				allMatch(map -> parametersEvaluation(map, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(),
						RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(),
						RBVDProperties.FIELD_PARTICIPANT_ROLE_ID.getValue(), RBVDProperties.FIELD_PARTY_ORDER_NUMBER.getValue(),
						RBVDProperties.FIELD_USER_AUDIT_ID.getValue()))) {
			LOGGER.info("***** PISDR012Impl - executeSaveParticipants - PARAMETERS OK ... EXECUTING *****");
			try {
				affectedRows = this.jdbcUtils.batchUpdate(RBVDProperties.QUERY_INSERT_INSRNC_CTR_PARTICIPANT.getValue(), participantsMap);
			} catch (NoResultException ex) {
				LOGGER.info("***** PISDR012Impl - executeSaveParticipants - Database exception: {} *****", ex.getMessage());
				affectedRows = new int[0];
			}
		} else {
			LOGGER.info("executeSaveParticipants - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSRNC_CTR_PARTICIPANT]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveParticipants | Number of inserted rows: {} *****", Objects.nonNull(affectedRows) ? affectedRows.length : null);
		LOGGER.info("***** PISDR012Impl - executeSaveParticipants END *****");
		return affectedRows;
	}

	@Override
	public Map<String, Object> executeGetPolicyContract(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetPolicyContract START *****");

		Map<String, Object> response = null;
		if (parametersEvaluation(arguments, PISDProperties.FIELD_POLICY_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeGetPolicyContract - PARAMETERS OK ... EXECUTING *****");
			try {
				arguments.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_CONTRACT] Result -> Key {} with value: {}", key, value));
				LOGGER.info("PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue() ==> {}", PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue());
				response = this.jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue(), arguments);
				
			} catch (NoResultException ex) {
				LOGGER.debug("executeSaveParticipants - MISSING MANDATORY PARAMETERS [PISD.SELECT_INSURANCE_CONTRACT]");
				this.addAdvice(PISDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		} else {
			LOGGER.info("executeGetPolicyContract - MISSING MANDATORY PARAMETERS [PISD.SELECT_INSURANCE_CONTRACT]");
		}
		LOGGER.info("***** PISDR012Impl - executeGetPolicyContract END *****");
		return response;
	}

	@Override
	public boolean executeUpdateInsuranceContract(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeUpdateInsuranceContract START *****");
		int result;
		arguments.forEach((key, value) -> LOGGER.info("[PISD.UPDATE_INSURANCE_CONTRACT] Result -> Key1 {} with value: {}", key, value));
		if (parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), 
		PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(),PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())) {
			arguments.forEach((key, value) -> LOGGER.info("[PISD.UPDATE_INSURANCE_CONTRACT] Result -> Key2 {} with value: {}", key, value));
			LOGGER.info("***** PISDR0012Impl - executeUpdateInsuranceContract - PARAMETERS OK ... EXECUTING *****");
			result = this.jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_STATUS.getValue(), arguments);
			LOGGER.info("[PISD.QUERY_UPDATE_INSURANCE_CONTRACT_STATUS] Result -> {}", result);
			if(result==0)
				return false;
			else{
				LOGGER.info("***** PISDR0012Impl - executeUpdateInsuranceContract END *****");
				return true;
			}
			
		} else {

			LOGGER.info(
					"executeUpdateInsuranceContract - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSURANCE_CONTRACT]");
					return false;
		}
	
	}

	@Override
	public boolean executeUpdatePaymentSchedule(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeUpdatePaymentSchedule START *****");
		int result;
		arguments.forEach((key, value) -> LOGGER.info("[PISD.UPDATE_CTR_RECEIPTS] Result -> Key1 {} with value: {}", key, value));
		if (parametersEvaluation(arguments, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), 
		RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(),RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(),
		RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue())) {
			arguments.forEach((key, value) -> LOGGER.info("[PISD.UPDATE_CTR_RECEIPTS] Result -> Key2 {} with value: {}", key, value));
			LOGGER.info("***** PISDR0012Impl - executeUpdatePaymentSchedule - PARAMETERS OK ... EXECUTING *****");
			result = this.jdbcUtils.update(RBVDProperties.QUERY_UPDATE_INSURANCE_CTR_RECEIPTS.getValue(), arguments);
			LOGGER.info("[PISD.QUERY_UPDATE_INSURANCE_CTR_RECEIPTS] Result -> {}", result);
			if(result==0)
				return false;
			else{
				LOGGER.info("***** PISDR0012Impl - executeUpdatePaymentSchedule END *****");
				return true;
			}
			
		} else {

			LOGGER.info(
					"executeUpdatePaymentSchedule - MISSING MANDATORY PARAMETERS [PISD.UPDATE_CTR_RECEIPTS]");
					return false;
		}
	
	}

	@Override
	public Map<String, Object> executeQueryForGerInsuranceCompanyQuotaId(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeQueryForGerInsuranceCompanyQuotaId START *****");
		Map<String, Object> response = null;

		try {
			response = this.jdbcUtils.queryForMap(
					PISDProperties.ID_QUERY_FOR_GET_INSURANCE_COMPANY_QUOTA_ID.getValue(), arguments);
			response.forEach((key, value) -> LOGGER
					.info("[PISD.QUERY_FOR_GET_INSURANCE_COMPANY_QUOTA_ID] Result -> Key {} with value: {}", key, value));
		} catch (NoResultException ex) {
			LOGGER.info(
					"executeQueryForGerInsuranceCompanyQuotaId - QUERY EMPTY RESULT [PISD.QUERY_FOR_GET_INSURANCE_COMPANY_QUOTA_ID]");
			this.addAdvice(PISDErrors.QUERY_EMPTY_POLICY_QUOTA_INTERNAL_ID.getAdviceCode());
		}

		LOGGER.info("***** PISDR012Impl - executeQueryForGerInsuranceCompanyQuotaId END *****");
		return response;
	}

	@Override
	public Map<String, Object> executeGetInsuranceContractStartDate(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeGetInsuranceContractStartDate START *****");
		Map<String, Object> response = null;
		if (parametersEvaluation(arguments, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), 
		RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(),RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())) {
			try{
				response = this.jdbcUtils.queryForMap(RBVDProperties.QUERY_SELECT_INSURANCE_CONTRACT_START_DATE.getValue(), arguments);
				response.forEach((key, value) -> LOGGER.info("[PISD.QUERY_SELECT_INSURANCE_CONTRACT_START_DATE] Result -> Key {} with value: {}", key, value));
				LOGGER.info("***** PISDR012Impl - executeGetInsuranceContractStartDate END *****");
				return response;
			}
			catch (NoResultException ex){
				LOGGER.info(
					"executeGetInsuranceContractStartDate - QUERY EMPTY RESULT [PISD.QUERY_SELECT_INSURANCE_CONTRACT_START_DATE]");
			this.addAdvice(RBVDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}	
		} else {

			LOGGER.info(
					"executeGetInsuranceContractStartDate - MISSING MANDATORY PARAMETERS [PISD.QUERY_SELECT_INSURANCE_CONTRACT_START_DATE]");
					return response;
		}
		return response;
	}

	@Override
	public Map<String, Object> executeGetInsuranceContractStatus() {
		LOGGER.info("***** PISDR012Impl - executeGetInsuranceContractStatus START *****");
		List<Map<String, Object>> response = null;
		
			try {
				response = this.jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_INSURANCE_CONTRACT_DOCUMENT_STATUS.getValue());
				response.forEach(map -> map.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSRNC_ROLE_MODALITY] Result -> Key {} with value: {}", key, value)));
			} catch (NoResultException ex) {
				LOGGER.info("executeGetInsuranceContractStatus - NO QUERY_EMPTY_RESULT ERROR");
				this.addAdvice(RBVDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}

		LOGGER.info("***** PISDR012Impl - executeGetInsuranceContractStatus END *****");
		return buildResult(response);
	}
	
	@Override
	public Boolean executeUpdateInsuranceContractDocument(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR0012Impl - executeUpdateInsuranceContractDocument START *****");
		int result;
		if (parametersEvaluation(arguments, RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), 
		RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(),RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())) {
			arguments.forEach((key, value) -> LOGGER.info("[PISD.UPDATE_INSURANCE_CONTRACT_DOCUMENT_STATUS] Result -> Key2 {} with value: {}", key, value));
			LOGGER.info("***** PISDR0012Impl - executeUpdateInsuranceContractDocument - PARAMETERS OK ... EXECUTING *****");
			result = this.jdbcUtils.update(RBVDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_DOCUMENT_STATUS.getValue(), arguments);
			LOGGER.info("[PISD.UPDATE_INSURANCE_CONTRACT_DOCUMENT_STATUS] Result -> {}", result);
			if(result>0)
				return true;
			else{
				LOGGER.info("***** PISDR0012Impl - executeUpdateInsuranceContractDocument END *****");
				return false;
			}
		} else {
			LOGGER.info(
					"executeUpdateInsuranceContractDocument - MISSING MANDATORY PARAMETERS [PISD.UPDATE_INSURANCE_CONTRACT]");
					return false;
		}
	}
	
	@Override
	public Map<String, Object> executeGetOffer(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR012Impl - executeGetOffer START *****");

		List<Map<String, Object>> response = null;
		if (parametersEvaluation(arguments, RBVDProperties.FIELD_PRODUCT_OFFER_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeGetOffer - PARAMETERS OK ... EXECUTING *****");
			try {
				arguments.forEach((key, value) -> LOGGER.info("[PISD.SELECT_OFFER] Result -> Key {} with value: {}", key, value));
				LOGGER.info("PISDProperties.QUERY_SELECT_OFFER.getValue() ==> {}", RBVDProperties.QUERY_SELECT_OFFER.getValue());
				response = this.jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_OFFER.getValue(), arguments);
				response.forEach(map -> map.forEach((key, value) -> LOGGER.info("[PISD.SELECT_OFFER] Result -> Key {} with value: {}", key, value)));
			} catch (NoResultException ex) {
				LOGGER.debug("executeSaveParticipants - MISSING MANDATORY PARAMETERS [PISD.SELECT_OFFER]");
				this.addAdvice(RBVDErrors.QUERY_EMPTY_RESULT.getAdviceCode());
			}
		} else {
			LOGGER.info("executeGetOffer - MISSING MANDATORY PARAMETERS [PISD.SELECT_OFFER]");
		}
		LOGGER.info("***** PISDR012Impl - executeGetOffer END *****");
		return buildResult(response);
	}

	private boolean parametersEvaluation(Map<String, Object> arguments, String... keys) {
		return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
	}

	private Map<String, Object> buildResult(List<Map<String, Object>> response) {
		Map<String, Object> result = new HashMap<>();
		result.put(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue(), response);
		return result;
	}

	//Inicio Open Market
	public int executeUpdate(String nameProp, Map<String, Object> parameters){
		int response = 0;

		if(parameters != null && !parameters.isEmpty()){
			parameters.forEach((key, value) -> LOGGER.info("[" + nameProp + "] Result -> Key2 {} with value: {}", key, value));
			response = this.jdbcUtils.update(nameProp, parameters);
		}else{
			LOGGER.info("executeUpdate - MISSING MANDATORY PARAMETERS [{}]", nameProp);
		}

		return response;
	}
	//Fin Open Market
    

	//InserT Insurance Type
	@Override
	public int executeSaveInsuranceType(Map<String, Object> arguments){
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceType START *****");
		int affectedRows = 0;
		if (parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_TYPE_ID.getValue(),
				PISDProperties.FIELD_INSURANCE_TYPE_NAME.getValue(),
				PISDProperties.FIELD_REGISTRY_SITUATION_TYPE.getValue())) {
			LOGGER.info("***** PISDR012Impl - executeSaveInsuranceType - PARAMETERS OK ... EXECUTING *****");
			affectedRows = this.jdbcUtils.update(PISDProperties.QUERY_INSERT_INSURANCE_TYPE.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceType - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_QUOTATION_VEH]");
		}

		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceType | Number of inserted rows: {} *****", affectedRows);
		LOGGER.info("***** PISDR012Impl - executeSaveInsuranceType END *****");
		return affectedRows;
	}
    //Select Insurance Type
	
	@Override
	public Map<String, Object> executeSelectInsuranceType(Map<String, Object>  arguments){
		LOGGER.info("***** PISDR012Impl - executeSelectInsuranceType START *****");
		Map<String, Object> response = null;
		try {
			if (parametersEvaluation(arguments, PISDProperties.FIELD_INSURANCE_TYPE_ID.getValue())) {
			LOGGER.info("***** PISDR012Impl -  - PARAMETERS OK ... EXECUTING *****");
			response = this.jdbcUtils.queryForMap(
					PISDProperties.QUERY_SELECT_INSURANCE_TYPE.getValue(), arguments);
			response.forEach((key, value) -> LOGGER
					.info("[PISD.QUERY_SELECT_INSURANCE_TYPE] Result -> Key {} with value: {}", key, value));
		}   else{
			LOGGER.info(
					"executeSelectInsuranceType - PARAMETERS NULL");
			}
			
		} catch (NoResultException ex) {
			LOGGER.info(
					"executeSelectInsuranceType - QUERY EMPTY RESULT [PISD.QUERY_FOR_GET_INSURANCE_COMPANY_QUOTA_ID]");
		}


		LOGGER.info("***** PISDR012Impl - executeSelectInsuranceType END *****");
		return response;
	}

	public void rollBack(){
		Map<String, Object> response = null;
		this.jdbcUtils.update(PISDProperties.QUERY_ROLLBACK.getValue(),response);
	}

}