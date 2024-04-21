package cuit9622.dms.gateway.repository;

import cuit9622.dms.gateway.eneity.TokenStore;
import org.springframework.data.repository.CrudRepository;

public interface TokenStoreRepository extends CrudRepository<TokenStore, Long> {
    TokenStore findByUserId(long userId);
}
