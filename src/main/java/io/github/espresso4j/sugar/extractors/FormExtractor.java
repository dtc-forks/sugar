package io.github.espresso4j.sugar.extractors;

import io.github.espresso4j.espresso.Request;
import io.github.espresso4j.espresso.internal.IoUtils;
import io.github.espresso4j.sugar.Extractor;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FormExtractor implements Extractor {

    @Override
    public Map<String, Object> extract(Request request) throws Exception {
        if (!isFormRequest(request)) {
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IoUtils.copyStream(request.getBody(), byteArrayOutputStream);

        // FIXME: check charset from header
        String bodyAsString = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        // TODO: multi-value
        return Arrays.stream(bodyAsString.split("&"))
                .map((kvStr) -> {
                    try {
                        return URLDecoder.decode(kvStr, "UTF-8").split("=");
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap((kvPair) -> kvPair[0], (kvPair) -> kvPair[1]));
    }

    private boolean isFormRequest(Request request) {
        String contentType = request.getHeaders().get("Content-Type");
        return "application/x-www-form-urlencoded".equals(contentType);
    }

}
