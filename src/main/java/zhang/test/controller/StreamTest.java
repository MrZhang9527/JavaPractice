package zhang.test.controller;

import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Descripthon: Stream练习
 * @author: MrZhang
 * @date: 2021/2/23 10:43
 */
public class StreamTest extends BaseTest{
    public static void main(String[] args) throws IOException {
        // StreamTest streamTest = new StreamTest();
        // streamTest.streamDeal();
        new PDFTextStripper();
        ArrayList<Integer> list = new ArrayList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        int sum = list.stream().mapToInt(key -> key+1).sum();
        System.out.println(sum);
    }

    
    void streamDeal(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        Stream<String> stream = myList.stream();
        String collect = stream.collect(Collectors.joining(","));
        logger.info(collect);

        String s1 = myList.stream().filter(a -> a.contains("1")).map(String::toUpperCase).collect(Collectors.joining());
        logger.info(s1);
        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(a -> logger.info(a));

        List<String> toSort = new ArrayList<>();
        for (String s : myList) {
            if (s.startsWith("c")) {
                String toUpperCase = s.toUpperCase();
                toSort.add(toUpperCase);
            }
        }
        toSort.sort(null);
        for (String toUpperCase : toSort) {
            logger.info(toUpperCase);
        }
        String collect1 = myList.stream().filter(a -> a.contains("1")).map(String::toLowerCase).collect(Collectors.joining());
        logger.info(collect1);

        IntStream.of(1,2,3,4).forEach(a->logger.info(String.valueOf(a)));

        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }
}
