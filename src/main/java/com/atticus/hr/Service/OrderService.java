package com.atticus.hr.Service;

import com.atticus.hr.domain.Order;

public interface OrderService {
  public Iterable<Order> findAll();
}
