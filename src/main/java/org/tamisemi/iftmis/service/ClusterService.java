package org.tamisemi.iftmis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Cluster;
import org.tamisemi.iftmis.repository.ClusterRepository;
import org.tamisemi.iftmis.service.dto.ClusterDTO;
import org.tamisemi.iftmis.service.mapper.ClusterMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Cluster}.
 */
@Service
@Transactional
public class ClusterService {
    private final Logger log = LoggerFactory.getLogger(ClusterService.class);

    private final ClusterRepository clusterRepository;

    private final ClusterMapper clusterMapper;

    public ClusterService(ClusterRepository clusterRepository, ClusterMapper clusterMapper) {
        this.clusterRepository = clusterRepository;
        this.clusterMapper = clusterMapper;
    }

    public ClusterDTO save(ClusterDTO clusterDTO) {
        log.debug("Request to save Cluster : {}", clusterDTO);
        Cluster cluster = clusterMapper.toEntity(clusterDTO);
        cluster = clusterRepository.save(cluster);
        return clusterMapper.toDto(cluster);
    }

    @Transactional(readOnly = true)
    public Page<ClusterDTO> findAll(Pageable pageable) {
        return clusterRepository.findAll(pageable).map(clusterMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<ClusterDTO> findAll() {
        return clusterRepository.findAll().stream().map(clusterMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ClusterDTO> findOne(Long id) {
        return clusterRepository.findById(id).map(clusterMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Cluster : {}", id);
        clusterRepository.deleteById(id);
    }
}
