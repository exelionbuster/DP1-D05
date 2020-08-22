
package acme.features.anonymous.technologyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTechnologyRecordRepository extends AbstractRepository {

	@Query("select cr from TechnologyRecord cr")
	Collection<TechnologyRecord> findMany();

	@Query("select cr from TechnologyRecord cr where cr.id = ?1")
	TechnologyRecord findOneById(int id);
}
