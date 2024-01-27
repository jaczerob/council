package dev.jaczerob.council.database.toons;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToonRepository extends JpaRepository<ToonEntity, Integer> {
}
