package io.github.espresso4j.sugar;

import io.github.espresso4j.espresso.Espresso;
import io.github.espresso4j.espresso.Request;
import io.github.espresso4j.espresso.Response;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SugarTest {

    private static Request generateRequestFor(String contentType, String body) {
        Request request = new Request();

        request.setRequestMethod(Request.Method.POST);
        request.getHeaders().put("Content-Type", contentType);
        request.setBody(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        return request;
    }

    private static Request generateRequestFor(String queryString) {
        Request request = new Request();

        request.setRequestMethod(Request.Method.GET);
        request.setQueryString(queryString);

        return request;
    }

    @Test
    public void testForm() throws Exception {
        Request req = generateRequestFor("application/x-www-form-urlencoded", "a=123&b=%24abc");

        Espresso esp = r -> {
            Map<String, Object> params = Sugar.extension(r);

            assertEquals(2, params.size());
            assertEquals("123", params.get("a"));
            assertEquals("$abc", params.get("b"));

            return Response.of(200);
        };

        Espresso sugarEspresso = new Sugar(esp);
        Response resp = sugarEspresso.call(req);

        assertEquals(200, resp.status().intValue());
    }

    @Test
    public void testJson() throws Exception {
        Request req = generateRequestFor("application/json", "{\"a\": 123, \"b\": \"tom\"}");

        Espresso esp = r -> {
            Map<String, Object> params = Sugar.extension(r);

            assertEquals(2, params.size());
            assertEquals(123, params.get("a"));
            assertEquals("tom", params.get("b"));

            return Response.of(200);
        };

        Espresso sugarEspresso = new Sugar(esp);
        Response resp = sugarEspresso.call(req);

        assertEquals(200, resp.status().intValue());
    }

    @Test
    public void testQueryString() throws Exception {
        Request req = generateRequestFor("a=123&b=%24abc");

        Espresso esp = r -> {
            Map<String, Object> params = Sugar.extension(r);

            assertEquals(2, params.size());
            assertEquals("123", params.get("a"));
            assertEquals("$abc", params.get("b"));

            return Response.of(200);
        };

        Espresso sugarEspresso = new Sugar(esp);
        Response resp = sugarEspresso.call(req);

        assertEquals(200, resp.status().intValue());
    }

}
