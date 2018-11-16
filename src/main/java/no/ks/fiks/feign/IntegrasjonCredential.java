package no.ks.fiks.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class IntegrasjonCredential implements RequestInterceptor {
    private static final String INTEGRASJON_ID = "";
    private static final String INTEGRASJON_PASSORD = "";
    private final UUID integrasjonId;
    private final String integrasjonPassord;

    public IntegrasjonCredential(UUID integrasjonId, String integrasjonPassord) {
        this.integrasjonId = integrasjonId;
        this.integrasjonPassord = integrasjonPassord;
    }

    @Override
    public void apply(RequestTemplate template) {
        addHeader(template, INTEGRASJON_ID, integrasjonId.toString());
        addHeader(template, INTEGRASJON_PASSORD, integrasjonPassord);
    }

    private void addHeader(RequestTemplate template, String header, String value) {
        if (template.headers().containsKey(header)) {
            log.debug("Header, {}, was already set", header);
        } else {
            log.debug("Constructing Header {}", header);
            template.header(header, value);
        }
    }
}
