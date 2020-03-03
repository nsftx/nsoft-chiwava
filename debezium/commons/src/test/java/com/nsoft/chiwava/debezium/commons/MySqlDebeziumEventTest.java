package com.nsoft.chiwava.debezium.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsoft.chiwava.debezium.commons.mysql.MySqlDebeziumEvent;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MySqlDebeziumEventTest {
    @Test
    void shouldDeserializeMysqlDebeziumEvent() throws IOException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(
                classLoader.getResource("mysql_debezium_event_example.json").getFile());
        String debeziumEventAsString = new String(Files.readAllBytes(file.toPath()));

        MySqlDebeziumEvent event = mapper
                .readValue(debeziumEventAsString, MySqlDebeziumEvent.class);


        assertEquals("connector-db-name", event.getCandidate().getDatabase());
        assertEquals("connector-table-name", event.getCandidate().getTable());
        assertEquals(OperationType.CREATE, event.getCandidate().getOperation());
    }


    @Test
    void shouldFindProperProcessor() throws IOException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(
                classLoader.getResource("mysql_debezium_event_example.json").getFile());
        String debeziumEventAsString = new String(Files.readAllBytes(file.toPath()));

        MySqlDebeziumEvent event = mapper
                .readValue(debeziumEventAsString, MySqlDebeziumEvent.class);


        assertEquals("connector-db-name", event.getCandidate().getDatabase());
        assertEquals("connector-table-name", event.getCandidate().getTable());
        assertEquals(OperationType.CREATE, event.getCandidate().getOperation());
    }
}
