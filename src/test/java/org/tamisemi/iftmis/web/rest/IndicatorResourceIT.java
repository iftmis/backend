package org.tamisemi.iftmis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.Indicator;
import org.tamisemi.iftmis.domain.SubArea;
import org.tamisemi.iftmis.repository.IndicatorRepository;
import org.tamisemi.iftmis.service.IndicatorService;
import org.tamisemi.iftmis.service.dto.IndicatorDTO;
import org.tamisemi.iftmis.service.mapper.IndicatorMapper;

/**
 * Integration tests for the {@link IndicatorResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class IndicatorResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private IndicatorMapper indicatorMapper;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIndicatorMockMvc;

    private Indicator indicator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Indicator createEntity(EntityManager em) {
        Indicator indicator = new Indicator().name(DEFAULT_NAME);
        // Add required entity
        SubArea subArea;
        if (TestUtil.findAll(em, SubArea.class).isEmpty()) {
            subArea = SubAreaResourceIT.createEntity(em);
            em.persist(subArea);
            em.flush();
        } else {
            subArea = TestUtil.findAll(em, SubArea.class).get(0);
        }
        indicator.setSubArea(subArea);
        return indicator;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Indicator createUpdatedEntity(EntityManager em) {
        Indicator indicator = new Indicator().name(UPDATED_NAME);
        // Add required entity
        SubArea subArea;
        if (TestUtil.findAll(em, SubArea.class).isEmpty()) {
            subArea = SubAreaResourceIT.createUpdatedEntity(em);
            em.persist(subArea);
            em.flush();
        } else {
            subArea = TestUtil.findAll(em, SubArea.class).get(0);
        }
        indicator.setSubArea(subArea);
        return indicator;
    }

    @BeforeEach
    public void initTest() {
        indicator = createEntity(em);
    }

    @Test
    @Transactional
    public void createIndicator() throws Exception {
        int databaseSizeBeforeCreate = indicatorRepository.findAll().size();
        // Create the Indicator
        IndicatorDTO indicatorDTO = indicatorMapper.toDto(indicator);
        restIndicatorMockMvc
            .perform(
                post("/api/indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(indicatorDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Indicator in the database
        List<Indicator> indicatorList = indicatorRepository.findAll();
        assertThat(indicatorList).hasSize(databaseSizeBeforeCreate + 1);
        Indicator testIndicator = indicatorList.get(indicatorList.size() - 1);
        assertThat(testIndicator.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createIndicatorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = indicatorRepository.findAll().size();

        // Create the Indicator with an existing ID
        indicator.setId(1L);
        IndicatorDTO indicatorDTO = indicatorMapper.toDto(indicator);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndicatorMockMvc
            .perform(
                post("/api/indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(indicatorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Indicator in the database
        List<Indicator> indicatorList = indicatorRepository.findAll();
        assertThat(indicatorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = indicatorRepository.findAll().size();
        // set the field null
        indicator.setName(null);

        // Create the Indicator, which fails.
        IndicatorDTO indicatorDTO = indicatorMapper.toDto(indicator);

        restIndicatorMockMvc
            .perform(
                post("/api/indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(indicatorDTO))
            )
            .andExpect(status().isBadRequest());

        List<Indicator> indicatorList = indicatorRepository.findAll();
        assertThat(indicatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIndicators() throws Exception {
        // Initialize the database
        indicatorRepository.saveAndFlush(indicator);

        // Get all the indicatorList
        restIndicatorMockMvc
            .perform(get("/api/indicators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indicator.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getIndicator() throws Exception {
        // Initialize the database
        indicatorRepository.saveAndFlush(indicator);

        // Get the indicator
        restIndicatorMockMvc
            .perform(get("/api/indicators/{id}", indicator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(indicator.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingIndicator() throws Exception {
        // Get the indicator
        restIndicatorMockMvc.perform(get("/api/indicators/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIndicator() throws Exception {
        // Initialize the database
        indicatorRepository.saveAndFlush(indicator);

        int databaseSizeBeforeUpdate = indicatorRepository.findAll().size();

        // Update the indicator
        Indicator updatedIndicator = indicatorRepository.findById(indicator.getId()).get();
        // Disconnect from session so that the updates on updatedIndicator are not directly saved in db
        em.detach(updatedIndicator);
        updatedIndicator.name(UPDATED_NAME);
        IndicatorDTO indicatorDTO = indicatorMapper.toDto(updatedIndicator);

        restIndicatorMockMvc
            .perform(
                put("/api/indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(indicatorDTO))
            )
            .andExpect(status().isOk());

        // Validate the Indicator in the database
        List<Indicator> indicatorList = indicatorRepository.findAll();
        assertThat(indicatorList).hasSize(databaseSizeBeforeUpdate);
        Indicator testIndicator = indicatorList.get(indicatorList.size() - 1);
        assertThat(testIndicator.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingIndicator() throws Exception {
        int databaseSizeBeforeUpdate = indicatorRepository.findAll().size();

        // Create the Indicator
        IndicatorDTO indicatorDTO = indicatorMapper.toDto(indicator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIndicatorMockMvc
            .perform(
                put("/api/indicators")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(indicatorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Indicator in the database
        List<Indicator> indicatorList = indicatorRepository.findAll();
        assertThat(indicatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIndicator() throws Exception {
        // Initialize the database
        indicatorRepository.saveAndFlush(indicator);

        int databaseSizeBeforeDelete = indicatorRepository.findAll().size();

        // Delete the indicator
        restIndicatorMockMvc
            .perform(delete("/api/indicators/{id}", indicator.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Indicator> indicatorList = indicatorRepository.findAll();
        assertThat(indicatorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
