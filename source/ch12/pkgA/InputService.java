package pkgA;
import pkgB.InputServiceImpl;

public interface InputService {
    public String showInput(String msg);
    public static InputService getInstance() { // 디폴트 메서드
        return new InputServiceImpl();
    }
}
