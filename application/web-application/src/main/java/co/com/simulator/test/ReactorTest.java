package co.com.simulator.test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;

public class ReactorTest {
    public static void main(String[] args) {
        /*
         * Operadores creacionales
         */

        Mono.just("Cualquier data");
        Flux.just("Data1", "data2", "etc");

        Mono.create(sink -> sink.success());
        Flux.create(sink -> sink.complete());

        Mono.empty();
        Mono.never();
        Flux.empty();
        Flux.never();

        Mono.fromCallable(() -> "Hola mundo");
        Flux.range(0, 10);
        Flux.interval(Duration.ofMillis(1000));

        Flux.generate(System.out::println);
        Mono.fromSupplier(() -> "Esto es una prueba");

        Mono.error(new Throwable());
        Flux.error(new Throwable());

        Mono.defer();
        Flux.defer();

        /*
         * Operadores de transformación
         */

        Flux<String> test = Flux.just("test", "test2");

        Flux<String> result = test.map(String::toUpperCase);

        Flux<Integer> result2 = test.flatMap(word -> Mono.just(word.length()));

        Flux<Integer> result3 = test.concatMap(word -> Mono.just(word.length()));

        Flux<Integer> result4 = test.flatMapSequential(word -> Mono.just(word.length()))
                .subscribeOn(Schedulers.parallel());

        //Mono.just("test").expand();

        Flux<Integer> result5 = test.switchMap(word -> Mono.just(word.length()));
        Flux<Integer> result6 = test.flatMapIterable(word -> List.of(1, 2));
        Flux<Integer> result7 = test.handle((word, sink) -> {
            if (word.length() > 3) sink.next(word.length());
        });

        Flux<Integer> result8 = test.flatMapSequential(word -> Mono.just(word.length()))
                .cast(Integer.class);

        Flux<Signal<String>> result9 = test.materialize();


        Flux<String> result10 = test.scan("", String::concat);

        /*
         * Filtrado y muestreo
         */

        Flux<Integer> integerFlux = Flux.just(15, 20, 30, 17, 80);


        Flux<Integer> resultFilter = integerFlux.filter(number -> number >= 18);
        Flux<Integer> resultFilter2 = integerFlux
                .filterWhen(number -> Mono.just(number >= 18));

        Flux<Integer> resultFilter3 = integerFlux.distinct(Integer::byteValue);
        Flux<Integer> resultFilter4 = integerFlux.distinctUntilChanged();

        Flux<Integer> resultFilter5 = integerFlux.ofType(Integer.class);

        Flux<Integer> resultFilter6 = integerFlux.skip(Duration.ofSeconds(5)).skipLast(1);

        Flux<Integer> resultFilter7 = integerFlux.take(Duration.ofSeconds(5)).takeLast(1);

        Mono<Integer> resultFilter8 = integerFlux.elementAt(5).single();

        Mono<Integer> resultFilter9 = integerFlux.next();

        Flux<Integer> resultFilter10 = integerFlux.sample(Duration.ofSeconds(2));

        Flux<List<Integer>> resultFilter11 = integerFlux.buffer(5);

        Flux<Flux<Integer>> resultFilter12 = integerFlux.window(5);

        // Operadores de combinación

        Mono<String> mono1 = Mono.just("test");
        Mono<String> mono2 = Mono.just("test");

        mono1.concatWith(mono2);

    }
}
