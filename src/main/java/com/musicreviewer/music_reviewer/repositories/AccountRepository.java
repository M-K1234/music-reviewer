    package com.musicreviewer.music_reviewer.repositories;

    import java.util.Optional;

    //import java.util.Optional;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import com.musicreviewer.music_reviewer.entities.Account;


    public interface AccountRepository extends JpaRepository<Account, Integer> {
        @Query("SELECT a FROM Account a WHERE a.login.email = :email")
        Optional<Account> findByLoginEmail(@Param("email") String email);
    }
