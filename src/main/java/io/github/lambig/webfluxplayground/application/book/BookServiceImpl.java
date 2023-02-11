package io.github.lambig.webfluxplayground.application.book;

import io.github.lambig.funcifextension.predicate.By;
import io.github.lambig.webfluxplayground.controller.book.servie.book.BookService;
import io.github.lambig.webfluxplayground.controller.book.servie.book.BookServiceInput;
import io.github.lambig.webfluxplayground.controller.book.servie.book.BookServiceOutput;
import io.github.lambig.webfluxplayground.domain.core.book.Book;
import io.github.lambig.webfluxplayground.domain.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.util.function.Predicate.isEqual;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  @Override
  public Mono<BookServiceOutput> result(BookServiceInput serviceInput) {
    return this.bookRepository
      .findAll()
      .filter(By.having(Book::title).that(isEqual(serviceInput.title())))
      .map(book -> BookServiceOutput.Book.of(book.id(), book.title(), book.author()))
      .collect(toList())
      .map(BookServiceOutput::of);
  }
}
//