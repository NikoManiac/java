package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
//        Stream<String> wordStream = words.stream().map(item -> item);
        Stream<String> wordStream = words.stream().filter(item -> item.length() > 0);
        // --end-->
        {
            assertIterableEquals(
                    words,
                    wordStream.collect(Collectors.toList())
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Arrays.stream(words);
        // --end-->
        {
            assertArrayEquals(
                    words,
                    wordStream.toArray(String[]::new)
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream testLength = Stream.of(1, 2, 3, 4);

        boolean[] isClose = new boolean[]{false};
        testLength.onClose(() -> isClose[0] = true);

        Stream afterTestLength = testLength.filter(item -> (int) item > 2);
//        testLength.toArray();
        afterTestLength.toArray();
        afterTestLength.close();
        Stream<String> emptyWordStream = Stream.empty();
        // --end-->
        {
            // 证明Stream只能调用一次
            assertTrue(isClose[0]);
            // Equals的语义是值相等
            assertNotEquals(testLength.getClass(), afterTestLength.getClass());
            assertNotSame(testLength, afterTestLength);

            // 原本的测试
            assertEquals(0, emptyWordStream.count());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> infiniteEchos = Stream.generate(() -> "Echo").limit(10001);
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @Test
    void should_return_10001_when_skip_in_stream() {
        int[] number = new int[]{0};
        String stringStream = Stream.generate(() -> {
            number[0]++;
            return "Echo";
        }).skip(10000).findFirst().get();
        assertEquals(10001, number[0]);
    }

    @Test
    void should_return_10000_when_skip_in_stream() {
        int[] number = new int[]{0};

        Stream<String> stringStream = Stream.iterate("test", str -> {
            number[0]++;
            return "Echo";
        }).skip(10000);

        assertEquals(10000, number[0]);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.generate(() -> 10000).limit(10001);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(item -> item.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map(String::toUpperCase);
        // --end-->
        {
            assertArrayEquals(
                    new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                    filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                    new AnimeCharacter[]{
                            new AnimeCharacter("Naruto"),
                            new AnimeCharacter("Kisuke"),
                            new AnimeCharacter("Tomoya")
                    },
                    actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        Stream<Stream<Character>> streamStream = Stream.of(test1, test2, test3)
                .map(StreamingTest::letters);

        // concat one
        // Stream<Character> streamStream = Stream.of("test1", "test2", "test3")
        //      .map(StreamingTest::letters).flatMap(item -> item);

        Stream<Character> characterStream = streamStream.flatMap(item -> item);

        Character[] expected = {'t', 'e', 's', 't', '1', 't', 'e', 's', 't', '2', 't', 'e', 's', 't', '3'};
        assertArrayEquals(expected, characterStream.toArray());
    }

    @Test
    void should_use_para_to_array() {
        Stream<Stream<Character>> streamStream = Stream.of("qw", "ss", "dd")
                .map(StreamingTest::letters);

        Character[] characters = streamStream.flatMap(item -> item).toArray(Character[]::new);

        assertArrayEquals(new Character[]{'q', 'w', 's', 's', 'd', 'd'}, characters);

    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                    new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @Test
    void should_run_0_time_when_use_limit() {
        int[] count = new int[]{0};
        Stream<Integer> integerStream = Stream.iterate(0, i -> {
            count[0]++;
            return i + 1;
        }).limit(10);

        assertEquals(0, count[0]);
    }

    @Test
    void should_run_9_time_when_use_limit_to_array() {
        int[] count = new int[]{0};
         Stream<Integer> integerStream = Stream.iterate(0, i -> {
            count[0]++;
            return i + 1;
        }).limit(10);
        integerStream.toArray(Integer[]::new);
        int expected = 10 - 1;
        assertEquals(expected, count[0]);

    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
        // --end-->
        {
            assertArrayEquals(
                    letters("HelloWorld").toArray(Character[]::new),
                    concatStream.toArray(Character[]::new)
            );
        }
    }

    @Test
    void should_concat_sorted() {
        Stream<Integer> integerStream = Stream.of(5, 6, 7);
        Stream<Integer> integerStreamBigger = Stream.of(1, 2, 3);

        Stream<Integer> concatIntSoredAll = Stream.concat(integerStream.sorted(), integerStreamBigger.sorted());

        Integer[] integer = new Integer[]{5, 6, 7, 1, 2, 3};
        assertArrayEquals(integer, concatIntSoredAll.toArray(Integer[]::new));

    }

    @Test
    void should_concat_sorted_2() {
        Stream<Integer> integerStream = Stream.of(3, 2);
        Stream<Integer> integerStreamBigger = Stream.of(1, 4);

        Stream<Integer> concatIntSored2 = Stream.concat(integerStream, integerStreamBigger.sorted());

        Integer[] integer = new Integer[]{3, 2, 1, 4};
        assertArrayEquals(integer, concatIntSored2.toArray(Integer[]::new));

    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                    new Character[]{'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                    characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
                .iterate(0, i -> i + 1)
                .limit(20)
                .filter(v -> v % 2 == 0)
                .peek(v -> holder.setValue(holder.getValue() + v));

        hookStream.forEach(i -> {});

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @Test
    void should_not_use_peek_change_stream() {
        ArrayList<ValueHolder<Integer>> valueHolderArrayList = new ArrayList<>();
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(1);
        valueHolderArrayList.add(holder);

        Stream<Integer> integerStream = valueHolderArrayList.stream()
                .peek(item -> item.setValue(2))
                .map(ValueHolder::getValue);

        assertEquals(2, integerStream.toArray()[0]);
    }

    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class<NoSuchElementException> errorType = NoSuchElementException.class;
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> {
            if (!empty.isPresent()) throw new IllegalStateException();
        };
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = (item) -> result.add(item.get().toUpperCase());
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = new Function<Optional<String>, Optional<Boolean>>() {
            @Override
            public Optional<Boolean> apply(Optional<String> optionalS) {
                if (optionalS.isPresent()) {
                    result.add(optionalS.get().toUpperCase());
                    return Optional.of(true);
                } else {
                    return Optional.empty();
                }
            }
        };
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 4)
                .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
                .filter(i -> i > 1)
                .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = new Function<Stream<YieldOptional>, Optional<String>>() {
            @Override
            public Optional<String> apply(Stream<YieldOptional> yieldOptionalStream) {
                Optional<String> optionalS = Optional.empty();
                optionalS.of(yieldOptionalStream.findFirst());
                return optionalS;
            }
        };
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        Stream<String> stream = Stream.of("Hello", "What", "is", "your", "name");

        // TODO: please implement toList collector using `stream.collect`. You cannot use existing `toList` collector.
        // <--start
        ArrayList<String> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        // --end-->

        assertEquals(ArrayList.class, list.getClass());
        assertIterableEquals(
                Arrays.asList("Hello", "What", "is", "your", "name"),
                list
        );
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
        HashMap<String, Integer> map = stream.collect(HashMap::new, (newMap, item) -> {
            if (newMap.containsKey(item.getKey())) {
                newMap.put(item.getKey(), item.getValue());
            } else {

            }
        }, (map1, map2) -> map1.putAll(map2));
        // --end-->

        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
        Map<String, List<Integer>> map = stream
                .collect(Collectors.groupingBy(KeyValuePair::getKey, Collectors.mapping(KeyValuePair::getValue, Collectors.toList())));
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
                new KeyValuePair<>("Harry", 2002),
                new KeyValuePair<>("Bob", 2014),
                new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Integer> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
                .iterate(1, i -> i + 1)
                .limit(100)
                .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = Optional.ofNullable(numbers.stream().reduce(0, (a, b) -> a + b));
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = (int) words.stream().mapToInt(String::length).sum();
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.isPresent() ? optional.get() : defaultValue;
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */