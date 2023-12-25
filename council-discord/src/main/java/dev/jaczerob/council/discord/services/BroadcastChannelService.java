package dev.jaczerob.council.discord.services;

import dev.jaczerob.council.common.broadcast.models.BroadcastChannel;
import dev.jaczerob.council.common.broadcast.models.BroadcastChannelType;
import net.dv8tion.jda.api.JDA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class BroadcastChannelService {
    private static final Logger log = LoggerFactory.getLogger(BroadcastChannelService.class);

    private final WebClient webClient;

    public BroadcastChannelService(final WebClient.Builder webClientBuilder, final @Value("${DATABASE_URL}") String databaseUrl) {
        this.webClient = webClientBuilder.baseUrl(databaseUrl + "/database/broadcast-channels").build();
    }

    public List<BroadcastChannel> getBroadcastChannels() {
        final List<BroadcastChannel> broadcastChannels = this.webClient.get().retrieve().bodyToFlux(BroadcastChannel.class).collectList().block();

        if (broadcastChannels == null) {
            log.error("Failed to get broadcast channels");
            return List.of();
        } else {
            return broadcastChannels;
        }
    }

    public List<BroadcastChannel> getBroadcastChannels(final BroadcastChannelType type) {
        final List<BroadcastChannel> broadcastChannels = this.webClient.get().uri("/{type}", type).retrieve().bodyToFlux(BroadcastChannel.class).collectList().block();

        if (broadcastChannels == null) {
            log.error("Failed to get broadcast channels");
            return List.of();
        } else {
            return broadcastChannels;
        }
    }

    public Optional<BroadcastChannel> createBroadcastChannel(final long id, final BroadcastChannelType type) {
        return Optional.ofNullable(this.webClient.post().uri("/{id}/{type}", id, type).retrieve().bodyToMono(BroadcastChannel.class).block());
    }
}
