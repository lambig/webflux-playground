package io.github.lambig.webfluxplayground.domain.supporting.catalog;

import io.github.lambig.webfluxplayground.domain.Entity;

public class Catalog implements Entity<Catalog> {
  @Override
  public String id() {
    return null;
  }

  @Override
  public boolean equivalentTo(Catalog another) {
    return Entity.super.equivalentTo(another);
  }
}
