package com.bbva.pisd.lib.r012;

import com.bbva.apx.exception.db.NoResultException;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;

import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.insurance.utils.PISDErrors;
import com.bbva.pisd.dto.insurance.utils.PISDProperties;
import com.bbva.pisd.lib.r012.impl.PISDR012Impl;

import com.bbva.rbvd.dto.insrncsale.utils.RBVDProperties;
import org.apache.felix.resolver.util.ArrayMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PISDR012-app.xml",
		"classpath:/META-INF/spring/PISDR012-app-test.xml",
		"classpath:/META-INF/spring/PISDR012-arc.xml",
		"classpath:/META-INF/spring/PISDR012-arc-test.xml" })
public class PISDR012Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR012Test.class);
	private static final String CODE = "adviceCode";
	private static final String MESSAGE = "No se encontró data";

	private final PISDR012Impl pisdr012 = new PISDR012Impl();
	private JdbcUtils jdbcUtils;

	@Mock
	private Map<String, Object> argumentsForGetModalitiesInformation;

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
	private Map<String, Object> argumentsForSaveContractMov;
	@Mock
	private Map<String, Object> argumentsForInsrncModalityByRimacIds;
	@Mock
	private Map<String, Object> argumentsForGetPolicyContract;
	@Mock
	private Map<String, Object> argumentsForUpdateInsuranceContract;
	@Mock
	private Map<String, Object> argumentsForexecuteUpdatePaymentSchedule;
	@Mock
	private Map<String, Object> argumentsForGerInsuranceCompanyQuotaId;
	@Mock
	private Map<String, Object> argumentsForexecuteGetInsuranceContractStartDate;
	@Mock
	private List<Map<String, Object>> argumentsForGetInsuranceContractStatus;
	@Mock
	private Map<String, Object> argumentsUpdateInsuranceContractDocument;
	@Mock
	private Map<String, Object> argumentGetOffer;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ThreadContext.set(new Context());

		jdbcUtils = mock(JdbcUtils.class);
		pisdr012.setJdbcUtils(jdbcUtils);

	}

	@Test
	public void executeGetProductInformationOK() {
		LOGGER.info("PISDR012Test - Executing executeGetProductInformationOK...");
		when(this.jdbcUtils.queryForMap(PISDProperties.QUERY_GET_PRODUCT_INFORMATION.getValue(), "830")).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeGetProductInformation("830");
		assertNotNull(validation);
	}

	@Test
	public void executeGetProductInformationWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetProductInformationWithNoResultException...");
		when(this.jdbcUtils.queryForMap(PISDProperties.QUERY_GET_PRODUCT_INFORMATION.getValue(), "827")).thenThrow(new NoResultException(CODE, MESSAGE));
		Map<String, Object> validation = pisdr012.executeGetProductInformation("827");
		assertTrue(isEmpty(validation));
	}

	@Test
	public void executeGetProductModalitiesInformationOK() {
		LOGGER.info("PISDR012Test - Executing executeGetProductModalitiesInformationOK...");

		when(argumentsForGetModalitiesInformation.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn("830");
		when(argumentsForGetModalitiesInformation.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn(new ArrayList<>());
		when(argumentsForGetModalitiesInformation.get(PISDProperties.FIELD_SALE_CHANNEL_ID.getValue())).thenReturn("BI");

		when(this.jdbcUtils.queryForList(PISDProperties.QUERY_GET_PRODUCT_MODALITIES_INFORMATION.getValue(), argumentsForGetModalitiesInformation)).
				thenReturn(new ArrayList<>());

		Map<String, Object> validation = pisdr012.executeGetProductModalitiesInformation(argumentsForGetModalitiesInformation);
		assertNotNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetProductModalitiesInformationWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeGetProductModalitiesInformationWithParametersEvaluationFalse...");

		Map<String, Object> validation = pisdr012.executeGetProductModalitiesInformation(argumentsForGetModalitiesInformation);
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetProductModalitiesInformationWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetProductModalitiesInformationWithNoResultException...");

		when(argumentsForGetModalitiesInformation.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn("830");
		when(argumentsForGetModalitiesInformation.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn(new ArrayList<>());
		when(argumentsForGetModalitiesInformation.get(PISDProperties.FIELD_SALE_CHANNEL_ID.getValue())).thenReturn("BI");

		when(this.jdbcUtils.queryForList(PISDProperties.QUERY_GET_PRODUCT_MODALITIES_INFORMATION.getValue(), argumentsForGetModalitiesInformation)).
				thenThrow(new NoResultException(CODE, MESSAGE));

		Map<String, Object> validation = pisdr012.executeGetProductModalitiesInformation(argumentsForGetModalitiesInformation);
		assertNull(validation.get(PISDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetProductModalitySelectedOK() {
		LOGGER.info("PISDR012Test - Executing executeGetProductModalitySelectedOK...");

		when(this.jdbcUtils.queryForMap(PISDProperties.QUERY_GET_PRODUCT_MODALITY_SELECTED.getValue(), "01")).
				thenReturn(new HashMap<>());

		Map<String, Object> validation = pisdr012.executeGetProductModalitySelected("01");
		assertNotNull(validation);
	}

	@Test
	public void executeGetProductModalitySelectedWithNoResultException() {
		LOGGER.info("PISDR012Test - Executing executeGetProductModalitySelectedWithNoResultException...");

		when(this.jdbcUtils.queryForMap(PISDProperties.QUERY_GET_PRODUCT_MODALITY_SELECTED.getValue(), "04")).
				thenThrow(new NoResultException(CODE, MESSAGE));

		Map<String, Object> validation = pisdr012.executeGetProductModalitySelected("04");
		assertTrue(isEmpty(validation));
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
				thenThrow(new NoResultException(CODE, MESSAGE));
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

		when(this.jdbcUtils.update(anyString(), anyMap())).thenReturn(1);

		int validation = pisdr012.executeSaveInsuranceQuotation(argumentsForSaveSimulationQuotation);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
		assertEquals(1, validation);
	}

	@Test
	public void executeSaveInsuranceQuotationWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationWithParametersEvaluationFalse...");
		int validation = pisdr012.executeSaveInsuranceQuotation(argumentsForSaveSimulationQuotation);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
		assertEquals(0, validation);
	}

	@Test
	public void executeSaveInsuranceQuotationModOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationModOK...");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())).thenReturn("aaaaa");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(new BigDecimal("827"));
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn("modality");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_SALE_CHANNEL_ID.getValue())).thenReturn("channelCode");
		when(argumentsForSaveSimulationQuotationMod.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user01");

		when(this.jdbcUtils.update(anyString(), anyMap())).thenReturn(1);

		int validation = pisdr012.executeSaveInsuranceQuotationMod(argumentsForSaveSimulationQuotationMod);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
		assertEquals(1, validation);
	}

	@Test
	public void executeSaveInsuranceQuotationModWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationModWithParametersEvaluationFalse...");

		int validation = pisdr012.executeSaveInsuranceQuotationMod(argumentsForSaveSimulationQuotationMod);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
		assertEquals(0, validation);
	}

	@Test
	public void executeSaveInsuranceQuotationVehOK() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationVehOK...");
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_POLICY_QUOTA_INTERNAL_ID.getValue())).thenReturn("aaaaa");
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_PRODUCT_ID.getValue())).thenReturn(new BigDecimal("827"));
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_OR_FILTER_INSURANCE_MODALITY_TYPE.getValue())).thenReturn("modality");
		when(argumentsForSaveSimulationQuotationVeh.get(PISDProperties.FIELD_USER_AUDIT_ID.getValue())).thenReturn("user01");

		when(this.jdbcUtils.update(anyString(), anyMap())).thenReturn(1);

		int validation = pisdr012.executeSaveInsuranceQuotationVeh(argumentsForSaveSimulationQuotationVeh);
		verify(jdbcUtils, times(1)).update(anyString(), anyMap());
		assertEquals(1, validation);
	}

	@Test
	public void executeSaveInsuranceQuotationVehWithParametersEvaluationFalse() {
		LOGGER.info("PISDR012Test - Executing executeSaveInsuranceQuotationVehWithParametersEvaluationFalse...");

		int validation = pisdr012.executeSaveInsuranceQuotationVeh(argumentsForSaveSimulationQuotationVeh);
		verify(jdbcUtils, never()).update(anyString(), anyMap());
		assertEquals(0, validation);
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
	public void executeGetRequiredFieldsForEmissionServiceOK() {
		LOGGER.info("PISDR0012Test - Executing executeGetRequiredFieldsForEmissionServiceOK...");

		when(this.jdbcUtils.queryForMap(RBVDProperties.DYNAMIC_QUERY_FOR_INSURANCE_CONTRACT.getValue(), "policyQuotaInternalId")).thenReturn(new HashMap<>());

		Map<String, Object> validation = pisdr012.executeGetRequiredFieldsForEmissionService("policyQuotaInternalId");
		assertNotNull(validation);
	}

	@Test
	public void executeGetRequiredFieldsForEmissionServiceWithNoResultException() {
		LOGGER.info("PISDR0012Test - Executing executeGetRequiredFieldsForEmissionServiceWithNoResultException...");

		when(this.jdbcUtils.queryForMap(RBVDProperties.DYNAMIC_QUERY_FOR_INSURANCE_CONTRACT.getValue(), "policyQuotaInternalId")).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		Map<String, Object> validation = pisdr012.executeGetRequiredFieldsForEmissionService("policyQuotaInternalId");
		assertNull(validation);
	}

	@Test
	public void executeGetPaymentPeriodOK() {
		LOGGER.info("PISDR0012Test - Executing executeGetPaymentPeriodOK...");

		when(this.jdbcUtils.queryForMap(RBVDProperties.QUERY_SELECT_PAYMENT_PERIOD.getValue(), "frequencyType")).thenReturn(new HashMap<>());

		Map<String, Object> validation = pisdr012.executeGetPaymentPeriod("frequencyType");
		assertNotNull(validation);
	}

	@Test
	public void executeGetPaymentPeriodWithNoResultException() {
		LOGGER.info("PISDR0012Test - Executing executeGetPaymentPeriodWithNoResultException...");

		when(this.jdbcUtils.queryForMap(RBVDProperties.QUERY_SELECT_PAYMENT_PERIOD.getValue(), "frequencyType")).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		Map<String, Object> validation = pisdr012.executeGetPaymentPeriod("frequencyType");
		assertNull(validation);
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
	public void executeSaveReceipts_OK() {
		LOGGER.info("PISDR0012Test - Executing executeSaveReceipts_OK...");

		Map<String, Object> firstReceipt = new HashMap<>();
		firstReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		firstReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		firstReceipt.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), BigDecimal.valueOf(12));
		firstReceipt.put(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue(), "accountId");
		firstReceipt.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		Map<String, Object> secondReceipt = new HashMap<>();
		secondReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId2");
		secondReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId2");
		secondReceipt.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), BigDecimal.valueOf(12));
		secondReceipt.put(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue(), "accountId2");
		secondReceipt.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit2");

		List<Map<String, Object>> receiptList = new ArrayList<>();
		receiptList.add(firstReceipt);
		receiptList.add(secondReceipt);

		Map<String, Object>[] arguments = new Map[receiptList.size()];
		arguments = receiptList.toArray(arguments);

		when(this.jdbcUtils.batchUpdate(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), arguments)).thenReturn(new int[2]);

		int[] validation = pisdr012.executeSaveReceipts(arguments);

		verify(this.jdbcUtils, times(1)).batchUpdate(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), arguments);
		assertEquals(2, validation.length);
	}

	@Test
	public void executeSaveReceiptsWithNoResultException() {
		LOGGER.info("PISDR0012Test - Executing executeSaveReceiptsWithNoResultException...");

		Map<String, Object> firstReceipt = new HashMap<>();
		firstReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		firstReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		firstReceipt.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), BigDecimal.valueOf(12));
		firstReceipt.put(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue(), "accountId");
		firstReceipt.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		Map<String, Object> secondReceipt = new HashMap<>();
		secondReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId2");
		secondReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId2");
		secondReceipt.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), BigDecimal.valueOf(12));
		secondReceipt.put(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue(), "accountId2");
		secondReceipt.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit2");

		List<Map<String, Object>> receiptList = new ArrayList<>();
		receiptList.add(firstReceipt);
		receiptList.add(secondReceipt);

		Map<String, Object>[] arguments = new Map[receiptList.size()];
		arguments = receiptList.toArray(arguments);

		when(this.jdbcUtils.batchUpdate(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), arguments)).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));

		int[] validation = pisdr012.executeSaveReceipts(arguments);

		verify(this.jdbcUtils, times(1)).batchUpdate(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), arguments);
		assertEquals(0, validation.length);
	}

	@Test
	public void executeSaveReceiptsWithMissingParameters() {
		LOGGER.info("PISDR0012Test - Executing executeSaveReceiptsWithMissingParameters...");

		Map<String, Object> firstReceipt = new HashMap<>();
		firstReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId");
		firstReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue(), "branchId");
		firstReceipt.put(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue(), BigDecimal.valueOf(12));
		firstReceipt.put(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue(), "accountId");
		firstReceipt.put(RBVDProperties.FIELD_USER_AUDIT_ID.getValue(), "userAudit");

		Map<String, Object> secondReceipt = new HashMap<>();
		secondReceipt.put(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue(), "entityId2");

		List<Map<String, Object>> receiptList = new ArrayList<>();
		receiptList.add(firstReceipt);
		receiptList.add(secondReceipt);

		Map<String, Object>[] arguments = new Map[receiptList.size()];
		arguments = receiptList.toArray(arguments);

		int[] validation = pisdr012.executeSaveReceipts(arguments);
		verify(this.jdbcUtils, never()).batchUpdate(RBVDProperties.QUERY_INSERT_INSURANCE_CTR_RECEIPTS.getValue(), arguments);
		assertNull(validation);
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
	public void executeGetPolicyContractOK() {
		LOGGER.info("PISDR012Test - Executing executeGetPolicyContract...");
		when(argumentsForGetPolicyContract.get(PISDProperties.FIELD_POLICY_ID.getValue())).thenReturn(957685);
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue(), argumentsForGetPolicyContract)).thenReturn(new HashMap<>());
		Map<String, Object> validation = pisdr012.executeGetPolicyContract(argumentsForGetPolicyContract);
		assertNotNull(validation);
	}

	@Test
	public void executeGetPolicyContractError() {
		LOGGER.info("PISDR012Test - Executing executeGetPolicyContract...");
		when(argumentsForGetPolicyContract.get(PISDProperties.FIELD_POLICY_ID.getValue())).thenReturn(957685);
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue(), argumentsForGetPolicyContract)).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));
		Map<String, Object> validation = pisdr012.executeGetPolicyContract(argumentsForGetPolicyContract);
		assertNull(validation);
	}

	@Test
	public void executeGetPolicyContractValidationError() {
		LOGGER.info("PISDR012Test - Executing executeGetPolicyContract...");
		when(argumentsForGetPolicyContract.get(PISDProperties.FIELD_POLICY_ID.getValue())).thenReturn(null);
		when(jdbcUtils.queryForMap(PISDProperties.QUERY_SELECT_INSURANCE_CONTRACT.getValue(), argumentsForGetPolicyContract)).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));
		Map<String, Object> validation = pisdr012.executeGetPolicyContract(argumentsForGetPolicyContract);
		assertNull(validation);
	}

	@Test
	public void executeUpdateInsuranceContractOK() {
		LOGGER.info("PISDR012Test - Executing executeUpdateInsuranceContractOK...");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
	    when(jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_STATUS.getValue(), argumentsForUpdateInsuranceContract)).thenReturn(1);
		boolean validation = pisdr012.executeUpdateInsuranceContract(argumentsForUpdateInsuranceContract);
		assertTrue(validation);
	}

	@Test
	public void executeUpdateInsuranceContractError() {
		LOGGER.info("PISDR012Test - Executing executeUpdateInsuranceContractError...");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForUpdateInsuranceContract.get(PISDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
	    when(jdbcUtils.update(PISDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_STATUS.getValue(), argumentsForUpdateInsuranceContract)).thenReturn(0);
		boolean validation = pisdr012.executeUpdateInsuranceContract(argumentsForUpdateInsuranceContract);
		assertFalse(validation);
	}

	@Test
	public void executeUpdatePaymentScheduleOK() {
		LOGGER.info("PISDR012Test - Executing executeUpdatePaymentScheduleOK...");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue())).thenReturn("1");
	    when(jdbcUtils.update(RBVDProperties.QUERY_UPDATE_INSURANCE_CTR_RECEIPTS.getValue(), argumentsForexecuteUpdatePaymentSchedule)).thenReturn(1);
		boolean validation = pisdr012.executeUpdatePaymentSchedule(argumentsForexecuteUpdatePaymentSchedule);
		assertTrue(validation);
	}
	
	@Test
	public void executeUpdatePaymentScheduleError() {
		LOGGER.info("PISDR012Test - Executing executeUpdatePaymentScheduleError...");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
		when(argumentsForexecuteUpdatePaymentSchedule.get(RBVDProperties.FIELD_POLICY_RECEIPT_ID.getValue())).thenReturn("1");
	    when(jdbcUtils.update(RBVDProperties.QUERY_UPDATE_INSURANCE_CTR_RECEIPTS.getValue(), argumentsForexecuteUpdatePaymentSchedule)).thenReturn(0);
		boolean validation = pisdr012.executeUpdatePaymentSchedule(argumentsForexecuteUpdatePaymentSchedule);
		assertFalse(validation);
	}
    
	@Test
	public void executeGetInsuranceContractStartDateOK(){
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceContractStartDateOK...");
		when(argumentsForexecuteGetInsuranceContractStartDate.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForexecuteGetInsuranceContractStartDate.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForexecuteGetInsuranceContractStartDate.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
		when(jdbcUtils.queryForMap(RBVDProperties.QUERY_SELECT_INSURANCE_CONTRACT_START_DATE.getValue(), argumentsForexecuteGetInsuranceContractStartDate)).thenReturn(new HashMap<>());
	    Map<String,Object> validation = pisdr012.executeGetInsuranceContractStartDate(argumentsForexecuteGetInsuranceContractStartDate);
		assertNotNull(validation);
	}

	@Test
	public void executeGetInsuranceContractStartDateWithNoResultException(){
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceContractStartDateWithNoResultException...");
		when(argumentsForexecuteGetInsuranceContractStartDate.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsForexecuteGetInsuranceContractStartDate.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsForexecuteGetInsuranceContractStartDate.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
		when(jdbcUtils.queryForMap(RBVDProperties.QUERY_SELECT_INSURANCE_CONTRACT_START_DATE.getValue(), argumentsForexecuteGetInsuranceContractStartDate)).thenThrow(new NoResultException(MESSAGE));
	    Map<String,Object> validation = pisdr012.executeGetInsuranceContractStartDate(argumentsForexecuteGetInsuranceContractStartDate);
		assertNull(validation);
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

	@Test
	public void executeGetInsuranceContractStatusOK() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceContractStatusOK...");

		when(jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_INSURANCE_CONTRACT_DOCUMENT_STATUS.getValue())).thenReturn(argumentsForGetInsuranceContractStatus);
		Map<String, Object> validation = pisdr012.executeGetInsuranceContractStatus();
		assertNotNull(validation);
	}

	@Test
	public void executeGetInsuranceContractStatusError() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceContractStatusError...");

		when(jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_INSURANCE_CONTRACT_DOCUMENT_STATUS.getValue())).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));
		Map<String, Object> validation = pisdr012.executeGetInsuranceContractStatus();
		assertNotNull(validation);
	}

	@Test
	public void executeUpdateInsuranceContractDocumentOK() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceContractStatusOK...");
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
		when(jdbcUtils.update(RBVDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_DOCUMENT_STATUS.getValue(),argumentsUpdateInsuranceContractDocument)).thenReturn(1);
		Boolean validation = pisdr012.executeUpdateInsuranceContractDocument(argumentsUpdateInsuranceContractDocument);
		assertTrue(validation);
	}

	@Test
	public void executeUpdateInsuranceContractDocumentValidationError() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceContractStatusError...");
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn(null);
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn(null);
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn(null);
		when(jdbcUtils.update(RBVDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_DOCUMENT_STATUS.getValue(),argumentsUpdateInsuranceContractDocument)).thenThrow(new NoResultException("RBVD00111990", "ERROR EN LA BASE DE DATOS"));
		Boolean validation = pisdr012.executeUpdateInsuranceContractDocument(argumentsUpdateInsuranceContractDocument);
		assertFalse(validation);
	}

	@Test
	public void executeUpdateInsuranceContractDocumentNoUpdate() {
		LOGGER.info("PISDR012Test - Executing executeGetInsuranceContractStatusError...");
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_ENTITY_ID.getValue())).thenReturn("0011");
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSURANCE_CONTRACT_BRANCH_ID.getValue())).thenReturn("0241");
		when(argumentsUpdateInsuranceContractDocument.get(RBVDProperties.FIELD_INSRC_CONTRACT_INT_ACCOUNT_ID.getValue())).thenReturn("3999993329");
		when(jdbcUtils.update(RBVDProperties.QUERY_UPDATE_INSURANCE_CONTRACT_DOCUMENT_STATUS.getValue(),argumentsUpdateInsuranceContractDocument)).thenReturn(0);
		Boolean validation = pisdr012.executeUpdateInsuranceContractDocument(argumentsUpdateInsuranceContractDocument);
		assertFalse(validation);
	}
	
	@Test
	public void executeGetOfferOK(){
		LOGGER.info("PISDR012Test - Executing executeGetOfferOK...");
		when(argumentGetOffer.get(RBVDProperties.FIELD_PRODUCT_OFFER_ID.getValue())).thenReturn("0011");
		when(jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_OFFER.getValue(), argumentGetOffer)).thenReturn(new ArrayList<>());
	    Map<String,Object> validation = pisdr012.executeGetOffer(argumentGetOffer);
		assertNotNull(validation.get(RBVDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}


	@Test
	public void executeGetOfferWithNoResultException(){
		LOGGER.info("PISDR012Test - Executing executeGetOfferWithNoResultException...");
		when(argumentGetOffer.get(RBVDProperties.FIELD_PRODUCT_OFFER_ID.getValue())).thenReturn("0011");
		when(jdbcUtils.queryForList(RBVDProperties.QUERY_SELECT_OFFER.getValue(), argumentGetOffer)).thenThrow(new NoResultException(MESSAGE));
	    Map<String,Object> validation = pisdr012.executeGetOffer(argumentGetOffer);
		assertNull(validation.get(RBVDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

	@Test
	public void executeGetOfferWithNull(){
		LOGGER.info("PISDR012Test - Executing executeGetOfferWithNull...");
		when(argumentGetOffer.get(RBVDProperties.FIELD_POLICY_ID.getValue())).thenReturn(null);
	    Map<String,Object> validation = pisdr012.executeGetOffer(argumentGetOffer);
		assertNull(validation.get(RBVDProperties.KEY_OF_INSRC_LIST_RESPONSES.getValue()));
	}

}