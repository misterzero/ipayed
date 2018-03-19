package io.regentech.ipayed.web.rest;

import io.regentech.ipayed.IpayedApp;

import io.regentech.ipayed.domain.Prospect;
import io.regentech.ipayed.repository.ProspectRepository;
import io.regentech.ipayed.service.ProspectService;
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
 * Test class for the ProspectResource REST controller.
 *
 * @see ProspectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpayedApp.class)
public class ProspectResourceIntTest {

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DOB = "AAAAAAAAAA";
    private static final String UPDATED_DOB = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_IS_POLITICALLY_EXPOSED = "AAAAAAAAAA";
    private static final String UPDATED_IS_POLITICALLY_EXPOSED = "BBBBBBBBBB";

    private static final String DEFAULT_PROSPECT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROSPECT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TIN = "AAAAAAAAAA";
    private static final String UPDATED_TIN = "BBBBBBBBBB";

    private static final String DEFAULT_TIN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TIN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    @Autowired
    private ProspectRepository prospectRepository;

    @Autowired
    private ProspectService prospectService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProspectMockMvc;

    private Prospect prospect;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProspectResource prospectResource = new ProspectResource(prospectService);
        this.restProspectMockMvc = MockMvcBuilders.standaloneSetup(prospectResource)
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
    public static Prospect createEntity(EntityManager em) {
        Prospect prospect = new Prospect()
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .businessName(DEFAULT_BUSINESS_NAME)
            .city(DEFAULT_CITY)
            .country(DEFAULT_COUNTRY)
            .customerType(DEFAULT_CUSTOMER_TYPE)
            .dob(DEFAULT_DOB)
            .email(DEFAULT_EMAIL)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .phone(DEFAULT_PHONE)
            .isPoliticallyExposed(DEFAULT_IS_POLITICALLY_EXPOSED)
            .prospectId(DEFAULT_PROSPECT_ID)
            .state(DEFAULT_STATE)
            .status(DEFAULT_STATUS)
            .tin(DEFAULT_TIN)
            .tinType(DEFAULT_TIN_TYPE)
            .title(DEFAULT_TITLE)
            .zip(DEFAULT_ZIP);
        return prospect;
    }

    @Before
    public void initTest() {
        prospect = createEntity(em);
    }

    @Test
    @Transactional
    public void createProspect() throws Exception {
        int databaseSizeBeforeCreate = prospectRepository.findAll().size();

        // Create the Prospect
        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isCreated());

