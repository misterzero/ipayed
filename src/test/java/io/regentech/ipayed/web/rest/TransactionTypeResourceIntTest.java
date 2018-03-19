package io.regentech.ipayed.web.rest;

import io.regentech.ipayed.IpayedApp;

import io.regentech.ipayed.domain.TransactionType;
import io.regentech.ipayed.repository.TransactionTypeRepository;
import io.regentech.ipayed.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.regentech.ipayed.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TransactionTypeResource REST controller.
 *
 * @see TransactionTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpayedApp.class)
public class TransactionTypeResourceIntTest {

    private static final String DEFAULT_IPAYED_TRANS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_IPAYED_TRANS_TYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_IPAYED_ORIGINATOR_FEE = 1F;
    private static final Float UPDATED_IPAYED_ORIGINATOR_FEE = 2F;

    private static final Float DEFAULT_IPAYED_BENEFACTOR_FEE = 1F;
    private static final Float UPDATED_IPAYED_BENEFACTOR_FEE = 2F;

    private static final String DEFAULT_LOOP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LOOP_TYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_CHARGEUP_FEE = 1F;
    private static final Float UPDATED_CHARGEUP_FEE = 2F;

    private static final String DEFAULT_FEE_TO_IPAYED = "AAAAAAAAAA";
    private static final String UPDATED_FEE_TO_IPAYED = "BBBBBBBBBB";

    private static final String DEFAULT_TRANS_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANS_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGINATOR_ACCOUNT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORIGINATOR_ACCOUNT_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFACTOR_ACCOUNT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BENEFACTOR_ACCOUNT_TYPE_CODE = "BBBBBBBBBB";

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransactionTypeMockMvc;

