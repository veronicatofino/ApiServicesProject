package com.puj.admincenter.repository.concepts

import com.puj.admincenter.domain.concepts.Concept

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import javax.persistence.criteria.Predicate

import java.util.*
//import javax.persistence.criteria.Subquery


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
}