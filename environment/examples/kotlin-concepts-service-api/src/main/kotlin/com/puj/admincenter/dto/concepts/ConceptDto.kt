package com.puj.admincenter.dto.concepts

import com.puj.admincenter.domain.concepts.Concept

data class ConceptDto(
    val id: Int,
    val pxordx: String,
    val oldPxordx: String,
    val codeType: String,
    val conceptClassId: String,
    val conceptId: Int,
    val vocabularyId: String,
    val domainId: String,
    val track: String?,
    val standardConcept: String?,
    val code: String,
    val codeWithPeriods: String,
    val codeScheme: String,
    val longDesc: String,
    val shortDesc: String,
    val codeStatus: String,
    val codeChange: String,
    val codeChangeYear: String,
    val codePlannedType: String,
    val codeBillingStatus: String,
    val codeCmsClaimStatus: String,
    val sexCd: String?,
    val anatOrCond: String,
    val poaCodeStatus: String?,
    val poaCodeChange: String?,
    val poaCodeChangeYear: Int?,
    val validStartDate: String?,
    val validEndDate: String?,
    val invalidReason: String?,
    val createDt: String?
) {
    companion object {
        fun convert(concept: Concept): ConceptDto {
            val dto = ConceptDto(
                id = concept.id,
                pxordx = concept.pxordx,
                oldPxordx = concept.oldPxordx,
                codeType = concept.codeType,
                conceptClassId = concept.conceptClassId,
                conceptId = concept.conceptId,
                vocabularyId = concept.vocabularyId,
                domainId = concept.domainId,
                track = concept.track,
                standardConcept = concept.standardConcept,
                code = concept.code,
                codeWithPeriods = concept.codeWithPeriods,
                codeScheme = concept.codeScheme,
                longDesc = concept.longDesc,
                shortDesc = concept.shortDesc,
                codeStatus = concept.codeStatus,
                codeChange = concept.codeChange,
                codeChangeYear = concept.codeChangeYear,
                codePlannedType = concept.codePlannedType,
                codeBillingStatus = concept.codeBillingStatus,
                codeCmsClaimStatus = concept.codeCmsClaimStatus,
                sexCd = concept.sexCd,
                anatOrCond = concept.anatOrCond,
                poaCodeStatus = concept.poaCodeStatus,
                poaCodeChange = concept.poaCodeChange,
                poaCodeChangeYear = concept.poaCodeChangeYear,
                validStartDate = concept.validStartDate,
                validEndDate = concept.validEndDate,
                invalidReason = concept.invalidReason,
                createDt = concept.createDt
            )
            return dto
        }
    }
}