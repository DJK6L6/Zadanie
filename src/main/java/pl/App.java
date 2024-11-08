package pl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    private static final Random random = new Random();

    public static void main(String[] args) {
        String initialHash = randomHash();
        System.out.print("Initial hash: \"" + initialHash + "\"");

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            double amount = 10 + (1000 - 10) * random.nextDouble();
            Transaction transaction = new Transaction(amount, initialHash);

            int nonce = 0;
            String hash;
            do {
                hash = transaction.generateHash();
                nonce++;
                transaction.setNonce(nonce);
            } while (!hash.endsWith("00000"));

            transactions.add(transaction);
            transaction.setLastTransaction(hash);
        }

        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }

    private static String randomHash() {
        StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < 32; i++) {
            int randomByte = random.nextInt(256);
            sb.append(String.format("%02x", randomByte));
        }
        return sb.toString();
    }
}
