# Cloud Ninja - tools.build example of using java source

A game I developed in August 2011. The original program had a bunch of magic IDE cruft that I didn't understand at the time and don't understand now. I think it was built in NetBeans originally?

To get this to work I copied the java source and use clojure `tools.build`  to build an uberjar. I've never worked on a project that shared java/clojure source so this was a fun experiment. The original program was also built on Applets which are no longer supported. However, it turns out the Applet interface is like a framework version of JFrame so all I had to do was invert the calls; call `frame.foo()` rather than extend `Applet` and implement `foo()`.

Pretty impressed it all still works and how easy it was to get working with `tools.build`.

## To compile the java source

```
clj -T:build jcompile
```

## To run the repl

```
clj -M:dev
```

## To build the uberjar

```
clj -T:build uber
```

## Run uberjar

```
java -jar target/cloud-ninja-0.1.1.jar
```
