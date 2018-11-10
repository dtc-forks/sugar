package io.github.espresso4j.sugar;

import io.github.espresso4j.espresso.Espresso;
import io.github.espresso4j.espresso.ExtensionHolder;
import io.github.espresso4j.espresso.Request;
import io.github.espresso4j.espresso.Response;
import io.github.espresso4j.sugar.extractors.FormExtractor;
import io.github.espresso4j.sugar.extractors.JsonBodyExtractor;
import io.github.espresso4j.sugar.extractors.QueryParamExtractor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sugar implements Espresso {

    private Espresso inner;

    private List<Extractor> extractorList;

    public Sugar(Espresso inner) {
        this(inner, new QueryParamExtractor(), new JsonBodyExtractor(), new FormExtractor());
    }

    public Sugar(Espresso inner, Extractor... extractors) {
        this.inner = inner;
        this.extractorList = Arrays.asList(extractors);
    }

    @Override
    public Response call(Request request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        for (Extractor e: extractorList) {
            Map<String, Object> extractions = e.extract(request);
            if (extractions != null) {
                params.putAll(extractions);
            }
        }

        request.extension(Sugar.class, params);
        return inner.call(request);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> extension(ExtensionHolder extensionHolder) {
        return (Map<String, Object>) extensionHolder.extension(Sugar.class);
    }

}
