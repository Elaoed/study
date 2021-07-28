package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogApp {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Object.class);
        log.error("Hello error");
    }
}
