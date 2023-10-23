import java.util.*;
import java.util.stream.IntStream;

public class Roulette {
    private Random rand = new Random();
    private int MAX_ITER = 1000;
    private Map<String,List<Integer>> bets = new HashMap<>();
    private Map<String,Integer> coefs = new HashMap<>();
    private Map<String,Integer> balances = new HashMap<>();
    private String colors[] = new String[37];
    {
        colors[0]= "GREEN";
        List<String> br = Arrays.asList("RED", "BLACK");
        int idx = 0;
        for (int i = 1; i < 37; i++) {
            colors[i] = br.get(idx);
            if (i != 10 && i != 18 && i != 28) {
                idx = 1 - idx;
            }
        }
    }

    public int GetNumber() {
        int iter = 0;
        int num = rand.nextInt(MAX_ITER);
        while (iter < num) {
            num = rand.nextInt(MAX_ITER);
            iter++;
        }
        return num;
    }

    private List<Integer> ConvertBet(String type, Integer num) {
        List<Integer> arr = new ArrayList<>();
        if (type.equals("RED") || type.equals("BLACK") || type.equals("GREEN")) {
            arr = IntStream.range(0, 37).filter(i -> colors[i].equals(type)).boxed().toList();;
        } else if (type.equals("SINGLE")) {
            arr = Collections.singletonList(num);
        } else if (Objects.equals(type, "SPLIT HORIZONTAL")) {
            arr = Arrays.asList(num, num + 1);
        } else if (type.equals("SPLIT VERTICAL")) {
            arr = Arrays.asList(num, num + 3);
        } else if (type.equals("STREET")) {
            arr = Arrays.asList(num, num + 1, num + 2);
        } else if (type.equals("SQUARE")) {
            arr = Arrays.asList(num, num + 1, num + 3, num + 4);
        } else if (type.equals("DOUBLE STREET")) {
            arr = Arrays.asList(num, num + 1, num + 2, num + 3, num + 4, num + 5);
        } else if (type.equals("BUSKET")) {
            arr = Arrays.asList(0, num, num + 1);
        } else if (type.equals("FIRST FOUR")) {
            arr = Arrays.asList(0, 1, 2, 3);
        } else if (type.equals("HALF")) {
            if (num == 1) {
                arr = IntStream.range(1, 19).boxed().toList();;
            } else {
                arr = IntStream.range(19, 37).boxed().toList();;
            }
        } else if (type.equals("EVEN")) {
            arr = IntStream.range(1, 37).filter(i -> i % 2 == 0).boxed().toList();
        } else if (type.equals("ODD")) {
            arr = IntStream.range(1, 37).filter(i -> i % 2 == 1).boxed().toList();;
        } else if (type.equals("DOZEN")) {
            arr = IntStream.range(num, num + 12).boxed().toList();;
        } else if (type.equals("COLUMN")) {
            arr = IntStream.range(1, 37).filter(i -> i % 3 == num).boxed().toList();;
        }
        return arr;
    }

    private List<Integer> GetCoef(String type, Integer num) {
        return new ArrayList<>();
    }

    public void SetBet(String user, String type, Integer number) {
        bets.put(user, ConvertBet(type, number));
    }
}