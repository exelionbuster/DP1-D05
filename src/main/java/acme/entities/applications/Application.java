
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	//	Serialisation identifier ------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ---------------------------------------------

	@Column(unique = true)
	@NotBlank
	private String				ticker;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationDate;

	@NotBlank
	private String				statement;

	@NotBlank
	@Pattern(regexp = "^(pending|accepted|rejected)$")
	private String				status;

	@NotNull
	@Valid
	private Money				offer;

	private String				justification;

	// Relationships ------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investor			investor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound		investmentRound;

}
