package com.hg.springbootrestapi.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EventTest {

    @Test
    void builder() {
        Event event = Event.builder()
            .name("Inflearn Spring REST API")
            .description("REST API development with Spring")
            .build();
        assertNotNull(event);
    }

    @Test
    void javaBean() {
        // given
        String name = "Event";
        String description = "Spring";

        // when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // then
        assertEquals(name, event.getName());
        assertEquals(description, event.getDescription());
    }

    @ParameterizedTest
    @CsvSource({"0, 0, true", "100, 0, false", "0, 100, false"})
    void testFree(int basePrice, int maxPrice, boolean free) {
        Event event = Event.builder()
            .basePrice(basePrice)
            .maxPrice(maxPrice)
            .build();
        event.update();
        assertEquals(free, event.isFree());
    }

    @ParameterizedTest
    @CsvSource({"강남역, true", ", false"})
    void testOffline(String location, boolean offline) {
        // given
        Event event = Event.builder()
            .location(location)
            .build();

        // when
        event.update();

        // then
        assertEquals(offline, event.isOffline());
    }
}