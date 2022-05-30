package amazonapp.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazonapp.bean.Product;

@Repository
public interface MyLocalRepository extends JpaRepository<Product, Long> {

}
