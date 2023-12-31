package ru.ambulance.nurseservice.config

import kotlinx.coroutines.FlowPreview
import org.springdoc.kotlin.docRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import ru.ambulance.config.web.WebConfig
import ru.ambulance.function.logger
import ru.ambulance.nurseservice.controller.NurseHandler
import ru.ambulance.nurseservice.service.InvestigationResultService
import ru.ambulance.nurseservice.service.NurseShiftService
import ru.ambulance.nurseservice.service.TreatmentResultService

@Configuration
class NurseWebConfig : WebConfig() {

    private val log = logger()

    @Bean
    @FlowPreview
    fun nurseRouter(nurseHandler: NurseHandler): RouterFunction<ServerResponse> = docRouter {
        log.info("Nurse route is configuring!")
        POST("/nurse/beginShift/{nurseId}", accept(MediaType.APPLICATION_JSON), nurseHandler::beginShift)
        {
            it.operationId("beginShift")
                    .beanClass(NurseShiftService::class.java)
                    .beanMethod("beginShift")
        }
        PUT("/nurse/endShift/{nurseId}", accept(MediaType.APPLICATION_JSON), nurseHandler::endShift)
        {
            it.operationId("endShift")
                    .beanClass(NurseShiftService::class.java)
                    .beanMethod("endShift")
        }
        PUT("/nurse/updateInvestigationResult", accept(MediaType.APPLICATION_JSON), nurseHandler::updateInvestigationResult)
        {
            it.operationId("updateInvestigationResult")
                    .beanClass(InvestigationResultService::class.java)
                    .beanMethod("updateInvestigationResult")
        }
        PUT("/nurse/updateTreatmentResult", accept(MediaType.APPLICATION_JSON), nurseHandler::updateTreatmentResult)
        {
            it.operationId("updateTreatmentResult")
                    .beanClass(TreatmentResultService::class.java)
                    .beanMethod("updateTreatmentResult")
        }
        GET("/nurse/investigationResultList", accept(MediaType.APPLICATION_JSON), nurseHandler::showInvestigationResultList) {
            it.operationId("showInvestigationResultList")
                    .beanClass(InvestigationResultService::class.java)
                    .beanMethod("showInvestigationResultList")
        }
        GET("/nurse/treatmentResultList", accept(MediaType.APPLICATION_JSON), nurseHandler::showTreatmentResultList) {
            it.operationId("showTreatmentResultList")
                    .beanClass(TreatmentResultService::class.java)
                    .beanMethod("showTreatmentResultList")
        }
    }
}