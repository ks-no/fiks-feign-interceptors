# fiks-feign-interceptors
```
RequestInterceptors.integrasjon(UUID integrasjonId, String integrasjonPassord) {
RequestInterceptors.accessToken(Maskinportenklient maskinportenklient, String... scopes) {
```

# Maven
```
<dependency>
  <groupId>no.ks.fiks</groupId>
  <artifactId>feign-interceptors</artifactId>
  <version>1.0.1-SNAPSHOT</version>
</dependency>
```

# Eksempel
```
public <FeignApi> getFeignApi(UUID FiksOrgId, UUID integrasjonId, String integrasjonPassord, SomeOauthClient oauthKlient) {
	return Feign.build()	
        .interceptor(RequestInterceptors.integrasjon(integrasjonId, integrasjonPassord))
        .interceptor(RequestInterceptors.accessToken(() -> oauthKlient.getAccessToken())
	.build();
}
```
