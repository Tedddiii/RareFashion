package org.dipl.rarefashion.repository;

import org.dipl.rarefashion.entity.Product;
import org.dipl.rarefashion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
