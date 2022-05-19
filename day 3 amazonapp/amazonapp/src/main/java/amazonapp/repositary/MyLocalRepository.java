package amazonapp.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazonapp.bean.Product;

// to make database operation we are using jpa repository
/* JpaRepository is a JPA (Java Persistence API) specific extension of Repository. 
 * It contains the full API of CrudRepository and PagingAndSortingRepository.
 *  So it contains API for basic CRUD operations and also API
 *   for pagination and sorting.*/
@Repository
public interface MyLocalRepository extends JpaRepository<Product,Long> {

}
