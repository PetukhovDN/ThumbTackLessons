package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Proposal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProposalDaoImpl implements ProposalDao {

    @Override
    public UUID makeProposal(Proposal proposal, UUID token) throws ElectionsException {
        if (Database.getInstance().getValidTokensSet().contains(token)) {
            Database.getInstance().getProposalList().add(proposal);
            return token;
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public UUID addRatingForProposal(String proposal, int rate, UUID token) throws ElectionsException {
        if (Database.getInstance().getValidTokensSet().contains(token)) {
            for (Proposal proposalFromBase : Database.getInstance().getProposalList()) {
                if (proposalFromBase.getProposalInfo().equals(proposal)) {
                    if (proposalFromBase.getAuthorToken().equals(token)) {
                        throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
                    }
                    else {
                        proposalFromBase.getRating().put(token, rate);
                        return token;
                    }
                }
            }
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public UUID removeRatingFromProposal(String proposal, UUID token) throws ElectionsException {
        if (Database.getInstance().getValidTokensSet().contains(token)) {
            for (Proposal proposalFromBase : Database.getInstance().getProposalList()) {
                if (proposalFromBase.getProposalInfo().equals(proposal)) {
                    if (proposalFromBase.getAuthorToken().equals(token)) {
                        throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
                    }
                    else {
                        proposalFromBase.getRating().remove(token);
                        return token;
                    }
                }
            }
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public Map<String, Double> getAllProposals(UUID token) throws ElectionsException {
        Map<String, Double> results = new HashMap<>();
        if (Database.getInstance().getValidTokensSet().contains(token)) {
            for (Proposal proposal : Database.getInstance().getProposalList()) {
                double sumRatings = 0.0;
                int count = 0;
                for (Integer rating : proposal.getRating().values()) {
                    sumRatings += rating;
                    count++;
                }
                results.put(proposal.getProposalInfo(), sumRatings / count); //в map добавляется запись с ключом Предложением и значением Средней оценкой
            }
            return results;
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }
}