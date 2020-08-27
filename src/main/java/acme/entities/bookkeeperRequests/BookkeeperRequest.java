
package acme.entities.bookkeeperRequests;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookkeeperRequest extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firmName;

	@NotBlank
	private String				responsibilityStatement;

	private boolean				accepted;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	Authenticated				authenticated;

}
