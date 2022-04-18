// compare extracted birthmarks from given jar files with the specified_pair_matcher.
import jp.cafebabe.birthmarks.entities.Birthmarks;

// extract birthmarks by given extractor from given file path.
def extract(path, extractor) {
    source = pochi.source(path)
    return extractor.extract(source)
}

// gets UC birthmark extractor.
extractor = pochi.extractor("uc")

birthmarks = args.stream()
    .map(file -> extract(file, extractor))              // converts to Birthmarks
    .reduce(new Birthmarks(), (b1, b2) -> b1.merge(b2)) // merges to one Birthmarks object

pochi.config().put("pairer.list", "examples/sample_matching.csv") // specified pairs by csv file with the key "pairer.list".
pochi.config().put("pairer.relationer", "fully") // "classname" is also available.
comparator = pochi.comparator("jaccard_index")
pairer = pochi.pairer("specified")

result = comparator.compare(birthmarks, pairer)
println(jsonier.toJson(result))

