package com.puj.admincenter.service

import com.puj.admincenter.domain.concepts.Concept
/**import com.puj.admincenter.dto.concepts.ConceptDto*/
import com.puj.admincenter.dto.concepts.CreateConceptDto
import com.puj.admincenter.dto.IdResponseDto
import com.puj.admincenter.repository.concepts.ConceptRepository

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

    fun deleteById(conceptId: Int): ResponseEntity<*>{
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
}