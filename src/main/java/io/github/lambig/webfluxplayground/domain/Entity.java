package io.github.lambig.webfluxplayground.domain;

import java.util.Objects;

public interface Entity<E extends Entity<E>> extends DomainObject<E> {
  String id();

  @Override
  default boolean equivalentTo(E another) {
    return Objects.equals(this.id(), another.id());
  }
}
