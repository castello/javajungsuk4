package pkgB;
import pkgA.InputService;
import javax.swing.*;
import java.util.logging.Logger;

public class InputServiceImpl implements InputService {
    private static final Logger logger = 
						Logger.getLogger(InputServiceImpl.class.getName());

    public String showInput(String msg) {
        logger.info("msg = "+msg);
        return JOptionPane.showInputDialog(msg);
    }
}
