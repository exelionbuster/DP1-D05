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

import acme.entities.activities.Activity;
import acme.entities.configurations.Configuration;
import acme.entities.investmentRounds.InvestmentRound;
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

}
