package org.tamisemi.iftmis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.ClusterReport;
import org.tamisemi.iftmis.repository.ClusterReportRepository;
import org.tamisemi.iftmis.service.dto.ClusterReportDTO;
import org.tamisemi.iftmis.service.mapper.ClusterReportMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClusterReportService {
    private final Logger log = LoggerFactory.getLogger(ClusterReportService.class);

    @Autowired
    private ClusterReportRepository clusterRepository;

    @Autowired
    private ClusterReportMapper clusterReportMapper;

    public ClusterReportDTO save(ClusterReportDTO clusterDTO) {
        ClusterReport cluster = clusterReportMapper.toEntity(clusterDTO);
        cluster = clusterRepository.save(cluster);
        return clusterReportMapper.toDto(cluster);
    }

    @Transactional(readOnly = true)
    public List<ClusterReportDTO> findAll(Long clusterId) {
        return clusterRepository.findAllByClusterId(clusterId).stream()
            .map(clusterReportMapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ClusterReportDTO> findAll(Long clusterId, Pageable pageable) {
        return clusterRepository.findAllByClusterId(clusterId, pageable).map(clusterReportMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<ClusterReportDTO> findOne(Long id) {
        return clusterRepository.findById(id).map(clusterReportMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete ClusterReport : {}", id);
        clusterRepository.deleteById(id);
    }
}
