package io.acme.insurancequote.infrastructure.gateway;

import io.acme.insurancequote.application.gateway.CatalogGateway;
import io.acme.insurancequote.domain.models.Offer;
import io.acme.insurancequote.domain.models.Product;
import io.acme.insurancequote.infrastructure.gateway.dto.OfferResponse;
import io.acme.insurancequote.infrastructure.gateway.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Slf4j
@Service
public class CatalogGatewayImpl implements CatalogGateway {

    private final RestClient client = RestClient.create();
    @Value("${app.catalog.url}")
    private String baseUrl;

    @Override
    public Product getProductById(UUID id) {

        var uri = String.format("%s/product/%s", baseUrl, id);

        var productResponse = client.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> log.info("Product {} not found", id))
                .body(ProductResponse.class);

        if (productResponse == null) {
            return null;
        }

        return productResponse.toProduct();
    }

    @Override
    public Offer getOfferById(UUID offerId) {
        var uri = String.format("%s/offer/%s", baseUrl, offerId);

        var offerResponse = client.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) ->
                        log.info("Offer {} not found", offerId))
                .body(OfferResponse.class);

        if (offerResponse == null) {
            return null;
        }

        return offerResponse.toOffer();
    }
}