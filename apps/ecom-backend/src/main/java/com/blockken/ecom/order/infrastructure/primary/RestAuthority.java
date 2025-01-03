package com.blockken.ecom.order.infrastructure.primary;

import com.blockken.ecom.order.domain.user.aggregate.Authority;
import org.jilt.Builder;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Builder
public record RestAuthority(String name) {

  public static Set<String> fromSet(Set<Authority> authorities) {
    return authorities.stream()
      .map(authority -> authority.getName().name())
      .collect(toSet());
  }
}
