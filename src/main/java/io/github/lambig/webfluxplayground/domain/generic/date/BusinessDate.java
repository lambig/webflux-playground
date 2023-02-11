package io.github.lambig.webfluxplayground.domain.generic.date;

import io.github.lambig.webfluxplayground.domain.Value;

public class BusinessDate implements Value<BusinessDate> {
  @Override
  public boolean equivalentTo(BusinessDate another) {
    return false;
  }
}
