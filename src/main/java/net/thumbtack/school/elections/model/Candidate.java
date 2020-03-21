package net.thumbtack.school.elections.model;

import net.thumbtack.school.elections.dto.request.candidateRequests.AddCandidateDtoRequest;

import java.io.Serializable;
import java.util.Objects;

public class Candidate implements Serializable {

    private static final long serialVersionUID = -3872129141080909473L;

    private String firstName;
    private String lastName;

    public Candidate(AddCandidateDtoRequest candidateDtoRequest) {
        this.firstName = candidateDtoRequest.getFirstName();
        this.lastName = candidateDtoRequest.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidate)) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return Objects.equals(firstName, candidate.firstName) &&
                Objects.equals(lastName, candidate.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

