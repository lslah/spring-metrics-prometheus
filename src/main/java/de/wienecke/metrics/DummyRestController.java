package de.wienecke.metrics;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/dummy")
@RestController
public class DummyRestController {
    private final List<String> strings = new CopyOnWriteArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public DummyRestController() {
        strings.add("0");
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> index() {
        final int N = counter.incrementAndGet();
        strings.add(strings.get(strings.size()-1) + N);
        return strings;
    }
}
