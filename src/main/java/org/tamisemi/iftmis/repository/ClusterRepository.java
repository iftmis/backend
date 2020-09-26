package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Cluster;
import org.tamisemi.iftmis.domain.FindingCategory;


@Repository
public interface ClusterRepository extends JpaRepository<Cluster, Long> {}
