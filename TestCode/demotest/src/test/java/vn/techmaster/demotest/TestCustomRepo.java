package vn.techmaster.demotest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.demotest.repository.EmployeeRepo;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;
@DataJpaTest
public class TestCustomRepo {
    @Autowired private EntityManager em;
    @Autowired EmployeeRepo employeeRepo;
    @Test
    public void findByEmailandLastName(){
            
    }
}
