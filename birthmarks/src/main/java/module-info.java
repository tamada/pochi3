/**
 * <h1>The birthmarking toolkit API</h1>
 * <p>
 * This module defines the API of the birthmarking toolkit.
 * The API contains the interfaces of {@link jp.cafebabe.birthmarks.extractors.Extractor},
 * {@link jp.cafebabe.birthmarks.comparators.Comparator}, {@link jp.cafebabe.birthmarks.pairers.Pairer}, and their builders,
 * and related entity classes.
 * </p>
 * <h2>The birthmarks</h2>
 * <h3>The birthmark types</h3>
 * <p>
 * We categorize the birthmarks several types by focusing some aspects in software.
 * The birthmark types represent to the concrete implementation of the {@link jp.cafebabe.birthmarks.extractors.Extractor} interface,
 * which are given by <code>jp.cafebabe.pochi.core</code> module.
 * </p>
 * <h3>The structure of the birthmarks</h3>
 * <p>
 * Then, pochi defines five kinds of birthmark structures: list, vector, set, graph, and tree.
 * The structure are the different aspect of the birthmark types.
 * For example, we assume a quite simple birthmark type, <strong>literals</strong>,
 * which composed of the literals, such as strings, integers, and etc.
 * In the list structure, pochi collects the literals from the class files and stores them in the appearance orders.
 * On the other hand, in the vector structure, pochi constructs frequency vectors from the whole literals.
 * Similarly, the set structure are constructed as the set of literals.
 * </p><p>
 * However, graph, and tree structure are related to the birthmark types.
 * That is, graph and tree structured birthmarks are constructed by special types of birthmarks.
 * </p><p>
 * Of course, pochi can convert the birthmark structure each other.
 * The figure 1 shows the convertable relations.
 * Each circle in the figure 1 shows the birthmark structure, represents by the first letter of the structures.
 * The dotted arrows shows it can convert, however, contains information loss.
 * Also, table 1 shows the same relations in the Table representation.
 * 👍 means that can convert, ✂️ means that it brings information loss of conversion, 🆖 means that it cannot convert.
 * </p>
 * <figure>
 *     <img src="{@docRoot}/../images/birthmark_structure_converter.svg" alt="birthmark structure converter">
 *     <figcaption>Fig. 1: The convertable relations among the structure types of birthmarks</figcaption>
 * </figure>
 * <table>
 *     <caption>Table 1: Converting rules</caption>
 *     <thead>
 *         <tr>
 *             <th></th>
 *             <th></th>
 *             <th colspan="5">To</th>
 *         </tr>
 *         <tr>
 *             <th></th>
 *             <th></th>
 *             <th>Graph</th>
 *             <th>Tree</th>
 *             <th>List</th>
 *             <th>Vector</th>
 *             <th>Set</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <th rowspan="5">From</th>
 *             <th>Graph</th>
 *             <td>👍</td>
 *             <td>🆖</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *         </tr>
 *         <tr>
 *             <th>Tree</th>
 *             <td>🆖</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *         </tr>
 *         <tr>
 *             <th>List</th>
 *             <td>🆖</td>
 *             <td>🆖</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *         </tr>
 *         <tr>
 *             <th>Vector</th>
 *             <td>🆖</td>
 *             <td>🆖</td>
 *             <td>✂️</td>
 *             <td>👍</td>
 *             <td>👍</td>
 *         </tr>
 *         <tr>
 *             <th>Set</th>
 *             <td>🆖</td>
 *             <td>🆖</td>
 *             <td>✂️</td>
 *             <td>✂️</td>
 *             <td>👍</td>
 *         </tr>
 *     </tbody>
 * </table>
 * <h2>The birthmarking phases</h2>
 * <p>
 * Pochi defines three birthmarking phases: reading, pairing, and comparing.
 * In each phase, the user uses some key interface to process the inputs and get resultant object as the output.
 * The output objects are the inputs for the next phase.
 * </p>
 * <h3>Reading Phase</h3>
 * <p>
 * The first phase, reading, is to extract birthmarks from class files or read birthmarks from JSON files.
 * Generally, a birthmark is extracted from some component in software, such as class, method, and package.
 * Then, the resultant object of the phase is the object of {@link jp.cafebabe.birthmarks.entities.Birthmarks}.
 * Also, the structure of the birthmarks are five types: list, vector, set, graph, and tree.
 * </p>
 * <h3>Pairing Phase</h3>
 * <p>
 * Next phase, pairing, builds the pair list of two {@link jp.cafebabe.birthmarks.entities.Birthmark} objects for comparing phase.
 * </p>
 * <h3>Comparing Phase</h3>
 * <p>
 * The final phase, comparing, compare two birthmarks and calculates similarity between them.
 * Pochi conducts the comparison for all of pairs built by the previous phase.
 * </p>
 * <h3>Key interfaces</h3>
 * <p>
 *     Table 2 shows the key interfaces and their description.
 *     The input/output objects of key interfaces in a phase are the same.
 *     And the output objects of a phase become the input objects for the next phase.
 * </p>
 * <table>
 *     <caption>Table 2: The key interfaces of the each birthmarking phase</caption>
 *     <thead>
 *         <tr>
 *             <th>Phase</th>
 *             <th>Interface</th>
 *             <th>Description</th>
 *             <th>Inputs</th>
 *             <th>Outputs</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td>Reading</td>
 *             <td>{@link jp.cafebabe.birthmarks.extractors.Extractor}</td>
 *             <td>It extracts birthmarks from the given DataSource and gets {@link jp.cafebabe.birthmarks.entities.Birthmarks} object.</td>
 *             <td>{@link jp.cafebabe.clpond.source.DataSource}</td>
 *             <td>{@link jp.cafebabe.birthmarks.entities.Birthmarks}</td>
 *         </tr>
 *         <tr>
 *             <td>Reading</td>
 *             <td>{@link jp.cafebabe.birthmarks.io.Parser}</td>
 *             <td>It reads the given JSON files and gets {@link jp.cafebabe.birthmarks.entities.Birthmarks} objects</td>
 *             <td>{@link java.nio.file.Path} for the JSON files</td>
 *             <td>{@link jp.cafebabe.birthmarks.entities.Birthmarks}</td>
 *         </tr>
 *         <tr>
 *             <td>Pairing</td>
 *             <td>{@link jp.cafebabe.birthmarks.pairers.Pairer}</td>
 *             <td>
 *                 From the given {@link jp.cafebabe.birthmarks.entities.Birthmarks} objects,
 *                 it constructs pair list of {@link jp.cafebabe.birthmarks.entities.Birthmark} objects.
 *             </td>
 *             <td>{@link jp.cafebabe.birthmarks.entities.Birthmarks}</td>
 *             <td>List objects of <code>{@link jp.cafebabe.birthmarks.comparators.Pair Pair&lt;Birthmark, Birthmark&gt;}</code></td>
 *         </tr>
 *         <tr>
 *             <td>Comparing</td>
 *             <td>{@link jp.cafebabe.birthmarks.comparators.Comparator}</td>
 *             <td>It compares two {@link jp.cafebabe.birthmarks.entities.Birthmark} objects and calculates similarity based on the given pair list.</td>
 *             <td><code>{@link jp.cafebabe.birthmarks.comparators.Pair Pair&lt;Birthmark, Birthmark&gt;}</code> objects (or its list)</td>
 *             <td>{@link jp.cafebabe.birthmarks.comparators.Comparisons}</td>
 *         </tr>
 *     </tbody>
 * </table>
 */
