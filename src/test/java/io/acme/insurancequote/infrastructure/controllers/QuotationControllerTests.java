package io.acme.insurancequote.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.acme.insurancequote.InsurancequoteApplicationTests;
import io.acme.insurancequote.application.gateway.CatalogGateway;
import io.acme.insurancequote.application.messaging.QuotationMessage;
import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuotationControllerTests extends InsurancequoteApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuotationRepository quotationRepository;

    @MockBean
    private CatalogGateway catalogGateway;

    @MockBean
    private QuotationMessage quotationMessage;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        when(catalogGateway.getProductById(any())).thenReturn(Utils.createProduct());
        when(catalogGateway.getOfferById(any())).thenReturn(Utils.createOffer());
        doNothing().when(quotationMessage).send(any());

    }

    @Test
    @DisplayName("Should create a quotation")
    public void shouldCreateQuotation() throws Exception {
        var request = Utils.createQuotationRequest();

        var body = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/quotation")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("Should get a quotation by id")
    public void shouldGetQuotationById() throws Exception {
        var product = Utils.createProduct();
        var offer = Utils.createOffer();
        var quotation = Utils.createValidQuotation(product, offer);

        var savedQuotation = quotationRepository.save(quotation);

        mockMvc.perform(get("/api/v1/quotation/{id}", savedQuotation.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedQuotation.getId()));
    }

}
