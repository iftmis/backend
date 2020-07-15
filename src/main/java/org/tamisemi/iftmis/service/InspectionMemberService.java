package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionMember;
import org.tamisemi.iftmis.repository.InspectionMemberRepository;
import org.tamisemi.iftmis.service.dto.InspectionMemberDTO;
import org.tamisemi.iftmis.service.mapper.InspectionMemberMapper;

/**
 * Service Implementation for managing {@link InspectionMember}.
 */
@Service
@Transactional
public class InspectionMemberService {
    private final Logger log = LoggerFactory.getLogger(InspectionMemberService.class);

    private final InspectionMemberRepository inspectionMemberRepository;

    private final InspectionMemberMapper inspectionMemberMapper;

    public InspectionMemberService(InspectionMemberRepository inspectionMemberRepository, InspectionMemberMapper inspectionMemberMapper) {
        this.inspectionMemberRepository = inspectionMemberRepository;
        this.inspectionMemberMapper = inspectionMemberMapper;
    }

    /**
     * Save a inspectionMember.
     *
     * @param inspectionMemberDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionMemberDTO save(InspectionMemberDTO inspectionMemberDTO) {
        log.debug("Request to save InspectionMember : {}", inspectionMemberDTO);
        InspectionMember inspectionMember = inspectionMemberMapper.toEntity(inspectionMemberDTO);
        inspectionMember = inspectionMemberRepository.save(inspectionMember);
        return inspectionMemberMapper.toDto(inspectionMember);
    }

    /**
     * Get all the inspectionMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionMemberDTO> findAll(Long inspectionId, Pageable pageable) {
        log.debug("Request to get all InspectionMembers");
        return inspectionMemberRepository.findByInspection_Id(inspectionId, pageable).map(inspectionMemberMapper::toDto);
    }

    /**
     * Get one inspectionMember by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionMemberDTO> findOne(Long id) {
        log.debug("Request to get InspectionMember : {}", id);
        return inspectionMemberRepository.findById(id).map(inspectionMemberMapper::toDto);
    }

    /**
     * Delete the inspectionMember by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionMember : {}", id);
        inspectionMemberRepository.deleteById(id);
    }
}