module jp.cafebabe.pochi.birthmarks {
    requires io.vavr;
    requires org.objectweb.asm;
    requires com.google.gson;
    requires jp.cafebabe.pochi.clpond;

    exports jp.cafebabe.birthmarks;
    exports jp.cafebabe.birthmarks.comparators;
    exports jp.cafebabe.birthmarks.config;
    exports jp.cafebabe.birthmarks.entities;
    exports jp.cafebabe.birthmarks.entities.elements;
    exports jp.cafebabe.birthmarks.entities.graph;
    exports jp.cafebabe.birthmarks.entities.impl;
    exports jp.cafebabe.birthmarks.events;
    exports jp.cafebabe.birthmarks.extractors;
    exports jp.cafebabe.birthmarks.io;
    exports jp.cafebabe.birthmarks.pairers;
    exports jp.cafebabe.birthmarks.utils;

    opens jp.cafebabe.birthmarks.comparators;
    opens jp.cafebabe.birthmarks.config;
    opens jp.cafebabe.birthmarks.events;
    opens jp.cafebabe.birthmarks.entities;
    opens jp.cafebabe.birthmarks.entities.elements;
    opens jp.cafebabe.birthmarks.entities.impl;
    opens jp.cafebabe.birthmarks.io;
    opens jp.cafebabe.birthmarks.pairers;
}