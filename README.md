# fiks-feign-interceptors
```
RequestInterceptors.settIntegrasjon(UUID integrasjonId, String integrasjonPassord) {
RequestInterceptors.settAccessToken(Maskinportenklient maskinportenklient, String... scopes) {
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
public <FeignApi> getFeignApi(UUID FiksOrgId, UUID integrasjonId, String integrasjonPassord, Maskinportenklient maskinportenklient, String... scopes) {
	return Feign.build()	
        .interceptor(RequestInterceptors.settIntegrasjon(integrasjonId, integrasjonPassord))
        .interceptor(RequestInterceptors.settAccessToken(maskinportenklient, "ks"))
	.build();
}
```
