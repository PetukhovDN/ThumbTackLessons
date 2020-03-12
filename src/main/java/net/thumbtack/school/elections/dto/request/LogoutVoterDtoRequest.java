package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.exceptions.ElectionsException;

import java.util.UUID;

public class LogoutVoterDtoRequest {
    private UUID token;

    public LogoutVoterDtoRequest(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void validate() throws ElectionsException {
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
    }
}
