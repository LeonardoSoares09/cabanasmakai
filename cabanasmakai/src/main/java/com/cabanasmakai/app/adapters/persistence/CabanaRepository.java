package com.cabanasmakai.app.adapters.persistence;

import com.cabanasmakai.app.domain.Cabanas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabanaRepository extends JpaRepository<Cabanas, Long> {
}
