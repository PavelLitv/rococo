package guru.qa.rococo.data.repository;

import guru.qa.rococo.data.CountryEntity;
import guru.qa.rococo.model.CountryJson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    CountryEntity getById(UUID uuid);


}
