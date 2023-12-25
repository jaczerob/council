package dev.jaczerob.council.database.services;

import dev.jaczerob.council.common.broadcast.models.BroadcastChannel;
import dev.jaczerob.council.common.broadcast.models.BroadcastChannelType;
import dev.jaczerob.council.database.models.BroadcastChannelEntity;
import dev.jaczerob.council.database.repositories.BroadcastChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BroadcastChannelService {
    private static final Logger log = LoggerFactory.getLogger(BroadcastChannelService.class);
    private final BroadcastChannelRepository repository;

    public BroadcastChannelService(final BroadcastChannelRepository repository) {
        this.repository = repository;
    }

    public List<BroadcastChannel> getBroadcastChannels(final BroadcastChannelType type) {
        return this.getBroadcastChannels(this.repository.findAllByType(type));
    }

    public List<BroadcastChannel> getBroadcastChannels() {
        return this.getBroadcastChannels(this.repository.findAll());
    }

    private List<BroadcastChannel> getBroadcastChannels(final List<BroadcastChannelEntity> entities) {
        return entities.stream()
                .map(entity -> new BroadcastChannel(entity.getType(), entity.getId()))
                .toList();
    }

    public Optional<BroadcastChannel> createBroadcastChannel(final long id, final BroadcastChannelType type) {
        if (this.repository.existsByIdAndType(id, type))
            return Optional.empty();

        final BroadcastChannelEntity broadcastChannelEntity = new BroadcastChannelEntity(id, type);
        this.repository.save(broadcastChannelEntity);

        log.info("Created broadcast channel: {}", broadcastChannelEntity);
        return Optional.of(new BroadcastChannel(broadcastChannelEntity.getType(), broadcastChannelEntity.getId()));
    }
}
