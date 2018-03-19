package io.regentech.ipayed.web.rest;

import io.regentech.ipayed.IpayedApp;

import io.regentech.ipayed.domain.BankAccountType;
import io.regentech.ipayed.repository.BankAccountTypeRepository;
import io.regentech.ipayed.service.BankAccountTypeService;
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
 * Test class for the BankAccountTypeResource REST controller.
 *
 * @see BankAccountTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpayedApp.class)
public class BankAccountTypeResourceIntTest {

    private static final String DEFAULT_POSSIBLE_IPAYED_ACCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_POSSIBLE_IPAYED_ACCOUNT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_TYPE_CODE = "BBBBBBBBBB";

    @Autowired
    private BankAccountTypeRepository bankAccountTypeRepository;

    @Autowired
    private BankAccountTypeService bankAccountTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBankAccountTypeMockMvc;

    private BankAccountType bankAccountType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BankAccountTypeResource bankAccountTypeResource = new BankAccountTypeResource(bankAccountTypeService);
        this.restBankAccountTypeMockMvc = MockMvcBuilders.standaloneSetup(bankAccountTypeResource)
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
    public static BankAccountType createEntity(EntityManager em) {
        BankAccountType bankAccountType = new BankAccountType()
            .possibleIpayedAccountType(DEFAULT_POSSIBLE_IPAYED_ACCOUNT_TYPE)
            .accountTypeCode(DEFAULT_ACCOUNT_TYPE_CODE);
        return bankAccountType;
    }

