package dev.jaczerob.council.database.models;

import dev.jaczerob.council.grpc.types.BroadcastChannelType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "broadcast_channels")
public class BroadcastChannelEntity {
    @Id
    private long id;
    private BroadcastChannelType type;

    public BroadcastChannelEntity() {
    }

    public BroadcastChannelEntity(final long id, final BroadcastChannelType type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return this.id;
    }

    public BroadcastChannelType getType() {
        return this.type;
    }
}
