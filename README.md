# Council
An all-purpose Toontown Rewritten Discord bot. 

Currently, this project is in very early development.

## Planned Features
- [ ] Group finding
- [ ] Toon profiles
- [ ] Toon statistics (gags, laff, etc.)
- [ ] Event streaming (updates, news, invasions, etc.)
- [ ] ToonHQ integration
- [ ] Damage calculation
- [ ] Gardening reminders and combinations

## Stack
- Java 21
- Spring Boot
- JDA
- ActiveMQ
- Docker

## Running
Set environment variables `DISCORD_TOKEN` and `DISCORD_GUILD_ID`.

Docker is preferred to run this project. To run, simply run the following commands:

```zsh
git clone https://github.com/jaczerob/council.git
cd council
docker-compose build
docker-compose up -d
```