package dev.jaczerob.council.database.repositories;

import dev.jaczerob.council.database.models.BroadcastChannelEntity;
import dev.jaczerob.council.grpc.types.BroadcastChannelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BroadcastChannelRepository extends JpaRepository<BroadcastChannelEntity, Long> {
    @Query("SELECT bc FROM BroadcastChannelEntity bc WHERE bc.type = ?1")
    List<BroadcastChannelEntity> findAllByType(final BroadcastChannelType type);
}
