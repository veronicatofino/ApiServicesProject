package com.puj.admincenter.controller

import com.puj.admincenter.domain.concepts.Concept
import com.puj.admincenter.dto.concepts.CreateConceptDto
import com.puj.admincenter.dto.concepts.ConceptDto
import com.puj.admincenter.service.ConceptService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.data.web.PageableDefault
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import javax.validation.Valid
import javax.servlet.http.HttpServletRequest
import java.security.Principal
import java.util.Date
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CrossOrigin
@RequestMapping("/concepts")
@RestController
class ConceptController(private val conceptService: ConceptService) {
    companion object {
        val logger = LoggerFactory.getLogger(ConceptController::class.java)!!
    }

    @PostMapping(
        value = ["/create"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    /*fun create(@RequestBody @Valid createConceptDto: CreateConceptDto,
               @RequestHeader(value="authorization", required=false) authorization: String): ResponseEntity<*>
        = conceptService.create(createConceptDto)*/
    
    fun create(@RequestBody @Valid createConceptDto: CreateConceptDto): ResponseEntity<*>
        = conceptService.create(createConceptDto)
     
    @PutMapping(
        value = ["/update/{conceptId}"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )

    fun create(@RequestBody @Valid conceptDto: ConceptDto, @PathVariable conceptId: Int): ResponseEntity<*>
        = conceptService.updateByConceptId(conceptDto, conceptId)

    @DeleteMapping(
        value = ["/delete/{conceptId}"]
    )

    fun delete(@PathVariable conceptId: Int): ResponseEntity<*>
        = conceptService.deleteByConceptId(conceptId)

    @GetMapping(
        value = ["/getConceptsByGeneralParams"],
        produces = ["application/json"]
    )
    fun getConceptsByGeneralParams(@RequestParam(value = "vocabularyId", required = false) vocabularyId: String?,
                                   @RequestParam(value = "conceptId", required = false) conceptId: String?,
                                   @RequestParam(value = "domainId", required = false) domainId: String?,
                                   @RequestParam(value = "shortDesc", required = false) shortDesc: String?) : ResponseEntity<*> {
        return conceptService.getConceptsByGeneralParams(vocabularyId, 
                                                        conceptId,
                                                        domainId,
                                                        shortDesc)
    }
}

