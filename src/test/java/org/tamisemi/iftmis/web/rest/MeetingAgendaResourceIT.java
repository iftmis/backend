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
import org.springframework.util.Base64Utils;
import org.tamisemi.iftmis.IftmisApp;
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.domain.MeetingAgenda;
import org.tamisemi.iftmis.repository.MeetingAgendaRepository;

/**
 * Integration tests for the {@link MeetingAgendaResource} REST controller.
 */
@SpringBootTest(classes = IftmisApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MeetingAgendaResourceIT {
    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MeetingAgendaRepository meetingAgendaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMeetingAgendaMockMvc;

    private MeetingAgenda meetingAgenda;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeetingAgenda createEntity(EntityManager em) {
        MeetingAgenda meetingAgenda = new MeetingAgenda().description(DEFAULT_DESCRIPTION);
        // Add required entity
        Meeting meeting;
        if (TestUtil.findAll(em, Meeting.class).isEmpty()) {
            meeting = MeetingResourceIT.createEntity(em);
            em.persist(meeting);
            em.flush();
        } else {
            meeting = TestUtil.findAll(em, Meeting.class).get(0);
        }
        meetingAgenda.setMeeting(meeting);
        return meetingAgenda;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeetingAgenda createUpdatedEntity(EntityManager em) {
        MeetingAgenda meetingAgenda = new MeetingAgenda().description(UPDATED_DESCRIPTION);
        // Add required entity
        Meeting meeting;
        if (TestUtil.findAll(em, Meeting.class).isEmpty()) {
            meeting = MeetingResourceIT.createUpdatedEntity(em);
            em.persist(meeting);
            em.flush();
        } else {
            meeting = TestUtil.findAll(em, Meeting.class).get(0);
        }
        meetingAgenda.setMeeting(meeting);
        return meetingAgenda;
    }

    @BeforeEach
    public void initTest() {
        meetingAgenda = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeetingAgenda() throws Exception {
        int databaseSizeBeforeCreate = meetingAgendaRepository.findAll().size();
        // Create the MeetingAgenda
        restMeetingAgendaMockMvc
            .perform(
                post("/api/meeting-agenda")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingAgenda))
            )
            .andExpect(status().isCreated());

        // Validate the MeetingAgenda in the database
        List<MeetingAgenda> meetingAgendaList = meetingAgendaRepository.findAll();
        assertThat(meetingAgendaList).hasSize(databaseSizeBeforeCreate + 1);
        MeetingAgenda testMeetingAgenda = meetingAgendaList.get(meetingAgendaList.size() - 1);
        assertThat(testMeetingAgenda.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMeetingAgendaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = meetingAgendaRepository.findAll().size();

        // Create the MeetingAgenda with an existing ID
        meetingAgenda.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeetingAgendaMockMvc
            .perform(
                post("/api/meeting-agenda")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingAgenda))
            )
            .andExpect(status().isBadRequest());

        // Validate the MeetingAgenda in the database
        List<MeetingAgenda> meetingAgendaList = meetingAgendaRepository.findAll();
        assertThat(meetingAgendaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMeetingAgenda() throws Exception {
        // Initialize the database
        meetingAgendaRepository.saveAndFlush(meetingAgenda);

        // Get all the meetingAgendaList
        restMeetingAgendaMockMvc
            .perform(get("/api/meeting-agenda?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(meetingAgenda.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getMeetingAgenda() throws Exception {
        // Initialize the database
        meetingAgendaRepository.saveAndFlush(meetingAgenda);

        // Get the meetingAgenda
        restMeetingAgendaMockMvc
            .perform(get("/api/meeting-agenda/{id}", meetingAgenda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(meetingAgenda.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMeetingAgenda() throws Exception {
        // Get the meetingAgenda
        restMeetingAgendaMockMvc.perform(get("/api/meeting-agenda/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeetingAgenda() throws Exception {
        // Initialize the database
        meetingAgendaRepository.saveAndFlush(meetingAgenda);

        int databaseSizeBeforeUpdate = meetingAgendaRepository.findAll().size();

        // Update the meetingAgenda
        MeetingAgenda updatedMeetingAgenda = meetingAgendaRepository.findById(meetingAgenda.getId()).get();
        // Disconnect from session so that the updates on updatedMeetingAgenda are not directly saved in db
        em.detach(updatedMeetingAgenda);
        updatedMeetingAgenda.description(UPDATED_DESCRIPTION);

        restMeetingAgendaMockMvc
            .perform(
                put("/api/meeting-agenda")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMeetingAgenda))
            )
            .andExpect(status().isOk());

        // Validate the MeetingAgenda in the database
        List<MeetingAgenda> meetingAgendaList = meetingAgendaRepository.findAll();
        assertThat(meetingAgendaList).hasSize(databaseSizeBeforeUpdate);
        MeetingAgenda testMeetingAgenda = meetingAgendaList.get(meetingAgendaList.size() - 1);
        assertThat(testMeetingAgenda.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMeetingAgenda() throws Exception {
        int databaseSizeBeforeUpdate = meetingAgendaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeetingAgendaMockMvc
            .perform(
                put("/api/meeting-agenda")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(meetingAgenda))
            )
            .andExpect(status().isBadRequest());

        // Validate the MeetingAgenda in the database
        List<MeetingAgenda> meetingAgendaList = meetingAgendaRepository.findAll();
        assertThat(meetingAgendaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeetingAgenda() throws Exception {
        // Initialize the database
        meetingAgendaRepository.saveAndFlush(meetingAgenda);

        int databaseSizeBeforeDelete = meetingAgendaRepository.findAll().size();

        // Delete the meetingAgenda
        restMeetingAgendaMockMvc
            .perform(delete("/api/meeting-agenda/{id}", meetingAgenda.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MeetingAgenda> meetingAgendaList = meetingAgendaRepository.findAll();
        assertThat(meetingAgendaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
