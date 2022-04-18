// compare extracted birthmarks from given jar files.
import jp.cafebabe.birthmarks.entities.Birthmarks;

// extract birthmarks by given extractor from given file path.
def extract(path, extractor) {
    source = pochi.source(path)
    return extractor.extract(source)
}

// gets UC birthmark extractor.
extractor = pochi.extractor("3-gram")

birthmarks = args.stream()
    .map(file -> extract(file, extractor))              // converts to Birthmarks
    .reduce(new Birthmarks(), (b1, b2) -> b1.merge(b2)) // merges to one Birthmarks object

comparator = pochi.comparator("jaccard_index")
pairer = pochi.pairer("round_robin")
result = comparator.compare(birthmarks, pairer)
println(jsonier.toJson(result))
