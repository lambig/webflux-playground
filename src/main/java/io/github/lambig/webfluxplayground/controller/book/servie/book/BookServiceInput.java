package io.github.lambig.webfluxplayground.controller.book.servie.book;

import io.github.lambig.webfluxplayground.controller.application.Service;
import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class BookServiceInput implements Service.ServiceInput {
  String title;
}
