package dev.jaczerob.council.discord.framework.interactions.models;

import dev.jaczerob.council.discord.framework.exceptions.DiscordException;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.springframework.lang.NonNull;

import java.util.List;

public abstract class Interaction<T extends GenericCommandInteractionEvent> {
    public abstract @NonNull String getName();
    public abstract @NonNull MessageCreateData execute(final @NonNull T event) throws DiscordException;

    public @NonNull List<Long> getRequiredChannels() {
        return List.of();
    }

    public @NonNull List<Long> getRequiredRoles() {
        return List.of();
    }

    public @NonNull List<Permission> getRequiredPermissions() {
        return List.of();
    }

    public boolean isEphemeral() {
        return false;
    }

    public void beforeExecute(final @NonNull T event) throws DiscordException {

    }

    public void afterExecute(final @NonNull T event) throws DiscordException {

    }
}
