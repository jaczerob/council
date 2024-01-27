package dev.jaczerob.council.database.toons;

import dev.jaczerob.council.common.toonstats.models.Toons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToonService {
    private static final Logger log = LoggerFactory.getLogger(ToonService.class);

    private final ToonRepository toonRepository;

    public ToonService(final ToonRepository toonRepository) {
        this.toonRepository = toonRepository;
    }

    public List<ToonEntity> save(final Toons toons) {
        final List<ToonEntity> toonEntities = toons.toons().stream().map(ToonEntity::fromToon).toList();
        return this.toonRepository.saveAll(toonEntities);
    }
}
