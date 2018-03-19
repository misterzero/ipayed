package io.regentech.ipayed.web.rest;

import io.regentech.ipayed.IpayedApp;

import io.regentech.ipayed.domain.CustomerActivity;
import io.regentech.ipayed.repository.CustomerActivityRepository;
import io.regentech.ipayed.service.CustomerActivityService;
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
 * Test class for the CustomerActivityResource REST controller.
 *
 * @see CustomerActivityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpayedApp.class)
public class CustomerActivityResourceIntTest {

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_LOGIN_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_LOGIN_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_LENGTH_SECONDS = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_LENGTH_SECONDS = "BBBBBBBBBB";

    @Autowired
    private CustomerActivityRepository customerActivityRepository;

    @Autowired
    private CustomerActivityService customerActivityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCustomerActivityMockMvc;

    private CustomerActivity customerActivity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerActivityResource customerActivityResource = new CustomerActivityResource(customerActivityService);
        this.restCustomerActivityMockMvc = MockMvcBuilders.standaloneSetup(customerActivityResource)
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
    public static CustomerActivity createEntity(EntityManager em) {
        CustomerActivity customerActivity = new CustomerActivity()
            .ipAddress(DEFAULT_IP_ADDRESS)
            .lastLoginDate(DEFAULT_LAST_LOGIN_DATE)
            .sessionLengthSeconds(DEFAULT_SESSION_LENGTH_SECONDS);
        return customerActivity;
    }

    @Before
    public void initTest() {
        customerActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerActivity() throws Exception {
        int databaseSizeBeforeCreate = customerActivityRepository.findAll().size();

        // Create the CustomerActivity
        restCustomerActivityMockMvc.perform(post("/api/customer-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerActivity)))
            .andExpect(status().isCreated());

        // Validate the CustomerActivity in the database
        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerActivity testCustomerActivity = customerActivityList.get(customerActivityList.size() - 1);
        assertThat(testCustomerActivity.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testCustomerActivity.getLastLoginDate()).isEqualTo(DEFAULT_LAST_LOGIN_DATE);
        assertThat(testCustomerActivity.getSessionLengthSeconds()).isEqualTo(DEFAULT_SESSION_LENGTH_SECONDS);
    }

    @Test
    @Transactional
    public void createCustomerActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerActivityRepository.findAll().size();

        // Create the CustomerActivity with an existing ID
        customerActivity.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerActivityMockMvc.perform(post("/api/customer-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerActivity)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerActivity in the database
        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIpAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerActivityRepository.findAll().size();
        // set the field null
        customerActivity.setIpAddress(null);

        // Create the CustomerActivity, which fails.

        restCustomerActivityMockMvc.perform(post("/api/customer-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerActivity)))
            .andExpect(status().isBadRequest());

        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastLoginDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerActivityRepository.findAll().size();
        // set the field null
        customerActivity.setLastLoginDate(null);

        // Create the CustomerActivity, which fails.

        restCustomerActivityMockMvc.perform(post("/api/customer-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerActivity)))
            .andExpect(status().isBadRequest());

        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSessionLengthSecondsIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerActivityRepository.findAll().size();
        // set the field null
        customerActivity.setSessionLengthSeconds(null);

        // Create the CustomerActivity, which fails.

        restCustomerActivityMockMvc.perform(post("/api/customer-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerActivity)))
            .andExpect(status().isBadRequest());

        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustomerActivities() throws Exception {
        // Initialize the database
        customerActivityRepository.saveAndFlush(customerActivity);

        // Get all the customerActivityList
        restCustomerActivityMockMvc.perform(get("/api/customer-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].lastLoginDate").value(hasItem(DEFAULT_LAST_LOGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].sessionLengthSeconds").value(hasItem(DEFAULT_SESSION_LENGTH_SECONDS.toString())));
    }

    @Test
    @Transactional
    public void getCustomerActivity() throws Exception {
        // Initialize the database
        customerActivityRepository.saveAndFlush(customerActivity);

        // Get the customerActivity
        restCustomerActivityMockMvc.perform(get("/api/customer-activities/{id}", customerActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerActivity.getId().intValue()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS.toString()))
            .andExpect(jsonPath("$.lastLoginDate").value(DEFAULT_LAST_LOGIN_DATE.toString()))
            .andExpect(jsonPath("$.sessionLengthSeconds").value(DEFAULT_SESSION_LENGTH_SECONDS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerActivity() throws Exception {
        // Get the customerActivity
        restCustomerActivityMockMvc.perform(get("/api/customer-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerActivity() throws Exception {
        // Initialize the database
        customerActivityService.save(customerActivity);

        int databaseSizeBeforeUpdate = customerActivityRepository.findAll().size();

        // Update the customerActivity
        CustomerActivity updatedCustomerActivity = customerActivityRepository.findOne(customerActivity.getId());
        // Disconnect from session so that the updates on updatedCustomerActivity are not directly saved in db
        em.detach(updatedCustomerActivity);
        updatedCustomerActivity
            .ipAddress(UPDATED_IP_ADDRESS)
            .lastLoginDate(UPDATED_LAST_LOGIN_DATE)
            .sessionLengthSeconds(UPDATED_SESSION_LENGTH_SECONDS);

        restCustomerActivityMockMvc.perform(put("/api/customer-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomerActivity)))
            .andExpect(status().isOk());

        // Validate the CustomerActivity in the database
        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeUpdate);
        CustomerActivity testCustomerActivity = customerActivityList.get(customerActivityList.size() - 1);
        assertThat(testCustomerActivity.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testCustomerActivity.getLastLoginDate()).isEqualTo(UPDATED_LAST_LOGIN_DATE);
        assertThat(testCustomerActivity.getSessionLengthSeconds()).isEqualTo(UPDATED_SESSION_LENGTH_SECONDS);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerActivity() throws Exception {
        int databaseSizeBeforeUpdate = customerActivityRepository.findAll().size();

        // Create the CustomerActivity

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCustomerActivityMockMvc.perform(put("/api/customer-activities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerActivity)))
            .andExpect(status().isCreated());

        // Validate the CustomerActivity in the database
        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCustomerActivity() throws Exception {
        // Initialize the database
        customerActivityService.save(customerActivity);

        int databaseSizeBeforeDelete = customerActivityRepository.findAll().size();

        // Get the customerActivity
        restCustomerActivityMockMvc.perform(delete("/api/customer-activities/{id}", customerActivity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CustomerActivity> customerActivityList = customerActivityRepository.findAll();
        assertThat(customerActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerActivity.class);
        CustomerActivity customerActivity1 = new CustomerActivity();
        customerActivity1.setId(1L);
        CustomerActivity customerActivity2 = new CustomerActivity();
        customerActivity2.setId(customerActivity1.getId());
        assertThat(customerActivity1).isEqualTo(customerActivity2);
        customerActivity2.setId(2L);
        assertThat(customerActivity1).isNotEqualTo(customerActivity2);
        customerActivity1.setId(null);
        assertThat(customerActivity1).isNotEqualTo(customerActivity2);
    }
}
