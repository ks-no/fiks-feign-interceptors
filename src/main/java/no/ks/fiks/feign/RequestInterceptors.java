package no.ks.fiks.feign;

import feign.RequestInterceptor;
import no.ks.fiks.maskinporten.Maskinportenklient;
import java.util.UUID;

public final class RequestInterceptors{
	private RequestInterceptors(){}

        public static RequestInterceptor representerer(UUID fiksOrgId) {
                return new RepresentererFiksOrgId(fiksOrgId);
        }

        public static RequestInterceptor autentiser(UUID integrasjonId, String integrasjonPassord) {
                return new IntegrasjonCredential(integrasjonId, integrasjonPassord);
        }

        public static RequestInterceptor autoriser(Maskinportenklient maskinportenklient, String... scopes) {
                return new AutoriseresViaMaskinporten(maskinportenklient, scopes);
        }
}
