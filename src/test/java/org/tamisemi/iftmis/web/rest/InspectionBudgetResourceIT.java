package org.tamisemi.iftmis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
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
import org.tamisemi.iftmis.domain.GfsCode;
import org.tamisemi.iftmis.domain.Inspection;
import org.tamisemi.iftmis.domain.InspectionBudget;
import org.tamisemi.iftmis.repository.InspectionBudgetRepository;
import org.tamisemi.iftmis.service.InspectionBudgetService;
import org.tamisemi.iftmis.service.dto.InspectionBudgetDTO;
import org.tamisemi.iftmis.service.mapper.InspectionBudgetMapper;

/**
 * Integration tests for the {@link InspectionBudgetResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InspectionBudgetResourceIT {
    private static final Float DEFAULT_QUANTITY = 0F;
    private static final Float UPDATED_QUANTITY = 1F;

    private static final Float DEFAULT_FREQUENCY = 0F;
    private static final Float UPDATED_FREQUENCY = 1F;

    private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal(0);
    private static final BigDecimal UPDATED_UNIT_PRICE = new BigDecimal(1);

    @Autowired
    private InspectionBudgetRepository inspectionBudgetRepository;

    @Autowired
    private InspectionBudgetMapper inspectionBudgetMapper;

    @Autowired
    private InspectionBudgetService inspectionBudgetService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionBudgetMockMvc;

    private InspectionBudget inspectionBudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionBudget createEntity(EntityManager em) {
        InspectionBudget inspectionBudget = new InspectionBudget()
            .quantity(DEFAULT_QUANTITY)
            .frequency(DEFAULT_FREQUENCY)
            .unitPrice(DEFAULT_UNIT_PRICE);
        // Add required entity
        GfsCode gfsCode;
        if (TestUtil.findAll(em, GfsCode.class).isEmpty()) {
            gfsCode = GfsCodeResourceIT.createEntity(em);
            em.persist(gfsCode);
            em.flush();
        } else {
            gfsCode = TestUtil.findAll(em, GfsCode.class).get(0);
        }
        inspectionBudget.setGfsCode(gfsCode);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        inspectionBudget.setInspection(inspection);
        return inspectionBudget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InspectionBudget createUpdatedEntity(EntityManager em) {
        InspectionBudget inspectionBudget = new InspectionBudget()
            .quantity(UPDATED_QUANTITY)
            .frequency(UPDATED_FREQUENCY)
            .unitPrice(UPDATED_UNIT_PRICE);
        // Add required entity
        GfsCode gfsCode;
        if (TestUtil.findAll(em, GfsCode.class).isEmpty()) {
            gfsCode = GfsCodeResourceIT.createUpdatedEntity(em);
            em.persist(gfsCode);
            em.flush();
        } else {
            gfsCode = TestUtil.findAll(em, GfsCode.class).get(0);
        }
        inspectionBudget.setGfsCode(gfsCode);
        // Add required entity
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            inspection = InspectionResourceIT.createUpdatedEntity(em);
            em.persist(inspection);
            em.flush();
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        inspectionBudget.setInspection(inspection);
        return inspectionBudget;
    }

    @BeforeEach
    public void initTest() {
        inspectionBudget = createEntity(em);
    }

    @Test
    @Transactional
    public void createInspectionBudget() throws Exception {
        int databaseSizeBeforeCreate = inspectionBudgetRepository.findAll().size();
        // Create the InspectionBudget
        InspectionBudgetDTO inspectionBudgetDTO = inspectionBudgetMapper.toDto(inspectionBudget);
        restInspectionBudgetMockMvc
            .perform(
                post("/api/inspection-budgets")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionBudgetDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InspectionBudget in the database
        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeCreate + 1);
        InspectionBudget testInspectionBudget = inspectionBudgetList.get(inspectionBudgetList.size() - 1);
        assertThat(testInspectionBudget.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testInspectionBudget.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testInspectionBudget.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
    }

    @Test
    @Transactional
    public void createInspectionBudgetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inspectionBudgetRepository.findAll().size();

        // Create the InspectionBudget with an existing ID
        inspectionBudget.setId(1L);
        InspectionBudgetDTO inspectionBudgetDTO = inspectionBudgetMapper.toDto(inspectionBudget);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionBudgetMockMvc
            .perform(
                post("/api/inspection-budgets")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionBudgetDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionBudget in the database
        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionBudgetRepository.findAll().size();
        // set the field null
        inspectionBudget.setQuantity(null);

        // Create the InspectionBudget, which fails.
        InspectionBudgetDTO inspectionBudgetDTO = inspectionBudgetMapper.toDto(inspectionBudget);

        restInspectionBudgetMockMvc
            .perform(
                post("/api/inspection-budgets")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionBudgetDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrequencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionBudgetRepository.findAll().size();
        // set the field null
        inspectionBudget.setFrequency(null);

        // Create the InspectionBudget, which fails.
        InspectionBudgetDTO inspectionBudgetDTO = inspectionBudgetMapper.toDto(inspectionBudget);

        restInspectionBudgetMockMvc
            .perform(
                post("/api/inspection-budgets")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionBudgetDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUnitPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionBudgetRepository.findAll().size();
        // set the field null
        inspectionBudget.setUnitPrice(null);

        // Create the InspectionBudget, which fails.
        InspectionBudgetDTO inspectionBudgetDTO = inspectionBudgetMapper.toDto(inspectionBudget);

        restInspectionBudgetMockMvc
            .perform(
                post("/api/inspection-budgets")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionBudgetDTO))
            )
            .andExpect(status().isBadRequest());

        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInspectionBudgets() throws Exception {
        // Initialize the database
        inspectionBudgetRepository.saveAndFlush(inspectionBudget);

        // Get all the inspectionBudgetList
        restInspectionBudgetMockMvc
            .perform(get("/api/inspection-budgets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspectionBudget.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY.doubleValue())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.intValue())));
    }

    @Test
    @Transactional
    public void getInspectionBudget() throws Exception {
        // Initialize the database
        inspectionBudgetRepository.saveAndFlush(inspectionBudget);

        // Get the inspectionBudget
        restInspectionBudgetMockMvc
            .perform(get("/api/inspection-budgets/{id}", inspectionBudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspectionBudget.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY.doubleValue()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInspectionBudget() throws Exception {
        // Get the inspectionBudget
        restInspectionBudgetMockMvc.perform(get("/api/inspection-budgets/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInspectionBudget() throws Exception {
        // Initialize the database
        inspectionBudgetRepository.saveAndFlush(inspectionBudget);

        int databaseSizeBeforeUpdate = inspectionBudgetRepository.findAll().size();

        // Update the inspectionBudget
        InspectionBudget updatedInspectionBudget = inspectionBudgetRepository.findById(inspectionBudget.getId()).get();
        // Disconnect from session so that the updates on updatedInspectionBudget are not directly saved in db
        em.detach(updatedInspectionBudget);
        updatedInspectionBudget.quantity(UPDATED_QUANTITY).frequency(UPDATED_FREQUENCY).unitPrice(UPDATED_UNIT_PRICE);
        InspectionBudgetDTO inspectionBudgetDTO = inspectionBudgetMapper.toDto(updatedInspectionBudget);

        restInspectionBudgetMockMvc
            .perform(
                put("/api/inspection-budgets")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionBudgetDTO))
            )
            .andExpect(status().isOk());

        // Validate the InspectionBudget in the database
        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeUpdate);
        InspectionBudget testInspectionBudget = inspectionBudgetList.get(inspectionBudgetList.size() - 1);
        assertThat(testInspectionBudget.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testInspectionBudget.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testInspectionBudget.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingInspectionBudget() throws Exception {
        int databaseSizeBeforeUpdate = inspectionBudgetRepository.findAll().size();

        // Create the InspectionBudget
        InspectionBudgetDTO inspectionBudgetDTO = inspectionBudgetMapper.toDto(inspectionBudget);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionBudgetMockMvc
            .perform(
                put("/api/inspection-budgets")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionBudgetDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InspectionBudget in the database
        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInspectionBudget() throws Exception {
        // Initialize the database
        inspectionBudgetRepository.saveAndFlush(inspectionBudget);

        int databaseSizeBeforeDelete = inspectionBudgetRepository.findAll().size();

        // Delete the inspectionBudget
        restInspectionBudgetMockMvc
            .perform(delete("/api/inspection-budgets/{id}", inspectionBudget.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InspectionBudget> inspectionBudgetList = inspectionBudgetRepository.findAll();
        assertThat(inspectionBudgetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
