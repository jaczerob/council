package dev.jaczerob.council.discord.commands;

import dev.jaczerob.council.discord.framework.exceptions.DiscordException;
import dev.jaczerob.council.discord.framework.interactions.models.SlashCommand;
import dev.jaczerob.council.discord.services.ToontownUpdatesService;
import dev.jaczerob.council.discord.utils.EmbedCreator;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.springframework.lang.NonNull;

class InvasionsSubCommand extends SlashCommand {
    private final ToontownUpdatesService toontownUpdatesService;

    public InvasionsSubCommand(final ToontownUpdatesService toontownUpdatesService) {
        this.toontownUpdatesService = toontownUpdatesService;
    }

    @Override
    public @NonNull String getName() {
        return "invasions";
    }

    @Override
    public @NonNull String getDescription() {
        return "Gets the current invasions";
    }

    @Override
    public boolean isEphemeral() {
        return true;
    }

    @Override
    public @NonNull MessageCreateData execute(final @NonNull SlashCommandInteractionEvent event) throws DiscordException {
        final MessageEmbed embed = EmbedCreator.fromInvasions(this.toontownUpdatesService.getDistricts().orElseThrow(() -> new DiscordException("No districts found", true)));
        return new MessageCreateBuilder().setEmbeds(embed).build();
    }
}
