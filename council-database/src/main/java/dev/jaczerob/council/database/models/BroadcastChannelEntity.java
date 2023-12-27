package dev.jaczerob.council.database.models;

import dev.jaczerob.council.common.broadcast.models.BroadcastChannelType;
import jakarta.persistence.*;

@Entity
@Table(
        name = "broadcast_channels",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"channelId", "type"})
        }
)
public class BroadcastChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long channelId;
    private BroadcastChannelType type;

    public BroadcastChannelEntity() { }

    public BroadcastChannelEntity(final long channelId, final BroadcastChannelType type) {
        this.id = 0;
        this.channelId = channelId;
        this.type = type;
    }

    public long getId() {
        return this.id;
    }

    public BroadcastChannelType getType() {
        return this.type;
    }

    public long getChannelId() {
        return this.channelId;
    }
}
