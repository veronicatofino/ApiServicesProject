package com.puj.admincenter.domain.concepts

import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.Modifying

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.persistence.criteria.JoinType

class ConceptSpecification(private val vocabularyId : String?,
                           private val conceptId : String?,
                           private val domainId : String?,
                           private val shortDesc : String?) : Specification<Concept> {
        override fun toPredicate(root : Root<Concept>, query : CriteriaQuery<*>, cb : CriteriaBuilder) : Predicate? {
            val p = mutableListOf<Predicate>()
            vocabularyId?.let { p.add(cb.equal(root.get<String>("vocabularyId"), vocabularyId)) }
            conceptId?.let { p.add(cb.equal(root.get<String>("conceptId"), conceptId)) }
            domainId?.let { p.add(cb.equal(root.get<String>("domainId"), domainId)) }
            shortDesc?.let{ p.add(cb.like(root.get<String>("shortDesc"), "%$shortDesc%")) }
            p.add(cb.lessThan(root.get<Int>("deletionMark"), 1))
            return cb.and(*p.toTypedArray())
        }
    }