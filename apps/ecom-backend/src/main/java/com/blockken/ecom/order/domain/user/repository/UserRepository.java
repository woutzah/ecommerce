package com.blockken.ecom.order.domain.user.repository;

import com.blockken.ecom.order.domain.user.aggregate.User;
import com.blockken.ecom.order.domain.user.vo.UserAddressToUpdate;
import com.blockken.ecom.order.domain.user.vo.UserEmail;
import com.blockken.ecom.order.domain.user.vo.UserPublicId;

import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> getByPublicId(UserPublicId userPublicId);

  Optional<User> getOneByEmail(UserEmail userEmail);

  void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddressToUpdate);
}
