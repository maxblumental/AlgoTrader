import ru.sbt.exchange.AlgoLocalRunner;
import ru.sbt.exchange.client.RandomAlgoStrategy;

/**
 * Created by Maxim on 11/20/2015.
 */
public class Test {
    public static void main(String[] args) {
        AlgoLocalRunner algoLocalRunner = new AlgoLocalRunner();

        algoLocalRunner.runServer();

        algoLocalRunner.runClient("first", new RandomAlgoStrategy());
        algoLocalRunner.runClient("second", new RandomAlgoStrategy());
    }
}
