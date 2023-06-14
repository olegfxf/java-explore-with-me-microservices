package ru.practicum.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.stats.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("checkstyle:Regexp")
@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {
    List<Hit> findAllByTimestampAfter(LocalDateTime start);

    List<Hit> findAllByTimestampIsBetween(LocalDateTime start, LocalDateTime end);

    List<Hit> findAllByTimestampBefore(LocalDateTime end);


    @Query(value = "SELECT  DISTINCT on (h.ip) h.id, h.app, h.uri,  h.ip, h.timestamp FROM hits AS h WHERE h.timestamp > :startDate ORDER  BY  h.ip ", nativeQuery = true)
    List<Hit> getHitDistinctAfter(@Param("startDate") LocalDateTime startDate);


    @Query(value = "SELECT  DISTINCT on (h.ip) h.id, h.app, h.uri,  h.ip, h.timestamp FROM hits AS h WHERE h.timestamp BETWEEN :startDate AND  :endDate" +
            " ORDER  BY  h.ip ", nativeQuery = true)
    List<Hit> getHitDistinctBetween(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);


    @Query(value = "SELECT  DISTINCT on (h.ip) h.id, h.app, h.uri,  h.ip, h.timestamp FROM hits AS h WHERE h.timestamp < :endDate ORDER  BY  h.ip ", nativeQuery = true)
    List<Hit> getHitDistinctBefore(@Param("endDate") LocalDateTime endDate);
}
