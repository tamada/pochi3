package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.comparators.Comparator;
import jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
import jp.cafebabe.birthmarks.comparators.ComparatorType;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.io.BirthmarkParser;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.clpond.source.DataSource;
import jp.cafebabe.clpond.source.factories.DataSourceBuilderFactory;
import jp.cafebabe.pochi.birthmarks.ExtractorBuilderFactory;
import jp.cafebabe.pochi.pairers.PairerBuilderFactory;
import jp.cafebabe.pochi.utils.ServiceBuilderFactory;

import java.io.IOException;
import java.nio.file.Path;

public class BirthmarkSystem {
    private ExtractorBuilderFactory extractorFactory = new ExtractorBuilderFactory();
    private ServiceBuilderFactory<ComparatorBuilder, ComparatorType, Comparator> comparatorFactory = new ServiceBuilderFactory<>(ComparatorBuilder.class);
    private PairerBuilderFactory<Birthmark> pairerFactory = new PairerBuilderFactory<>();
    private Pochi pochi;

    public BirthmarkSystem(Pochi pochi) {
        this.pochi = pochi;
    }

    public Configuration config() {
        return pochi.config();
    }

    public DataSource source(String path) throws IOException {
        return source(Path.of(path));
    }

    public DataSource source(Path path) throws IOException {
        return new DataSourceBuilderFactory()
                .find(path).get()
                .build(path);
    }

    public BirthmarkParser parser() {
        return new BirthmarkParser();
    }

    public Extractor extractor(String name) {
        return extractorFactory.builder(name)
                .get().build(config());
    }

    public Pairer<Birthmark> pairer(String name) {
        return pairerFactory.builder(name)
                .get().build(config());
    }

    public Comparator comparator(String name) {
        return comparatorFactory.builder(name)
                .map(b -> b.build(config()))
                .get();
    }
}
