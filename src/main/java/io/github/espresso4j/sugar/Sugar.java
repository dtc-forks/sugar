package io.github.espresso4j.sugar;

import io.github.espresso4j.espresso.Espresso;
import io.github.espresso4j.espresso.Request;
import io.github.espresso4j.espresso.Response;
import io.github.espresso4j.sugar.extractors.FormExtractor;
import io.github.espresso4j.sugar.extractors.JsonBodyExtractor;
import io.github.espresso4j.sugar.extractors.QueryParamExtractor;

import java.util.Arrays;
import java.util.List;

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
    public Response call(Request request) {
        // TODO: extract
        return inner.call(request);
    }

}