    @Before
    public void initTest() {
        bankAccountType = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankAccountType() throws Exception {
        int databaseSizeBeforeCreate = bankAccountTypeRepository.findAll().size();

        // Create the BankAccountType
        restBankAccountTypeMockMvc.perform(post("/api/bank-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountType)))
            .andExpect(status().isCreated());

        // Validate the BankAccountType in the database
        List<BankAccountType> bankAccountTypeList = bankAccountTypeRepository.findAll();
        assertThat(bankAccountTypeList).hasSize(databaseSizeBeforeCreate + 1);
        BankAccountType testBankAccountType = bankAccountTypeList.get(bankAccountTypeList.size() - 1);
        assertThat(testBankAccountType.getPossibleIpayedAccountType()).isEqualTo(DEFAULT_POSSIBLE_IPAYED_ACCOUNT_TYPE);
        assertThat(testBankAccountType.getAccountTypeCode()).isEqualTo(DEFAULT_ACCOUNT_TYPE_CODE);
    }

    @Test
    @Transactional
    public void createBankAccountTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankAccountTypeRepository.findAll().size();

        // Create the BankAccountType with an existing ID
        bankAccountType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankAccountTypeMockMvc.perform(post("/api/bank-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountType)))
            .andExpect(status().isBadRequest());

        // Validate the BankAccountType in the database
        List<BankAccountType> bankAccountTypeList = bankAccountTypeRepository.findAll();
        assertThat(bankAccountTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPossibleIpayedAccountTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankAccountTypeRepository.findAll().size();
        // set the field null
        bankAccountType.setPossibleIpayedAccountType(null);

        // Create the BankAccountType, which fails.

        restBankAccountTypeMockMvc.perform(post("/api/bank-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountType)))
            .andExpect(status().isBadRequest());

        List<BankAccountType> bankAccountTypeList = bankAccountTypeRepository.findAll();
        assertThat(bankAccountTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccountTypeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankAccountTypeRepository.findAll().size();
        // set the field null
        bankAccountType.setAccountTypeCode(null);

        // Create the BankAccountType, which fails.

        restBankAccountTypeMockMvc.perform(post("/api/bank-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountType)))
            .andExpect(status().isBadRequest());

        List<BankAccountType> bankAccountTypeList = bankAccountTypeRepository.findAll();
        assertThat(bankAccountTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBankAccountTypes() throws Exception {
        // Initialize the database
        bankAccountTypeRepository.saveAndFlush(bankAccountType);

        // Get all the bankAccountTypeList
        restBankAccountTypeMockMvc.perform(get("/api/bank-account-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankAccountType.getId().intValue())))
            .andExpect(jsonPath("$.[*].possibleIpayedAccountType").value(hasItem(DEFAULT_POSSIBLE_IPAYED_ACCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].accountTypeCode").value(hasItem(DEFAULT_ACCOUNT_TYPE_CODE.toString())));
    }

    @Test
    @Transactional
    public void getBankAccountType() throws Exception {
        // Initialize the database
        bankAccountTypeRepository.saveAndFlush(bankAccountType);

        // Get the bankAccountType
        restBankAccountTypeMockMvc.perform(get("/api/bank-account-types/{id}", bankAccountType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bankAccountType.getId().intValue()))
            .andExpect(jsonPath("$.possibleIpayedAccountType").value(DEFAULT_POSSIBLE_IPAYED_ACCOUNT_TYPE.toString()))
            .andExpect(jsonPath("$.accountTypeCode").value(DEFAULT_ACCOUNT_TYPE_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankAccountType() throws Exception {
        // Get the bankAccountType
        restBankAccountTypeMockMvc.perform(get("/api/bank-account-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankAccountType() throws Exception {
        // Initialize the database
        bankAccountTypeService.save(bankAccountType);

        int databaseSizeBeforeUpdate = bankAccountTypeRepository.findAll().size();

        // Update the bankAccountType
        BankAccountType updatedBankAccountType = bankAccountTypeRepository.findOne(bankAccountType.getId());
        // Disconnect from session so that the updates on updatedBankAccountType are not directly saved in db
        em.detach(updatedBankAccountType);
        updatedBankAccountType
            .possibleIpayedAccountType(UPDATED_POSSIBLE_IPAYED_ACCOUNT_TYPE)
            .accountTypeCode(UPDATED_ACCOUNT_TYPE_CODE);

        restBankAccountTypeMockMvc.perform(put("/api/bank-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBankAccountType)))
            .andExpect(status().isOk());

        // Validate the BankAccountType in the database
        List<BankAccountType> bankAccountTypeList = bankAccountTypeRepository.findAll();
        assertThat(bankAccountTypeList).hasSize(databaseSizeBeforeUpdate);
        BankAccountType testBankAccountType = bankAccountTypeList.get(bankAccountTypeList.size() - 1);
        assertThat(testBankAccountType.getPossibleIpayedAccountType()).isEqualTo(UPDATED_POSSIBLE_IPAYED_ACCOUNT_TYPE);
        assertThat(testBankAccountType.getAccountTypeCode()).isEqualTo(UPDATED_ACCOUNT_TYPE_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingBankAccountType() throws Exception {
        int databaseSizeBeforeUpdate = bankAccountTypeRepository.findAll().size();

        // Create the BankAccountType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBankAccountTypeMockMvc.perform(put("/api/bank-account-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountType)))
            .andExpect(status().isCreated());

        // Validate the BankAccountType in the database
        List<BankAccountType> bankAccountTypeList = bankAccountTypeRepository.findAll();
        assertThat(bankAccountTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBankAccountType() throws Exception {
        // Initialize the database
        bankAccountTypeService.save(bankAccountType);

        int databaseSizeBeforeDelete = bankAccountTypeRepository.findAll().size();

        // Get the bankAccountType
        restBankAccountTypeMockMvc.perform(delete("/api/bank-account-types/{id}", bankAccountType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BankAccountType> bankAccountTypeList = bankAccountTypeRepository.findAll();
        assertThat(bankAccountTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankAccountType.class);
        BankAccountType bankAccountType1 = new BankAccountType();
        bankAccountType1.setId(1L);
        BankAccountType bankAccountType2 = new BankAccountType();
        bankAccountType2.setId(bankAccountType1.getId());
        assertThat(bankAccountType1).isEqualTo(bankAccountType2);
        bankAccountType2.setId(2L);
        assertThat(bankAccountType1).isNotEqualTo(bankAccountType2);
        bankAccountType1.setId(null);
        assertThat(bankAccountType1).isNotEqualTo(bankAccountType2);
    }
}
