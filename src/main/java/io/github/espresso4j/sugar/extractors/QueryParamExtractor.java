package io.github.espresso4j.sugar.extractors;

import io.github.espresso4j.espresso.Request;
import io.github.espresso4j.sugar.Extractor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueryParamExtractor implements Extractor {

    @Override
    public Map<String, Object> extract(Request request) throws Exception {
        String queryString = request.getQueryString();

        if (queryString == null || queryString.isEmpty()) {
            return null;
        }

        // TODO: multi-value
        return Arrays.stream(queryString.split("&"))
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

}
