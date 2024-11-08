package pl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Transaction {
    private final double amount;
    private String lastTransaction;
    private int nonce;

    public Transaction(double amount, String lastTransaction) {
        this.amount = amount;
        this.lastTransaction = lastTransaction;
        this.nonce = 0;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public void setLastTransaction(String lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    @Override
    public String toString() {
        return "\n{" + "\namount: " + String.format(java.util.Locale.US, "%.2f", amount) + ",\n" +
                "lastTransaction: \"" + lastTransaction + "\",\n" +
                "nonce: " + nonce + "\n}";
    }

    public String generateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = amount + lastTransaction + nonce;
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
