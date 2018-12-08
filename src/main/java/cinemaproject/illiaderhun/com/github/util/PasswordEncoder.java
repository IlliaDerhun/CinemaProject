package cinemaproject.illiaderhun.com.github.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    private static final Logger LOGGER = Logger.getLogger(PasswordEncoder.class.getSimpleName());

    public static String encodeIt(String base) {
        LOGGER.info("method encodeIt start with base: " + base);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            LOGGER.info("encodeIt return: " + hexString.toString());
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("encodeIt caught NoSuchAlgorithmException");
            LOGGER.trace(e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encodeIt caught UnsupportedEncodingException");
            LOGGER.trace(e);
        }
        return null;
    }
}
