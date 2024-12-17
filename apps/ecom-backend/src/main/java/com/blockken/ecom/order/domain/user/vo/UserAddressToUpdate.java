package com.blockken.ecom.order.domain.user.vo;

import com.blockken.ecom.shared.error.domain.Assert;

public record UserAddressToUpdate(UserPublicId userPublicId, UserAddress userAddress) {

  public UserAddressToUpdate {
    Assert.notNull("userPublicId", userPublicId);
    Assert.notNull("userAddress", userAddress);
  }
}
