package no.ks.fiks.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import no.ks.fiks.maskinporten.Maskinportenklient;

@Slf4j
public class AutoriseresViaMaskinporten implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    private final Maskinportenklient maskinportenklient;
    private final String[] scopes;

    public AutoriseresViaMaskinporten(Maskinportenklient maskinportenklient, String... scopes) {
        this.maskinportenklient = maskinportenklient;
        this.scopes = scopes;
    }

    @Override
    public void apply(RequestTemplate template) {
        if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
            log.debug("The Authorization token has been already set");
        } else {
            log.debug("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
            template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
                    maskinportenklient.getAccessToken(scopes)));
        }
    }
}
