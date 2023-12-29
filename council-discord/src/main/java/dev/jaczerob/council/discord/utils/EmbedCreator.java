package dev.jaczerob.council.discord.utils;

import dev.jaczerob.council.common.toontown.models.districts.Districts;
import dev.jaczerob.council.common.toontown.models.fieldoffices.ZonedFieldOffice;
import dev.jaczerob.council.common.toontown.models.fieldoffices.ZonedFieldOffices;
import dev.jaczerob.council.common.toontown.models.news.NewsPartial;
import dev.jaczerob.council.common.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.council.common.toontown.models.status.Status;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;

public class EmbedCreator {
    public static MessageEmbed fromReleaseNotes(final ReleaseNotes releaseNotes) {
        final EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Release Notes");
        embed.setDescription(String.format("(%s) %s", releaseNotes.slug(), releaseNotes.date()));

        final String[] notes = releaseNotes.body().substring(1).split("=");
        for (final String note : notes) {
            final String[] noteParts = note.split("\\*");

            final String noteTitle = noteParts[0];
            final StringBuilder noteBody = new StringBuilder();
            for (int i = 1; i < noteParts.length; i++) {
                noteBody.append(String.format("• %s\n", noteParts[i]));
            }

            embed.addField(noteTitle, noteBody.toString(), false);
        }

        return fill(embed);
    }

    public static MessageEmbed fromStatus(final Status status) {
        final EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle(String.format("Toontown Rewritten is: %s", status.open() ? "Open" : "Closed"));

        if (!status.status().isEmpty()) {
            embed.setDescription(status.status());
        }

        return fill(embed);
    }

    public static MessageEmbed fromNews(final NewsPartial news) {
        final EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Toontown Rewritten News");
        embed.setDescription(String.format("%s - %s", news.author(), news.title()));
        embed.setUrl(news.url());

        return fill(embed);
    }

    public static MessageEmbed fromFieldOffices(final ZonedFieldOffices fieldOffices) {
        final EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Field Offices");

        fieldOffices.fieldOffices().forEach(fieldOffice -> {
            final String zone = fieldOffice.zone();
            final String difficulty = "⭐".repeat(fieldOffice.difficulty());
            final int annexes = fieldOffice.annexes();
            final boolean open = fieldOffice.open();

            embed.addField(zone, String.format("Difficulty: %s\nAnnexes: %d\nOpen: %s", difficulty, annexes, open), true);
        });

        return fill(embed);
    }

    public static MessageEmbed fromDistricts(final Districts districts) {
        final EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Districts");

        districts.districts().forEach(district -> {
            final String districtName = district.name();
            final int districtPopulation = district.population();
            final String districtStatus = district.status();

            final String output;
            if (district.invasion() != null) {
                output = String.format("Population: %d\nStatus: %s\nInvasion Cog: %s\nInvasion Progress: %s", districtPopulation, districtStatus, district.invasion().type(), district.invasion().progress());
            } else {
                output = String.format("Population: %d\nStatus: %s", districtPopulation, districtStatus);
            }

            embed.addField(districtName, output, true);
        });

        return fill(embed);
    }

    public static MessageEmbed fromInvasions(final Districts districts) {
        final EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("Invasions");

        districts.districts().forEach(district -> {
            if (district.invasion() != null) {
                final String districtName = district.name();
                final String districtStatus = district.status();
                final String invasionCog = district.invasion().type();
                final String invasionProgress = district.invasion().progress();

                embed.addField(districtName, String.format("Invasion Cog: %s\nInvasion Progress: %s", invasionCog, invasionProgress), true);
            }
        });

        return fill(embed);
    }

    private static MessageEmbed fill(final EmbedBuilder embed) {
        embed.setColor(Color.pink);

        return embed.build();
    }
}
