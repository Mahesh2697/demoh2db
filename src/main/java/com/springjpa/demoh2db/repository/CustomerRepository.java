package com.springjpa.demoh2db.repository;

import com.springjpa.demoh2db.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {


    Optional<CustomerEntity> findByName(String name);

    Optional<CustomerEntity> getByIdAndName(int id, String name);

    @Query("select u from CustomerEntity u where u.email = ?1")
    CustomerEntity findByEmail(String email);

}
