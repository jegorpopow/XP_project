import java.util.*; 

public class Roulette {
    private Random rand = new Random();
    private int MAX_ITER = 1000;
    private Map<String,List<Integer>> bets = new HashMap<>();
    private Map<String,Integer> coefs = new HashMap<>();
    private Map<String,Integer> balances = new HashMap<>();
    private List<String> colors = new ArrayList<Integer>(37);

    {
        colors[0] = "GREEN";
        List<String> br = Arrays.asList("RED", "BLACK");
        int idx = 0;
        for (int i = 1; i < 37; i++) {
            colors[i] = br[idx];
            if (i != 10 && i != 18 && i != 28) {
                idx = 1 - idx;
            }
        }
    }

    public int GetNumber() {
        int iter = 0;
        int num = rand.nextInt(MAX_ITER);
        while (num < iter) {
            num = rand.nextInt(MAX_ITER);
            iter++;
        }
        return num;
    }

    private List<Integer> ConvertBet(String type, Integer num) {
        List<Integer> arr = new List<>;
        if (type == "RED" || type == "BLACK" || type == "GREEN") {
            arr = IntStream.range(0, 37).filter(i -> colors.get(i) == type).toArray();
        } else if (type == "SINGLE") {
            arr = Arrays.asList(num);
        } else if (type == "SPLIT HORIZONTAL") {
            arr = Arrays.asList(num, num + 1);
        } else if (type == "SPLIT VERTICAL") {
            arr = Arrays.asList(num, num + 3);
        } else if (type == "STREET") {
            arr = Arrays.asList(num, num + 1, num + 2);
        } else if (type == "SQUARE") {
            arr = Arrays.asList(num, num + 1, num + 3, num + 4);
        } else if (type == "DOUBLE STREET") {
            arr = Arrays.asList(num, num + 1, num + 2, num + 3, num + 4, num + 5);
        } else if (type == "BUSKET") {
            arr = Arrays.asList(0, num, num + 1);
        } else if (type == "FIRST FOUR") {
            arr = Arrays.asList(0, 1, 2, 3);
        } else if (type == "HALF") {
            if (num == 1) {
                IntStream.range(1, 19).toArray();
            } else {
                IntStream.range(19, 37).toArray();
            }
        } else if (type == "EVEN") {
            arr = IntStream.range(1, 37).filter(i -> i % 2 == 0).toArray();
        } else if (type == "ODD") {
            arr = IntStream.range(1, 37).filter(i -> i % 2 == 1).toArray();
        } else if (type == "DOZEN") {
            arr = IntStream.range(num, num + 12).toArray();
        } else if (type == "COLUMN") {
            arr = IntStream.range(1, 37).filter(i -> i % 3 == num).toArray();
        }
        return arr;
    }

    private List<Integer> GetCoef(String type, Integer num) {
    }

    public void SetBet(String user, String type, Integer number) {
        bets[user] = ConvertBet(type, num);
        coefs[user] = bets[user].
    }

}