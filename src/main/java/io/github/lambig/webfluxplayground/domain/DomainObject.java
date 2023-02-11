package io.github.lambig.webfluxplayground.domain;

public interface DomainObject<D> {
  boolean equivalentTo(D another);
}
