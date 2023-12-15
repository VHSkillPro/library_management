package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	public static byte[] getByte(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return md.digest(input.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getString(String input) {
		BigInteger number = new BigInteger(1, getByte(input));
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
		return hexString.toString();
	}
}
