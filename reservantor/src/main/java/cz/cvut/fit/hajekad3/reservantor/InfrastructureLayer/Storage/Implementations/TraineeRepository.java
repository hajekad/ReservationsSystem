package cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Implementations;

import cz.cvut.fit.hajekad3.reservantor.DomainLayer.Trainee;
import cz.cvut.fit.hajekad3.reservantor.InfrastructureLayer.Storage.Abstractions.ITraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class TraineeRepository implements ITraineeRepository {
    @Autowired
    @Lazy
    ITraineeRepository iTraineeRepository;

    private JpaRepository<Trainee, Long> jpaRepository;

    public TraineeRepository() {
    }
    @Override
    public Trainee saveTrainee(Trainee trainee) {
        return jpaRepository.save(trainee);
    }

//    @Override
//    public Trainee getTrainee(Long id) {
//        Optional<Trainee> ret = jpaRepository.findById(id);;
//
//        if(ret.isEmpty())
//            throw new NoSuchElementException("Id is not in the database.");
//
//        return ret.get();
//    }

    @Override
    public Trainee getTrainee(Long id) {
        Trainee ret = new Trainee();
        ret.fill();
        return ret;
    }
}
