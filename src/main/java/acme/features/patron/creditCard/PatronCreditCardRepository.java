
package acme.features.patron.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronCreditCardRepository extends AbstractRepository {

	@Query("select cc from CreditCard cc")
	Collection<CreditCard> findMany();

	@Query("select cc from CreditCard cc where cc.id = ?1")
	CreditCard findOneById(int id);

	@Query("select p from Patron p where p.userAccount.id = ?1")
	Patron findPatronByUserAccountId(int id);

}
