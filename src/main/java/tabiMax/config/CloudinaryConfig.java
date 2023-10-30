package tabiMax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	@Bean
	public Cloudinary cloud() {
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
		  "cloud_name", "dzumda5fa",
		  "api_key", "959631823469922",
		  "api_secret", "NFULtoFFUlC6qCPN1BSiRw5KUGk","secure",true));
		return cloudinary;
	}
}
