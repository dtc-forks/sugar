# Sugar

[![Maven Central](https://img.shields.io/maven-central/v/io.github.espresso4j/sugar.svg)](https://search.maven.org/artifact/io.github.espresso4j/sugar)
[![Javadocs](http://www.javadoc.io/badge/io.github.espresso4j/sugar.svg)](http://www.javadoc.io/doc/io.github.espresso4j/sugar)
[![Travis (.org)](https://img.shields.io/travis/espresso4j/sugar.svg)](https://travis-ci.org/espresso4j/sugar)
![GitHub](https://img.shields.io/github/license/espresso4j/sugar.svg)
[![Liberapay patrons](https://img.shields.io/liberapay/patrons/Sunng.svg)](https://liberapay.com/Sunng/donate)

Sugar extract web request parameter from common sources for you to
access from espresso handler.

By default Sugar ship three extractors, the query string extractor,
the json body extractor and the form body one. You can implement the
`Extractor` interface and implement your own extractor.

## Usage

Sugar acts as a middleware for Espresso application. Simply wrap your
espresso application in `new Sugar(espresso)` then you will be able to
access extracted parameters in handler function:

```java
// Works for get request like http://hostname/?a=helloworld
// or json post request with body {"a": "helloworld"}
// or form post ...
var espresso = (req) -> {
    var a = Sugar.extension(req).get("a");
    return Response.of(200).body(a);
}
```

## Extending Sugar

Implement your own `Extractor` class to extractor more data from
request and access it in the Sugar's way.

## License

See [license](https://github.com/espresso4j/sugar/blob/master/LICENSE)
