package co.com.mutantes.mutantes.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MutantService {
     public ResponseEntity<Object> isMutant(List<String> dna);
}
