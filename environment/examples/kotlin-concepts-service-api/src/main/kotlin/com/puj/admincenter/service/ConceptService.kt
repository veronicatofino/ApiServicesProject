package com.puj.admincenter.service

import com.puj.admincenter.domain.concepts.Concept
import com.puj.admincenter.dto.concepts.ConceptDto
import com.puj.admincenter.dto.concepts.CreateConceptDto
import com.puj.admincenter.dto.IdResponseDto
import com.puj.admincenter.repository.concepts.ConceptRepository
import com.puj.admincenter.domain.concepts.ConceptSpecification

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory
import java.io.Serializable
import java.util.*

@Service
class ConceptService(private val conceptRepository: ConceptRepository){
    companion object {
        val LOG = LoggerFactory.getLogger(ConceptService::class.java)!!
    }

    fun count(): Long {
        return conceptRepository.count()
    }

    fun create(createConceptDto: CreateConceptDto): ResponseEntity<*>{
        if (conceptRepository.existsByConceptId(createConceptDto.conceptId)) {
            val messageError = "Concept with concept id: ${createConceptDto.conceptId} already exists."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                        HttpStatus.CONFLICT)
        }

        val concept = Concept(pxordx = createConceptDto.pxordx,
                              oldPxordx = createConceptDto.oldPxordx, 
                              codeType = createConceptDto.codeType,
                              conceptClassId = createConceptDto.conceptClassId,
                              conceptId = createConceptDto.conceptId,
                              vocabularyId = createConceptDto.vocabularyId,
                              domainId = createConceptDto.domainId,
                              track = createConceptDto.track,
                              standardConcept = createConceptDto.standardConcept,
                              code = createConceptDto.code,
                              codeWithPeriods = createConceptDto.codeWithPeriods,
                              codeScheme = createConceptDto.codeScheme,
                              longDesc = createConceptDto.longDesc,
                              shortDesc = createConceptDto.shortDesc,
                              codeStatus = createConceptDto.codeStatus,
                              codeChange = createConceptDto.codeChange,
                              codeChangeYear = createConceptDto.codeChangeYear,
                              codePlannedType = createConceptDto.codePlannedType,
                              codeBillingStatus = createConceptDto.codeBillingStatus,
                              codeCmsClaimStatus = createConceptDto.codeCmsClaimStatus,
                              sexCd = createConceptDto.sexCd,
                              anatOrCond = createConceptDto.anatOrCond,
                              poaCodeStatus = createConceptDto.poaCodeStatus,
                              poaCodeChange = createConceptDto.poaCodeChange,
                              poaCodeChangeYear = createConceptDto.poaCodeChangeYear, 
                              validStartDate = createConceptDto.validStartDate, 
                              validEndDate = createConceptDto.validEndDate, 
                              invalidReason = createConceptDto.invalidReason, 
                              createDt = createConceptDto.createDt,
                              deletionMark = false)
        val conceptSaved = conceptRepository.save(concept)
        LOG.info("Concept ${createConceptDto.conceptId} created with id ${conceptSaved.id}")

        val responseDto = IdResponseDto(conceptSaved.id.toLong())
        return ResponseEntity<IdResponseDto>(responseDto,
                                             HttpStatus.CREATED)
    }

    fun deleteByConceptId(conceptId: Int): ResponseEntity<*>{
        if (!(conceptRepository.existsByConceptId(conceptId))) {
            val messageError = "Concept with concept id: ${conceptId} does not exist."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                        HttpStatus.CONFLICT)
        }

        conceptRepository.logicalRemoval(conceptId)
        LOG.info("Concept ${conceptId} was deleted.")
        return ResponseEntity<Any>(HttpStatus.NO_CONTENT)
    }

    fun getConceptsByGeneralParams(vocabularyId : String?,
                                   conceptId : String?,
                                   domainId : String?,
                                   shortDesc : String?) : ResponseEntity<*> {
        val concepts = conceptRepository.findAll(ConceptSpecification(vocabularyId,
                                                                      conceptId,
                                                                      domainId,
                                                                      shortDesc)).map { ConceptDto.convert(it) }
        val numberOfConceptsFound = concepts.size
        if (!concepts.isEmpty()) {
            LOG.info("${numberOfConceptsFound} concepts found with VocabularyId: ${vocabularyId}, ConceptId: ${conceptId}, DomainId: ${domainId} and ShortDesc: ${shortDesc}")
            return ResponseEntity<List<ConceptDto>>(concepts, HttpStatus.OK)
        }
        else {
            LOG.info("No concepts were found with VocabularyId: ${vocabularyId}, ConceptId: ${conceptId}, DomainId: ${domainId} and ShortDesc: ${shortDesc}")
            return ResponseEntity<Any>("No concepts were found", HttpStatus.NOT_FOUND)
        }
    }
    
    fun updateByConceptId(conceptDto: ConceptDto, conceptId: Int): ResponseEntity<*>{
        if (!(conceptRepository.existsByConceptId(conceptId))) {
            val messageError = "Concept with concept id: ${conceptId} does not exist."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                        HttpStatus.CONFLICT)
        }
        /*val concept = Concept(pxordx = conceptDto.pxordx,
                            oldPxordx = conceptDto.oldPxordx, 
                            codeType = conceptDto.codeType,
                            conceptClassId = conceptDto.conceptClassId,
                            conceptId = conceptDto.conceptId,
                            vocabularyId = conceptDto.vocabularyId,
                            domainId = conceptDto.domainId,
                            track = conceptDto.track,
                            standardConcept = conceptDto.standardConcept,
                            code = conceptDto.code,
                            codeWithPeriods = conceptDto.codeWithPeriods,
                            codeScheme = conceptDto.codeScheme,
                            longDesc = conceptDto.longDesc,
                            shortDesc = conceptDto.shortDesc,
                            codeStatus = conceptDto.codeStatus,
                            codeChange = conceptDto.codeChange,
                            codeChangeYear = conceptDto.codeChangeYear,
                            codePlannedType = conceptDto.codePlannedType,
                            codeBillingStatus = conceptDto.codeBillingStatus,
                            codeCmsClaimStatus = conceptDto.codeCmsClaimStatus,
                            sexCd = conceptDto.sexCd,
                            anatOrCond = conceptDto.anatOrCond,
                            poaCodeStatus = conceptDto.poaCodeStatus,
                            poaCodeChange = conceptDto.poaCodeChange,
                            poaCodeChangeYear = conceptDto.poaCodeChangeYear, 
                            validStartDate = conceptDto.validStartDate, 
                            validEndDate = conceptDto.validEndDate, 
                            invalidReason = conceptDto.invalidReason, 
                            createDt = conceptDto.createDt)*/
        
        conceptRepository.updateByConceptId(conceptDto, conceptId)
        LOG.info("Concept ${conceptId} was updated.")
        return ResponseEntity<Any>(HttpStatus.NO_CONTENT)
    }
}