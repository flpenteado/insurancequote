package io.acme.insurancequote.application.usecase;

import io.acme.insurancequote.application.gateway.CatalogGateway;
import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateQuotationUseCaseTests {

    private final CatalogGateway catalogGateway = mock(CatalogGateway.class);
    private final QuotationRepository quotationRepository = mock(QuotationRepository.class);
    private final CreateQuotationUseCase useCase = new CreateQuotationUseCase(catalogGateway, quotationRepository);

    @Test
    @DisplayName("Should validate successfully")
    void shouldValidateSuccessfully() {
        var mockedProduct = Utils.createProduct();
        var mockedOffer = Utils.createOffer();
        var mockedQuotation = Utils.createValidQuotation(mockedProduct, mockedOffer);

        when(catalogGateway.getProductById(any())).thenReturn(mockedProduct);
        when(catalogGateway.getOfferById(any())).thenReturn(mockedOffer);
        when(quotationRepository.save(any())).thenReturn(mockedQuotation);

        var quotation = useCase.execute(mockedQuotation);

        verify(catalogGateway, times(1)).getProductById(any());
        verify(catalogGateway, times(1)).getOfferById(any());
        verify(quotationRepository, times(1)).save(any());

        assert quotation != null;

    }
}
