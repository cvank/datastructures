package com.playarea.trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Chandu on 9/11/2017.
 */
public class TriesTest {


    public static void main(String[] args) throws IOException {
        String[] words = {"abcD", "abcDE", "abcZx", "abcYT", "acbrt"};
        Trie trie = new Trie(words);
        trie.fetch("AB", false);
        fromcsv("C:\\Users\\Chandu\\Downloads\\all_india_PO_list_without_APS_offices_ver2.csv");
    }

    private static void fromcsv(final String path) throws IOException {
        long start = System.currentTimeMillis();
        List<String> collect = Files.lines(Paths.get(path)).parallel().skip(1).map(line -> Arrays.stream(line.split(",")).findFirst())
                .filter(s -> s.isPresent()).map(s -> s.get()).
                        collect(Collectors.toList());
        collect.addAll(collect.parallelStream().map(s->new StringBuffer(s).reverse().toString()).collect(Collectors.toList()));
        System.out.println(collect.size());
        System.out.println("Total time:" + (System.currentTimeMillis() - start));
        Trie trie = new Trie(collect);
        trie.fetch("amwab", false);
        System.out.println("Total time:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        trie.fetch("Baba Ghulam Shah Badshah University", false);
        System.out.println("Total time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        trie.fetch("Canto", false);
        System.out.println("Total time:" + (System.currentTimeMillis() - start));
    }

}
