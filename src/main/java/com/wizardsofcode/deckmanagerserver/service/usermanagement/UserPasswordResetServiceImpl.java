package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserPasswordReset;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserPasswordResetDAO;
import com.wizardsofcode.deckmanagerserver.model.validation.exception.InvalidResetTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserPasswordResetServiceImpl implements UserPasswordResetService {

    @Autowired
    private UserPasswordResetDAO passwordResetDAO;

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Override
    public boolean resetPassword(UserPasswordReset passwordReset) throws InvalidResetTokenException {

        if (passwordReset != null) {
            Date today = new Date();
            if (today.compareTo(passwordReset.getExpiresAt()) >= 0) {
                throw new InvalidResetTokenException("The reset token supplied has expired");
            }
            if (passwordReset.isUsed())
                throw new InvalidResetTokenException("This reset token has already been used");
            userManagementService.updatePasswordFromReset(passwordReset);
            passwordResetDAO.delete(passwordReset.getPasswordResetID());
        } else {
            throw new InvalidResetTokenException("The supplied token does not exist or is invalid");
        }
        return true;
    }

    @Override
    public UserPasswordReset createPasswordresetRequest(User user) {
        UserPasswordReset passwordReset = new UserPasswordReset(user);
        passwordReset = passwordResetDAO.save(passwordReset);
        emailNotificationService.sendResetPasswordMessage(user,passwordReset);
        return passwordReset;
    }

    @Override
    public UserPasswordReset retrievePasswordRequest(UUID uuid) {
        return passwordResetDAO.findOne(uuid);
    }

    @Override
    public boolean checkIfExists(User user) {
        if(passwordResetDAO.countByUser(user) > 0){
            return true;
        }

        return false;
    }
}
