package com.blockken.ecom.order.domain.user.service;

import com.blockken.ecom.order.domain.user.aggregate.User;
import com.blockken.ecom.order.domain.user.repository.UserRepository;
import com.blockken.ecom.order.domain.user.vo.UserEmail;
import com.blockken.ecom.order.domain.user.vo.UserPublicId;

import java.util.Optional;

public class UserReader {

  private final UserRepository userRepository;

  public UserReader(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getByPublicId(UserPublicId userPublicId) {
    return userRepository.getByPublicId(userPublicId);
  }

  public Optional<User> getByEmail(UserEmail userEmail) {
    return userRepository.getOneByEmail(userEmail);
  }
}
