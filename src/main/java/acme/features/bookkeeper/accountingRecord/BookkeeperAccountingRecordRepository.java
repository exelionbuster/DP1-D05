
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select ar from AccountingRecord ar where ar.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select ar from AccountingRecord ar where ar.investmentRound.id = ?1 and ( ar.draft=false or ar.bookkeeper.id = ?2)")
	Collection<AccountingRecord> findManyByInvestmentRound(int invRId, int bookkeeperId);

	@Query("select b from Bookkeeper b where b.id = ?1")
	Bookkeeper findOneBookkeeperById(int id);

}
