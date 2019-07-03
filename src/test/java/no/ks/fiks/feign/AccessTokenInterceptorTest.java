package no.ks.fiks.feign;

import feign.RequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AccessTokenInterceptorTest {

    @Test
    @DisplayName("The Authorization header should be set on the request when apply is called")
    void testAuthorizationeader() {
        AtomicInteger count = new AtomicInteger();

        RequestTemplate requestTemplate = new RequestTemplate();
        AccessTokenInterceptor accessTokenInterceptor = new AccessTokenInterceptor(() -> String.valueOf(count.getAndIncrement()));

        accessTokenInterceptor.apply(requestTemplate);

        Collection<String> authorization = requestTemplate.headers().get("Authorization");

        assertThat(authorization.size(), is(1));
        assertThat(authorization.iterator().next(), is("Bearer 0"));
    }

    @Test
    @DisplayName("The Authorization header should be overwritten if apply is called twice")
    void testApplyTwice() {
        AtomicInteger count = new AtomicInteger();

        RequestTemplate requestTemplate = new RequestTemplate();
        AccessTokenInterceptor accessTokenInterceptor = new AccessTokenInterceptor(() -> String.valueOf(count.getAndIncrement()));

        accessTokenInterceptor.apply(requestTemplate);
        accessTokenInterceptor.apply(requestTemplate);
        Collection<String> authorization = requestTemplate.headers().get("Authorization");

        assertThat(authorization.size(), is(1));
        assertThat(authorization.iterator().next(), is("Bearer 1"));
    }
}
