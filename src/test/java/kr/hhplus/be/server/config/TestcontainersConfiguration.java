package kr.hhplus.be.server.config;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

@Configuration
@Profile("mysql")
class TestcontainersConfiguration {

	public static final MySQLContainer<?> MYSQL_CONTAINER;

	static {
		try {
			if (isDockerAvailable()) {
				MYSQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
						.withDatabaseName("hhplus")
						.withUsername("test")
						.withPassword("test");
				MYSQL_CONTAINER.start();

				System.setProperty("spring.datasource.url", MYSQL_CONTAINER.getJdbcUrl() + "?characterEncoding=UTF-8&serverTimezone=UTC");
				System.setProperty("spring.datasource.username", MYSQL_CONTAINER.getUsername());
				System.setProperty("spring.datasource.password", MYSQL_CONTAINER.getPassword());
			} else {
				MYSQL_CONTAINER = null;
				System.out.println("Docker not available, skipping TestContainers");
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize TestContainers", e);
		}
	}

	private static boolean isDockerAvailable() {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			if (osName.contains("window")) {
				return false;
			}

			ProcessBuilder processBuilder = new ProcessBuilder("docker", "--version");
			Process process = processBuilder.start();
			int exitCode = process.waitFor();

			if (exitCode == 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	@PreDestroy
	public void preDestroy() {
		if (MYSQL_CONTAINER.isRunning()) {
			MYSQL_CONTAINER.stop();
		}
	}
}