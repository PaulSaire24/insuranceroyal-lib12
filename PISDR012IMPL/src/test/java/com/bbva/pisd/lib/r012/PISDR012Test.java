package com.bbva.pisd.lib.r012;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;

import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.dto.insurance.utils.PISDErrors;
import com.bbva.pisd.dto.insurance.utils.PISDProperties;
import com.bbva.pisd.lib.r012.impl.PISDR012Impl;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

}