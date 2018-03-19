package io.regentech.ipayed.web.rest;

import io.regentech.ipayed.IpayedApp;

import io.regentech.ipayed.domain.Trans8Mar;
import io.regentech.ipayed.repository.Trans8MarRepository;
import io.regentech.ipayed.service.Trans8MarService;
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
 * Test class for the Trans8MarResource REST controller.
 *
 * @see Trans8MarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpayedApp.class)
public class Trans8MarResourceIntTest {

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final Float DEFAULT_BEGIN_ACCOUNT_BALANCE = 1F;
    private static final Float UPDATED_BEGIN_ACCOUNT_BALANCE = 2F;

    private static final String DEFAULT_IS_CD = "AAAAAAAAAA";
    private static final String UPDATED_IS_CD = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_DATETIME = "AAAAAAAAAA";
    private static final String UPDATED_DATETIME = "BBBBBBBBBB";

    private static final Float DEFAULT_END_ACCOUNT_BALANCE = 1F;
    private static final Float UPDATED_END_ACCOUNT_BALANCE = 2F;

    private static final Float DEFAULT_ENTITY_FEE = 1F;
    private static final Float UPDATED_ENTITY_FEE = 2F;

    private static final String DEFAULT_LINKED_IPAYED_TRANS = "AAAAAAAAAA";
    private static final String UPDATED_LINKED_IPAYED_TRANS = "BBBBBBBBBB";

    private static final String DEFAULT_LINKED_TRANS = "AAAAAAAAAA";
    private static final String UPDATED_LINKED_TRANS = "BBBBBBBBBB";

    private static final String DEFAULT_TRANS_FAMILY_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANS_FAMILY_ID = "BBBBBBBBBB";

    @Autowired
    private Trans8MarRepository trans8MarRepository;

    @Autowired
    private Trans8MarService trans8MarService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrans8MarMockMvc;

    private Trans8Mar trans8Mar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Trans8MarResource trans8MarResource = new Trans8MarResource(trans8MarService);
        this.restTrans8MarMockMvc = MockMvcBuilders.standaloneSetup(trans8MarResource)
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
    public static Trans8Mar createEntity(EntityManager em) {
        Trans8Mar trans8Mar = new Trans8Mar()
            .amount(DEFAULT_AMOUNT)
            .beginAccountBalance(DEFAULT_BEGIN_ACCOUNT_BALANCE)
            .isCd(DEFAULT_IS_CD)
            .currency(DEFAULT_CURRENCY)
            .datetime(DEFAULT_DATETIME)
            .endAccountBalance(DEFAULT_END_ACCOUNT_BALANCE)
            .entityFee(DEFAULT_ENTITY_FEE)
            .linkedIpayedTrans(DEFAULT_LINKED_IPAYED_TRANS)
            .linkedTrans(DEFAULT_LINKED_TRANS)
            .transFamilyId(DEFAULT_TRANS_FAMILY_ID);
        return trans8Mar;
    }

