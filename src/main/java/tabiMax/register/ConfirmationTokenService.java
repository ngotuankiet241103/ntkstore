package tabiMax.register;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tabiMax.entity.ConfirmationToken;
import tabiMax.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken confirmationToken) {
		confirmationTokenRepository.save(confirmationToken);
	}
	public Optional<ConfirmationToken> findByToken(String token) {
		
		return confirmationTokenRepository.findByToken(token);
	}
	public void setConfirmedAt(String token) {
		LocalDateTime confirmAt = LocalDateTime.now();
		confirmationTokenRepository.updateConfirmAt(token, confirmAt);
		
	}
}
