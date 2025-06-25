package com.example.unearthedtruths.discovery.repo;

import com.example.unearthedtruths.discovery.model.Discovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscoveryRepo extends JpaRepository<Discovery, Long> {

    @Query("SELECT d FROM Discovery d " +
            "WHERE (:bibleEra IS NULL OR d.bibleEra = :bibleEra) " +
            "AND (:region IS NULL OR d.region = :region) " +
            "AND (:type IS NULL OR d.type = :type)")
    List<Discovery> findByFilters(String bibleEra, String region, String type);


    @Query("SELECT d FROM Discovery d WHERE " +
            "LOWER(d.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.bibleEra) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.region) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.type) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Discovery> search(@Param("query") String query);
}
