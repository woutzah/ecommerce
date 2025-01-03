package com.blockken.ecom.order.application;

import com.blockken.ecom.order.domain.user.aggregate.User;
import com.blockken.ecom.order.domain.user.repository.UserRepository;
import com.blockken.ecom.order.domain.user.service.UserReader;
import com.blockken.ecom.order.domain.user.service.UserSynchronizer;
import com.blockken.ecom.order.domain.user.vo.UserAddressToUpdate;
import com.blockken.ecom.order.domain.user.vo.UserEmail;
import com.blockken.ecom.order.infrastructure.secondary.service.kinde.KindeService;
import com.blockken.ecom.shared.authentication.application.AuthenticatedUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersApplicationService {

  private final UserReader userReader;
  private final UserSynchronizer userSynchronizer;

  public UsersApplicationService(UserRepository userRepository, KindeService kindeService) {
    this.userReader = new UserReader(userRepository);
    this.userSynchronizer = new UserSynchronizer(userRepository, kindeService);
  }

  @Transactional
  public User getAuthenticatedUserWithSync(Jwt jwtToken, boolean forceSync) {
    userSynchronizer.syncWithIdp(jwtToken, forceSync);

    return getAuthenticatedUser();
  }

  @Transactional(readOnly = true)
  public User getAuthenticatedUser() {
    return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
      .orElseThrow();
  }

  @Transactional
  public void updateAddress(UserAddressToUpdate userAddressToUpdate) {
    userSynchronizer.updateAddress(userAddressToUpdate);
  }
}
