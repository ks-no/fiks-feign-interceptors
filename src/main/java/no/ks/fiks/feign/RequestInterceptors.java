package no.ks.fiks.feign;

import feign.RequestInterceptor;

import java.util.UUID;
import java.util.function.Supplier;

public final class RequestInterceptors{
	private RequestInterceptors(){}

        public static RequestInterceptor integrasjon(UUID integrasjonId, String integrasjonPassord) {
                return new IntegrasjonCredentials(integrasjonId, integrasjonPassord);
        }

        public static RequestInterceptor accessToken(Supplier<String> accessTokenSupplier) {
                return new AccessTokenInterceptor(accessTokenSupplier);
        }
}
