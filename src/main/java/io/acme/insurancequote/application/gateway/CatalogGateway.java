package io.acme.insurancequote.application.gateway;

import io.acme.insurancequote.domain.models.Offer;
import io.acme.insurancequote.domain.models.Product;

import java.util.UUID;

public interface CatalogGateway {
    Product getProductById(UUID productId);
    Offer getOfferById(UUID offerId);
}
