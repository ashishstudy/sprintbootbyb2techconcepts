package in.ashishgoyal.springrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashishgoyal.springrestapi.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
