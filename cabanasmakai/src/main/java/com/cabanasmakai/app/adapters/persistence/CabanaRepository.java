package com.cabanasmakai.app.adapters.persistence;

import com.cabanasmakai.app.domain.Cabanas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabanaRepository extends JpaRepository<Cabanas, Long> {
}
