package dev.jaczerob.council.database.services;

import dev.jaczerob.council.database.models.BroadcastChannelEntity;
import dev.jaczerob.council.database.repositories.BroadcastChannelRepository;
import dev.jaczerob.council.grpc.types.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class BroadcastChannelServiceImpl extends BroadcastChannelServiceGrpc.BroadcastChannelServiceImplBase {
    @Autowired
    private BroadcastChannelRepository repository;

    @Override
    public void getBroadcastChannels(final GetBroadcastChannelsRequest request, final StreamObserver<GetBroadcastChannelsResponse> responseObserver) {
        final List<BroadcastChannelEntity> broadcastChannelEntities;

        if (request.hasType()) {
            broadcastChannelEntities = this.repository.findAllByType(request.getType());
        } else {
            broadcastChannelEntities = this.repository.findAll();
        }

        final GetBroadcastChannelsResponse.Builder response = GetBroadcastChannelsResponse.newBuilder();
        broadcastChannelEntities.forEach(broadcastChannelEntity -> response.addChannels(
                BroadcastChannel.newBuilder()
                        .setId(broadcastChannelEntity.getId())
                        .setType(broadcastChannelEntity.getType())
                        .build()
        ));

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void createBroadcastChannel(final CreateBroadcastChannelRequest request, final StreamObserver<CreateBroadcastChannelResponse> responseObserver) {
        final BroadcastChannelEntity broadcastChannelEntity = new BroadcastChannelEntity(request.getId(), request.getType());
        this.repository.save(broadcastChannelEntity);

        final CreateBroadcastChannelResponse response = CreateBroadcastChannelResponse.newBuilder()
                .setChannel(BroadcastChannel.newBuilder()
                        .setId(broadcastChannelEntity.getId())
                        .setType(broadcastChannelEntity.getType())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
