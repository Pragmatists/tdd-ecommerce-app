package pl.pragmatists.trainings.ecommerce.user.persistence;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
