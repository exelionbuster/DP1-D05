
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	//	Serialisation identifier ---------------------

	private static final long	serialVersionUID	= 1L;

	//	Attribute ------------------------------------

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotBlank
	private String				rookieGoal;

	@Valid
	private Money				rookieReward;

	@NotBlank
	private String				averageGoal;

	@Valid
	private Money				averageReward;

	@NotBlank
	private String				expertGoal;

	@Valid
	private Money				expertReward;
}
