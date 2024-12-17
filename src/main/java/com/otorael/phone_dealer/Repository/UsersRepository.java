package com.otorael.phone_dealer.Repository;

import com.otorael.phone_dealer.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail (String email);
}
