package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoachRepository extends ICoachRepositoryExtraMethods, JpaRepository<Coach, Long> {
}
