package no.ks.fiks.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class IntegrasjonCredentials implements RequestInterceptor {
    private static final String INTEGRASJON_ID = "IntegrasjonId";
    private static final String INTEGRASJON_PASSORD = "IntegrasjonPassord";
    private final UUID integrasjonId;
    private final String integrasjonPassord;

    public IntegrasjonCredentials(UUID integrasjonId, String integrasjonPassord) {
        this.integrasjonId = integrasjonId;
        this.integrasjonPassord = integrasjonPassord;
    }

    @Override
    public void apply(RequestTemplate template) {
        addHeader(template, INTEGRASJON_ID, integrasjonId.toString());
        addHeader(template, INTEGRASJON_PASSORD, integrasjonPassord);
    }

    private void addHeader(RequestTemplate template, String header, String value) {
        log.debug("Constructing Header {}", header);
        template.header(header, value);
    }
}
