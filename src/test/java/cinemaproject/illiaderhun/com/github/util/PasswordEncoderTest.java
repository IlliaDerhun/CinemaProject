package cinemaproject.illiaderhun.com.github.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncoderTest {

    @Test
    public void encodeIt() {
        assertEquals("ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae"
                , PasswordEncoder.encodeIt("test123"));
    }
}