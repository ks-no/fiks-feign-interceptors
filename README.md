# fiks-feign-interceptors
```
RequestInterceptors.representerer(UUID fiksOrgId) {
RequestInterceptors.autentiser(UUID integrasjonId, String integrasjonPassord) {
RequestInterceptors.autoriser(Maskinportenklient maskinportenklient, String... scopes) {
```

# Maven
```
<dependency>
  <groupId>no.ks.fiks</groupId>
  <artifactId>fiks-feign-interceptors</artifactId>
  <version>1.0.0</version>
</dependency>
```

# Eksempel
```
public <FeignApi> getFeignApi(UUID FiksOrgId, UUID integrasjonId, String integrasjonPassord, Maskinportenklient maskinportenklient, String... scopes) {
	return Feign.build()
	.interceptor(RequestInterceptors.representerer(fiksOrgId))
        .interceptor(RequestInterceptors.autentiser(integrasjonId, integrasjonPassord))
        .interceptor(RequestInterceptors.autoriser(maskinportenklient, "ks"))
	.build();
}
```
