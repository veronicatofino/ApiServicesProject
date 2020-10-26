package com.puj.admincenter.repository.concepts

import com.puj.admincenter.domain.concepts.Concept
import com.puj.admincenter.dto.concepts.ConceptDto
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

@Repository
interface ConceptRepository : JpaRepository<Concept, Int>,
                           JpaSpecificationExecutor<Concept> {


    @Query("""
        SELECT COUNT(concept) > 0
        FROM Concept concept
        WHERE concept.conceptId = :conceptId
    """)
    fun existsByConceptId(@Param("conceptId") conceptId: Int): Boolean

    @Transactional
    @Modifying
    @Query("""
        UPDATE Concept concept
        SET concept.deletionMark = true
        WHERE concept.conceptId = :conceptId
    """)
    fun logicalRemoval(conceptId: Int)
    
    @Transactional
    @Modifying
    @Query("""
            UPDATE Concept concept
            SET concept.pxordx = :#{#conceptDto.pxordx},
                concept.codeType = :#{#conceptDto.codeType},
                concept.conceptClassId = :#{#conceptDto.conceptClassId},
                concept.conceptId = :conceptId,
                concept.vocabularyId = :#{#conceptDto.vocabularyId},
                concept.domainId = :#{#conceptDto.domainId},
                concept.track = :#{#conceptDto.track},
                concept.standardConcept = :#{#conceptDto.standardConcept},
                concept.code = :#{#conceptDto.code},
                concept.codeWithPeriods = :#{#conceptDto.codeWithPeriods},
                concept.codeScheme = :#{#conceptDto.codeScheme},
                concept.longDesc = :#{#conceptDto.longDesc},
                concept.shortDesc = :#{#conceptDto.shortDesc},
                concept.codeStatus = :#{#conceptDto.codeStatus},
                concept.codeChange = :#{#conceptDto.codeChange},
                concept.codeChangeYear = :#{#conceptDto.codeChangeYear},
                concept.codePlannedType = :#{#conceptDto.codePlannedType},
                concept.codeBillingStatus = :#{#conceptDto.codeBillingStatus},
                concept.codeCmsClaimStatus = :#{#conceptDto.codeCmsClaimStatus},
                concept.sexCd = :#{#conceptDto.sexCd},
                concept.anatOrCond = :#{#conceptDto.anatOrCond},
                concept.poaCodeStatus = :#{#conceptDto.poaCodeStatus},
                concept.poaCodeChange = :#{#conceptDto.poaCodeChange},
                concept.poaCodeChangeYear = :#{#conceptDto.poaCodeChangeYear},
                concept.validStartDate = :#{#conceptDto.validStartDate},
                concept.validEndDate = :#{#conceptDto.validEndDate},
                concept.invalidReason = :#{#conceptDto.invalidReason}
            WHERE concept.conceptId = :conceptId
        """)
        fun updateByConceptId(conceptDto: ConceptDto,conceptId: Int)
    
}