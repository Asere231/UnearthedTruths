package com.example.unearthedtruths.discovery.controller;

import com.example.unearthedtruths.discovery.DiscoveryService;
import com.example.unearthedtruths.discovery.model.Discovery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    @PostMapping("discovery")
    public ResponseEntity<String> saveDiscovery(@RequestBody Discovery discovery) {
        return discoveryService.saveDiscovery(discovery);
    }

    @GetMapping("discoveries")
    public ResponseEntity<List<Discovery>> getAllDiscoveries() {
        return discoveryService.getAllDiscoveries();
    }

    @PutMapping("discovery/{id}")
    public ResponseEntity<Discovery> updateDiscovery(@PathVariable Long id, @RequestBody Discovery updatedDiscovery) {
        return discoveryService.updateDiscovery(id, updatedDiscovery);
    }

    @DeleteMapping("discovery/{id}")
    public ResponseEntity<String> deleteDiscovery(@PathVariable Long id) {
        return discoveryService.deleteDiscovery(id);
    }

    @GetMapping("discoveries/filters")
    public ResponseEntity<List<Discovery>> getFilteredDiscoveries(@RequestParam Map<String, String> filters) {
        String bibleEra = filters.get("bibleEra");
        String region = filters.get("region");
        String type = filters.get("type");

        System.out.println("Filters in Controller: ");
        System.out.println("bibleEra: " + bibleEra);
        System.out.println("region: " + region);
        System.out.println("type: " + type);

        return discoveryService.getFilteredDiscoveries(bibleEra, region, type);
    }

    @GetMapping("discoveries/search")
    public ResponseEntity<List<Discovery>> searchDiscoveries(@RequestParam String query) {
        return discoveryService.searchDiscoveries(query);
    }
}