        // Validate the Prospect in the database
        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeCreate + 1);
        Prospect testProspect = prospectList.get(prospectList.size() - 1);
        assertThat(testProspect.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testProspect.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testProspect.getBusinessName()).isEqualTo(DEFAULT_BUSINESS_NAME);
        assertThat(testProspect.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testProspect.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testProspect.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testProspect.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testProspect.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProspect.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testProspect.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testProspect.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testProspect.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testProspect.getIsPoliticallyExposed()).isEqualTo(DEFAULT_IS_POLITICALLY_EXPOSED);
        assertThat(testProspect.getProspectId()).isEqualTo(DEFAULT_PROSPECT_ID);
        assertThat(testProspect.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testProspect.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProspect.getTin()).isEqualTo(DEFAULT_TIN);
        assertThat(testProspect.getTinType()).isEqualTo(DEFAULT_TIN_TYPE);
        assertThat(testProspect.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProspect.getZip()).isEqualTo(DEFAULT_ZIP);
    }

    @Test
    @Transactional
    public void createProspectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prospectRepository.findAll().size();

        // Create the Prospect with an existing ID
        prospect.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        // Validate the Prospect in the database
        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAddress1IsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setAddress1(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setCity(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setCountry(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCustomerTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setCustomerType(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDobIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setDob(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setEmail(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setFirstName(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setLastName(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setPhone(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsPoliticallyExposedIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setIsPoliticallyExposed(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProspectIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setProspectId(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setState(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setStatus(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTinIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setTin(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZipIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRepository.findAll().size();
        // set the field null
        prospect.setZip(null);

        // Create the Prospect, which fails.

        restProspectMockMvc.perform(post("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isBadRequest());

        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProspects() throws Exception {
        // Initialize the database
        prospectRepository.saveAndFlush(prospect);

        // Get all the prospectList
        restProspectMockMvc.perform(get("/api/prospects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prospect.getId().intValue())))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1.toString())))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2.toString())))
            .andExpect(jsonPath("$.[*].businessName").value(hasItem(DEFAULT_BUSINESS_NAME.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].customerType").value(hasItem(DEFAULT_CUSTOMER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].isPoliticallyExposed").value(hasItem(DEFAULT_IS_POLITICALLY_EXPOSED.toString())))
            .andExpect(jsonPath("$.[*].prospectId").value(hasItem(DEFAULT_PROSPECT_ID.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].tin").value(hasItem(DEFAULT_TIN.toString())))
            .andExpect(jsonPath("$.[*].tinType").value(hasItem(DEFAULT_TIN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP.toString())));
    }

    @Test
    @Transactional
    public void getProspect() throws Exception {
        // Initialize the database
        prospectRepository.saveAndFlush(prospect);

        // Get the prospect
        restProspectMockMvc.perform(get("/api/prospects/{id}", prospect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(prospect.getId().intValue()))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.businessName").value(DEFAULT_BUSINESS_NAME.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.customerType").value(DEFAULT_CUSTOMER_TYPE.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.isPoliticallyExposed").value(DEFAULT_IS_POLITICALLY_EXPOSED.toString()))
            .andExpect(jsonPath("$.prospectId").value(DEFAULT_PROSPECT_ID.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.tin").value(DEFAULT_TIN.toString()))
            .andExpect(jsonPath("$.tinType").value(DEFAULT_TIN_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProspect() throws Exception {
        // Get the prospect
        restProspectMockMvc.perform(get("/api/prospects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProspect() throws Exception {
        // Initialize the database
        prospectService.save(prospect);

        int databaseSizeBeforeUpdate = prospectRepository.findAll().size();

        // Update the prospect
        Prospect updatedProspect = prospectRepository.findOne(prospect.getId());
        // Disconnect from session so that the updates on updatedProspect are not directly saved in db
        em.detach(updatedProspect);
        updatedProspect
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .businessName(UPDATED_BUSINESS_NAME)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .dob(UPDATED_DOB)
            .email(UPDATED_EMAIL)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .phone(UPDATED_PHONE)
            .isPoliticallyExposed(UPDATED_IS_POLITICALLY_EXPOSED)
            .prospectId(UPDATED_PROSPECT_ID)
            .state(UPDATED_STATE)
            .status(UPDATED_STATUS)
            .tin(UPDATED_TIN)
            .tinType(UPDATED_TIN_TYPE)
            .title(UPDATED_TITLE)
            .zip(UPDATED_ZIP);

        restProspectMockMvc.perform(put("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProspect)))
            .andExpect(status().isOk());

        // Validate the Prospect in the database
        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeUpdate);
        Prospect testProspect = prospectList.get(prospectList.size() - 1);
        assertThat(testProspect.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testProspect.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testProspect.getBusinessName()).isEqualTo(UPDATED_BUSINESS_NAME);
        assertThat(testProspect.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testProspect.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testProspect.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testProspect.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testProspect.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProspect.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testProspect.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testProspect.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testProspect.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testProspect.getIsPoliticallyExposed()).isEqualTo(UPDATED_IS_POLITICALLY_EXPOSED);
        assertThat(testProspect.getProspectId()).isEqualTo(UPDATED_PROSPECT_ID);
        assertThat(testProspect.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testProspect.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProspect.getTin()).isEqualTo(UPDATED_TIN);
        assertThat(testProspect.getTinType()).isEqualTo(UPDATED_TIN_TYPE);
        assertThat(testProspect.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProspect.getZip()).isEqualTo(UPDATED_ZIP);
    }

    @Test
    @Transactional
    public void updateNonExistingProspect() throws Exception {
        int databaseSizeBeforeUpdate = prospectRepository.findAll().size();

        // Create the Prospect

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProspectMockMvc.perform(put("/api/prospects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prospect)))
            .andExpect(status().isCreated());

        // Validate the Prospect in the database
        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProspect() throws Exception {
        // Initialize the database
        prospectService.save(prospect);

        int databaseSizeBeforeDelete = prospectRepository.findAll().size();

        // Get the prospect
        restProspectMockMvc.perform(delete("/api/prospects/{id}", prospect.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Prospect> prospectList = prospectRepository.findAll();
        assertThat(prospectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Prospect.class);
        Prospect prospect1 = new Prospect();
        prospect1.setId(1L);
        Prospect prospect2 = new Prospect();
        prospect2.setId(prospect1.getId());
        assertThat(prospect1).isEqualTo(prospect2);
        prospect2.setId(2L);
        assertThat(prospect1).isNotEqualTo(prospect2);
        prospect1.setId(null);
        assertThat(prospect1).isNotEqualTo(prospect2);
    }
}
