import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller", "model", "repository", "service"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
