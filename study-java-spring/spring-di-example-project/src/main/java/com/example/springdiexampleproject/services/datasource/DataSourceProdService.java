package com.example.springdiexampleproject.services.datasource;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service("dataSourceService")
public class DataSourceProdService implements DataSourceService {


  @Override
  public String value() {
    return "prod";
  }

}
