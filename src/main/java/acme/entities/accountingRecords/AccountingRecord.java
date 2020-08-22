
package acme.entities.accountingRecords;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountingRecord extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	private boolean				draft;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationDate;

	@NotBlank
	private String				body;


	// Transient

	@Transient
	public String getStatus() {

		String res = "published";
		if (this.isDraft()) {
			res = "draft";
		}

		return res;

	}


	// Relaciones

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Bookkeeper		bookkeeper;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound	investmentRound;

}
