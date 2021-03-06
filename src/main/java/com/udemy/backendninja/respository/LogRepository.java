package com.udemy.backendninja.respository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.backendninja.entity.Log;

/**
 * The Interface LogRepository.
 */
@Repository("logRepository")
public interface LogRepository  extends JpaRepository<Log, Serializable>{ // lo usaremos en el RequestTimeInterceptor

}
