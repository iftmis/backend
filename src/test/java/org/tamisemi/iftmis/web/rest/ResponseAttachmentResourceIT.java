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
import org.tamisemi.iftmis.domain.FileResource;
import org.tamisemi.iftmis.domain.FindingResponse;
import org.tamisemi.iftmis.domain.ResponseAttachment;
import org.tamisemi.iftmis.repository.ResponseAttachmentRepository;
import org.tamisemi.iftmis.service.ResponseAttachmentService;
import org.tamisemi.iftmis.service.dto.ResponseAttachmentDTO;
import org.tamisemi.iftmis.service.mapper.ResponseAttachmentMapper;

/**
 * Integration tests for the {@link ResponseAttachmentResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ResponseAttachmentResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ResponseAttachmentRepository responseAttachmentRepository;

    @Autowired
    private ResponseAttachmentMapper responseAttachmentMapper;

    @Autowired
    private ResponseAttachmentService responseAttachmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResponseAttachmentMockMvc;

    private ResponseAttachment responseAttachment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResponseAttachment createEntity(EntityManager em) {
        ResponseAttachment responseAttachment = new ResponseAttachment().name(DEFAULT_NAME);
        // Add required entity
        FileResource fileResource;
        if (TestUtil.findAll(em, FileResource.class).isEmpty()) {
            fileResource = FileResourceResourceIT.createEntity(em);
            em.persist(fileResource);
            em.flush();
        } else {
            fileResource = TestUtil.findAll(em, FileResource.class).get(0);
        }
        responseAttachment.setAttachment(fileResource);
        // Add required entity
        FindingResponse findingResponse;
        if (TestUtil.findAll(em, FindingResponse.class).isEmpty()) {
            findingResponse = FindingResponseResourceIT.createEntity(em);
            em.persist(findingResponse);
            em.flush();
        } else {
            findingResponse = TestUtil.findAll(em, FindingResponse.class).get(0);
        }
        responseAttachment.setResponse(findingResponse);
        return responseAttachment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResponseAttachment createUpdatedEntity(EntityManager em) {
        ResponseAttachment responseAttachment = new ResponseAttachment().name(UPDATED_NAME);
        // Add required entity
        FileResource fileResource;
        if (TestUtil.findAll(em, FileResource.class).isEmpty()) {
            fileResource = FileResourceResourceIT.createUpdatedEntity(em);
            em.persist(fileResource);
            em.flush();
        } else {
            fileResource = TestUtil.findAll(em, FileResource.class).get(0);
        }
        responseAttachment.setAttachment(fileResource);
        // Add required entity
        FindingResponse findingResponse;
        if (TestUtil.findAll(em, FindingResponse.class).isEmpty()) {
            findingResponse = FindingResponseResourceIT.createUpdatedEntity(em);
            em.persist(findingResponse);
            em.flush();
        } else {
            findingResponse = TestUtil.findAll(em, FindingResponse.class).get(0);
        }
        responseAttachment.setResponse(findingResponse);
        return responseAttachment;
    }

    @BeforeEach
    public void initTest() {
        responseAttachment = createEntity(em);
    }

    @Test
    @Transactional
    public void createResponseAttachment() throws Exception {
        int databaseSizeBeforeCreate = responseAttachmentRepository.findAll().size();
        // Create the ResponseAttachment
        ResponseAttachmentDTO responseAttachmentDTO = responseAttachmentMapper.toDto(responseAttachment);
        restResponseAttachmentMockMvc
            .perform(
                post("/api/response-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(responseAttachmentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ResponseAttachment in the database
        List<ResponseAttachment> responseAttachmentList = responseAttachmentRepository.findAll();
        assertThat(responseAttachmentList).hasSize(databaseSizeBeforeCreate + 1);
        ResponseAttachment testResponseAttachment = responseAttachmentList.get(responseAttachmentList.size() - 1);
        assertThat(testResponseAttachment.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createResponseAttachmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = responseAttachmentRepository.findAll().size();

        // Create the ResponseAttachment with an existing ID
        responseAttachment.setId(1L);
        ResponseAttachmentDTO responseAttachmentDTO = responseAttachmentMapper.toDto(responseAttachment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResponseAttachmentMockMvc
            .perform(
                post("/api/response-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(responseAttachmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResponseAttachment in the database
        List<ResponseAttachment> responseAttachmentList = responseAttachmentRepository.findAll();
        assertThat(responseAttachmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = responseAttachmentRepository.findAll().size();
        // set the field null
        responseAttachment.setName(null);

        // Create the ResponseAttachment, which fails.
        ResponseAttachmentDTO responseAttachmentDTO = responseAttachmentMapper.toDto(responseAttachment);

        restResponseAttachmentMockMvc
            .perform(
                post("/api/response-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(responseAttachmentDTO))
            )
            .andExpect(status().isBadRequest());

        List<ResponseAttachment> responseAttachmentList = responseAttachmentRepository.findAll();
        assertThat(responseAttachmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllResponseAttachments() throws Exception {
        // Initialize the database
        responseAttachmentRepository.saveAndFlush(responseAttachment);

        // Get all the responseAttachmentList
        restResponseAttachmentMockMvc
            .perform(get("/api/response-attachments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(responseAttachment.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void getResponseAttachment() throws Exception {
        // Initialize the database
        responseAttachmentRepository.saveAndFlush(responseAttachment);

        // Get the responseAttachment
        restResponseAttachmentMockMvc
            .perform(get("/api/response-attachments/{id}", responseAttachment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(responseAttachment.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingResponseAttachment() throws Exception {
        // Get the responseAttachment
        restResponseAttachmentMockMvc.perform(get("/api/response-attachments/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResponseAttachment() throws Exception {
        // Initialize the database
        responseAttachmentRepository.saveAndFlush(responseAttachment);

        int databaseSizeBeforeUpdate = responseAttachmentRepository.findAll().size();

        // Update the responseAttachment
        ResponseAttachment updatedResponseAttachment = responseAttachmentRepository.findById(responseAttachment.getId()).get();
        // Disconnect from session so that the updates on updatedResponseAttachment are not directly saved in db
        em.detach(updatedResponseAttachment);
        updatedResponseAttachment.name(UPDATED_NAME);
        ResponseAttachmentDTO responseAttachmentDTO = responseAttachmentMapper.toDto(updatedResponseAttachment);

        restResponseAttachmentMockMvc
            .perform(
                put("/api/response-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(responseAttachmentDTO))
            )
            .andExpect(status().isOk());

        // Validate the ResponseAttachment in the database
        List<ResponseAttachment> responseAttachmentList = responseAttachmentRepository.findAll();
        assertThat(responseAttachmentList).hasSize(databaseSizeBeforeUpdate);
        ResponseAttachment testResponseAttachment = responseAttachmentList.get(responseAttachmentList.size() - 1);
        assertThat(testResponseAttachment.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingResponseAttachment() throws Exception {
        int databaseSizeBeforeUpdate = responseAttachmentRepository.findAll().size();

        // Create the ResponseAttachment
        ResponseAttachmentDTO responseAttachmentDTO = responseAttachmentMapper.toDto(responseAttachment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResponseAttachmentMockMvc
            .perform(
                put("/api/response-attachments")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(responseAttachmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResponseAttachment in the database
        List<ResponseAttachment> responseAttachmentList = responseAttachmentRepository.findAll();
        assertThat(responseAttachmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResponseAttachment() throws Exception {
        // Initialize the database
        responseAttachmentRepository.saveAndFlush(responseAttachment);

        int databaseSizeBeforeDelete = responseAttachmentRepository.findAll().size();

        // Delete the responseAttachment
        restResponseAttachmentMockMvc
            .perform(delete("/api/response-attachments/{id}", responseAttachment.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ResponseAttachment> responseAttachmentList = responseAttachmentRepository.findAll();
        assertThat(responseAttachmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
