package dev.jaczerob.council.database.controllers;

import dev.jaczerob.council.common.broadcast.models.BroadcastChannel;
import dev.jaczerob.council.common.broadcast.models.BroadcastChannelType;
import dev.jaczerob.council.database.services.BroadcastChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broadcast-channels")
public class DatabaseController {
    private final BroadcastChannelService broadcastChannelService;

    public DatabaseController(final BroadcastChannelService broadcastChannelService) {
        this.broadcastChannelService = broadcastChannelService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<BroadcastChannel>> getAllByType(final @PathVariable BroadcastChannelType type) {
        final List<BroadcastChannel> broadcastChannels = this.broadcastChannelService.getBroadcastChannels(type);
        return ResponseEntity.ok(broadcastChannels);
    }

    @GetMapping
    public ResponseEntity<List<BroadcastChannel>> getAll() {
        final List<BroadcastChannel> broadcastChannels = this.broadcastChannelService.getBroadcastChannels();
        return ResponseEntity.ok(broadcastChannels);
    }

    @PostMapping("/{channelId}/{type}")
    public ResponseEntity<BroadcastChannel> create(final @PathVariable long channelId, final @PathVariable BroadcastChannelType type) {
        return ResponseEntity.of(this.broadcastChannelService.createBroadcastChannel(channelId, type));
    }
}
