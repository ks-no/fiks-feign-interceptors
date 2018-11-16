package no.ks.fiks.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class RepresentererFiksOrgId implements RequestInterceptor {
    private static final String INTEGRASJON_ID = "";

    private final UUID integrasjonId;

    public RepresentererFiksOrgId(UUID integrasjonId) {
        this.integrasjonId = integrasjonId;
    }

    @Override
    public void apply(RequestTemplate template) {
        addHeader(template, INTEGRASJON_ID, integrasjonId.toString());
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
