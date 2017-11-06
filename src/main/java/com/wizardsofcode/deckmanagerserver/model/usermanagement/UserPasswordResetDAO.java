package com.wizardsofcode.deckmanagerserver.model.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPasswordResetDAO extends JpaRepository<UserPasswordReset, UUID> {

    int countByUser(User user);

}
