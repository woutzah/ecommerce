package com.blockken.ecom.order.domain.user.aggregate;

import com.blockken.ecom.order.domain.user.vo.*;
import com.blockken.ecom.shared.error.domain.Assert;
import org.jilt.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

@Builder
public class User {

  private UserLastname lastname;
  private UserFirstname firstname;
  private UserEmail email;
  private UserPublicId userPublicId;
  private UserImageUrl imageUrl;
  private Instant lastModifiedDate;
  private Instant createdDate;
  private Instant lastSeenDate;
  private Set<Authority> authorities;
  private Long dbId;
  private UserAddress userAddress;

  public User(UserLastname lastname, UserFirstname firstname, UserEmail email, UserPublicId userPublicId, UserImageUrl imageUrl, Instant lastModifiedDate, Instant createdDate, Instant lastSeenDate, Set<Authority> authorities, Long dbId, UserAddress userAddress) {
    this.lastname = lastname;
    this.firstname = firstname;
    this.email = email;
    this.userPublicId = userPublicId;
    this.imageUrl = imageUrl;
    this.lastModifiedDate = lastModifiedDate;
    this.createdDate = createdDate;
    this.lastSeenDate = lastSeenDate;
    this.authorities = authorities;
    this.dbId = dbId;
    this.userAddress = userAddress;
  }

  private void assertMandatoryFields() {
    Assert.notNull("lastname", lastname);
    Assert.notNull("firstname", firstname);
    Assert.notNull("email", email);
    Assert.notNull("authorities", authorities);
  }

  public void updateFromUser(User user) {
    this.email = user.email;
    this.imageUrl = user.imageUrl;
    this.firstname = user.firstname;
    this.lastname = user.lastname;
  }

  public void initPublicIdForSignup() {
    this.userPublicId = new UserPublicId(UUID.randomUUID());
  }

  public static User fromTokenAttributes(Map<String, Object> attributes, List<String> rolesFromAccessToken) {
    UserBuilder userBuilder = UserBuilder.user();

    if (attributes.containsKey("preferred_email")) {
      userBuilder.email(new UserEmail(attributes.get("preferred_email").toString()));
    }

    if (attributes.containsKey("last_name")) {
      userBuilder.lastname(new UserLastname(attributes.get("last_name").toString()));
    }

    if (attributes.containsKey("first_name")) {
      userBuilder.firstname(new UserFirstname(attributes.get("first_name").toString()));
    }

    if (attributes.containsKey("picture")) {
      userBuilder.imageUrl(new UserImageUrl(attributes.get("picture").toString()));
    }

    if (attributes.containsKey("last_signed_in")) {
      userBuilder.lastSeenDate(Instant.parse(attributes.get("last_signed_in").toString()));
    }

    Set<Authority> authorities = rolesFromAccessToken.stream()
      .map(authority -> AuthorityBuilder.authority()
        .authorityName(new AuthorityName(authority))
        .build())
      .collect(toSet());

    userBuilder.authorities(authorities);

    return userBuilder.build();
  }

  public UserLastname getLastname() {
    return lastname;
  }

  public UserFirstname getFirstname() {
    return firstname;
  }

  public UserEmail getEmail() {
    return email;
  }

  public UserPublicId getUserPublicId() {
    return userPublicId;
  }

  public UserImageUrl getImageUrl() {
    return imageUrl;
  }

  public Instant getLastModifiedDate() {
    return lastModifiedDate;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public Instant getLastSeenDate() {
    return lastSeenDate;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public Long getDbId() {
    return dbId;
  }

  public UserAddress getUserAddress() {
    return userAddress;
  }
}
