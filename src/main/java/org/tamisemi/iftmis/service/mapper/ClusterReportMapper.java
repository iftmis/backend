package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tamisemi.iftmis.domain.ClusterReport;
import org.tamisemi.iftmis.service.dto.ClusterReportDTO;

/**
 * Mapper for the entity {@link ClusterReport} and its DTO {@link ClusterReportDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {ClusterMapper.class}
)
public interface ClusterReportMapper extends EntityMapper<ClusterReportDTO, ClusterReport> {
    @Mapping(source = "cluster.id", target = "clusterId")
    @Mapping(source = "cluster.name", target = "clusterName")
    ClusterReportDTO toDto(ClusterReport risk);

    @Mapping(source = "clusterId", target = "cluster")
    ClusterReport toEntity(ClusterReportDTO riskDTO);

    default ClusterReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClusterReport clusterReport = new ClusterReport();
        clusterReport.setId(id);
        return clusterReport;
    }
}
