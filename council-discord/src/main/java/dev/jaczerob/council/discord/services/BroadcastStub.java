package dev.jaczerob.council.discord.services;

import dev.jaczerob.council.grpc.types.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastStub {
    @GrpcClient("broadcast")
    private BroadcastChannelServiceGrpc.BroadcastChannelServiceBlockingStub broadcastChannelServiceBlockingStub;

    public List<BroadcastChannel> getBroadcastChannels(final BroadcastChannelType type) {
        final GetBroadcastChannelsRequest request = GetBroadcastChannelsRequest.newBuilder()
                .setType(type)
                .build();

        return this.broadcastChannelServiceBlockingStub.getBroadcastChannels(request).getChannelsList();
    }

    public List<BroadcastChannel> getBroadcastChannels() {
        final GetBroadcastChannelsRequest request = GetBroadcastChannelsRequest.newBuilder()
                .build();

        return this.broadcastChannelServiceBlockingStub.getBroadcastChannels(request).getChannelsList();
    }

    public BroadcastChannel createBroadcastChannel(final long channelId, final BroadcastChannelType type) {
        final CreateBroadcastChannelRequest request = CreateBroadcastChannelRequest.newBuilder()
                .setId(channelId)
                .setType(type)
                .build();

        return this.broadcastChannelServiceBlockingStub.createBroadcastChannel(request).getChannel();
    }
}