    @Before
    public void initTest() {
        trans8Mar = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrans8Mar() throws Exception {
        int databaseSizeBeforeCreate = trans8MarRepository.findAll().size();

        // Create the Trans8Mar
        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isCreated());

        // Validate the Trans8Mar in the database
        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeCreate + 1);
        Trans8Mar testTrans8Mar = trans8MarList.get(trans8MarList.size() - 1);
        assertThat(testTrans8Mar.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testTrans8Mar.getBeginAccountBalance()).isEqualTo(DEFAULT_BEGIN_ACCOUNT_BALANCE);
        assertThat(testTrans8Mar.getIsCd()).isEqualTo(DEFAULT_IS_CD);
        assertThat(testTrans8Mar.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testTrans8Mar.getDatetime()).isEqualTo(DEFAULT_DATETIME);
        assertThat(testTrans8Mar.getEndAccountBalance()).isEqualTo(DEFAULT_END_ACCOUNT_BALANCE);
        assertThat(testTrans8Mar.getEntityFee()).isEqualTo(DEFAULT_ENTITY_FEE);
        assertThat(testTrans8Mar.getLinkedIpayedTrans()).isEqualTo(DEFAULT_LINKED_IPAYED_TRANS);
        assertThat(testTrans8Mar.getLinkedTrans()).isEqualTo(DEFAULT_LINKED_TRANS);
        assertThat(testTrans8Mar.getTransFamilyId()).isEqualTo(DEFAULT_TRANS_FAMILY_ID);
    }

    @Test
    @Transactional
    public void createTrans8MarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trans8MarRepository.findAll().size();

        // Create the Trans8Mar with an existing ID
        trans8Mar.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        // Validate the Trans8Mar in the database
        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setAmount(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBeginAccountBalanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setBeginAccountBalance(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCdIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setIsCd(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setCurrency(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatetimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setDatetime(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndAccountBalanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setEndAccountBalance(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntityFeeIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setEntityFee(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLinkedIpayedTransIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setLinkedIpayedTrans(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLinkedTransIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setLinkedTrans(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransFamilyIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = trans8MarRepository.findAll().size();
        // set the field null
        trans8Mar.setTransFamilyId(null);

        // Create the Trans8Mar, which fails.

        restTrans8MarMockMvc.perform(post("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isBadRequest());

        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTrans8Mars() throws Exception {
        // Initialize the database
        trans8MarRepository.saveAndFlush(trans8Mar);

        // Get all the trans8MarList
        restTrans8MarMockMvc.perform(get("/api/trans-8-mars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trans8Mar.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].beginAccountBalance").value(hasItem(DEFAULT_BEGIN_ACCOUNT_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].isCd").value(hasItem(DEFAULT_IS_CD.toString())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].datetime").value(hasItem(DEFAULT_DATETIME.toString())))
            .andExpect(jsonPath("$.[*].endAccountBalance").value(hasItem(DEFAULT_END_ACCOUNT_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].entityFee").value(hasItem(DEFAULT_ENTITY_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].linkedIpayedTrans").value(hasItem(DEFAULT_LINKED_IPAYED_TRANS.toString())))
            .andExpect(jsonPath("$.[*].linkedTrans").value(hasItem(DEFAULT_LINKED_TRANS.toString())))
            .andExpect(jsonPath("$.[*].transFamilyId").value(hasItem(DEFAULT_TRANS_FAMILY_ID.toString())));
    }

    @Test
    @Transactional
    public void getTrans8Mar() throws Exception {
        // Initialize the database
        trans8MarRepository.saveAndFlush(trans8Mar);

        // Get the trans8Mar
        restTrans8MarMockMvc.perform(get("/api/trans-8-mars/{id}", trans8Mar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trans8Mar.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.beginAccountBalance").value(DEFAULT_BEGIN_ACCOUNT_BALANCE.doubleValue()))
            .andExpect(jsonPath("$.isCd").value(DEFAULT_IS_CD.toString()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.datetime").value(DEFAULT_DATETIME.toString()))
            .andExpect(jsonPath("$.endAccountBalance").value(DEFAULT_END_ACCOUNT_BALANCE.doubleValue()))
            .andExpect(jsonPath("$.entityFee").value(DEFAULT_ENTITY_FEE.doubleValue()))
            .andExpect(jsonPath("$.linkedIpayedTrans").value(DEFAULT_LINKED_IPAYED_TRANS.toString()))
            .andExpect(jsonPath("$.linkedTrans").value(DEFAULT_LINKED_TRANS.toString()))
            .andExpect(jsonPath("$.transFamilyId").value(DEFAULT_TRANS_FAMILY_ID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrans8Mar() throws Exception {
        // Get the trans8Mar
        restTrans8MarMockMvc.perform(get("/api/trans-8-mars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrans8Mar() throws Exception {
        // Initialize the database
        trans8MarService.save(trans8Mar);

        int databaseSizeBeforeUpdate = trans8MarRepository.findAll().size();

        // Update the trans8Mar
        Trans8Mar updatedTrans8Mar = trans8MarRepository.findOne(trans8Mar.getId());
        // Disconnect from session so that the updates on updatedTrans8Mar are not directly saved in db
        em.detach(updatedTrans8Mar);
        updatedTrans8Mar
            .amount(UPDATED_AMOUNT)
            .beginAccountBalance(UPDATED_BEGIN_ACCOUNT_BALANCE)
            .isCd(UPDATED_IS_CD)
            .currency(UPDATED_CURRENCY)
            .datetime(UPDATED_DATETIME)
            .endAccountBalance(UPDATED_END_ACCOUNT_BALANCE)
            .entityFee(UPDATED_ENTITY_FEE)
            .linkedIpayedTrans(UPDATED_LINKED_IPAYED_TRANS)
            .linkedTrans(UPDATED_LINKED_TRANS)
            .transFamilyId(UPDATED_TRANS_FAMILY_ID);

        restTrans8MarMockMvc.perform(put("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTrans8Mar)))
            .andExpect(status().isOk());

        // Validate the Trans8Mar in the database
        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeUpdate);
        Trans8Mar testTrans8Mar = trans8MarList.get(trans8MarList.size() - 1);
        assertThat(testTrans8Mar.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testTrans8Mar.getBeginAccountBalance()).isEqualTo(UPDATED_BEGIN_ACCOUNT_BALANCE);
        assertThat(testTrans8Mar.getIsCd()).isEqualTo(UPDATED_IS_CD);
        assertThat(testTrans8Mar.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testTrans8Mar.getDatetime()).isEqualTo(UPDATED_DATETIME);
        assertThat(testTrans8Mar.getEndAccountBalance()).isEqualTo(UPDATED_END_ACCOUNT_BALANCE);
        assertThat(testTrans8Mar.getEntityFee()).isEqualTo(UPDATED_ENTITY_FEE);
        assertThat(testTrans8Mar.getLinkedIpayedTrans()).isEqualTo(UPDATED_LINKED_IPAYED_TRANS);
        assertThat(testTrans8Mar.getLinkedTrans()).isEqualTo(UPDATED_LINKED_TRANS);
        assertThat(testTrans8Mar.getTransFamilyId()).isEqualTo(UPDATED_TRANS_FAMILY_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingTrans8Mar() throws Exception {
        int databaseSizeBeforeUpdate = trans8MarRepository.findAll().size();

        // Create the Trans8Mar

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTrans8MarMockMvc.perform(put("/api/trans-8-mars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trans8Mar)))
            .andExpect(status().isCreated());

        // Validate the Trans8Mar in the database
        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTrans8Mar() throws Exception {
        // Initialize the database
        trans8MarService.save(trans8Mar);

        int databaseSizeBeforeDelete = trans8MarRepository.findAll().size();

        // Get the trans8Mar
        restTrans8MarMockMvc.perform(delete("/api/trans-8-mars/{id}", trans8Mar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trans8Mar> trans8MarList = trans8MarRepository.findAll();
        assertThat(trans8MarList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trans8Mar.class);
        Trans8Mar trans8Mar1 = new Trans8Mar();
        trans8Mar1.setId(1L);
        Trans8Mar trans8Mar2 = new Trans8Mar();
        trans8Mar2.setId(trans8Mar1.getId());
        assertThat(trans8Mar1).isEqualTo(trans8Mar2);
        trans8Mar2.setId(2L);
        assertThat(trans8Mar1).isNotEqualTo(trans8Mar2);
        trans8Mar1.setId(null);
        assertThat(trans8Mar1).isNotEqualTo(trans8Mar2);
    }
}
