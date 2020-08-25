
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findApplicationById(int id);

	@Query("select a from Application a where a.investor.id = ?1")
	Collection<Application> findManyByApplicationId(int investorId);

	@Query("select i from Investor i where i.userAccount.id = ?1")
	Investor findOneInvestorByUserAccountId(int id);

	@Query("select count(a)>0 from Application a where a.ticker = ?1")
	Boolean checkUniqueTicker(String ticker);

}
