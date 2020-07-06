package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Procedure;
import org.tamisemi.iftmis.repository.ProcedureRepository;
import org.tamisemi.iftmis.service.dto.ProcedureDTO;
import org.tamisemi.iftmis.service.mapper.ProcedureMapper;

/**
 * Service Implementation for managing {@link Procedure}.
 */
@Service
@Transactional
public class ProcedureService {
    private final Logger log = LoggerFactory.getLogger(ProcedureService.class);

    private final ProcedureRepository procedureRepository;

    private final ProcedureMapper procedureMapper;

    public ProcedureService(ProcedureRepository procedureRepository, ProcedureMapper procedureMapper) {
        this.procedureRepository = procedureRepository;
        this.procedureMapper = procedureMapper;
    }

    /**
     * Save a procedure.
     *
     * @param procedureDTO the entity to save.
     * @return the persisted entity.
     */
    public ProcedureDTO save(ProcedureDTO procedureDTO) {
        log.debug("Request to save Procedure : {}", procedureDTO);
        Procedure procedure = procedureMapper.toEntity(procedureDTO);
        procedure = procedureRepository.save(procedure);
        return procedureMapper.toDto(procedure);
    }

    /**
     * Get all the procedures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProcedureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Procedures");
        return procedureRepository.findAll(pageable).map(procedureMapper::toDto);
    }

    /**
     * Get one procedure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProcedureDTO> findOne(Long id) {
        log.debug("Request to get Procedure : {}", id);
        return procedureRepository.findById(id).map(procedureMapper::toDto);
    }

    /**
     * Delete the procedure by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Procedure : {}", id);
        procedureRepository.deleteById(id);
    }
}
