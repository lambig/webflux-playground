package io.github.lambig.webfluxplayground.domain.repository;

import io.github.lambig.webfluxplayground.domain.DomainObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Entity<E> = DomainObject<E>
 *
 * @param <D>
 */
public interface Repository<D extends DomainObject<D>> {
  Mono<CommandResult<D>> save(D domainObject);

  Mono<D> findBy(Long id);

  Flux<D> findAll();

  interface CommandResult<D> {
  }
}
