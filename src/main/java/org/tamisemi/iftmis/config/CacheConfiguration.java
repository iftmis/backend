package org.tamisemi.iftmis.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, org.tamisemi.iftmis.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, org.tamisemi.iftmis.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, org.tamisemi.iftmis.domain.User.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Authority.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.User.class.getName() + ".authorities");
            createCache(cm, org.tamisemi.iftmis.domain.PersistentToken.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.User.class.getName() + ".persistentTokens");
            createCache(cm, org.tamisemi.iftmis.domain.ResponseAttachment.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.EntityAuditEvent.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.OrganisationUnitLevel.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.OrganisationUnit.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.FinancialYear.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Quarter.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.FileResource.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.GfsCode.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.FindingCategory.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.FindingSubCategory.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.AuditableArea.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.SubArea.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Indicator.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Procedure.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Notification.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Inspection.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionMember.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Meeting.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.MeetingMember.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.MeetingAgenda.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.MeetingAttachment.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionArea.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionSubArea.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionIndicator.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionProcedure.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionWorkDone.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionFinding.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionRecommendation.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Finding.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.FindingRecommendation.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.FindingResponse.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.RiskRank.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.RiskRegister.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Objective.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.RiskCategory.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.Risk.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.RiskRating.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionPlan.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionActivity.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionActivityQuarter.class.getName());
            createCache(cm, org.tamisemi.iftmis.domain.InspectionActivity.class.getName() + ".risks");
            createCache(cm, org.tamisemi.iftmis.domain.InspectionActivity.class.getName() + ".organisationUnits");
            createCache(cm, org.tamisemi.iftmis.domain.InspectionBudget.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
