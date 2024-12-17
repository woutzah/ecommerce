package com.blockken.ecom.order.domain.user.repository;

import com.blockken.ecom.order.domain.user.aggregate.User;

public interface UserRepository {

  void save(User user);
}
