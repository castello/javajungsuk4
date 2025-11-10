package pkgC;
import pkgA.InputService;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = 
							Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        InputService service = InputService.getInstance();
// InputServiceImpl service = new InputServiceImpl(); // 에러. 노출되지 않은 패키지 
	
        String answer = service.showInput("What is your favorite fruit?");
        logger.info("answer = " + answer);
    }
}
