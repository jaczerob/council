package dev.jaczerob.council.discord.commands;

import dev.jaczerob.council.discord.framework.exceptions.DiscordException;
import dev.jaczerob.council.discord.framework.interactions.models.SlashCommand;
import dev.jaczerob.council.discord.services.ToontownUpdatesService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TTRCommand extends SlashCommand {
    @Autowired
    private ToontownUpdatesService toontownUpdatesService;

    @Override
    public @NonNull String getName() {
        return "ttr";
    }

    @Override
    public boolean isEphemeral() {
        return true;
    }

    @Override
    public @NonNull List<SlashCommand> getSubCommands() {
        return List.of(
                new ReleaseNotesSubCommand(this.toontownUpdatesService),
                new StatusSubCommand(this.toontownUpdatesService),
                new NewsSubCommand(this.toontownUpdatesService),
                new FieldOfficesSubCommand(this.toontownUpdatesService),
                new DistrictsSubCommand(this.toontownUpdatesService),
                new InvasionsSubCommand(this.toontownUpdatesService)
        );
    }

    @Override
    public @NonNull String getDescription() {
        return "Sets this channel as a broadcast channel";
    }

    @Override
    public @NonNull MessageCreateData execute(final @NonNull SlashCommandInteractionEvent event) throws DiscordException {
        return new MessageCreateBuilder().build();
    }

}
