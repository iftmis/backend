package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.ClusterReport} entity.
 */
@ApiModel(description = "The ClusterReport(cluster_reports) entity.\n@author Kachinga")
public class ClusterReportDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    private String code;

    private String name;

    private String description;

    private Long clusterId;

    private Long clusterName;

    public ClusterReportDTO(Long id, String code, String name, String description, Long clusterId, Long clusterName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.clusterId = clusterId;
        this.clusterName = clusterName;
    }

    public ClusterReportDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    public Long getClusterName() {
        return clusterName;
    }

    public void setClusterName(Long clusterName) {
        this.clusterName = clusterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClusterReportDTO)) {
            return false;
        }

        return id != null && id.equals(((ClusterReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClusterReportDTO{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", clusterId=" + clusterId +
            ", clusterName=" + clusterName +
            '}';
    }
}
