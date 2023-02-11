package io.github.lambig.webfluxplayground.domain;

public interface Aggregate<A extends Aggregate<A>> extends DomainObject<A> {
}
