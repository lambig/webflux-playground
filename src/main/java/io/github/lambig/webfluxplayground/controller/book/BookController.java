package io.github.lambig.webfluxplayground.controller.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lambig.either.Either;
import io.github.lambig.either.EitherOf;
import io.github.lambig.webfluxplayground.controller.book.servie.book.BookService;
import io.github.lambig.webfluxplayground.controller.book.servie.book.BookServiceInput;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.StandardException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
@Value
@Getter(value = AccessLevel.PRIVATE)
@Accessors(fluent = true)
public class BookController {
  ObjectMapper objectMapper;
  BookService bookService;

  private String asStringValue(Object o) {
    try {
      return this.objectMapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @StandardException
  public static final class StringifyFailureException extends RuntimeException {
  }


  @Bean
  RouterFunction<ServerResponse> routes() {
    return
      route(GET("/book"), this::hello)
        .andRoute(GET("/book/{title}"), this::find)
        .andRoute(GET("/luck"), this::testLuck);
  }

  Mono<ServerResponse> hello(ServerRequest request) {
    return ok().body(Flux.just("Hello", "World!"), String.class);
  }

  Mono<ServerResponse> find(ServerRequest request) {
    return this.bookService
      .result(BookServiceInput.of(request.pathVariable("title")))
      .map(this::asStringValue)
      .flatMap(json -> ok().body(Flux.just(json), String.class))
      .onErrorMap(StringifyFailureException.class, Exception::getCause);
  }

  Mono<ServerResponse> testLuck(ServerRequest request) {
    return Mono.fromSupplier(this::luck)
      .flatMap(either -> either.asJoined(
        string -> ok().body(Flux.just(string), String.class),
        exception -> ok().body(Flux.just(exception.getMessage()), String.class)
      ));
  }

  private Either<String, RuntimeException> luck() {
    try {
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      return (random.nextInt() % 2 != 0)
        ? EitherOf.left("succeeded")
        : EitherOf.right(new RuntimeException("failed"));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }

  }
}
