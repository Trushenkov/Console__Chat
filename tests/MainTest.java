import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MainTest {

    @Test
    public void main() {

        String actual = "serVer";

        char answer = Character.toLowerCase(actual.charAt(0));

        char expected = 's';

        assertEquals(expected,answer);
    }
}