package tabiMax.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import tabiMax.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends Repository<ConfirmationToken, Long> {
	void save(ConfirmationToken confirmationToken);

	Optional<ConfirmationToken> findByToken(String token);

	@Transactional
	@Modifying
	@Query("UPDATE ConfirmationToken c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
	void updateConfirmAt(String token, LocalDateTime confirmAt);
}
