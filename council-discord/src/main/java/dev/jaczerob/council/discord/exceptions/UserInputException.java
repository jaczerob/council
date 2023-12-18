package dev.jaczerob.council.discord.exceptions;

public class UserInputException extends DiscordException {
    public UserInputException(final String message) {
        super(message, true);
    }
}
