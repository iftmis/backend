package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.config.Constants;
import org.tamisemi.iftmis.domain.FinancialYear;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.RiskRegister;
import org.tamisemi.iftmis.domain.User;
import org.tamisemi.iftmis.service.FinancialYearService;
import org.tamisemi.iftmis.service.RiskRegisterService;
import org.tamisemi.iftmis.service.UserService;
import org.tamisemi.iftmis.service.dto.FinancialYearDTO;
import org.tamisemi.iftmis.service.dto.RiskRegisterDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FinancialYear}.
 */
@RestController
@RequestMapping("/api")
public class FinancialYearResource {
    private final Logger log = LoggerFactory.getLogger(FinancialYearResource.class);

    private static final String ENTITY_NAME = "financialYear";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinancialYearService financialYearService;
    private final UserService userService;
    private final RiskRegisterService riskRegisterService;

    public FinancialYearResource(
        FinancialYearService financialYearService,
        UserService userService,
        RiskRegisterService riskRegisterService
    ) {
        this.financialYearService = financialYearService;
        this.userService = userService;
        this.riskRegisterService = riskRegisterService;
    }

    /**
     * {@code POST  /financial-years} : Create a new financialYear.
     *
     * @param financialYearDTO the financialYearDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new financialYearDTO, or with status {@code 400 (Bad Request)} if the financialYear has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/financial-years")
    public ResponseEntity<FinancialYearDTO> createFinancialYear(@Valid @RequestBody FinancialYearDTO financialYearDTO)
        throws URISyntaxException {
        log.debug("REST request to save FinancialYear : {}", financialYearDTO);
        if (financialYearDTO.getId() != null) {
            throw new BadRequestAlertException("A new financialYear cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinancialYearDTO result = financialYearService.save(financialYearDTO);
        return ResponseEntity
            .created(new URI("/api/financial-years/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /financial-years} : Updates an existing financialYear.
     *
     * @param financialYearDTO the financialYearDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financialYearDTO,
     * or with status {@code 400 (Bad Request)} if the financialYearDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the financialYearDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/financial-years")
    public ResponseEntity<FinancialYearDTO> updateFinancialYear(@Valid @RequestBody FinancialYearDTO financialYearDTO)
        throws URISyntaxException {
        log.debug("REST request to update FinancialYear : {}", financialYearDTO);
        if (financialYearDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinancialYearDTO result = financialYearService.save(financialYearDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, financialYearDTO.getId().toString()))
            .body(result);
    }

    /**
     * @return
     */
    @GetMapping("/financial-years")
    public ResponseEntity<List<FinancialYear>> getAllFinancialYears() {
        log.debug("REST request to get a list of FinancialYears");
        List<FinancialYear> items = financialYearService.findAll();
        return ResponseEntity.ok().body(items);
    }

    /**
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @GetMapping("/financial-years/page")
    public ResponseEntity<List<FinancialYearDTO>> getAllPagedFinancialYears(
        @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
        @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
        @RequestParam(value = "sortBy", defaultValue = "startDate") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<FinancialYearDTO> items = financialYearService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), items);
        return ResponseEntity.ok().headers(headers).body(items.getContent());
    }

    /**
     * {@code GET  /financial-years/:id} : get the "id" financialYear.
     *
     * @param id the id of the financialYearDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the financialYearDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/financial-years/{id}")
    public ResponseEntity<FinancialYearDTO> getFinancialYear(@PathVariable Long id) {
        log.debug("REST request to get FinancialYear : {}", id);
        Optional<FinancialYearDTO> financialYearDTO = financialYearService.findOne(id);
        return ResponseUtil.wrapOrNotFound(financialYearDTO);
    }

    /**
     * {@code DELETE  /financial-years/:id} : delete the "id" financialYear.
     *
     * @param id the id of the financialYearDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/financial-years/{id}")
    public ResponseEntity<Void> deleteFinancialYear(@PathVariable Long id) {
        log.debug("REST request to delete FinancialYear : {}", id);
        financialYearService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/financial-years/activate")
    public ResponseEntity<?> activate(@Valid @RequestBody FinancialYear financialYear) throws URISyntaxException {
        if (financialYear.getId() == null) {
            return ResponseEntity.badRequest().body("New Financial Year is required");
        }
        if (financialYear.getClosed()) {
            return ResponseEntity.badRequest().body("Financial Year Selected is already closed");
        }
        if (financialYearService.closeCurrentFinancialYear()) {
            Optional<FinancialYearDTO> row = financialYearService.findOne(financialYear.getId());
            if (row.isPresent()) {
                FinancialYearDTO financialYearDTO = row.get();
                financialYearDTO.setIsOpened(true);
                financialYearDTO.setClosed(false);
                FinancialYearDTO result = financialYearService.save(financialYearDTO);
                List<RiskRegisterDTO> riskRegisters = riskRegisterService.findAllByFinancialYearId(result.getId());
                if (riskRegisters.size() == 0) {
                    User currentUser = userService.currentUser();
                    OrganisationUnit organisationUnit = currentUser.getOrganisationUnit();
                    String riskRegisterName = organisationUnit.getName() + " - " + result.getName();
                    RiskRegisterDTO riskRegisterDTO = new RiskRegisterDTO();
                    riskRegisterDTO.setApprovedDate(null);
                    riskRegisterDTO.setIsApproved(false);
                    riskRegisterDTO.setApprovedBy(null);
                    riskRegisterDTO.setFinancialYearId(result.getId());
                    riskRegisterDTO.setFinancialYearName(result.getName());
                    riskRegisterDTO.setName(riskRegisterName);
                    riskRegisterDTO.setOrganisationUnitId(organisationUnit.getId());
                    riskRegisterDTO.setOrganisationUnitName(organisationUnit.getName());
                    riskRegisterService.save(riskRegisterDTO);
                }
                return ResponseEntity
                    .created(new URI("/api/financial-years/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                    .body(result);
            } else {
                return ResponseUtil.wrapOrNotFound(row);
            }
        } else {
            return ResponseEntity.badRequest().body("Financial Year Could not be activated");
        }
    }
}
