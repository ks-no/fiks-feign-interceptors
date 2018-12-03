package no.ks.fiks.feign;

import feign.RequestInterceptor;
import no.ks.fiks.maskinporten.Maskinportenklient;
import java.util.UUID;

public final class RequestInterceptors{
	private RequestInterceptors(){}

        public static RequestInterceptor integrasjon(UUID integrasjonId, String integrasjonPassord) {
                return new IntegrasjonCredentials(integrasjonId, integrasjonPassord);
        }

        public static RequestInterceptor accessToken(Maskinportenklient maskinportenklient, String... scopes) {
                return new MaskinportenCredentials(maskinportenklient, scopes);
        }
}
