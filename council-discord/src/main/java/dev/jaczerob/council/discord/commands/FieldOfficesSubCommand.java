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

class FieldOfficesSubCommand extends SlashCommand {
    private final ToontownUpdatesService toontownUpdatesService;

    public FieldOfficesSubCommand(final ToontownUpdatesService toontownUpdatesService) {
        this.toontownUpdatesService = toontownUpdatesService;
    }

    @Override
    public @NonNull String getName() {
        return "field_offices";
    }

    @Override
    public @NonNull String getDescription() {
        return "Gets the current status of field offices";
    }

    @Override
    public boolean isEphemeral() {
        return true;
    }

    @Override
    public @NonNull MessageCreateData execute(final @NonNull SlashCommandInteractionEvent event) throws DiscordException {
        final MessageEmbed embed = EmbedCreator.fromFieldOffices(this.toontownUpdatesService.getFieldOffices().orElseThrow(() -> new DiscordException("No field offices found", true)));
        return new MessageCreateBuilder().setEmbeds(embed).build();
    }
}
