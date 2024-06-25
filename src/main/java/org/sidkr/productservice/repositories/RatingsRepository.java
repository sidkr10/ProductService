package org.sidkr.productservice.repositories;

import org.sidkr.productservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingsRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findRatingById(Long productId);
}
