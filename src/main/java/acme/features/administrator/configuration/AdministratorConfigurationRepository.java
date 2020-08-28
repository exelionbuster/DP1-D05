
package acme.features.administrator.configuration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigurationRepository extends AbstractRepository {

	@Query("select c from Configuration c")
	Configuration findOne();

	@Query("select c.invRoundKinds from Configuration c")
	String findInvRoundKinds();

	@Query("select c.spamWords from Configuration c")
	String findSpamWords();

	@Query("select c.threshold from Configuration c")
	Double findSpamThreshold();
}
