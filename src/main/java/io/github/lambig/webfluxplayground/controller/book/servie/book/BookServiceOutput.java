package io.github.lambig.webfluxplayground.controller.book.servie.book;

import io.github.lambig.webfluxplayground.controller.application.Service;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor(staticName = "of")
public class BookServiceOutput implements Service.ServiceOutput {
  List<Book> books;

  @Value
  @RequiredArgsConstructor(staticName = "of")
  public static class Book {
    String id;
    String title;
    String author;
  }
}


