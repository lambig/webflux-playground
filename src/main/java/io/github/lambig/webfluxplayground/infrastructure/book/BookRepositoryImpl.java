package io.github.lambig.webfluxplayground.infrastructure.book;

import io.github.lambig.webfluxplayground.domain.core.book.Book;
import io.github.lambig.webfluxplayground.domain.repository.book.BookRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

@Repository
public class BookRepositoryImpl implements BookRepository {

  @Override
  public Mono<CommandResult<Book>> save(Book domainObject) {
    return null;
  }

  @Override
  public Mono<Book> findBy(Long id) {
    return null;
  }

  @Override
  public Flux<Book> findAll() {
    return
      Flux.fromStream(
        IntStream
          .range(0, 10)
          .mapToObj(number ->
            Book.of(
              String.valueOf(number),
              "Title" + number,
              "author" + number))
      );
  }

}
