package org.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    @Query("SELECT person from Person person WHERE person.companyName = :companyName")
    List<PersonEntity> findByCompany(@Param("companyName") String companyName);
}
