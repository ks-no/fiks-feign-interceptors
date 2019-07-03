package no.ks.fiks.feign;

import feign.RequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IntegrasjonCredentialsTest {

    @Test
    @DisplayName("The IntegrasjonId header should be set on the request when apply is called")
    void testIntegrasjonIdHeader() {
        UUID integrasjonId = UUID.randomUUID();

        RequestTemplate requestTemplate = new RequestTemplate();
        IntegrasjonCredentials integrasjonCredentials = new IntegrasjonCredentials(integrasjonId, UUID.randomUUID().toString());

        integrasjonCredentials.apply(requestTemplate);

        Collection<String> integrasjonIdHeader = requestTemplate.headers().get("IntegrasjonId");

        assertThat(integrasjonIdHeader.size(), is(1));
        assertThat(integrasjonIdHeader.iterator().next(), is(integrasjonId.toString()));
    }

    @Test
    @DisplayName("The IntegrasjonPassord header should be set on the request when apply is called")
    void testIntegrasjonPassordHeader() {
        String integrasjonPassord = UUID.randomUUID().toString();

        RequestTemplate requestTemplate = new RequestTemplate();
        IntegrasjonCredentials integrasjonCredentials = new IntegrasjonCredentials(UUID.randomUUID(), integrasjonPassord);

        integrasjonCredentials.apply(requestTemplate);

        Collection<String> integrasjonPassordHeader = requestTemplate.headers().get("IntegrasjonPassord");

        assertThat(integrasjonPassordHeader.size(), is(1));
        assertThat(integrasjonPassordHeader.iterator().next(), is(integrasjonPassord));
    }

    @Test
    @DisplayName("The IntegrasjonId header should be overwritten if apply is called twice")
    void testIntegrasjonIdApplyTwice() {
        UUID integrasjonId = UUID.randomUUID();

        RequestTemplate requestTemplate = new RequestTemplate();
        IntegrasjonCredentials integrasjonCredentials = new IntegrasjonCredentials(integrasjonId, UUID.randomUUID().toString());

        integrasjonCredentials.apply(requestTemplate);
        integrasjonCredentials.apply(requestTemplate);

        Collection<String> integrasjonIdHeader = requestTemplate.headers().get("IntegrasjonId");

        assertThat(integrasjonIdHeader.size(), is(1));
        assertThat(integrasjonIdHeader.iterator().next(), is(integrasjonId.toString()));
    }

    @Test
    @DisplayName("The IntegrasjonPassord header should be overwritten if apply is called twice")
    void testIntegrasjonPassordApplyTwice() {
        String integrasjonPassord = UUID.randomUUID().toString();

        RequestTemplate requestTemplate = new RequestTemplate();
        IntegrasjonCredentials integrasjonCredentials = new IntegrasjonCredentials(UUID.randomUUID(), integrasjonPassord);

        integrasjonCredentials.apply(requestTemplate);
        integrasjonCredentials.apply(requestTemplate);

        Collection<String> integrasjonPassordHeader = requestTemplate.headers().get("IntegrasjonPassord");

        assertThat(integrasjonPassordHeader.size(), is(1));
        assertThat(integrasjonPassordHeader.iterator().next(), is(integrasjonPassord));
    }
}
