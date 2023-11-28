import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();

        // Fill the list with random positive and negative values
        for (int i = 0; i < 10; i++) {
            numbers.add(random.nextInt(201) - 100); // Generate values between -100 and 100
        }

        // Use stream to filter and modify the numbers
        List<Integer> result = numbers.stream()
                .filter(n -> n % 3 == 0) // Filter numbers divisible by 3
                .map(n -> Math.abs(n))  // Remove the negative sign if present
                .collect(Collectors.toList());

        // Display the result
        System.out.println(result);
    }
}
