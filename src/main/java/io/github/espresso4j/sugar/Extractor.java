package io.github.espresso4j.sugar;

import io.github.espresso4j.espresso.Request;

import java.util.Map;

public interface Extractor {

    Map<String, Object> extract(Request request) throws Exception;

}
