package com.atticus.hr.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atticus.hr.domain.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client,Long> {

}
