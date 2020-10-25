package com.puj.admincenter.domain.concepts

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "concept")
data class Concept(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Column(nullable = false, unique = true)
    val pxordx: String = "",

    @Column(nullable = false)
    val oldPxordx: String = "",

    @Column(nullable = false)
    val codeType: String = "",

    @Column(nullable = false)
    val conceptClassId: String = "",

    @Column(nullable = true)
    val conceptId: Int = 0,

    @Column(nullable = false)
    val vocabularyId: String = "",

    @Column(nullable = false)
    val domainId: String = "",

    @Column(nullable = true)
    val track: String? = "",

    @Column(nullable = true)
    val standardConcept: String? = "",

    @Column(nullable = false)
    val code: String = "",

    @Column(nullable = false)
    val codeWithPeriods: String = "",

    @Column(nullable = false)
    val codeScheme: String = "",

    @Column(nullable = false)
    val longDesc: String = "",

    @Column(nullable = false)
    val shortDesc: String = "",

    @Column(nullable = false)
    val codeStatus: String = "",

    @Column(nullable = false)
    val codeChange: String = "",

    @Column(nullable = false)
    val codeChangeYear: String = "",

    @Column(nullable = false)
    val codePlannedType: String = "",

    @Column(nullable = false)
    val codeBillingStatus: String = "",

    @Column(nullable = false)
    val codeCmsClaimStatus: String = "",

    @Column(nullable = true)
    val sexCd: String? = "",

    @Column(nullable = false)
    val anatOrCond: String = "",

    @Column(nullable = true)
    val poaCodeStatus: String? = "",

    @Column(nullable = true)
    val poaCodeChange: String? = "",

    @Column(nullable = true)
    val poaCodeChangeYear: Int? = 0,

    @Column(nullable = true)
    val validStartDate: String? = "",

    @Column(nullable = true)
    val validEndDate: String? = "",

    @Column(nullable = true)
    val invalidReason: String? = "",

    @Column(nullable = true)
    val createDt: String? = ""
)