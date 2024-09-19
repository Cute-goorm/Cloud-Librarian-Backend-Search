package Cloud.Book_Search_Backend_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class BookSearchBackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSearchBackendApiApplication.class, args);
	}
}