    private TransactionType transactionType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransactionTypeResource transactionTypeResource = new TransactionTypeResource(transactionTypeRepository);
        this.restTransactionTypeMockMvc = MockMvcBuilders.standaloneSetup(transactionTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionType createEntity(EntityManager em) {
        TransactionType transactionType = new TransactionType()
            .ipayedTransType(DEFAULT_IPAYED_TRANS_TYPE)
            .ipayedOriginatorFee(DEFAULT_IPAYED_ORIGINATOR_FEE)
            .ipayedBenefactorFee(DEFAULT_IPAYED_BENEFACTOR_FEE)
            .loopType(DEFAULT_LOOP_TYPE)
            .chargeupFee(DEFAULT_CHARGEUP_FEE)
            .feeToIpayed(DEFAULT_FEE_TO_IPAYED)
            .transTypeCode(DEFAULT_TRANS_TYPE_CODE)
            .originatorAccountTypeCode(DEFAULT_ORIGINATOR_ACCOUNT_TYPE_CODE)
            .benefactorAccountTypeCode(DEFAULT_BENEFACTOR_ACCOUNT_TYPE_CODE);
        return transactionType;
    }

    @Before
    public void initTest() {
        transactionType = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransactionType() throws Exception {
        int databaseSizeBeforeCreate = transactionTypeRepository.findAll().size();

        // Create the TransactionType
        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isCreated());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TransactionType testTransactionType = transactionTypeList.get(transactionTypeList.size() - 1);
        assertThat(testTransactionType.getIpayedTransType()).isEqualTo(DEFAULT_IPAYED_TRANS_TYPE);
        assertThat(testTransactionType.getIpayedOriginatorFee()).isEqualTo(DEFAULT_IPAYED_ORIGINATOR_FEE);
        assertThat(testTransactionType.getIpayedBenefactorFee()).isEqualTo(DEFAULT_IPAYED_BENEFACTOR_FEE);
        assertThat(testTransactionType.getLoopType()).isEqualTo(DEFAULT_LOOP_TYPE);
        assertThat(testTransactionType.getChargeupFee()).isEqualTo(DEFAULT_CHARGEUP_FEE);
        assertThat(testTransactionType.getFeeToIpayed()).isEqualTo(DEFAULT_FEE_TO_IPAYED);
        assertThat(testTransactionType.getTransTypeCode()).isEqualTo(DEFAULT_TRANS_TYPE_CODE);
        assertThat(testTransactionType.getOriginatorAccountTypeCode()).isEqualTo(DEFAULT_ORIGINATOR_ACCOUNT_TYPE_CODE);
        assertThat(testTransactionType.getBenefactorAccountTypeCode()).isEqualTo(DEFAULT_BENEFACTOR_ACCOUNT_TYPE_CODE);
    }

    @Test
    @Transactional
    public void createTransactionTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionTypeRepository.findAll().size();

        // Create the TransactionType with an existing ID
        transactionType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIpayedTransTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setIpayedTransType(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIpayedOriginatorFeeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setIpayedOriginatorFee(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIpayedBenefactorFeeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setIpayedBenefactorFee(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoopTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setLoopType(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChargeupFeeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setChargeupFee(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFeeToIpayedIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setFeeToIpayed(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransTypeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setTransTypeCode(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOriginatorAccountTypeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setOriginatorAccountTypeCode(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBenefactorAccountTypeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeRepository.findAll().size();
        // set the field null
        transactionType.setBenefactorAccountTypeCode(null);

        // Create the TransactionType, which fails.

        restTransactionTypeMockMvc.perform(post("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isBadRequest());

        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactionTypes() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get all the transactionTypeList
        restTransactionTypeMockMvc.perform(get("/api/transaction-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionType.getId().intValue())))
            .andExpect(jsonPath("$.[*].ipayedTransType").value(hasItem(DEFAULT_IPAYED_TRANS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ipayedOriginatorFee").value(hasItem(DEFAULT_IPAYED_ORIGINATOR_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].ipayedBenefactorFee").value(hasItem(DEFAULT_IPAYED_BENEFACTOR_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].loopType").value(hasItem(DEFAULT_LOOP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].chargeupFee").value(hasItem(DEFAULT_CHARGEUP_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].feeToIpayed").value(hasItem(DEFAULT_FEE_TO_IPAYED.toString())))
            .andExpect(jsonPath("$.[*].transTypeCode").value(hasItem(DEFAULT_TRANS_TYPE_CODE.toString())))
            .andExpect(jsonPath("$.[*].originatorAccountTypeCode").value(hasItem(DEFAULT_ORIGINATOR_ACCOUNT_TYPE_CODE.toString())))
            .andExpect(jsonPath("$.[*].benefactorAccountTypeCode").value(hasItem(DEFAULT_BENEFACTOR_ACCOUNT_TYPE_CODE.toString())));
    }

    @Test
    @Transactional
    public void getTransactionType() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);

        // Get the transactionType
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/{id}", transactionType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transactionType.getId().intValue()))
            .andExpect(jsonPath("$.ipayedTransType").value(DEFAULT_IPAYED_TRANS_TYPE.toString()))
            .andExpect(jsonPath("$.ipayedOriginatorFee").value(DEFAULT_IPAYED_ORIGINATOR_FEE.doubleValue()))
            .andExpect(jsonPath("$.ipayedBenefactorFee").value(DEFAULT_IPAYED_BENEFACTOR_FEE.doubleValue()))
            .andExpect(jsonPath("$.loopType").value(DEFAULT_LOOP_TYPE.toString()))
            .andExpect(jsonPath("$.chargeupFee").value(DEFAULT_CHARGEUP_FEE.doubleValue()))
            .andExpect(jsonPath("$.feeToIpayed").value(DEFAULT_FEE_TO_IPAYED.toString()))
            .andExpect(jsonPath("$.transTypeCode").value(DEFAULT_TRANS_TYPE_CODE.toString()))
            .andExpect(jsonPath("$.originatorAccountTypeCode").value(DEFAULT_ORIGINATOR_ACCOUNT_TYPE_CODE.toString()))
            .andExpect(jsonPath("$.benefactorAccountTypeCode").value(DEFAULT_BENEFACTOR_ACCOUNT_TYPE_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransactionType() throws Exception {
        // Get the transactionType
        restTransactionTypeMockMvc.perform(get("/api/transaction-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionType() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);
        int databaseSizeBeforeUpdate = transactionTypeRepository.findAll().size();

        // Update the transactionType
        TransactionType updatedTransactionType = transactionTypeRepository.findOne(transactionType.getId());
        // Disconnect from session so that the updates on updatedTransactionType are not directly saved in db
        em.detach(updatedTransactionType);
        updatedTransactionType
            .ipayedTransType(UPDATED_IPAYED_TRANS_TYPE)
            .ipayedOriginatorFee(UPDATED_IPAYED_ORIGINATOR_FEE)
            .ipayedBenefactorFee(UPDATED_IPAYED_BENEFACTOR_FEE)
            .loopType(UPDATED_LOOP_TYPE)
            .chargeupFee(UPDATED_CHARGEUP_FEE)
            .feeToIpayed(UPDATED_FEE_TO_IPAYED)
            .transTypeCode(UPDATED_TRANS_TYPE_CODE)
            .originatorAccountTypeCode(UPDATED_ORIGINATOR_ACCOUNT_TYPE_CODE)
            .benefactorAccountTypeCode(UPDATED_BENEFACTOR_ACCOUNT_TYPE_CODE);

        restTransactionTypeMockMvc.perform(put("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransactionType)))
            .andExpect(status().isOk());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeUpdate);
        TransactionType testTransactionType = transactionTypeList.get(transactionTypeList.size() - 1);
        assertThat(testTransactionType.getIpayedTransType()).isEqualTo(UPDATED_IPAYED_TRANS_TYPE);
        assertThat(testTransactionType.getIpayedOriginatorFee()).isEqualTo(UPDATED_IPAYED_ORIGINATOR_FEE);
        assertThat(testTransactionType.getIpayedBenefactorFee()).isEqualTo(UPDATED_IPAYED_BENEFACTOR_FEE);
        assertThat(testTransactionType.getLoopType()).isEqualTo(UPDATED_LOOP_TYPE);
        assertThat(testTransactionType.getChargeupFee()).isEqualTo(UPDATED_CHARGEUP_FEE);
        assertThat(testTransactionType.getFeeToIpayed()).isEqualTo(UPDATED_FEE_TO_IPAYED);
        assertThat(testTransactionType.getTransTypeCode()).isEqualTo(UPDATED_TRANS_TYPE_CODE);
        assertThat(testTransactionType.getOriginatorAccountTypeCode()).isEqualTo(UPDATED_ORIGINATOR_ACCOUNT_TYPE_CODE);
        assertThat(testTransactionType.getBenefactorAccountTypeCode()).isEqualTo(UPDATED_BENEFACTOR_ACCOUNT_TYPE_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransactionType() throws Exception {
        int databaseSizeBeforeUpdate = transactionTypeRepository.findAll().size();

        // Create the TransactionType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransactionTypeMockMvc.perform(put("/api/transaction-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transactionType)))
            .andExpect(status().isCreated());

        // Validate the TransactionType in the database
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTransactionType() throws Exception {
        // Initialize the database
        transactionTypeRepository.saveAndFlush(transactionType);
        int databaseSizeBeforeDelete = transactionTypeRepository.findAll().size();

        // Get the transactionType
        restTransactionTypeMockMvc.perform(delete("/api/transaction-types/{id}", transactionType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TransactionType> transactionTypeList = transactionTypeRepository.findAll();
        assertThat(transactionTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionType.class);
        TransactionType transactionType1 = new TransactionType();
        transactionType1.setId(1L);
        TransactionType transactionType2 = new TransactionType();
        transactionType2.setId(transactionType1.getId());
        assertThat(transactionType1).isEqualTo(transactionType2);
        transactionType2.setId(2L);
        assertThat(transactionType1).isNotEqualTo(transactionType2);
        transactionType1.setId(null);
        assertThat(transactionType1).isNotEqualTo(transactionType2);
    }
}
