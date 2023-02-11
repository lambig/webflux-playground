package io.github.lambig.webfluxplayground.domain.core.book;

import io.github.lambig.webfluxplayground.domain.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor(staticName = "of")
@Accessors(fluent = true)
@Getter
public class Book implements Entity<Book> {
  private final String id;
  private final String title;
  private final String author;
  private BookStatus status = BookStatus.NEW;

  Book worn() {
    this.status = BookStatus.FINE;
    return this;
  }
}
