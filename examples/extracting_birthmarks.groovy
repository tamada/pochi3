// extract UC birthmarks from given jar files.

// extract birthmarks by given extractor from given file path.
def extract(path, extractor, name) {
    printf("extract %s birthmark from %s%n", name, path)
    // obtains DataSource object for extracting birthmarks.
    source = pochi.source(path)
    // returns Birthmarks object contains Birthmark objects.
    return extractor.extract(source)
}

// prints each Birthmark object.
def printBirthmarks(birthmarks) {

    birthmarks.each(b -> println(jsonier.toJson(b)))
}

name = "3-gram"

// gets UC birthmark extractor.
extractor = pochi.extractor(name)

args.stream()
    .map(file -> extract(file, extractor, name)) // converts to Birthmarks
    .each(birthmarks -> printBirthmarks(birthmarks)) // print given birthmarks.
