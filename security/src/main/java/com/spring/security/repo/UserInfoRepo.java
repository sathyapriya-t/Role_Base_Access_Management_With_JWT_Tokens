package com.spring.security.repo;

import com.spring.security.user.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends CrudRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String username);
}
