
package acme.entities.configurations;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				spamWords;

	@Range(min = 0, max = 100)
	private Double				threshold;

	@NotBlank
	private String				activitySectors;

	@NotBlank
	private String				invRoundKinds;

}
