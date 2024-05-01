import org.example.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

	@Test
	public void getValidChoiceTest() {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("1".getBytes());

		Scanner scanner = new Scanner(byteArrayInputStream);

		assertEquals(Main.getValidatedChoice(scanner), 1);
	}
}
