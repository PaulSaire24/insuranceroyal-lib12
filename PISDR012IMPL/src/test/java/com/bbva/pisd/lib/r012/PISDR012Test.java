package com.bbva.pisd.lib.r012;

import com.bbva.apx.exception.db.NoResultException;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;

import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.insurance.utils.PISDErrors;
import com.bbva.pisd.dto.insurance.utils.PISDProperties;
import com.bbva.pisd.lib.r012.impl.PISDR012Impl;

import com.bbva.rbvd.dto.insrncsale.utils.RBVDProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PISDR012-app.xml",
		"classpath:/META-INF/spring/PISDR012-app-test.xml",
		"classpath:/META-INF/spring/PISDR012-arc.xml",
		"classpath:/META-INF/spring/PISDR012-arc-test.xml" })
public class PISDR012Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR012Test.class);
	private static final String MESSAGE = "No se encontró data";

	private final PISDR012Impl pisdr012 = new PISDR012Impl();
	private JdbcUtils jdbcUtils;

	@Mock
	private Map<String, Object> argumentsForInsuranceProduct;
	@Mock
	private Map<String, Object> argumentsForGetProductId;
	@Mock
	private Map<String, Object> argumentsForInsuranceProductModalityOrConsiderations;
	@Mock
	private Map<String, Object> argumentsForSaveSimulation;
	@Mock
	private Map<String, Object> argumentsForSaveSimulationProduct;
	@Mock
	private Map<String, Object> argumentsForSaveSimulationVehicle;
	@Mock
	private Map<String, Object> argumentsForSaveSimulationQuotation;
	@Mock
	private Map<String, Object> argumentsForSaveSimulationQuotationMod;
	@Mock
	private Map<String, Object> argumentsForSaveSimulationQuotationVeh;
	@Mock
	private Map<String, Object> argumentsForRegisterQuotationVeh;
	@Mock
	private Map<String, Object> argumentsForSaveContract;
	@Mock
	private Map<String, Object> argumentsForSaveFirstReceipt;
	@Mock
	private Map<String, Object> argumentsForSaveContractMov;
	@Mock
	private Map<String, Object> argumentsForSaveCtrParticipant;
	@Mock
	private Map<String, Object> argumentsForInsrncModalityByRimacIds;
	@Mock
	private Map<String, Object> argumentsForGetPolicyContract;
	@Mock
	private Map<String, Object> argumentsForUpdateInsuranceContract;
	@Mock
	private Map<String, Object> argumentsForGerInsuranceCompanyQuotaId;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ThreadContext.set(new Context());

		jdbcUtils = mock(JdbcUtils.class);
		pisdr012.setJdbcUtils(jdbcUtils);

	}

	@Test
	public void executeInsuranceProductOK() {
		LOGGER.info("PISDR012Test - Executing executeInsuranceProductOK...");
		when(argumentsForInsuranceProduct.get(PISDProperties.FILTER_INSURANCE_PRODUCT_TYPE.getValue())).thenReturn("827");
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSRC_PRODUCT.getValue(), argumentsForInsuranceProduct)).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeInsuranceProduct(argumentsForInsuranceProduct);
		assertNotNull(validation);
	}

	@Test
	public void executeInsuranceProductWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeInsuranceProductWithParametersEvaluationFalse...");
		Map<String, Object> validation = pisdr012.executeInsuranceProduct(argumentsForInsuranceProduct);
		assertNull(validation);
	}

	@Test
	public void executeInsuranceProductWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeInsuranceProductWithNoResultException...");
		when(argumentsForInsuranceProduct.get(PISDProperties.FILTER_INSURANCE_PRODUCT_TYPE.getValue())).thenReturn("827");
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSRC_PRODUCT.getValue(), argumentsForInsuranceProduct)).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeInsuranceProduct(argumentsForInsuranceProduct);
		assertNull(validation);
	}

	@Test
	public void executeGetProductIdForRimacOK() {
		LOGGER.info("PISDR012Test - Executing executeGetProductIdForRimacOK...");
		when(argumentsForGetProductId.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_RISK_BUSINESS_ID.getValue())).thenReturn(912L);
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSRC_PRODUCT_FOR_RIMAC.getValue(), argumentsForGetProductId)).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeGetProductIdForRimac(argumentsForGetProductId);
		assertNotNull(validation);
	}

	@Test
	public void executeGetProductIdForRimacWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeGetProductIdForRimacWithParametersEvaluationFalse...");
		Map<String, Object> validation = pisdr012.executeGetProductIdForRimac(argumentsForGetProductId);
		assertNull(validation);
	}

	@Test
	public void executeGetProductIdForRimacWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetProductIdForRimacWithNoResultException...");
		when(argumentsForGetProductId.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_RISK_BUSINESS_ID.getValue())).thenReturn(912L);
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSRC_PRODUCT_FOR_RIMAC.getValue(), argumentsForGetProductId)).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeGetProductIdForRimac(argumentsForGetProductId);
		assertNull(validation);
	}

	@Test
	public void executeInsuranceProductModalityOK() {
		LOGGER.info("PISDR012Test - Executing executeInsuranceProductModalityOK...");
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(356L);
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn(new ArrayList<>());
		when(jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRNC_PRD_MODALITY.getValue(), argumentsForInsuranceProductModalityOrConsiderations)).
				thenReturn(new ArrayList<>());
		Map<String, Object> validation = pisdr012.executeInsuranceProductModality(argumentsForInsuranceProductModalityOrConsiderations);
		assertNotNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeInsuranceProductModalityWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeInsuranceProductModalityWithParametersEvaluationFalse...");
		Map<String, Object> validation = pisdr012.executeInsuranceProductModality(argumentsForInsuranceProductModalityOrConsiderations);
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeInsuranceProductModalityWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeInsuranceProductModalityWithNoResultException...");
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(356L);
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn(new ArrayList<>());
		when(jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRNC_PRD_MODALITY.getValue(), argumentsForInsuranceProductModalityOrConsiderations)).
				thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeInsuranceProductModality(argumentsForInsuranceProductModalityOrConsiderations);
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetConsiderationsOK() {
		LOGGER.info("PISDR012Test - Executing executeGetConsiderationsOK...");
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(356L);
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn(new ArrayList<>());
		when(jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_CONSIDERATIONS.getValue(), argumentsForInsuranceProductModalityOrConsiderations)).
				thenReturn(new ArrayList<>());
		Map<String, Object> validation = pisdr012.executeGetConsiderations(argumentsForInsuranceProductModalityOrConsiderations);
		assertNotNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetConsiderationsWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeGetConsiderationsWithParametersEvaluationFalse...");
		Map<String, Object> validation = pisdr012.executeGetConsiderations(argumentsForInsuranceProductModalityOrConsiderations);
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetConsiderationsWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetConsiderationsWithNoResultException...");
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(356L);
		when(argumentsForInsuranceProductModalityOrConsiderations.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn(new ArrayList<>());
		when(jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_CONSIDERATIONS.getValue(), argumentsForInsuranceProductModalityOrConsiderations)).
				thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeGetConsiderations(argumentsForInsuranceProductModalityOrConsiderations);
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetSimulationIdOK() {
		LOGGER.info("PISDR012Test - Executing executeGetSimulationIdOK...");
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_SIMULATION_ID.getValue())).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeGetSimulationId();
		assertNotNull(validation);
	}

	@Test
	public void executeSaveSimulationOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveSimulationOK...");
		when(argumentsForSaveSimulation.get(PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue())).thenReturn(new BigDecimal("14"));
		when(argumentsForSaveSimulation.get(PISDProperties.FIELD_CUSTOMER_ID.getValue())).thenReturn("something");
		when(argumentsForSaveSimulation.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user01");
		boolean validation = pisdr012.executeSaveSimulation(argumentsForSaveSimulation);
		assertTrue(validation);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveSimulationWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveSimulationWithParametersEvaluationFalse...");
		boolean validation = pisdr012.executeSaveSimulation(argumentsForSaveSimulation);
		assertFalse(validation);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveSimulationProductOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveSimulationProductOK...");
		when(argumentsForSaveSimulationProduct.get(PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue())).thenReturn(new BigDecimal("1"));
		when(argumentsForSaveSimulationProduct.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(new BigDecimal("1"));
		when(argumentsForSaveSimulationProduct.get(PISDProperties.FIELD_SALE_CHANNEL_ID.getValue())).thenReturn("something");
		when(argumentsForSaveSimulationProduct.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user01");
		pisdr012.executeSaveSimulationProduct(argumentsForSaveSimulationProduct);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveSimulationProductWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveSimulationProductWithParametersEvaluationFalse...");
		pisdr012.executeSaveSimulationProduct(argumentsForSaveSimulationProduct);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveSimulationVehicleOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveSimulationVehicleOK...");
		when(argumentsForSaveSimulationVehicle.get(PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue())).thenReturn(new BigDecimal("1"));
		when(argumentsForSaveSimulationVehicle.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(new BigDecimal("1"));
		when(argumentsForSaveSimulationVehicle.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("something");

		pisdr012.executeSaveSimulationVehicle(argumentsForSaveSimulationVehicle);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveSimulationVehicleWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveSimulationVehicleWithParametersEvaluationFalse...");
		pisdr012.executeSaveSimulationVehicle(argumentsForSaveSimulationVehicle);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeGetInsuranceQuotationOK() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceQuotationOK...");

		when(jdbcUtils.queryForList(anyString(), anyString())).thenReturn(new ArrayList<>());
		Map<String, Object> validation = pisdr012.executeGetInsuranceQuotation("66666");

		assertNotNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetInsuranceQuotationWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceQuotationWithNoResultException...");

		when(jdbcUtils.queryForList(anyString(), anyString())).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeGetInsuranceQuotation("888888");

		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
		assertEquals(PISDErrors.ERROR_NO_RESULT_JDBC_INSRC_QUOTATION.getAdviceCode(), this.pisdr012.getAdviceList().get(0).getCode());
	}

	@Test
	public void executeGetInsuranceSimulationIdAndExpiredDateOK() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceSimulationIdAndExpiredDateOK...");

		when(jdbcUtils.queryForMap(anyString(), anyString())).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeGetInsuranceSimulationIdAndExpiredDate("quotationId");
		assertNotNull(validation);
	}

	@Test
	public void executeGetInsuranceSimulationIdAndExpiredDateWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceSimulationIdAndExpiredDateWithNoResultException...");

		when(jdbcUtils.queryForMap(anyString(), anyString())).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeGetInsuranceSimulationIdAndExpiredDate("34213");
		assertNull(validation);
	}

	@Test
	public void executeSaveInsuranceQuotationOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationOK...");
		when(argumentsForSaveSimulationQuotation.get(PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())).thenReturn("policy_value");
		when(argumentsForSaveSimulationQuotation.get(PISDProperties.FIELD_INSURANCE_SIMULATION_ID.getValue())).thenReturn(new BigDecimal("827"));
		when(argumentsForSaveSimulationQuotation.get(PISDProperties.FIELD_INSURANCE_COMPANY_QUOTA_ID.getValue())).thenReturn("aaaaaa");
		when(argumentsForSaveSimulationQuotation.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user01");

		pisdr012.executeSaveInsuranceQuotation(argumentsForSaveSimulationQuotation);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveInsuranceQuotationWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationWithParametersEvaluationFalse...");
		pisdr012.executeSaveInsuranceQuotation(argumentsForSaveSimulationQuotation);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveInsuranceQuotationModOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationModOK...");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())).thenReturn("aaaaa");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(new BigDecimal("827"));
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn("modality");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_SALE_CHANNEL_ID.getValue())).thenReturn("channelCode");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user01");

		pisdr012.executeSaveInsuranceQuotationMod(argumentsForSaveSimulationQuotationMod);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveInsuranceQuotationModWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationModWithParametersEvaluationFalse...");
		pisdr012.executeSaveInsuranceQuotationMod(argumentsForSaveSimulationQuotationMod);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveInsuranceQuotationVehOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationVehOK...");
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())).thenReturn("aaaaa");
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(new BigDecimal("827"));
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn("modality");
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user01");

		pisdr012.executeSaveInsuranceQuotationVeh(argumentsForSaveSimulationQuotationVeh);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveInsuranceQuotationVehWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationVehWithParametersEvaluationFalse...");
		pisdr012.executeSaveInsuranceQuotationVeh(argumentsForSaveSimulationQuotationVeh);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeGetCompanyDescByIdOK() {
		LOGGER.info("PISDR012Test - Executing executeGetCompanyDescByIdOK...");
		when(jdbcUtils.queryForMap(anyString(), any(BigDecimal.class))).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeGetCompanyDescById(BigDecimal.valueOf(1));
		assertNotNull(validation);
	}

	@Test
	public void executeGetCompanyDescByIdWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetCompanyDescByIdWithNoResultException...");
		when(jdbcUtils.queryForMap(anyString(), any(BigDecimal.class))).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeGetCompanyDescById(BigDecimal.valueOf(1));
		assertNull(validation);
	}

	@Test
	public void executeGetPlansBBVAWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeGetPlansBBVAWithParametersEvaluationFalse...");
		Map<String, Object> validation = pisdr012.executeGetPlansBBVA(argumentsForInsrncModalityByRimacIds);
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetPlansBBVAWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetPlansBBVAWithNoResultException...");

		when(argumentsForInsrncModalityByRimacIds.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(1L);
		when(argumentsForInsrncModalityByRimacIds.get(PISDProperties.FIELD_INSURANCE_COMPANY_MODALITY_ID.getValue())).thenReturn(new ArrayList<>());
		when(jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRNC_PRD_MODALITY_BY_RIMAC_IDS.getValue(), argumentsForInsrncModalityByRimacIds)).thenThrow(new NoResultException(MESSAGE));

		Map<String, Object> validation = pisdr012.executeGetPlansBBVA(argumentsForInsrncModalityByRimacIds);

		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
		assertEquals("PISD00120000", this.pisdr012.getAdviceList().get(0).getCode());
	}

	@Test
	public void executeGetPlansBBVA_OK() {
		LOGGER.info("PISDR012Test - Executing executeGetPlansBBVAWithNoResultException...");

		when(argumentsForInsrncModalityByRimacIds.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(1L);
		when(argumentsForInsrncModalityByRimacIds.get(PISDProperties.FIELD_INSURANCE_COMPANY_MODALITY_ID.getValue())).thenReturn(new ArrayList<>());
		when(jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSRNC_PRD_MODALITY_BY_RIMAC_IDS.getValue(), argumentsForInsrncModalityByRimacIds)).thenReturn(new ArrayList<>());

		Map<String, Object> validation = pisdr012.executeGetPlansBBVA(argumentsForInsrncModalityByRimacIds);

		assertNotNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeQueryForDetailQuotationServiceOK() {
		LOGGER.info("PISDR012Test - Executing executeQueryForDetailQuotationServiceOK...");
		when(jdbcUtils.queryForMap(anyString(), anyString())).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeQueryForDetailQuotationService("policyQuotaInternal");
		assertNotNull(validation);
	}

	@Test
	public void executeQueryForDetailQuotationServiceWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeQueryForDetailQuotationServiceWithNoResultException...");
		when(jdbcUtils.queryForMap(anyString(), anyString())).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeQueryForDetailQuotationService("policyQuotaInternal");
		assertNull(validation);
		assertEquals("PISD00120026", this.pisdr012.getAdviceList().get(0).getCode());
	}

	@Test
	public void executeRegisterAdditionalInsuranceQuotationVehOK() {
		LOGGER.info("PISDR0012Test - Executing executeRegisterAdditionalInsuranceQuotationVehOK...");
		when(argumentsForRegisterQuotationVeh.get(PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())).thenReturn("aaaaa");;

		pisdr012.executeRegisterAdditionalQuotationVeh(argumentsForRegisterQuotationVeh);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeRegisterAdditionalInsuranceQuotationVehWithParametersEvaluationFalse() {
		LOGGER.info("PISDR0012Test - Executing executeRegisterAdditionalInsuranceQuotationVehWithParametersEvaluationFalse...");
		pisdr012.executeRegisterAdditionalQuotationVeh(argumentsForRegisterQuotationVeh);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeGetRegisterAdditionalByIdOK() {
		LOGGER.info("PISDR012Test - Executing executeGetRegisterAdditionalByIdOK...");
		when(jdbcUtils.queryForMap(anyString(),  anyString())).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeRegisterAdditionalCompanyQuotaId("companyQuotaId");
		assertNotNull(validation);
	}

	@Test
	public void executeGetRegisterAdditionalByIdWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetRegisterAdditionalByIdWithNoResultException...");
		when(jdbcUtils.queryForMap(anyString(), anyString())).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeRegisterAdditionalCompanyQuotaId("12345");
		assertNull(validation);
	}

	@Test
	public void executeRegisterAdditionalInsuranceQuotationBranchOK() {
		LOGGER.info("PISDR0012Test - Executing executeRegisterAdditionalInsuranceQuotationBranchOK...");
		when(argumentsForRegisterQuotationVeh.get(PISDProperties.FIELD_LAST_CHANGE_BRANCH_ID.getValue())).thenReturn("aaaa");;

		pisdr012.executeRegisterAdditionalQuotationBranch(argumentsForRegisterQuotationVeh);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeRegisterAdditionalInsuranceQuotationBranchWithParametersEvaluationFalse() {
		LOGGER.info("PISDR0012Test - Executing executeRegisterAdditionalInsuranceQuotationBranchWithParametersEvaluationFalse...");
		pisdr012.executeRegisterAdditionalQuotationBranch(argumentsForRegisterQuotationVeh);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeRegisterAdditionalInsuranceQuotationBranchModOK() {
		LOGGER.info("PISDR0012Test - Executing executeRegisterAdditionalInsuranceQuotationBranchModOK...");
		when(argumentsForRegisterQuotationVeh.get(PISDProperties.FIELD_LAST_CHANGE_BRANCH_ID.getValue())).thenReturn("aaaa");;

		pisdr012.executeRegisterAdditionalQuotationBranchMod(argumentsForRegisterQuotationVeh);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
	}

	@Test
	public void executeRegisterAdditionalInsuranceQuotationBranchModWithParametersEvaluationFalse() {
		LOGGER.info("PISDR0012Test - Executing executeRegisterAdditionalInsuranceQuotationBranchModWithParametersEvaluationFalse...");
		pisdr012.executeRegisterAdditionalQuotationBranchMod(argumentsForRegisterQuotationVeh);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
	}

	@Test
	public void executeSaveContractOK() {
		LOGGER.info("PISDR0012Test - Executing executeSaveContractOK...");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("contract_entity");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("contract_branch");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("contract_int");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn("insrce_product");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn("insrce_modality");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_COMPANY_ID.getValue())).thenReturn("insrce_company");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_POLICY_ID.getValue())).thenReturn("policy_id");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_START_DATE.getValue())).thenReturn("insrce_contract_start");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_END_DATE.getValue())).thenReturn("insrce_contract_end");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_CUSTOMER_ID.getValue())).thenReturn("customer_id");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE.getValue())).thenReturn("contract_status");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user_audit");

		when(this.jdbcUtils.update(anyString(), anyMap())).thenReturn(1);

		int validation = pisdr012.executeSaveContract(argumentsForSaveContract);

		verify(this.jdbcUtils, times(1)).update(anyString(), anyMap());
		assertEquals(1, validation);
	}

	@Test
	public void executeSaveContractWithNoResultException() {
		LOGGER.info("PISDR0012Test - Executing executeSaveContractWithNoResultException...");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("contract_entity");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("contract_branch");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("contract_int");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn("insrce_product");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn("insrce_modality");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_COMPANY_ID.getValue())).thenReturn("insrce_company");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_POLICY_ID.getValue())).thenReturn("policy_id");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_START_DATE.getValue())).thenReturn("insrce_contract_start");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_END_DATE.getValue())).thenReturn("insrce_contract_end");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_CUSTOMER_ID.getValue())).thenReturn("customer_id");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_INSRNC_CO_CONTRACT_STATUS_TYPE.getValue())).thenReturn("contract_status");
		when(argumentsForSaveContract.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user_audit");

		when(this.jdbcUtils.update(anyString(), anyMap())).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		int validation = pisdr012.executeSaveContract(argumentsForSaveContract);
		verify(this.jdbcUtils, times(1)).update(anyString(), anyMap());
		assertEquals(-1, validation);
	}

	@Test
	public void executeSaveContractWithMissingParameters() {
		LOGGER.info("PISDR0012Test - Executing executeSaveContractWithMissingParameters...");
		int validation = pisdr012.executeSaveContract(argumentsForSaveContract);
		verify(this.jdbcUtils, never()).update(anyString(), anyMap());
		assertEquals(0, validation);
	}

	@Test
	public void executeSaveFirstReceipt_OK() {
		LOGGER.info("PISDR0012Test - Executing executeSaveFirstReceipt_OK...");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("entityId");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("branchId");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("accountId");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue())).thenReturn(BigDecimal.valueOf(12));
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("userAudit");

		when(this.jdbcUtils.update(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), argumentsForSaveFirstReceipt)).thenReturn(1);

		int validation = pisdr012.executeSaveFirstReceipt(argumentsForSaveFirstReceipt);

		verify(this.jdbcUtils, times(1)).update(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), argumentsForSaveFirstReceipt);
		assertEquals(1, validation);
	}

	@Test
	public void executeSaveFirstReceiptWithNoResultException() {
		LOGGER.info("PISDR0012Test - Executing executeSaveFirstReceiptWithNoResultException...");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("entityId");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("branchId");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("accountId");
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue())).thenReturn(BigDecimal.valueOf(12));
		when(argumentsForSaveFirstReceipt.get(RBVDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("userAudit");

		when(this.jdbcUtils.update(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), argumentsForSaveFirstReceipt)).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		int validation = pisdr012.executeSaveFirstReceipt(argumentsForSaveFirstReceipt);

		verify(this.jdbcUtils, times(1)).update(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), argumentsForSaveFirstReceipt);
		assertEquals(-1, validation);
	}

	@Test
	public void executeSaveFirstReceiptWithMissingParameters() {
		LOGGER.info("PISDR0012Test - Executing executeSaveFirstReceiptWithMissingParameters...");
		int validation = pisdr012.executeSaveFirstReceipt(argumentsForSaveFirstReceipt);
		verify(this.jdbcUtils, never()).update(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), argumentsForSaveFirstReceipt);
		assertEquals(0, validation);
	}

	@Test
	public void executeSaveContractMove_OK() {
		LOGGER.info("PISDR0012Test - Executing executeSaveContractMove_OK...");

		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("entityId");
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("branchId");
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("accountId");
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_POLICY_MOVEMENT_NUMBER.getValue())).thenReturn(BigDecimal.valueOf(1));
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("userAudit");

		when(this.jdbcUtils.update(RBVDProperties.QUERY_INSERT_INSRNC_CONTRACT_MOV.getValue(), argumentsForSaveContractMov)).thenReturn(1);

		int validation = pisdr012.executeSaveContractMove(argumentsForSaveContractMov);

		verify(this.jdbcUtils, times(1)).update(RBVDProperties.QUERY_INSERT_INSRNC_CONTRACT_MOV.getValue(), argumentsForSaveContractMov);
		assertEquals(1, validation);
	}

	@Test
	public void executeSaveContractMoveWithNoResultException() {
		LOGGER.info("PISDR0012Test - Executing executeSaveContractMoveWithNoResultException...");

		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("entityId");
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("branchId");
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("accountId");
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_POLICY_MOVEMENT_NUMBER.getValue())).thenReturn(BigDecimal.valueOf(1));
		when(argumentsForSaveContractMov.get(RBVDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("userAudit");

		when(this.jdbcUtils.update(RBVDProperties.QUERY_INSERT_INSRNC_CONTRACT_MOV.getValue(), argumentsForSaveContractMov)).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		int validation = pisdr012.executeSaveContractMove(argumentsForSaveContractMov);

		verify(this.jdbcUtils, times(1)).update(RBVDProperties.QUERY_INSERT_INSRNC_CONTRACT_MOV.getValue(), argumentsForSaveContractMov);
		assertEquals(-1, validation);
	}

	@Test
	public void executeSaveContractMoveWithMissingParameters() {
		LOGGER.info("PISDR0012Test - Executing executeSaveContractMoveWithMissingParameters...");

		int validation = pisdr012.executeSaveContractMove(argumentsForSaveContractMov);
		verify(this.jdbcUtils, never()).update(RBVDProperties.QUERY_INSERT_INSRNC_CONTRACT_MOV.getValue(), argumentsForSaveContractMov);
		assertEquals(0, validation);
	}

	@Test
	public void executeSaveParticipants_OK() {
		LOGGER.info("PISDR0012Test - Executing executeSaveParticipants_OK...");
		Map<String, Object> firstArgumentsForSaveParticipants = new HashMap<>();
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), "accountId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTICIPANT_ROLE_ID.getValue(), BigDecimal.valueOf(1));
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTY_ORDER_NUMBER.getValue(), BigDecimal.valueOf(1));
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		Map<String, Object> secondArgumentsForSaveParticipants = new HashMap<>();
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), "accountId");
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTICIPANT_ROLE_ID.getValue(), BigDecimal.valueOf(1));
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTY_ORDER_NUMBER.getValue(), BigDecimal.valueOf(1));
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		List<Map<String, Object>> mapList = new ArrayList<>();
		mapList.add(firstArgumentsForSaveParticipants);
		mapList.add(secondArgumentsForSaveParticipants);

		Map<String, Object>[] arguments = new Map[mapList.size()];
		arguments = mapList.toArray(arguments);

		when(this.jdbcUtils.batchUpdate(RBVDProperties.QUERY_INSERT_INSRNC_CTR_PARTICIPANT.getValue(), arguments)).thenReturn(new int[2]);

		int[] validation = pisdr012.executeSaveParticipants(arguments);

		verify(this.jdbcUtils, times(1)).batchUpdate(RBVDProperties.QUERY_INSERT_INSRNC_CTR_PARTICIPANT.getValue(), arguments);
		assertEquals(2, validation.length);
	}

	@Test
	public void executeSaveParticipantsWithNoResultException() {
		LOGGER.info("PISDR0012Test - Executing executeSaveParticipantsWithNoResultException...");
		Map<String, Object> firstArgumentsForSaveParticipants = new HashMap<>();
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), "accountId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTICIPANT_ROLE_ID.getValue(), BigDecimal.valueOf(1));
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTY_ORDER_NUMBER.getValue(), BigDecimal.valueOf(1));
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		Map<String, Object> secondArgumentsForSaveParticipants = new HashMap<>();
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), "accountId");
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTICIPANT_ROLE_ID.getValue(), BigDecimal.valueOf(1));
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTY_ORDER_NUMBER.getValue(), BigDecimal.valueOf(1));
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		List<Map<String, Object>> mapList = new ArrayList<>();
		mapList.add(firstArgumentsForSaveParticipants);
		mapList.add(secondArgumentsForSaveParticipants);

		Map<String, Object>[] arguments = new Map[mapList.size()];
		arguments = mapList.toArray(arguments);

		when(this.jdbcUtils.batchUpdate(RBVDProperties.QUERY_INSERT_INSRNC_CTR_PARTICIPANT.getValue(), arguments)).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		int[] validation = pisdr012.executeSaveParticipants(arguments);

		verify(this.jdbcUtils, times(1)).batchUpdate(RBVDProperties.QUERY_INSERT_INSRNC_CTR_PARTICIPANT.getValue(), arguments);
		assertEquals(0, validation.length);
	}

	@Test
	public void executeSaveParticipantsWithMissingParameters() {
		LOGGER.info("PISDR0012Test - Executing executeSaveParticipantsWithMissingParameters...");
		Map<String, Object> firstArgumentsForSaveParticipants = new HashMap<>();
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), "accountId");
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTICIPANT_ROLE_ID.getValue(), BigDecimal.valueOf(1));
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_PARTY_ORDER_NUMBER.getValue(), BigDecimal.valueOf(1));
		firstArgumentsForSaveParticipants.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		Map<String, Object> secondArgumentsForSaveParticipants = new HashMap<>();
		secondArgumentsForSaveParticipants.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");

		List<Map<String, Object>> mapList = new ArrayList<>();
		mapList.add(firstArgumentsForSaveParticipants);
		mapList.add(secondArgumentsForSaveParticipants);

		Map<String, Object>[] arguments = new Map[mapList.size()];
		arguments = mapList.toArray(arguments);

		int[] validation = pisdr012.executeSaveParticipants(arguments);
		verify(this.jdbcUtils, never()).batchUpdate(RBVDProperties.QUERY_INSERT_INSRNC_CTR_PARTICIPANT.getValue(), arguments);
		assertNull(validation);
	}

	@Test
	public void executeGetRolesByProductAndModality_OK() {
		LOGGER.info("PISDR012Test - Executing executeGetRolesByProductAndModality_OK...");
		when(jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_INSRNC_ROLE_MODALITY.getValue(), BigDecimal.valueOf(1), "modalityType"))
				.thenReturn(new ArrayList<>());

		Map<String, Object> validation = pisdr012.executeGetRolesByProductAndModality(BigDecimal.valueOf(1), "modalityType");
		assertNotNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetRolesByProductAndModalityWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetRolesByProductAndModalityWithNoResultException...");
		when(jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_INSRNC_ROLE_MODALITY.getValue(), BigDecimal.valueOf(1), "modalityType"))
				.thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		Map<String, Object> validation = pisdr012.executeGetRolesByProductAndModality(BigDecimal.valueOf(1), "modalityType");
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetRolesByProductAndModalityWithMissingParameters() {
		LOGGER.info("PISDR012Test - Executing executeGetRolesByProductAndModality...");
		Map<String, Object> validation = pisdr012.executeGetRolesByProductAndModality(null, "modalityType");

		verify(this.jdbcUtils, never()).queryForList(anyString(), any(), anyString());
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetPaymentFrequencyIdOK() {
		LOGGER.info("PISDR012Test - Executing executeGetPaymentFrequencyIdOK...");
		when(jdbcUtils.queryForLong(RBVDProperties.QUERY_GET_PAYMENT_FREQUENCY_ID.getValue(), "paymentFrequencyType")).thenReturn(1L);
		Long validation = pisdr012.executeGetPaymentFrequencyId("paymentFrequencyType");
		assertEquals(new Long(1), validation);
	}

	@Test
	public void executeGetPaymentFrequencyIdWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetPaymentFrequencyIdWithNoResultException...");
		when(jdbcUtils.queryForLong(RBVDProperties.QUERY_GET_PAYMENT_FREQUENCY_ID.getValue(), "paymentFrequencyType"))
				.thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));
		Long validation = pisdr012.executeGetPaymentFrequencyId("paymentFrequencyType");
		assertNull(validation);
	}

	@Test
	public void executeGetPolicyContractOK() {
		LOGGER.info("PISDR012Test - Executing executeGetPolicyContract...");
		when(argumentsForGetPolicyContract.get(PISDProperties.FIELD_POLICY_ID.getValue())).thenReturn(957685);
		List<Map<String,Object>> ex = new ArrayList<>();
		Map<String,Object> ex1 = new HashMap<>();
		ex1.put("prueba1", "prueba2");
		ex1.put("prueba3", "prueba4");
		ex.add(ex1);

		when(jdbcUtils.queryForList(PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue(), argumentsForGetPolicyContract)).thenReturn(ex);
		Map<String, Object> validation = pisdr012.executeGetPolicyContract(argumentsForGetPolicyContract);
		assertNotNull(validation);
	}

	@Test
	public void executeUpdateInsuranceContractOK() {
		LOGGER.info("PISDR012Test - Executing executeUpdateInsuranceContract...");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
	    when(jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_STATUS.getValue(), argumentsForUpdateInsuranceContract)).thenReturn(1);
		boolean validation = pisdr012.executeUpdateInsuranceContract(argumentsForUpdateInsuranceContract);
		assertTrue(validation);
	}

	@Test
	public void executeUpdateInsuranceContractError() {
		LOGGER.info("PISDR012Test - Executing executeUpdateInsuranceContract...");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
	    when(jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_STATUS.getValue(), argumentsForUpdateInsuranceContract)).thenReturn(0);
		boolean validation = pisdr012.executeUpdateInsuranceContract(argumentsForUpdateInsuranceContract);
		assertFalse(validation);
	}

	@Test
	public void executeQueryForGerInsuranceCompanyQuotaId() {
		LOGGER.info("PISDR012Test - Executing executeQueryForGerInsuranceCompanyQuotaId...");
		when(jdbcUtils.queryForMap(PISDProperties.ID_QUERY_FOR_GET_INSURANCE_COMPANY_QUOTA_ID.getValue(), argumentsForGerInsuranceCompanyQuotaId)).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeQueryForGerInsuranceCompanyQuotaId(argumentsForGerInsuranceCompanyQuotaId);
		assertNotNull(validation);
	}

	@Test
	public void executeQueryForGerInsuranceCompanyQuotaIdWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeQueryForGerInsuranceCompanyQuotaIdWithNoResultException...");

		when(jdbcUtils.queryForMap(PISDProperties.ID_QUERY_FOR_GET_INSURANCE_COMPANY_QUOTA_ID.getValue(), argumentsForGerInsuranceCompanyQuotaId)).thenThrow(new NoResultException(MESSAGE));
		Map<String, Object> validation = pisdr012.executeQueryForGerInsuranceCompanyQuotaId(argumentsForGerInsuranceCompanyQuotaId);
		assertNull(validation);
		assertEquals("PISD00120032", this.pisdr012.getAdviceList().get(0).getCode());
	}

}