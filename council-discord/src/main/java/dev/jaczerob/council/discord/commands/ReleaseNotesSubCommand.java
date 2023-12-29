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

class ReleaseNotesSubCommand extends SlashCommand {
    private final ToontownUpdatesService toontownUpdatesService;

    public ReleaseNotesSubCommand(final ToontownUpdatesService toontownUpdatesService) {
        this.toontownUpdatesService = toontownUpdatesService;
    }

    @Override
    public @NonNull String getName() {
        return "release_notes";
    }

    @Override
    public @NonNull String getDescription() {
        return "Gets the latest release notes from Toontown Rewritten";
    }

    @Override
    public boolean isEphemeral() {
        return true;
    }

    @Override
    public @NonNull MessageCreateData execute(final @NonNull SlashCommandInteractionEvent event) throws DiscordException {
        final MessageEmbed embed = EmbedCreator.fromReleaseNotes(this.toontownUpdatesService.getReleaseNotes().orElseThrow(() -> new DiscordException("No release notes found", true)));
        return new MessageCreateBuilder().setEmbeds(embed).build();
    }
}
