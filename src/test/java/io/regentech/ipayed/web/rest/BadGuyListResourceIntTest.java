package io.regentech.ipayed.web.rest;

import io.regentech.ipayed.IpayedApp;

import io.regentech.ipayed.domain.BadGuyList;
import io.regentech.ipayed.repository.BadGuyListRepository;
import io.regentech.ipayed.service.BadGuyListService;
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
 * Test class for the BadGuyListResource REST controller.
 *
 * @see BadGuyListResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IpayedApp.class)
public class BadGuyListResourceIntTest {

    private static final String DEFAULT_UNIQUE_RECORD = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_RECORD = "BBBBBBBBBB";

    private static final String DEFAULT_SDN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SDN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SDN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SDN_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SANCTIONS_PROGRAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SANCTIONS_PROGRAM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE_OF_INDIVIDUAL = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_OF_INDIVIDUAL = "BBBBBBBBBB";

    private static final String DEFAULT_VESSEL_CALL_SIGN = "AAAAAAAAAA";
    private static final String UPDATED_VESSEL_CALL_SIGN = "BBBBBBBBBB";

    private static final String DEFAULT_VESSEL_TONNAGE = "AAAAAAAAAA";
    private static final String UPDATED_VESSEL_TONNAGE = "BBBBBBBBBB";

    private static final String DEFAULT_GROSS_REGISTERED_TONNAGE = "AAAAAAAAAA";
    private static final String UPDATED_GROSS_REGISTERED_TONNAGE = "BBBBBBBBBB";

    private static final String DEFAULT_IS_VESSEL = "AAAAAAAAAA";
    private static final String UPDATED_IS_VESSEL = "BBBBBBBBBB";

    private static final String DEFAULT_VESSEL_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_VESSEL_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private BadGuyListRepository badGuyListRepository;

    @Autowired
    private BadGuyListService badGuyListService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBadGuyListMockMvc;

    private BadGuyList badGuyList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BadGuyListResource badGuyListResource = new BadGuyListResource(badGuyListService);
        this.restBadGuyListMockMvc = MockMvcBuilders.standaloneSetup(badGuyListResource)
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
    public static BadGuyList createEntity(EntityManager em) {
        BadGuyList badGuyList = new BadGuyList()
            .uniqueRecord(DEFAULT_UNIQUE_RECORD)
            .sdnName(DEFAULT_SDN_NAME)
            .sdnType(DEFAULT_SDN_TYPE)
            .sanctionsProgramName(DEFAULT_SANCTIONS_PROGRAM_NAME)
            .titleOfIndividual(DEFAULT_TITLE_OF_INDIVIDUAL)
            .vesselCallSign(DEFAULT_VESSEL_CALL_SIGN)
            .vesselTonnage(DEFAULT_VESSEL_TONNAGE)
            .grossRegisteredTonnage(DEFAULT_GROSS_REGISTERED_TONNAGE)
            .isVessel(DEFAULT_IS_VESSEL)
            .vesselOwner(DEFAULT_VESSEL_OWNER)
            .remark(DEFAULT_REMARK);
        return badGuyList;
    }

    @Before
    public void initTest() {
        badGuyList = createEntity(em);
    }

    @Test
    @Transactional
    public void createBadGuyList() throws Exception {
        int databaseSizeBeforeCreate = badGuyListRepository.findAll().size();

        // Create the BadGuyList
        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isCreated());

