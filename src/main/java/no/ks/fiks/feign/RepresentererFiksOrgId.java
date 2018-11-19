package no.ks.fiks.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class RepresentererFiksOrgId implements RequestInterceptor {
    private static final String FIKS_ORG_ID = "FiksOrgId";

    private final UUID fiksOrgId;

    public RepresentererFiksOrgId(UUID fiksOrgId) {
        this.fiksOrgId = fiksOrgId;
    }

    @Override
    public void apply(RequestTemplate template) {
        addHeader(template, FIKS_ORG_ID, fiksOrgId.toString());
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
