import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Camp {
    List<Boyscout> list = new ArrayList<>();

    public void split() {
        System.out.println(list.stream()
                .sorted(Comparator.comparingInt(Boyscout::getAge))
                .collect(Collectors.groupingBy(Boyscout::getCommand)));
    }

    public void addBoyscout(Boyscout boyscout) {
        list.add(boyscout);
    }
}