        // Validate the BadGuyList in the database
        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeCreate + 1);
        BadGuyList testBadGuyList = badGuyListList.get(badGuyListList.size() - 1);
        assertThat(testBadGuyList.getUniqueRecord()).isEqualTo(DEFAULT_UNIQUE_RECORD);
        assertThat(testBadGuyList.getSdnName()).isEqualTo(DEFAULT_SDN_NAME);
        assertThat(testBadGuyList.getSdnType()).isEqualTo(DEFAULT_SDN_TYPE);
        assertThat(testBadGuyList.getSanctionsProgramName()).isEqualTo(DEFAULT_SANCTIONS_PROGRAM_NAME);
        assertThat(testBadGuyList.getTitleOfIndividual()).isEqualTo(DEFAULT_TITLE_OF_INDIVIDUAL);
        assertThat(testBadGuyList.getVesselCallSign()).isEqualTo(DEFAULT_VESSEL_CALL_SIGN);
        assertThat(testBadGuyList.getVesselTonnage()).isEqualTo(DEFAULT_VESSEL_TONNAGE);
        assertThat(testBadGuyList.getGrossRegisteredTonnage()).isEqualTo(DEFAULT_GROSS_REGISTERED_TONNAGE);
        assertThat(testBadGuyList.getIsVessel()).isEqualTo(DEFAULT_IS_VESSEL);
        assertThat(testBadGuyList.getVesselOwner()).isEqualTo(DEFAULT_VESSEL_OWNER);
        assertThat(testBadGuyList.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createBadGuyListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = badGuyListRepository.findAll().size();

        // Create the BadGuyList with an existing ID
        badGuyList.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        // Validate the BadGuyList in the database
        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUniqueRecordIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setUniqueRecord(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSdnNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setSdnName(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSdnTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setSdnType(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSanctionsProgramNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setSanctionsProgramName(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleOfIndividualIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setTitleOfIndividual(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVesselCallSignIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setVesselCallSign(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVesselTonnageIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setVesselTonnage(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGrossRegisteredTonnageIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setGrossRegisteredTonnage(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsVesselIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setIsVessel(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVesselOwnerIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setVesselOwner(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemarkIsRequired() throws Exception {
        int databaseSizeBeforeTest = badGuyListRepository.findAll().size();
        // set the field null
        badGuyList.setRemark(null);

        // Create the BadGuyList, which fails.

        restBadGuyListMockMvc.perform(post("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isBadRequest());

        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBadGuyLists() throws Exception {
        // Initialize the database
        badGuyListRepository.saveAndFlush(badGuyList);

        // Get all the badGuyListList
        restBadGuyListMockMvc.perform(get("/api/bad-guy-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(badGuyList.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniqueRecord").value(hasItem(DEFAULT_UNIQUE_RECORD.toString())))
            .andExpect(jsonPath("$.[*].sdnName").value(hasItem(DEFAULT_SDN_NAME.toString())))
            .andExpect(jsonPath("$.[*].sdnType").value(hasItem(DEFAULT_SDN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].sanctionsProgramName").value(hasItem(DEFAULT_SANCTIONS_PROGRAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].titleOfIndividual").value(hasItem(DEFAULT_TITLE_OF_INDIVIDUAL.toString())))
            .andExpect(jsonPath("$.[*].vesselCallSign").value(hasItem(DEFAULT_VESSEL_CALL_SIGN.toString())))
            .andExpect(jsonPath("$.[*].vesselTonnage").value(hasItem(DEFAULT_VESSEL_TONNAGE.toString())))
            .andExpect(jsonPath("$.[*].grossRegisteredTonnage").value(hasItem(DEFAULT_GROSS_REGISTERED_TONNAGE.toString())))
            .andExpect(jsonPath("$.[*].isVessel").value(hasItem(DEFAULT_IS_VESSEL.toString())))
            .andExpect(jsonPath("$.[*].vesselOwner").value(hasItem(DEFAULT_VESSEL_OWNER.toString())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())));
    }

    @Test
    @Transactional
    public void getBadGuyList() throws Exception {
        // Initialize the database
        badGuyListRepository.saveAndFlush(badGuyList);

        // Get the badGuyList
        restBadGuyListMockMvc.perform(get("/api/bad-guy-lists/{id}", badGuyList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(badGuyList.getId().intValue()))
            .andExpect(jsonPath("$.uniqueRecord").value(DEFAULT_UNIQUE_RECORD.toString()))
            .andExpect(jsonPath("$.sdnName").value(DEFAULT_SDN_NAME.toString()))
            .andExpect(jsonPath("$.sdnType").value(DEFAULT_SDN_TYPE.toString()))
            .andExpect(jsonPath("$.sanctionsProgramName").value(DEFAULT_SANCTIONS_PROGRAM_NAME.toString()))
            .andExpect(jsonPath("$.titleOfIndividual").value(DEFAULT_TITLE_OF_INDIVIDUAL.toString()))
            .andExpect(jsonPath("$.vesselCallSign").value(DEFAULT_VESSEL_CALL_SIGN.toString()))
            .andExpect(jsonPath("$.vesselTonnage").value(DEFAULT_VESSEL_TONNAGE.toString()))
            .andExpect(jsonPath("$.grossRegisteredTonnage").value(DEFAULT_GROSS_REGISTERED_TONNAGE.toString()))
            .andExpect(jsonPath("$.isVessel").value(DEFAULT_IS_VESSEL.toString()))
            .andExpect(jsonPath("$.vesselOwner").value(DEFAULT_VESSEL_OWNER.toString()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBadGuyList() throws Exception {
        // Get the badGuyList
        restBadGuyListMockMvc.perform(get("/api/bad-guy-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBadGuyList() throws Exception {
        // Initialize the database
        badGuyListService.save(badGuyList);

        int databaseSizeBeforeUpdate = badGuyListRepository.findAll().size();

        // Update the badGuyList
        BadGuyList updatedBadGuyList = badGuyListRepository.findOne(badGuyList.getId());
        // Disconnect from session so that the updates on updatedBadGuyList are not directly saved in db
        em.detach(updatedBadGuyList);
        updatedBadGuyList
            .uniqueRecord(UPDATED_UNIQUE_RECORD)
            .sdnName(UPDATED_SDN_NAME)
            .sdnType(UPDATED_SDN_TYPE)
            .sanctionsProgramName(UPDATED_SANCTIONS_PROGRAM_NAME)
            .titleOfIndividual(UPDATED_TITLE_OF_INDIVIDUAL)
            .vesselCallSign(UPDATED_VESSEL_CALL_SIGN)
            .vesselTonnage(UPDATED_VESSEL_TONNAGE)
            .grossRegisteredTonnage(UPDATED_GROSS_REGISTERED_TONNAGE)
            .isVessel(UPDATED_IS_VESSEL)
            .vesselOwner(UPDATED_VESSEL_OWNER)
            .remark(UPDATED_REMARK);

        restBadGuyListMockMvc.perform(put("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBadGuyList)))
            .andExpect(status().isOk());

        // Validate the BadGuyList in the database
        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeUpdate);
        BadGuyList testBadGuyList = badGuyListList.get(badGuyListList.size() - 1);
        assertThat(testBadGuyList.getUniqueRecord()).isEqualTo(UPDATED_UNIQUE_RECORD);
        assertThat(testBadGuyList.getSdnName()).isEqualTo(UPDATED_SDN_NAME);
        assertThat(testBadGuyList.getSdnType()).isEqualTo(UPDATED_SDN_TYPE);
        assertThat(testBadGuyList.getSanctionsProgramName()).isEqualTo(UPDATED_SANCTIONS_PROGRAM_NAME);
        assertThat(testBadGuyList.getTitleOfIndividual()).isEqualTo(UPDATED_TITLE_OF_INDIVIDUAL);
        assertThat(testBadGuyList.getVesselCallSign()).isEqualTo(UPDATED_VESSEL_CALL_SIGN);
        assertThat(testBadGuyList.getVesselTonnage()).isEqualTo(UPDATED_VESSEL_TONNAGE);
        assertThat(testBadGuyList.getGrossRegisteredTonnage()).isEqualTo(UPDATED_GROSS_REGISTERED_TONNAGE);
        assertThat(testBadGuyList.getIsVessel()).isEqualTo(UPDATED_IS_VESSEL);
        assertThat(testBadGuyList.getVesselOwner()).isEqualTo(UPDATED_VESSEL_OWNER);
        assertThat(testBadGuyList.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingBadGuyList() throws Exception {
        int databaseSizeBeforeUpdate = badGuyListRepository.findAll().size();

        // Create the BadGuyList

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBadGuyListMockMvc.perform(put("/api/bad-guy-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(badGuyList)))
            .andExpect(status().isCreated());

        // Validate the BadGuyList in the database
        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBadGuyList() throws Exception {
        // Initialize the database
        badGuyListService.save(badGuyList);

        int databaseSizeBeforeDelete = badGuyListRepository.findAll().size();

        // Get the badGuyList
        restBadGuyListMockMvc.perform(delete("/api/bad-guy-lists/{id}", badGuyList.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BadGuyList> badGuyListList = badGuyListRepository.findAll();
        assertThat(badGuyListList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BadGuyList.class);
        BadGuyList badGuyList1 = new BadGuyList();
        badGuyList1.setId(1L);
        BadGuyList badGuyList2 = new BadGuyList();
        badGuyList2.setId(badGuyList1.getId());
        assertThat(badGuyList1).isEqualTo(badGuyList2);
        badGuyList2.setId(2L);
        assertThat(badGuyList1).isNotEqualTo(badGuyList2);
        badGuyList1.setId(null);
        assertThat(badGuyList1).isNotEqualTo(badGuyList2);
    }
}
