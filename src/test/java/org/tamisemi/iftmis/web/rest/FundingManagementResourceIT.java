package org.tamisemi.iftmis.web.rest;

import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.FundingManagement;
import org.tamisemi.iftmis.domain.TheClusters;
import org.tamisemi.iftmis.domain.FindingSubCategory;
import org.tamisemi.iftmis.domain.FinancialYear;
import org.tamisemi.iftmis.domain.OrganisationUnitLevel;
import org.tamisemi.iftmis.repository.FundingManagementRepository;
import org.tamisemi.iftmis.service.FundingManagementService;
import org.tamisemi.iftmis.service.dto.FundingManagementDTO;
import org.tamisemi.iftmis.service.mapper.FundingManagementMapper;
import org.tamisemi.iftmis.service.dto.FundingManagementCriteria;
import org.tamisemi.iftmis.service.FundingManagementQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FundingManagementResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FundingManagementResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;
    private static final Integer SMALLER_LEVEL = 1 - 1;

    private static final String DEFAULT_CONDITIONS = "AAAAAAAAAA";
    private static final String UPDATED_CONDITIONS = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSES = "AAAAAAAAAA";
    private static final String UPDATED_CAUSES = "BBBBBBBBBB";

    private static final String DEFAULT_IMPACT = "AAAAAAAAAA";
    private static final String UPDATED_IMPACT = "BBBBBBBBBB";

    private static final String DEFAULT_IMPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_IMPLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMENDATION = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMENDATION = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGEMENT_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_MANAGEMENT_RESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_IMPLEMENTATION = "AAAAAAAAAA";
    private static final String UPDATED_IMPLEMENTATION = "BBBBBBBBBB";

    private static final String DEFAULT_AUDIT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTS = "BBBBBBBBBB";

    @Autowired
    private FundingManagementRepository fundingManagementRepository;

    @Autowired
    private FundingManagementMapper fundingManagementMapper;

    @Autowired
    private FundingManagementService fundingManagementService;

    @Autowired
    private FundingManagementQueryService fundingManagementQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundingManagementMockMvc;

    private FundingManagement fundingManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundingManagement createEntity(EntityManager em) {
        FundingManagement fundingManagement = new FundingManagement()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .code(DEFAULT_CODE)
            .level(DEFAULT_LEVEL)
            .conditions(DEFAULT_CONDITIONS)
            .causes(DEFAULT_CAUSES)
            .impact(DEFAULT_IMPACT)
            .implication(DEFAULT_IMPLICATION)
            .recommendation(DEFAULT_RECOMMENDATION)
            .managementResponse(DEFAULT_MANAGEMENT_RESPONSE)
            .implementation(DEFAULT_IMPLEMENTATION)
            .auditComment(DEFAULT_AUDIT_COMMENT)
            .status(DEFAULT_STATUS)
            .contacts(DEFAULT_CONTACTS);
        // Add required entity
        TheClusters theClusters;
        if (TestUtil.findAll(em, TheClusters.class).isEmpty()) {
            theClusters = TheClustersResourceIT.createEntity(em);
            em.persist(theClusters);
            em.flush();
        } else {
            theClusters = TestUtil.findAll(em, TheClusters.class).get(0);
        }
        fundingManagement.setTheClusters(theClusters);
        // Add required entity
        FindingSubCategory findingSubCategory;
        if (TestUtil.findAll(em, FindingSubCategory.class).isEmpty()) {
            findingSubCategory = FindingSubCategoryResourceIT.createEntity(em);
            em.persist(findingSubCategory);
            em.flush();
        } else {
            findingSubCategory = TestUtil.findAll(em, FindingSubCategory.class).get(0);
        }
        fundingManagement.setFindingSubCategory(findingSubCategory);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        fundingManagement.setFinancialYear(financialYear);
        // Add required entity
        OrganisationUnitLevel organisationUnitLevel;
        if (TestUtil.findAll(em, OrganisationUnitLevel.class).isEmpty()) {
            organisationUnitLevel = OrganisationUnitLevelResourceIT.createEntity(em);
            em.persist(organisationUnitLevel);
            em.flush();
        } else {
            organisationUnitLevel = TestUtil.findAll(em, OrganisationUnitLevel.class).get(0);
        }
        fundingManagement.setOrganisationUnitLevel(organisationUnitLevel);
        return fundingManagement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundingManagement createUpdatedEntity(EntityManager em) {
        FundingManagement fundingManagement = new FundingManagement()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .code(UPDATED_CODE)
            .level(UPDATED_LEVEL)
            .conditions(UPDATED_CONDITIONS)
            .causes(UPDATED_CAUSES)
            .impact(UPDATED_IMPACT)
            .implication(UPDATED_IMPLICATION)
            .recommendation(UPDATED_RECOMMENDATION)
            .managementResponse(UPDATED_MANAGEMENT_RESPONSE)
            .implementation(UPDATED_IMPLEMENTATION)
            .auditComment(UPDATED_AUDIT_COMMENT)
            .status(UPDATED_STATUS)
            .contacts(UPDATED_CONTACTS);
        // Add required entity
        TheClusters theClusters;
        if (TestUtil.findAll(em, TheClusters.class).isEmpty()) {
            theClusters = TheClustersResourceIT.createUpdatedEntity(em);
            em.persist(theClusters);
            em.flush();
        } else {
            theClusters = TestUtil.findAll(em, TheClusters.class).get(0);
        }
        fundingManagement.setTheClusters(theClusters);
        // Add required entity
        FindingSubCategory findingSubCategory;
        if (TestUtil.findAll(em, FindingSubCategory.class).isEmpty()) {
            findingSubCategory = FindingSubCategoryResourceIT.createUpdatedEntity(em);
            em.persist(findingSubCategory);
            em.flush();
        } else {
            findingSubCategory = TestUtil.findAll(em, FindingSubCategory.class).get(0);
        }
        fundingManagement.setFindingSubCategory(findingSubCategory);
        // Add required entity
        FinancialYear financialYear;
        if (TestUtil.findAll(em, FinancialYear.class).isEmpty()) {
            financialYear = FinancialYearResourceIT.createUpdatedEntity(em);
            em.persist(financialYear);
            em.flush();
        } else {
            financialYear = TestUtil.findAll(em, FinancialYear.class).get(0);
        }
        fundingManagement.setFinancialYear(financialYear);
        // Add required entity
        OrganisationUnitLevel organisationUnitLevel;
        if (TestUtil.findAll(em, OrganisationUnitLevel.class).isEmpty()) {
            organisationUnitLevel = OrganisationUnitLevelResourceIT.createUpdatedEntity(em);
            em.persist(organisationUnitLevel);
            em.flush();
        } else {
            organisationUnitLevel = TestUtil.findAll(em, OrganisationUnitLevel.class).get(0);
        }
        fundingManagement.setOrganisationUnitLevel(organisationUnitLevel);
        return fundingManagement;
    }

    @BeforeEach
    public void initTest() {
        fundingManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createFundingManagement() throws Exception {
        int databaseSizeBeforeCreate = fundingManagementRepository.findAll().size();
        // Create the FundingManagement
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(fundingManagement);
        restFundingManagementMockMvc.perform(post("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isCreated());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeCreate + 1);
        FundingManagement testFundingManagement = fundingManagementList.get(fundingManagementList.size() - 1);
        assertThat(testFundingManagement.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFundingManagement.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFundingManagement.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFundingManagement.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testFundingManagement.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testFundingManagement.getConditions()).isEqualTo(DEFAULT_CONDITIONS);
        assertThat(testFundingManagement.getCauses()).isEqualTo(DEFAULT_CAUSES);
        assertThat(testFundingManagement.getImpact()).isEqualTo(DEFAULT_IMPACT);
        assertThat(testFundingManagement.getImplication()).isEqualTo(DEFAULT_IMPLICATION);
        assertThat(testFundingManagement.getRecommendation()).isEqualTo(DEFAULT_RECOMMENDATION);
        assertThat(testFundingManagement.getManagementResponse()).isEqualTo(DEFAULT_MANAGEMENT_RESPONSE);
        assertThat(testFundingManagement.getImplementation()).isEqualTo(DEFAULT_IMPLEMENTATION);
        assertThat(testFundingManagement.getAuditComment()).isEqualTo(DEFAULT_AUDIT_COMMENT);
        assertThat(testFundingManagement.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFundingManagement.getContacts()).isEqualTo(DEFAULT_CONTACTS);
    }

    @Test
    @Transactional
    public void createFundingManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fundingManagementRepository.findAll().size();

        // Create the FundingManagement with an existing ID
        fundingManagement.setId(1L);
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(fundingManagement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundingManagementMockMvc.perform(post("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFundingManagements() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList
        restFundingManagementMockMvc.perform(get("/api/funding-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundingManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].conditions").value(hasItem(DEFAULT_CONDITIONS)))
            .andExpect(jsonPath("$.[*].causes").value(hasItem(DEFAULT_CAUSES)))
            .andExpect(jsonPath("$.[*].impact").value(hasItem(DEFAULT_IMPACT)))
            .andExpect(jsonPath("$.[*].implication").value(hasItem(DEFAULT_IMPLICATION)))
            .andExpect(jsonPath("$.[*].recommendation").value(hasItem(DEFAULT_RECOMMENDATION)))
            .andExpect(jsonPath("$.[*].managementResponse").value(hasItem(DEFAULT_MANAGEMENT_RESPONSE)))
            .andExpect(jsonPath("$.[*].implementation").value(hasItem(DEFAULT_IMPLEMENTATION)))
            .andExpect(jsonPath("$.[*].auditComment").value(hasItem(DEFAULT_AUDIT_COMMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].contacts").value(hasItem(DEFAULT_CONTACTS)));
    }
    
    @Test
    @Transactional
    public void getFundingManagement() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get the fundingManagement
        restFundingManagementMockMvc.perform(get("/api/funding-managements/{id}", fundingManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundingManagement.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.conditions").value(DEFAULT_CONDITIONS))
            .andExpect(jsonPath("$.causes").value(DEFAULT_CAUSES))
            .andExpect(jsonPath("$.impact").value(DEFAULT_IMPACT))
            .andExpect(jsonPath("$.implication").value(DEFAULT_IMPLICATION))
            .andExpect(jsonPath("$.recommendation").value(DEFAULT_RECOMMENDATION))
            .andExpect(jsonPath("$.managementResponse").value(DEFAULT_MANAGEMENT_RESPONSE))
            .andExpect(jsonPath("$.implementation").value(DEFAULT_IMPLEMENTATION))
            .andExpect(jsonPath("$.auditComment").value(DEFAULT_AUDIT_COMMENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.contacts").value(DEFAULT_CONTACTS));
    }


    @Test
    @Transactional
    public void getFundingManagementsByIdFiltering() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        Long id = fundingManagement.getId();

        defaultFundingManagementShouldBeFound("id.equals=" + id);
        defaultFundingManagementShouldNotBeFound("id.notEquals=" + id);

        defaultFundingManagementShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFundingManagementShouldNotBeFound("id.greaterThan=" + id);

        defaultFundingManagementShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFundingManagementShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title equals to DEFAULT_TITLE
        defaultFundingManagementShouldBeFound("title.equals=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title equals to UPDATED_TITLE
        defaultFundingManagementShouldNotBeFound("title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title not equals to DEFAULT_TITLE
        defaultFundingManagementShouldNotBeFound("title.notEquals=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title not equals to UPDATED_TITLE
        defaultFundingManagementShouldBeFound("title.notEquals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title in DEFAULT_TITLE or UPDATED_TITLE
        defaultFundingManagementShouldBeFound("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE);

        // Get all the fundingManagementList where title equals to UPDATED_TITLE
        defaultFundingManagementShouldNotBeFound("title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title is not null
        defaultFundingManagementShouldBeFound("title.specified=true");

        // Get all the fundingManagementList where title is null
        defaultFundingManagementShouldNotBeFound("title.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByTitleContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title contains DEFAULT_TITLE
        defaultFundingManagementShouldBeFound("title.contains=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title contains UPDATED_TITLE
        defaultFundingManagementShouldNotBeFound("title.contains=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTitleNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where title does not contain DEFAULT_TITLE
        defaultFundingManagementShouldNotBeFound("title.doesNotContain=" + DEFAULT_TITLE);

        // Get all the fundingManagementList where title does not contain UPDATED_TITLE
        defaultFundingManagementShouldBeFound("title.doesNotContain=" + UPDATED_TITLE);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description equals to DEFAULT_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description equals to UPDATED_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description not equals to DEFAULT_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description not equals to UPDATED_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the fundingManagementList where description equals to UPDATED_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description is not null
        defaultFundingManagementShouldBeFound("description.specified=true");

        // Get all the fundingManagementList where description is null
        defaultFundingManagementShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description contains DEFAULT_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description contains UPDATED_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where description does not contain DEFAULT_DESCRIPTION
        defaultFundingManagementShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the fundingManagementList where description does not contain UPDATED_DESCRIPTION
        defaultFundingManagementShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where type equals to DEFAULT_TYPE
        defaultFundingManagementShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the fundingManagementList where type equals to UPDATED_TYPE
        defaultFundingManagementShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where type not equals to DEFAULT_TYPE
        defaultFundingManagementShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

        // Get all the fundingManagementList where type not equals to UPDATED_TYPE
        defaultFundingManagementShouldBeFound("type.notEquals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultFundingManagementShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the fundingManagementList where type equals to UPDATED_TYPE
        defaultFundingManagementShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where type is not null
        defaultFundingManagementShouldBeFound("type.specified=true");

        // Get all the fundingManagementList where type is null
        defaultFundingManagementShouldNotBeFound("type.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByTypeContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where type contains DEFAULT_TYPE
        defaultFundingManagementShouldBeFound("type.contains=" + DEFAULT_TYPE);

        // Get all the fundingManagementList where type contains UPDATED_TYPE
        defaultFundingManagementShouldNotBeFound("type.contains=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByTypeNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where type does not contain DEFAULT_TYPE
        defaultFundingManagementShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

        // Get all the fundingManagementList where type does not contain UPDATED_TYPE
        defaultFundingManagementShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where code equals to DEFAULT_CODE
        defaultFundingManagementShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the fundingManagementList where code equals to UPDATED_CODE
        defaultFundingManagementShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where code not equals to DEFAULT_CODE
        defaultFundingManagementShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the fundingManagementList where code not equals to UPDATED_CODE
        defaultFundingManagementShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where code in DEFAULT_CODE or UPDATED_CODE
        defaultFundingManagementShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the fundingManagementList where code equals to UPDATED_CODE
        defaultFundingManagementShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where code is not null
        defaultFundingManagementShouldBeFound("code.specified=true");

        // Get all the fundingManagementList where code is null
        defaultFundingManagementShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByCodeContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where code contains DEFAULT_CODE
        defaultFundingManagementShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the fundingManagementList where code contains UPDATED_CODE
        defaultFundingManagementShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where code does not contain DEFAULT_CODE
        defaultFundingManagementShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the fundingManagementList where code does not contain UPDATED_CODE
        defaultFundingManagementShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level equals to DEFAULT_LEVEL
        defaultFundingManagementShouldBeFound("level.equals=" + DEFAULT_LEVEL);

        // Get all the fundingManagementList where level equals to UPDATED_LEVEL
        defaultFundingManagementShouldNotBeFound("level.equals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level not equals to DEFAULT_LEVEL
        defaultFundingManagementShouldNotBeFound("level.notEquals=" + DEFAULT_LEVEL);

        // Get all the fundingManagementList where level not equals to UPDATED_LEVEL
        defaultFundingManagementShouldBeFound("level.notEquals=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level in DEFAULT_LEVEL or UPDATED_LEVEL
        defaultFundingManagementShouldBeFound("level.in=" + DEFAULT_LEVEL + "," + UPDATED_LEVEL);

        // Get all the fundingManagementList where level equals to UPDATED_LEVEL
        defaultFundingManagementShouldNotBeFound("level.in=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level is not null
        defaultFundingManagementShouldBeFound("level.specified=true");

        // Get all the fundingManagementList where level is null
        defaultFundingManagementShouldNotBeFound("level.specified=false");
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level is greater than or equal to DEFAULT_LEVEL
        defaultFundingManagementShouldBeFound("level.greaterThanOrEqual=" + DEFAULT_LEVEL);

        // Get all the fundingManagementList where level is greater than or equal to UPDATED_LEVEL
        defaultFundingManagementShouldNotBeFound("level.greaterThanOrEqual=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level is less than or equal to DEFAULT_LEVEL
        defaultFundingManagementShouldBeFound("level.lessThanOrEqual=" + DEFAULT_LEVEL);

        // Get all the fundingManagementList where level is less than or equal to SMALLER_LEVEL
        defaultFundingManagementShouldNotBeFound("level.lessThanOrEqual=" + SMALLER_LEVEL);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsLessThanSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level is less than DEFAULT_LEVEL
        defaultFundingManagementShouldNotBeFound("level.lessThan=" + DEFAULT_LEVEL);

        // Get all the fundingManagementList where level is less than UPDATED_LEVEL
        defaultFundingManagementShouldBeFound("level.lessThan=" + UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByLevelIsGreaterThanSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where level is greater than DEFAULT_LEVEL
        defaultFundingManagementShouldNotBeFound("level.greaterThan=" + DEFAULT_LEVEL);

        // Get all the fundingManagementList where level is greater than SMALLER_LEVEL
        defaultFundingManagementShouldBeFound("level.greaterThan=" + SMALLER_LEVEL);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByConditionsIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where conditions equals to DEFAULT_CONDITIONS
        defaultFundingManagementShouldBeFound("conditions.equals=" + DEFAULT_CONDITIONS);

        // Get all the fundingManagementList where conditions equals to UPDATED_CONDITIONS
        defaultFundingManagementShouldNotBeFound("conditions.equals=" + UPDATED_CONDITIONS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByConditionsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where conditions not equals to DEFAULT_CONDITIONS
        defaultFundingManagementShouldNotBeFound("conditions.notEquals=" + DEFAULT_CONDITIONS);

        // Get all the fundingManagementList where conditions not equals to UPDATED_CONDITIONS
        defaultFundingManagementShouldBeFound("conditions.notEquals=" + UPDATED_CONDITIONS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByConditionsIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where conditions in DEFAULT_CONDITIONS or UPDATED_CONDITIONS
        defaultFundingManagementShouldBeFound("conditions.in=" + DEFAULT_CONDITIONS + "," + UPDATED_CONDITIONS);

        // Get all the fundingManagementList where conditions equals to UPDATED_CONDITIONS
        defaultFundingManagementShouldNotBeFound("conditions.in=" + UPDATED_CONDITIONS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByConditionsIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where conditions is not null
        defaultFundingManagementShouldBeFound("conditions.specified=true");

        // Get all the fundingManagementList where conditions is null
        defaultFundingManagementShouldNotBeFound("conditions.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByConditionsContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where conditions contains DEFAULT_CONDITIONS
        defaultFundingManagementShouldBeFound("conditions.contains=" + DEFAULT_CONDITIONS);

        // Get all the fundingManagementList where conditions contains UPDATED_CONDITIONS
        defaultFundingManagementShouldNotBeFound("conditions.contains=" + UPDATED_CONDITIONS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByConditionsNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where conditions does not contain DEFAULT_CONDITIONS
        defaultFundingManagementShouldNotBeFound("conditions.doesNotContain=" + DEFAULT_CONDITIONS);

        // Get all the fundingManagementList where conditions does not contain UPDATED_CONDITIONS
        defaultFundingManagementShouldBeFound("conditions.doesNotContain=" + UPDATED_CONDITIONS);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByCausesIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where causes equals to DEFAULT_CAUSES
        defaultFundingManagementShouldBeFound("causes.equals=" + DEFAULT_CAUSES);

        // Get all the fundingManagementList where causes equals to UPDATED_CAUSES
        defaultFundingManagementShouldNotBeFound("causes.equals=" + UPDATED_CAUSES);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCausesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where causes not equals to DEFAULT_CAUSES
        defaultFundingManagementShouldNotBeFound("causes.notEquals=" + DEFAULT_CAUSES);

        // Get all the fundingManagementList where causes not equals to UPDATED_CAUSES
        defaultFundingManagementShouldBeFound("causes.notEquals=" + UPDATED_CAUSES);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCausesIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where causes in DEFAULT_CAUSES or UPDATED_CAUSES
        defaultFundingManagementShouldBeFound("causes.in=" + DEFAULT_CAUSES + "," + UPDATED_CAUSES);

        // Get all the fundingManagementList where causes equals to UPDATED_CAUSES
        defaultFundingManagementShouldNotBeFound("causes.in=" + UPDATED_CAUSES);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCausesIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where causes is not null
        defaultFundingManagementShouldBeFound("causes.specified=true");

        // Get all the fundingManagementList where causes is null
        defaultFundingManagementShouldNotBeFound("causes.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByCausesContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where causes contains DEFAULT_CAUSES
        defaultFundingManagementShouldBeFound("causes.contains=" + DEFAULT_CAUSES);

        // Get all the fundingManagementList where causes contains UPDATED_CAUSES
        defaultFundingManagementShouldNotBeFound("causes.contains=" + UPDATED_CAUSES);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByCausesNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where causes does not contain DEFAULT_CAUSES
        defaultFundingManagementShouldNotBeFound("causes.doesNotContain=" + DEFAULT_CAUSES);

        // Get all the fundingManagementList where causes does not contain UPDATED_CAUSES
        defaultFundingManagementShouldBeFound("causes.doesNotContain=" + UPDATED_CAUSES);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByImpactIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where impact equals to DEFAULT_IMPACT
        defaultFundingManagementShouldBeFound("impact.equals=" + DEFAULT_IMPACT);

        // Get all the fundingManagementList where impact equals to UPDATED_IMPACT
        defaultFundingManagementShouldNotBeFound("impact.equals=" + UPDATED_IMPACT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImpactIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where impact not equals to DEFAULT_IMPACT
        defaultFundingManagementShouldNotBeFound("impact.notEquals=" + DEFAULT_IMPACT);

        // Get all the fundingManagementList where impact not equals to UPDATED_IMPACT
        defaultFundingManagementShouldBeFound("impact.notEquals=" + UPDATED_IMPACT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImpactIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where impact in DEFAULT_IMPACT or UPDATED_IMPACT
        defaultFundingManagementShouldBeFound("impact.in=" + DEFAULT_IMPACT + "," + UPDATED_IMPACT);

        // Get all the fundingManagementList where impact equals to UPDATED_IMPACT
        defaultFundingManagementShouldNotBeFound("impact.in=" + UPDATED_IMPACT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImpactIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where impact is not null
        defaultFundingManagementShouldBeFound("impact.specified=true");

        // Get all the fundingManagementList where impact is null
        defaultFundingManagementShouldNotBeFound("impact.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByImpactContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where impact contains DEFAULT_IMPACT
        defaultFundingManagementShouldBeFound("impact.contains=" + DEFAULT_IMPACT);

        // Get all the fundingManagementList where impact contains UPDATED_IMPACT
        defaultFundingManagementShouldNotBeFound("impact.contains=" + UPDATED_IMPACT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImpactNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where impact does not contain DEFAULT_IMPACT
        defaultFundingManagementShouldNotBeFound("impact.doesNotContain=" + DEFAULT_IMPACT);

        // Get all the fundingManagementList where impact does not contain UPDATED_IMPACT
        defaultFundingManagementShouldBeFound("impact.doesNotContain=" + UPDATED_IMPACT);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByImplicationIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implication equals to DEFAULT_IMPLICATION
        defaultFundingManagementShouldBeFound("implication.equals=" + DEFAULT_IMPLICATION);

        // Get all the fundingManagementList where implication equals to UPDATED_IMPLICATION
        defaultFundingManagementShouldNotBeFound("implication.equals=" + UPDATED_IMPLICATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplicationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implication not equals to DEFAULT_IMPLICATION
        defaultFundingManagementShouldNotBeFound("implication.notEquals=" + DEFAULT_IMPLICATION);

        // Get all the fundingManagementList where implication not equals to UPDATED_IMPLICATION
        defaultFundingManagementShouldBeFound("implication.notEquals=" + UPDATED_IMPLICATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplicationIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implication in DEFAULT_IMPLICATION or UPDATED_IMPLICATION
        defaultFundingManagementShouldBeFound("implication.in=" + DEFAULT_IMPLICATION + "," + UPDATED_IMPLICATION);

        // Get all the fundingManagementList where implication equals to UPDATED_IMPLICATION
        defaultFundingManagementShouldNotBeFound("implication.in=" + UPDATED_IMPLICATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplicationIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implication is not null
        defaultFundingManagementShouldBeFound("implication.specified=true");

        // Get all the fundingManagementList where implication is null
        defaultFundingManagementShouldNotBeFound("implication.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByImplicationContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implication contains DEFAULT_IMPLICATION
        defaultFundingManagementShouldBeFound("implication.contains=" + DEFAULT_IMPLICATION);

        // Get all the fundingManagementList where implication contains UPDATED_IMPLICATION
        defaultFundingManagementShouldNotBeFound("implication.contains=" + UPDATED_IMPLICATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplicationNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implication does not contain DEFAULT_IMPLICATION
        defaultFundingManagementShouldNotBeFound("implication.doesNotContain=" + DEFAULT_IMPLICATION);

        // Get all the fundingManagementList where implication does not contain UPDATED_IMPLICATION
        defaultFundingManagementShouldBeFound("implication.doesNotContain=" + UPDATED_IMPLICATION);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByRecommendationIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where recommendation equals to DEFAULT_RECOMMENDATION
        defaultFundingManagementShouldBeFound("recommendation.equals=" + DEFAULT_RECOMMENDATION);

        // Get all the fundingManagementList where recommendation equals to UPDATED_RECOMMENDATION
        defaultFundingManagementShouldNotBeFound("recommendation.equals=" + UPDATED_RECOMMENDATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByRecommendationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where recommendation not equals to DEFAULT_RECOMMENDATION
        defaultFundingManagementShouldNotBeFound("recommendation.notEquals=" + DEFAULT_RECOMMENDATION);

        // Get all the fundingManagementList where recommendation not equals to UPDATED_RECOMMENDATION
        defaultFundingManagementShouldBeFound("recommendation.notEquals=" + UPDATED_RECOMMENDATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByRecommendationIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where recommendation in DEFAULT_RECOMMENDATION or UPDATED_RECOMMENDATION
        defaultFundingManagementShouldBeFound("recommendation.in=" + DEFAULT_RECOMMENDATION + "," + UPDATED_RECOMMENDATION);

        // Get all the fundingManagementList where recommendation equals to UPDATED_RECOMMENDATION
        defaultFundingManagementShouldNotBeFound("recommendation.in=" + UPDATED_RECOMMENDATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByRecommendationIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where recommendation is not null
        defaultFundingManagementShouldBeFound("recommendation.specified=true");

        // Get all the fundingManagementList where recommendation is null
        defaultFundingManagementShouldNotBeFound("recommendation.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByRecommendationContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where recommendation contains DEFAULT_RECOMMENDATION
        defaultFundingManagementShouldBeFound("recommendation.contains=" + DEFAULT_RECOMMENDATION);

        // Get all the fundingManagementList where recommendation contains UPDATED_RECOMMENDATION
        defaultFundingManagementShouldNotBeFound("recommendation.contains=" + UPDATED_RECOMMENDATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByRecommendationNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where recommendation does not contain DEFAULT_RECOMMENDATION
        defaultFundingManagementShouldNotBeFound("recommendation.doesNotContain=" + DEFAULT_RECOMMENDATION);

        // Get all the fundingManagementList where recommendation does not contain UPDATED_RECOMMENDATION
        defaultFundingManagementShouldBeFound("recommendation.doesNotContain=" + UPDATED_RECOMMENDATION);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByManagementResponseIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where managementResponse equals to DEFAULT_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldBeFound("managementResponse.equals=" + DEFAULT_MANAGEMENT_RESPONSE);

        // Get all the fundingManagementList where managementResponse equals to UPDATED_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldNotBeFound("managementResponse.equals=" + UPDATED_MANAGEMENT_RESPONSE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByManagementResponseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where managementResponse not equals to DEFAULT_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldNotBeFound("managementResponse.notEquals=" + DEFAULT_MANAGEMENT_RESPONSE);

        // Get all the fundingManagementList where managementResponse not equals to UPDATED_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldBeFound("managementResponse.notEquals=" + UPDATED_MANAGEMENT_RESPONSE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByManagementResponseIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where managementResponse in DEFAULT_MANAGEMENT_RESPONSE or UPDATED_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldBeFound("managementResponse.in=" + DEFAULT_MANAGEMENT_RESPONSE + "," + UPDATED_MANAGEMENT_RESPONSE);

        // Get all the fundingManagementList where managementResponse equals to UPDATED_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldNotBeFound("managementResponse.in=" + UPDATED_MANAGEMENT_RESPONSE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByManagementResponseIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where managementResponse is not null
        defaultFundingManagementShouldBeFound("managementResponse.specified=true");

        // Get all the fundingManagementList where managementResponse is null
        defaultFundingManagementShouldNotBeFound("managementResponse.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByManagementResponseContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where managementResponse contains DEFAULT_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldBeFound("managementResponse.contains=" + DEFAULT_MANAGEMENT_RESPONSE);

        // Get all the fundingManagementList where managementResponse contains UPDATED_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldNotBeFound("managementResponse.contains=" + UPDATED_MANAGEMENT_RESPONSE);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByManagementResponseNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where managementResponse does not contain DEFAULT_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldNotBeFound("managementResponse.doesNotContain=" + DEFAULT_MANAGEMENT_RESPONSE);

        // Get all the fundingManagementList where managementResponse does not contain UPDATED_MANAGEMENT_RESPONSE
        defaultFundingManagementShouldBeFound("managementResponse.doesNotContain=" + UPDATED_MANAGEMENT_RESPONSE);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByImplementationIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implementation equals to DEFAULT_IMPLEMENTATION
        defaultFundingManagementShouldBeFound("implementation.equals=" + DEFAULT_IMPLEMENTATION);

        // Get all the fundingManagementList where implementation equals to UPDATED_IMPLEMENTATION
        defaultFundingManagementShouldNotBeFound("implementation.equals=" + UPDATED_IMPLEMENTATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplementationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implementation not equals to DEFAULT_IMPLEMENTATION
        defaultFundingManagementShouldNotBeFound("implementation.notEquals=" + DEFAULT_IMPLEMENTATION);

        // Get all the fundingManagementList where implementation not equals to UPDATED_IMPLEMENTATION
        defaultFundingManagementShouldBeFound("implementation.notEquals=" + UPDATED_IMPLEMENTATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplementationIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implementation in DEFAULT_IMPLEMENTATION or UPDATED_IMPLEMENTATION
        defaultFundingManagementShouldBeFound("implementation.in=" + DEFAULT_IMPLEMENTATION + "," + UPDATED_IMPLEMENTATION);

        // Get all the fundingManagementList where implementation equals to UPDATED_IMPLEMENTATION
        defaultFundingManagementShouldNotBeFound("implementation.in=" + UPDATED_IMPLEMENTATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplementationIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implementation is not null
        defaultFundingManagementShouldBeFound("implementation.specified=true");

        // Get all the fundingManagementList where implementation is null
        defaultFundingManagementShouldNotBeFound("implementation.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByImplementationContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implementation contains DEFAULT_IMPLEMENTATION
        defaultFundingManagementShouldBeFound("implementation.contains=" + DEFAULT_IMPLEMENTATION);

        // Get all the fundingManagementList where implementation contains UPDATED_IMPLEMENTATION
        defaultFundingManagementShouldNotBeFound("implementation.contains=" + UPDATED_IMPLEMENTATION);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByImplementationNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where implementation does not contain DEFAULT_IMPLEMENTATION
        defaultFundingManagementShouldNotBeFound("implementation.doesNotContain=" + DEFAULT_IMPLEMENTATION);

        // Get all the fundingManagementList where implementation does not contain UPDATED_IMPLEMENTATION
        defaultFundingManagementShouldBeFound("implementation.doesNotContain=" + UPDATED_IMPLEMENTATION);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByAuditCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where auditComment equals to DEFAULT_AUDIT_COMMENT
        defaultFundingManagementShouldBeFound("auditComment.equals=" + DEFAULT_AUDIT_COMMENT);

        // Get all the fundingManagementList where auditComment equals to UPDATED_AUDIT_COMMENT
        defaultFundingManagementShouldNotBeFound("auditComment.equals=" + UPDATED_AUDIT_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByAuditCommentIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where auditComment not equals to DEFAULT_AUDIT_COMMENT
        defaultFundingManagementShouldNotBeFound("auditComment.notEquals=" + DEFAULT_AUDIT_COMMENT);

        // Get all the fundingManagementList where auditComment not equals to UPDATED_AUDIT_COMMENT
        defaultFundingManagementShouldBeFound("auditComment.notEquals=" + UPDATED_AUDIT_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByAuditCommentIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where auditComment in DEFAULT_AUDIT_COMMENT or UPDATED_AUDIT_COMMENT
        defaultFundingManagementShouldBeFound("auditComment.in=" + DEFAULT_AUDIT_COMMENT + "," + UPDATED_AUDIT_COMMENT);

        // Get all the fundingManagementList where auditComment equals to UPDATED_AUDIT_COMMENT
        defaultFundingManagementShouldNotBeFound("auditComment.in=" + UPDATED_AUDIT_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByAuditCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where auditComment is not null
        defaultFundingManagementShouldBeFound("auditComment.specified=true");

        // Get all the fundingManagementList where auditComment is null
        defaultFundingManagementShouldNotBeFound("auditComment.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByAuditCommentContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where auditComment contains DEFAULT_AUDIT_COMMENT
        defaultFundingManagementShouldBeFound("auditComment.contains=" + DEFAULT_AUDIT_COMMENT);

        // Get all the fundingManagementList where auditComment contains UPDATED_AUDIT_COMMENT
        defaultFundingManagementShouldNotBeFound("auditComment.contains=" + UPDATED_AUDIT_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByAuditCommentNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where auditComment does not contain DEFAULT_AUDIT_COMMENT
        defaultFundingManagementShouldNotBeFound("auditComment.doesNotContain=" + DEFAULT_AUDIT_COMMENT);

        // Get all the fundingManagementList where auditComment does not contain UPDATED_AUDIT_COMMENT
        defaultFundingManagementShouldBeFound("auditComment.doesNotContain=" + UPDATED_AUDIT_COMMENT);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where status equals to DEFAULT_STATUS
        defaultFundingManagementShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the fundingManagementList where status equals to UPDATED_STATUS
        defaultFundingManagementShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where status not equals to DEFAULT_STATUS
        defaultFundingManagementShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the fundingManagementList where status not equals to UPDATED_STATUS
        defaultFundingManagementShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultFundingManagementShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the fundingManagementList where status equals to UPDATED_STATUS
        defaultFundingManagementShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where status is not null
        defaultFundingManagementShouldBeFound("status.specified=true");

        // Get all the fundingManagementList where status is null
        defaultFundingManagementShouldNotBeFound("status.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByStatusContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where status contains DEFAULT_STATUS
        defaultFundingManagementShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the fundingManagementList where status contains UPDATED_STATUS
        defaultFundingManagementShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where status does not contain DEFAULT_STATUS
        defaultFundingManagementShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the fundingManagementList where status does not contain UPDATED_STATUS
        defaultFundingManagementShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByContactsIsEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where contacts equals to DEFAULT_CONTACTS
        defaultFundingManagementShouldBeFound("contacts.equals=" + DEFAULT_CONTACTS);

        // Get all the fundingManagementList where contacts equals to UPDATED_CONTACTS
        defaultFundingManagementShouldNotBeFound("contacts.equals=" + UPDATED_CONTACTS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByContactsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where contacts not equals to DEFAULT_CONTACTS
        defaultFundingManagementShouldNotBeFound("contacts.notEquals=" + DEFAULT_CONTACTS);

        // Get all the fundingManagementList where contacts not equals to UPDATED_CONTACTS
        defaultFundingManagementShouldBeFound("contacts.notEquals=" + UPDATED_CONTACTS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByContactsIsInShouldWork() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where contacts in DEFAULT_CONTACTS or UPDATED_CONTACTS
        defaultFundingManagementShouldBeFound("contacts.in=" + DEFAULT_CONTACTS + "," + UPDATED_CONTACTS);

        // Get all the fundingManagementList where contacts equals to UPDATED_CONTACTS
        defaultFundingManagementShouldNotBeFound("contacts.in=" + UPDATED_CONTACTS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByContactsIsNullOrNotNull() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where contacts is not null
        defaultFundingManagementShouldBeFound("contacts.specified=true");

        // Get all the fundingManagementList where contacts is null
        defaultFundingManagementShouldNotBeFound("contacts.specified=false");
    }
                @Test
    @Transactional
    public void getAllFundingManagementsByContactsContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where contacts contains DEFAULT_CONTACTS
        defaultFundingManagementShouldBeFound("contacts.contains=" + DEFAULT_CONTACTS);

        // Get all the fundingManagementList where contacts contains UPDATED_CONTACTS
        defaultFundingManagementShouldNotBeFound("contacts.contains=" + UPDATED_CONTACTS);
    }

    @Test
    @Transactional
    public void getAllFundingManagementsByContactsNotContainsSomething() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        // Get all the fundingManagementList where contacts does not contain DEFAULT_CONTACTS
        defaultFundingManagementShouldNotBeFound("contacts.doesNotContain=" + DEFAULT_CONTACTS);

        // Get all the fundingManagementList where contacts does not contain UPDATED_CONTACTS
        defaultFundingManagementShouldBeFound("contacts.doesNotContain=" + UPDATED_CONTACTS);
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByTheClustersIsEqualToSomething() throws Exception {
        // Get already existing entity
        TheClusters theClusters = fundingManagement.getTheClusters();
        fundingManagementRepository.saveAndFlush(fundingManagement);
        Long theClustersId = theClusters.getId();

        // Get all the fundingManagementList where theClusters equals to theClustersId
        defaultFundingManagementShouldBeFound("theClustersId.equals=" + theClustersId);

        // Get all the fundingManagementList where theClusters equals to theClustersId + 1
        defaultFundingManagementShouldNotBeFound("theClustersId.equals=" + (theClustersId + 1));
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByFindingSubCategoryIsEqualToSomething() throws Exception {
        // Get already existing entity
        FindingSubCategory findingSubCategory = fundingManagement.getFindingSubCategory();
        fundingManagementRepository.saveAndFlush(fundingManagement);
        Long findingSubCategoryId = findingSubCategory.getId();

        // Get all the fundingManagementList where findingSubCategory equals to findingSubCategoryId
        defaultFundingManagementShouldBeFound("findingSubCategoryId.equals=" + findingSubCategoryId);

        // Get all the fundingManagementList where findingSubCategory equals to findingSubCategoryId + 1
        defaultFundingManagementShouldNotBeFound("findingSubCategoryId.equals=" + (findingSubCategoryId + 1));
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByFinancialYearIsEqualToSomething() throws Exception {
        // Get already existing entity
        FinancialYear financialYear = fundingManagement.getFinancialYear();
        fundingManagementRepository.saveAndFlush(fundingManagement);
        Long financialYearId = financialYear.getId();

        // Get all the fundingManagementList where financialYear equals to financialYearId
        defaultFundingManagementShouldBeFound("financialYearId.equals=" + financialYearId);

        // Get all the fundingManagementList where financialYear equals to financialYearId + 1
        defaultFundingManagementShouldNotBeFound("financialYearId.equals=" + (financialYearId + 1));
    }


    @Test
    @Transactional
    public void getAllFundingManagementsByOrganisationUnitLevelIsEqualToSomething() throws Exception {
        // Get already existing entity
        OrganisationUnitLevel organisationUnitLevel = fundingManagement.getOrganisationUnitLevel();
        fundingManagementRepository.saveAndFlush(fundingManagement);
        Long organisationUnitLevelId = organisationUnitLevel.getId();

        // Get all the fundingManagementList where organisationUnitLevel equals to organisationUnitLevelId
        defaultFundingManagementShouldBeFound("organisationUnitLevelId.equals=" + organisationUnitLevelId);

        // Get all the fundingManagementList where organisationUnitLevel equals to organisationUnitLevelId + 1
        defaultFundingManagementShouldNotBeFound("organisationUnitLevelId.equals=" + (organisationUnitLevelId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFundingManagementShouldBeFound(String filter) throws Exception {
        restFundingManagementMockMvc.perform(get("/api/funding-managements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundingManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].conditions").value(hasItem(DEFAULT_CONDITIONS)))
            .andExpect(jsonPath("$.[*].causes").value(hasItem(DEFAULT_CAUSES)))
            .andExpect(jsonPath("$.[*].impact").value(hasItem(DEFAULT_IMPACT)))
            .andExpect(jsonPath("$.[*].implication").value(hasItem(DEFAULT_IMPLICATION)))
            .andExpect(jsonPath("$.[*].recommendation").value(hasItem(DEFAULT_RECOMMENDATION)))
            .andExpect(jsonPath("$.[*].managementResponse").value(hasItem(DEFAULT_MANAGEMENT_RESPONSE)))
            .andExpect(jsonPath("$.[*].implementation").value(hasItem(DEFAULT_IMPLEMENTATION)))
            .andExpect(jsonPath("$.[*].auditComment").value(hasItem(DEFAULT_AUDIT_COMMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].contacts").value(hasItem(DEFAULT_CONTACTS)));

        // Check, that the count call also returns 1
        restFundingManagementMockMvc.perform(get("/api/funding-managements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFundingManagementShouldNotBeFound(String filter) throws Exception {
        restFundingManagementMockMvc.perform(get("/api/funding-managements?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFundingManagementMockMvc.perform(get("/api/funding-managements/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFundingManagement() throws Exception {
        // Get the fundingManagement
        restFundingManagementMockMvc.perform(get("/api/funding-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFundingManagement() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        int databaseSizeBeforeUpdate = fundingManagementRepository.findAll().size();

        // Update the fundingManagement
        FundingManagement updatedFundingManagement = fundingManagementRepository.findById(fundingManagement.getId()).get();
        // Disconnect from session so that the updates on updatedFundingManagement are not directly saved in db
        em.detach(updatedFundingManagement);
        updatedFundingManagement
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .code(UPDATED_CODE)
            .level(UPDATED_LEVEL)
            .conditions(UPDATED_CONDITIONS)
            .causes(UPDATED_CAUSES)
            .impact(UPDATED_IMPACT)
            .implication(UPDATED_IMPLICATION)
            .recommendation(UPDATED_RECOMMENDATION)
            .managementResponse(UPDATED_MANAGEMENT_RESPONSE)
            .implementation(UPDATED_IMPLEMENTATION)
            .auditComment(UPDATED_AUDIT_COMMENT)
            .status(UPDATED_STATUS)
            .contacts(UPDATED_CONTACTS);
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(updatedFundingManagement);

        restFundingManagementMockMvc.perform(put("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isOk());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeUpdate);
        FundingManagement testFundingManagement = fundingManagementList.get(fundingManagementList.size() - 1);
        assertThat(testFundingManagement.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFundingManagement.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFundingManagement.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFundingManagement.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testFundingManagement.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testFundingManagement.getConditions()).isEqualTo(UPDATED_CONDITIONS);
        assertThat(testFundingManagement.getCauses()).isEqualTo(UPDATED_CAUSES);
        assertThat(testFundingManagement.getImpact()).isEqualTo(UPDATED_IMPACT);
        assertThat(testFundingManagement.getImplication()).isEqualTo(UPDATED_IMPLICATION);
        assertThat(testFundingManagement.getRecommendation()).isEqualTo(UPDATED_RECOMMENDATION);
        assertThat(testFundingManagement.getManagementResponse()).isEqualTo(UPDATED_MANAGEMENT_RESPONSE);
        assertThat(testFundingManagement.getImplementation()).isEqualTo(UPDATED_IMPLEMENTATION);
        assertThat(testFundingManagement.getAuditComment()).isEqualTo(UPDATED_AUDIT_COMMENT);
        assertThat(testFundingManagement.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFundingManagement.getContacts()).isEqualTo(UPDATED_CONTACTS);
    }

    @Test
    @Transactional
    public void updateNonExistingFundingManagement() throws Exception {
        int databaseSizeBeforeUpdate = fundingManagementRepository.findAll().size();

        // Create the FundingManagement
        FundingManagementDTO fundingManagementDTO = fundingManagementMapper.toDto(fundingManagement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundingManagementMockMvc.perform(put("/api/funding-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fundingManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundingManagement in the database
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFundingManagement() throws Exception {
        // Initialize the database
        fundingManagementRepository.saveAndFlush(fundingManagement);

        int databaseSizeBeforeDelete = fundingManagementRepository.findAll().size();

        // Delete the fundingManagement
        restFundingManagementMockMvc.perform(delete("/api/funding-managements/{id}", fundingManagement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FundingManagement> fundingManagementList = fundingManagementRepository.findAll();
        assertThat(fundingManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
