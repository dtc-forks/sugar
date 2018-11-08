package io.github.espresso4j.sugar.extractors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.espresso4j.espresso.Request;
import io.github.espresso4j.sugar.Extractor;

import java.util.Map;

public class JsonBodyExtractor implements Extractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, Object> extract(Request request) throws Exception {
        if (!isJsonRequest(request)) {
            return null;
        }

        TypeReference<Map<String, Object>> typeRef
                = new TypeReference<Map<String, Object>>() {};

        return objectMapper.readValue(request.getBody(), typeRef);
    }

    private boolean isJsonRequest(Request request) {
        String contentType = request.getHeaders().get("Content-Type");

        return "application/json".equals(contentType) && request.getBody() != null;
    }

}
