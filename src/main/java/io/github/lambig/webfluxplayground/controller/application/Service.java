package io.github.lambig.webfluxplayground.controller.application;

import reactor.core.publisher.Mono;

public interface Service<I extends Service.ServiceInput, O extends Service.ServiceOutput, S extends Service<I, O, S>> {

  Mono<O> result(I serviceInput);

  interface ServiceInput {
  }

  interface ServiceOutput {
  }
}
