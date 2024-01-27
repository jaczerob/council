package dev.jaczerob.council.common.broadcast.models;

public record BroadcastMessage(
        int id,
        BroadcastType type,
        String messageId,
        String channelId,
        String guildId
) {
    public BroadcastMessage(final BroadcastType type, final String messageId, final String channelId, final String guildId) {
        this(0, type, messageId, channelId, guildId);
    }
}
