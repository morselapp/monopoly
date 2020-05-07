import com.fasterxml.jackson.databind.ObjectMapper;
import monopoly.UserInput;

import java.util.Scanner;
import java.util.logging.Logger;

import static org.mockito.Mockito.mock;

public class BaseTest {
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected Logger log = Logger.getLogger(BaseTest.class.getName());
    protected Scanner scanner = new Scanner(System.in);
    protected UserInput userInput = mock(UserInput.class);
}
