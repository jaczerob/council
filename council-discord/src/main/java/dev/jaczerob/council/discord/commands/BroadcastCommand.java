package dev.jaczerob.council.discord.commands;

import dev.jaczerob.council.common.broadcast.models.BroadcastChannelType;
import dev.jaczerob.council.discord.framework.exceptions.DiscordException;
import dev.jaczerob.council.discord.framework.interactions.models.SlashCommand;
import dev.jaczerob.council.discord.services.BroadcastChannelService;
import dev.jaczerob.council.discord.services.BroadcastService;
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

    @Autowired
    private BroadcastService broadcastService;

    @Override
    public @NonNull String getName() {
        return "broadcast";
    }

    @Override
    public @NonNull List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.STRING, "broadcast-type", "The type of broadcast", true)
                        .addChoice("status", BroadcastChannelType.STATUS.name())
                        .addChoice("news", BroadcastChannelType.NEWS.name())
                        .addChoice("field-offices", BroadcastChannelType.FIELDOFFICES.name())
                        .addChoice("districts", BroadcastChannelType.DISTRICTS.name())
                        .addChoice("release-notes", BroadcastChannelType.RELEASENOTES.name())
        );
    }

    @Override
    public @NonNull MessageCreateData execute(final @NonNull SlashCommandInteractionEvent event) throws DiscordException {
        final BroadcastChannelType type = BroadcastChannelType.valueOf(event.getOption("broadcast-type").getAsString());

        switch (type) {
            case STATUS -> this.broadcastService.broadcastLatestStatus(event.getGuildChannel());
            case NEWS -> this.broadcastService.broadcastLatestNews(event.getGuildChannel());
            case FIELDOFFICES -> this.broadcastService.broadcastLatestFieldOffices(event.getGuildChannel());
            case DISTRICTS -> this.broadcastService.broadcastLatestDistricts(event.getGuildChannel());
            case RELEASENOTES -> this.broadcastService.broadcastLatestReleaseNotes(event.getGuildChannel());
        }

        this.broadcastChannelService.createBroadcastChannel(event.getChannel().getIdLong(), type);
        return new MessageCreateBuilder().setContent("This channel is now a broadcast channel for type: " + event.getOption("broadcast-type").getAsString()).build();
    }

    @Override
    public @NonNull String getDescription() {
        return "Sets this channel as a broadcast channel";
    }
}
