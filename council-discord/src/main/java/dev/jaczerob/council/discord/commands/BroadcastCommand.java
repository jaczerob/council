package dev.jaczerob.council.discord.commands;

import dev.jaczerob.council.common.broadcast.models.BroadcastChannelType;
import dev.jaczerob.council.discord.framework.exceptions.DiscordException;
import dev.jaczerob.council.discord.framework.interactions.models.SlashCommand;
import dev.jaczerob.council.discord.services.BroadcastChannelService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BroadcastCommand extends SlashCommand {
    @Autowired
    private BroadcastChannelService broadcastChannelService;

    @Override
    public @NonNull String getName() {
        return "broadcast";
    }

    @Override
    public @NonNull List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.STRING, "broadcast-type", "The type of broadcast", true)
                        .addChoice("status", "STATUS")
                        .addChoice("news", "NEWS")
                        .addChoice("field-offices", "FIELDOFFICES")
                        .addChoice("districts", "DISTRICTS")
                        .addChoice("release-notes", "RELEASENOTES")
        );
    }

    @Override
    public @NonNull MessageCreateData execute(final @NonNull SlashCommandInteractionEvent event) throws DiscordException {
        this.broadcastChannelService.createBroadcastChannel(event.getChannel().getIdLong(), BroadcastChannelType.valueOf(event.getOption("broadcast-type").getAsString()));
        return new MessageCreateBuilder().setContent("This channel is now a broadcast channel for type: " + event.getOption("broadcast-type")).build();
    }

    @Override
    public @NonNull String getDescription() {
        return "Sets this channel as a broadcast channel";
    }
}
