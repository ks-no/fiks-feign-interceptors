package no.ks.fiks.feign;

import feign.RequestInterceptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

class RequestInterceptorsTest {

    @Test
    @DisplayName("The accessToken method should create an AccessTokenInterceptor instance")
    void testCreateAccessTokenInterceptor() {
        RequestInterceptor requestInterceptor = RequestInterceptors.accessToken(() -> UUID.randomUUID().toString());
        assertThat(requestInterceptor, instanceOf(AccessTokenInterceptor.class));
    }

    @Test
    @DisplayName("The integrasjon method should create an IntegrasjonCredentials instance")
    void testCreateIntegrasjonCredentials() {
        RequestInterceptor requestInterceptor = RequestInterceptors.integrasjon(UUID.randomUUID(), UUID.randomUUID().toString());
        assertThat(requestInterceptor, instanceOf(IntegrasjonCredentials.class));
    }
}
