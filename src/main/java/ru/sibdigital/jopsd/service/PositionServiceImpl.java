package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Position;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@Slf4j
public class PositionServiceImpl extends SuperServiceImpl implements PositionService{

    @Override
    public Position save(Position position) {
        position.setUpdatedAt(Timestamp.from(Instant.now()));
        return positionRepository.save(position);
    }

}
