package de.wienecke.metrics;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class PrometheusEndpoint extends AbstractEndpoint<String> {

    private final CollectorRegistry registry;

    PrometheusEndpoint(CollectorRegistry registry) {
        super("prometheus",false, true);
        this.registry = registry;
    }

    @Override
    public String invoke() {
        Writer writer = new StringWriter();
        try {
            TextFormat.write004(writer, registry.metricFamilySamples());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
