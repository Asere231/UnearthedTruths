package com.example.unearthedtruths.discovery;

import com.example.unearthedtruths.discovery.model.Discovery;
import com.example.unearthedtruths.discovery.repo.DiscoveryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscoveryService {

    private final DiscoveryRepo discoveryRepo;

    @Transactional
    public ResponseEntity<String> saveDiscovery(Discovery discovery) {
        if (discovery == null)
            return ResponseEntity.badRequest().body("Discovery object is null");

        try {
            log.info("Saving a new discovery in database: '{}'", discovery.getTitle());
            discoveryRepo.save(discovery);
            return ResponseEntity.ok("Discovery added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Could not save Discovery object");
        }
    }

    @Transactional
    public ResponseEntity<List<Discovery>> getAllDiscoveries() {

        try {
            List<Discovery> discoveries = discoveryRepo.findAll();
            log.info("Retrieving all Discoveries");
            return ResponseEntity.ok(discoveries);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Discovery> updateDiscovery(Long id, Discovery updatedDiscovery) {
        Discovery discovery = discoveryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Discovery not found in 'updateDiscovery'"));

        // Update only the fields that are non-null (patch-style)
        if (updatedDiscovery.getBibleEra() != null)
            discovery.setBibleEra(updatedDiscovery.getBibleEra());

        if (updatedDiscovery.getTitle() != null)
            discovery.setTitle(updatedDiscovery.getTitle());

        if (updatedDiscovery.getDescription() != null)
            discovery.setDescription(updatedDiscovery.getDescription());

        if (updatedDiscovery.getLatitude() != -999)
            discovery.setLatitude(updatedDiscovery.getLatitude());

        if (updatedDiscovery.getLongitude() != -999)
            discovery.setLongitude(updatedDiscovery.getLongitude());

        if (updatedDiscovery.getRegion() != null)
            discovery.setRegion(updatedDiscovery.getRegion());

        if (updatedDiscovery.getType() != null)
            discovery.setType(updatedDiscovery.getType());

        if (updatedDiscovery.getSourceLink() != null)
            discovery.setSourceLink(updatedDiscovery.getSourceLink());

        try {
            log.info("Updating discovery '{}' in database", discovery.getTitle());
            discoveryRepo.save(discovery);
            return ResponseEntity.ok(discovery);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> deleteDiscovery(Long id) {

        try {
            log.info("Updating discovery with ID '{}' in database", id);
            discoveryRepo.deleteById(id);
            return ResponseEntity.ok("Discovery deleted successfully!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Discovery>> getFilteredDiscoveries(String bibleEra, String region, String type) {
        System.out.println("Filters in Service: ");
        System.out.println("bibleEra: " + bibleEra);
        System.out.println("region: " + region);
        System.out.println("type: " + type);

        try {
            log.info("Apply filters");
            return ResponseEntity.ok(discoveryRepo.findByFilters(bibleEra, region, type));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Discovery>> searchDiscoveries(String query) {
        try {
            log.info("Searching...");
            return ResponseEntity.ok(discoveryRepo.search(query));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
