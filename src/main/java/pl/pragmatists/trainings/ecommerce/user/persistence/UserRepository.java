package pl.pragmatists.trainings.ecommerce.user.persistence;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    User save(User product);

    User findOne(long userId);
}
