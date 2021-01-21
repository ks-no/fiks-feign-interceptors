# fiks-feign-interceptors

# Maven
```
<dependency>
  <groupId>no.ks.fiks</groupId>
  <artifactId>feign-interceptors</artifactId>
  <version>1.0.4</version>
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

# Multipart
```
<dependency>
    <groupId>io.github.openfeign.form</groupId>
    <artifactId>feign-form</artifactId>
    <version>3.8.0</version>
</dependency>
```
```
public <FeignApi> getFeignApi(UUID FiksOrgId, UUID integrasjonId, String integrasjonPassord, SomeOauthClient oauthKlient) {
	return Feign.build()
        .encoder(new FormEncoder(new JacksonEncoder(objectMapper)))
        .decoder(new ByteArrayDecoder(new JacksonDecoder(objectMapper)))
        ...
        .interceptor(...)
        ...
	.build();
}
```

## Upload
Feign-klient kan også brukes for multipart (json dokument + fil)

Må lage interface selv, ikke autogenerert fra Swagger.
```
public interface EsigneringSendApiExtended extends ApiClient.Api {

    @RequestLine("POST /esignering/api/v1/send/opprett")
    @Headers({
            "Content-Type: multipart/form-data",
            "Accept: application/json",
    })
    SigneringsoppdragResponse oppretterSigneringsoppdrag(@Param("signeringsoppdrag") FormData signeringsoppdrag, @Param("dokument") FormData dokument);

    default SigneringsoppdragResponse oppretterSigneringsoppdrag(ObjectMapper objectMapper, SigneringsoppdragRequest signeringsoppdrag, FormData dokument) throws IOException {
        FormData signeringsoppdragForm = FormData.builder()
                .contentType("application/json")
                .data(objectMapper.writeValueAsBytes(signeringsoppdrag))
                .build();

        return oppretterSigneringsoppdrag(signeringsoppdragForm, dokument);
    }

    // Bruk denne metoden for å laste opp json + fil
    default SigneringsoppdragResponse oppretterSigneringsoppdrag(SigneringsoppdragRequest signeringsoppdrag, FormData dokument) throws IOException {
        return oppretterSigneringsoppdrag(new ObjectMapper(), signeringsoppdrag, dokument);
    }

}
```
Controller
```
fun oppretterSigneringsoppdrag(
            @Valid @FormDataParam("signeringsoppdrag") signeringsoppdrag: SigneringsoppdragRequest,
            @FormDataParam("dokument") dokument: FormDataBodyPart,
    ):
```

## Download
Nedlastning av fil (byte[]) virker out-of-the-box med autogenerert kode dersom ByteArrayDecoder brukes for å dekode responsen.
```
    @Produces("application/octet-stream")
    fun getSignedDocument(...): Response {
        ...
        return Response.ok()
                .type(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                .header("Content-Disposition", "attachment; filename=\"$filename\"")
                .entity("...".toByteArray())
                .build()
    }
```
