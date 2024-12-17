package com.blockken.ecom.order.domain.user.aggregate;

import com.blockken.ecom.order.domain.user.vo.AuthorityName;
import com.blockken.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

@Builder
public class Authority {

  private AuthorityName authorityName;

  public Authority(AuthorityName authorityName) {
    Assert.notNull("authorityName", authorityName);
    this.authorityName = authorityName;
  }
}
