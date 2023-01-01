package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaceRepository extends IPlaceRepositoryExtraMethods, JpaRepository<Place, Long> {
}
