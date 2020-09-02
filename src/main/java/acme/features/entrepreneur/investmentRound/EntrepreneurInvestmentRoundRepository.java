/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.configurations.Configuration;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.entrepreneur.id = ?1")
	Collection<InvestmentRound> findMyInvestmentRound(int id);

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select c from Configuration c")
	Configuration selectConfiguration();

	@Query("select a from Activity a where a.investmentRound.id = ?1")
	Collection<Activity> findActivityByInvestmentRound(int id);

	@Query("select a from Activity a where a.id = ?1")
	Activity findActivityById(int id);

	@Query("select count(a)>0 from Application a where a.investmentRound.id = ?1")
	boolean hasApplications(int id);

	@Query("select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findEntrepreneurById(int userAccountId);

	@Query("select count(i)>0 from InvestmentRound i where i.ticker = ?1")
	Boolean checkUniqueTicker(String ticker);

	@Query("select sum(a.budget.amount)=a.investmentRound.amount.amount from Activity a where a.investmentRound.id = ?1")
	boolean checkAmount(int id);

	@Query("select c.invRoundKinds from Configuration c")
	String findInvRoundKinds();

	@Query("select ar from AccountingRecord ar where ar.investmentRound.id = ?1 and ar.draft=false")
	Collection<AccountingRecord> findAllByInvestmentRoundId(int id);

}
