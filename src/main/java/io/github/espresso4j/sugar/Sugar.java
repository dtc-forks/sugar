package io.github.espresso4j.sugar;

import io.github.espresso4j.espresso.Espresso;
import io.github.espresso4j.espresso.Request;
import io.github.espresso4j.espresso.Response;

public class Sugar implements Espresso {

    private Espresso inner;

    public Sugar(Espresso inner) {
        this.inner = inner;
    }

    @Override
    public Response call(Request request) {
        return inner.call(request);
    }

}
