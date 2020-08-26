
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select distinct ar.investmentRound from AccountingRecord ar where ar.bookkeeper.id=?1")
	Collection<InvestmentRound> findInvolvedInvestmentRound(int id);

	@Query("select distinct ir from InvestmentRound ir where not exists (select ar from AccountingRecord ar where ar.investmentRound = ir and ar.bookkeeper.id=?1)")
	Collection<InvestmentRound> findNotInvolvedInvestmentRound(int id);

	@Query("select count(a)>0 from Activity a where a.investmentRound.id=?1")
	boolean hasActivities(int id);

	@Query("select count(ar)>0 from AccountingRecord ar where ar.investmentRound.id=?1")
	boolean hasAccountingRecords(int id);

	@Query("select ir from InvestmentRound ir where ir.id = ?1")
	InvestmentRound findOneById(int id);
}
