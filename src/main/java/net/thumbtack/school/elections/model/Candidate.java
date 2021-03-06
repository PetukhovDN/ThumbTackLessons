package net.thumbtack.school.elections.model;

import net.thumbtack.school.elections.dto.request.AddCandidateRequest;

import java.io.Serializable;
import java.util.Objects;

public class Candidate implements Serializable {

    private static final long serialVersionUID = -3872129141080909473L;

    private String firstName;
    private String lastName;
    private String fullName;
    private boolean agreement;
    private CandidateProgram candidateProgram;

    public Candidate(AddCandidateRequest candidateDtoRequest) {
        this.firstName = candidateDtoRequest.getFirstName();
        this.lastName = candidateDtoRequest.getLastName();
        this.fullName = this.firstName + " " + this.lastName;
        this.agreement = false;
        this.candidateProgram = new CandidateProgram();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public CandidateProgram getCandidateProgram() {
        return candidateProgram;
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

