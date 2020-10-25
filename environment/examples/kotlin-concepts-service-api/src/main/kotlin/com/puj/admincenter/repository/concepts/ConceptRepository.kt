package com.puj.admincenter.repository.concepts

import com.puj.admincenter.domain.concepts.Concept

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
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
}