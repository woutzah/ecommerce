package com.blockken.ecom.order.infrastructure.secondary.repository;

import com.blockken.ecom.order.domain.user.aggregate.User;
import com.blockken.ecom.order.domain.user.repository.UserRepository;
import com.blockken.ecom.order.domain.user.vo.UserAddressToUpdate;
import com.blockken.ecom.order.domain.user.vo.UserEmail;
import com.blockken.ecom.order.domain.user.vo.UserPublicId;
import com.blockken.ecom.order.infrastructure.secondary.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpringDataUserRepository implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  public SpringDataUserRepository(JpaUserRepository jpaUserRepository) {
    this.jpaUserRepository = jpaUserRepository;
  }

  @Override
  public void save(User user) {
    if (user.getEmail() != null) {
      Optional<UserEntity> userToUpdateOpt = jpaUserRepository.findById(user.getDbId());

      if (userToUpdateOpt.isPresent()) {
        UserEntity userToUpdate = userToUpdateOpt.get();

        userToUpdate.updateFromUser(user);
        jpaUserRepository.save(userToUpdate);
      }
    }
  }

  @Override
  public Optional<User> getByPublicId(UserPublicId userPublicId) {
    return Optional.empty();
  }

  @Override
  public Optional<User> getOneByEmail(UserEmail userEmail) {
    return Optional.empty();
  }

  @Override
  public void updateAddress(UserPublicId userPublicId, UserAddressToUpdate userAddressToUpdate) {

  }
}
