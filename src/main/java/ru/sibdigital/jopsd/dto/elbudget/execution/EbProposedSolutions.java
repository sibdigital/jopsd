package ru.sibdigital.jopsd.dto.elbudget.execution;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "proposedSolution"
})
public class EbProposedSolutions<S extends EbProposedSolution> {
    @XmlElement(name = "ProposedSolution", required = true)
    private S proposedSolution;

    public S getProposedSolution() {
        return proposedSolution;
    }

    public void setProposedSolution(S proposedSolution) {
        this.proposedSolution = proposedSolution;
    }
}
