package com.blockken.ecom.order.domain.user.vo;

import com.blockken.ecom.shared.error.domain.Assert;

public record UserLastname(String value) {
  public UserLastname {
    Assert.field("value", value).maxLength(255);
  }
}
