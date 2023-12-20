package dev.jaczerob.council.discord.framework.exceptions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class DiscordException extends Exception {
    private final boolean isEphemeral;

    public DiscordException(final String message, final boolean isEphemeral) {
        super(message);
        this.isEphemeral = isEphemeral;
    }

    public boolean isEphemeral() {
        return this.isEphemeral;
    }

    public MessageEmbed getEmbed() {
        return new EmbedBuilder()
                .setTitle("Error occurred while executing command!")
                .setDescription(this.getMessage())
                .build();
    }
}
