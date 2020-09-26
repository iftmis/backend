package org.tamisemi.iftmis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.ClusterReport;

import java.util.List;


@Repository
public interface ClusterReportRepository extends JpaRepository<ClusterReport, Long> {
    List<ClusterReport> findAllByClusterId(Long clusterId);

    Page<ClusterReport> findAllByClusterId(Long clusterId, Pageable pageable);
}
