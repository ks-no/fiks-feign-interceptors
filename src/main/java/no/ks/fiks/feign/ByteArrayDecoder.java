package no.ks.fiks.feign;

import feign.Response;
import feign.Util;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class ByteArrayDecoder implements Decoder {
    private final Decoder delegate;

    public ByteArrayDecoder() {
        this(null);
    }

    public ByteArrayDecoder(Decoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (byte[].class.equals(type)) {
            return Util.toByteArray(response.body().asInputStream());
        }
        return delegate == null ? null : delegate.decode(response, type);
    }
}
