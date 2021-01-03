package com.projekt.inzynierka.repositories;

import com.projekt.inzynierka.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
