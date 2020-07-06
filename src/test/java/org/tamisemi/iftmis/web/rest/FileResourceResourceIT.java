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
import org.tamisemi.iftmis.repository.FileResourceRepository;
import org.tamisemi.iftmis.service.FileResourceService;
import org.tamisemi.iftmis.service.dto.FileResourceDTO;
import org.tamisemi.iftmis.service.mapper.FileResourceMapper;

/**
 * Integration tests for the {@link FileResourceResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FileResourceResourceIT {
    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTEXT_MD_5 = "AAAAAAAAAA";
    private static final String UPDATED_CONTEXT_MD_5 = "BBBBBBBBBB";

    private static final Double DEFAULT_SIZE = 1D;
    private static final Double UPDATED_SIZE = 2D;

    private static final Boolean DEFAULT_IS_ASSIGNED = false;
    private static final Boolean UPDATED_IS_ASSIGNED = true;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private FileResourceMapper fileResourceMapper;

    @Autowired
    private FileResourceService fileResourceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFileResourceMockMvc;

    private FileResource fileResource;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileResource createEntity(EntityManager em) {
        FileResource fileResource = new FileResource()
            .name(DEFAULT_NAME)
            .path(DEFAULT_PATH)
            .contentType(DEFAULT_CONTENT_TYPE)
            .contextMd5(DEFAULT_CONTEXT_MD_5)
            .size(DEFAULT_SIZE)
            .isAssigned(DEFAULT_IS_ASSIGNED)
            .type(DEFAULT_TYPE);
        return fileResource;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileResource createUpdatedEntity(EntityManager em) {
        FileResource fileResource = new FileResource()
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .contentType(UPDATED_CONTENT_TYPE)
            .contextMd5(UPDATED_CONTEXT_MD_5)
            .size(UPDATED_SIZE)
            .isAssigned(UPDATED_IS_ASSIGNED)
            .type(UPDATED_TYPE);
        return fileResource;
    }

    @BeforeEach
    public void initTest() {
        fileResource = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileResource() throws Exception {
        int databaseSizeBeforeCreate = fileResourceRepository.findAll().size();
        // Create the FileResource
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(fileResource);
        restFileResourceMockMvc
            .perform(
                post("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FileResource in the database
        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeCreate + 1);
        FileResource testFileResource = fileResourceList.get(fileResourceList.size() - 1);
        assertThat(testFileResource.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFileResource.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testFileResource.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testFileResource.getContextMd5()).isEqualTo(DEFAULT_CONTEXT_MD_5);
        assertThat(testFileResource.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testFileResource.isIsAssigned()).isEqualTo(DEFAULT_IS_ASSIGNED);
        assertThat(testFileResource.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createFileResourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileResourceRepository.findAll().size();

        // Create the FileResource with an existing ID
        fileResource.setId(1L);
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(fileResource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileResourceMockMvc
            .perform(
                post("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FileResource in the database
        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileResourceRepository.findAll().size();
        // set the field null
        fileResource.setName(null);

        // Create the FileResource, which fails.
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(fileResource);

        restFileResourceMockMvc
            .perform(
                post("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isBadRequest());

        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContextMd5IsRequired() throws Exception {
        int databaseSizeBeforeTest = fileResourceRepository.findAll().size();
        // set the field null
        fileResource.setContextMd5(null);

        // Create the FileResource, which fails.
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(fileResource);

        restFileResourceMockMvc
            .perform(
                post("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isBadRequest());

        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileResourceRepository.findAll().size();
        // set the field null
        fileResource.setSize(null);

        // Create the FileResource, which fails.
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(fileResource);

        restFileResourceMockMvc
            .perform(
                post("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isBadRequest());

        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileResourceRepository.findAll().size();
        // set the field null
        fileResource.setType(null);

        // Create the FileResource, which fails.
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(fileResource);

        restFileResourceMockMvc
            .perform(
                post("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isBadRequest());

        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFileResources() throws Exception {
        // Initialize the database
        fileResourceRepository.saveAndFlush(fileResource);

        // Get all the fileResourceList
        restFileResourceMockMvc
            .perform(get("/api/file-resources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileResource.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].contextMd5").value(hasItem(DEFAULT_CONTEXT_MD_5)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].isAssigned").value(hasItem(DEFAULT_IS_ASSIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }

    @Test
    @Transactional
    public void getFileResource() throws Exception {
        // Initialize the database
        fileResourceRepository.saveAndFlush(fileResource);

        // Get the fileResource
        restFileResourceMockMvc
            .perform(get("/api/file-resources/{id}", fileResource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fileResource.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.contextMd5").value(DEFAULT_CONTEXT_MD_5))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.isAssigned").value(DEFAULT_IS_ASSIGNED.booleanValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingFileResource() throws Exception {
        // Get the fileResource
        restFileResourceMockMvc.perform(get("/api/file-resources/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileResource() throws Exception {
        // Initialize the database
        fileResourceRepository.saveAndFlush(fileResource);

        int databaseSizeBeforeUpdate = fileResourceRepository.findAll().size();

        // Update the fileResource
        FileResource updatedFileResource = fileResourceRepository.findById(fileResource.getId()).get();
        // Disconnect from session so that the updates on updatedFileResource are not directly saved in db
        em.detach(updatedFileResource);
        updatedFileResource
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .contentType(UPDATED_CONTENT_TYPE)
            .contextMd5(UPDATED_CONTEXT_MD_5)
            .size(UPDATED_SIZE)
            .isAssigned(UPDATED_IS_ASSIGNED)
            .type(UPDATED_TYPE);
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(updatedFileResource);

        restFileResourceMockMvc
            .perform(
                put("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isOk());

        // Validate the FileResource in the database
        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeUpdate);
        FileResource testFileResource = fileResourceList.get(fileResourceList.size() - 1);
        assertThat(testFileResource.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFileResource.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testFileResource.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testFileResource.getContextMd5()).isEqualTo(UPDATED_CONTEXT_MD_5);
        assertThat(testFileResource.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testFileResource.isIsAssigned()).isEqualTo(UPDATED_IS_ASSIGNED);
        assertThat(testFileResource.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFileResource() throws Exception {
        int databaseSizeBeforeUpdate = fileResourceRepository.findAll().size();

        // Create the FileResource
        FileResourceDTO fileResourceDTO = fileResourceMapper.toDto(fileResource);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileResourceMockMvc
            .perform(
                put("/api/file-resources")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fileResourceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FileResource in the database
        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFileResource() throws Exception {
        // Initialize the database
        fileResourceRepository.saveAndFlush(fileResource);

        int databaseSizeBeforeDelete = fileResourceRepository.findAll().size();

        // Delete the fileResource
        restFileResourceMockMvc
            .perform(delete("/api/file-resources/{id}", fileResource.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FileResource> fileResourceList = fileResourceRepository.findAll();
        assertThat(fileResourceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
