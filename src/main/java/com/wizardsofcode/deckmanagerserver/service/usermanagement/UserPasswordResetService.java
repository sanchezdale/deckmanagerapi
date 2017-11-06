package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserPasswordReset;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.InvalidResetTokenException;

import java.util.UUID;

public interface UserPasswordResetService {

    boolean resetPassword(UserPasswordReset passwordReset) throws InvalidResetTokenException;

    UserPasswordReset createPasswordresetRequest(User user);

    UserPasswordReset retrievePasswordRequest(UUID uuid);

    boolean checkIfExists(User user);


}
