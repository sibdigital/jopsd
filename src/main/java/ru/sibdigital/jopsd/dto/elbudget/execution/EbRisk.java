package ru.sibdigital.jopsd.dto.elbudget.execution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.oxm.annotations.XmlMarshalNullRepresentation;
import org.eclipse.persistence.oxm.annotations.XmlNullPolicy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "isSolved",
        "solveDate",
        "justification",
        "riskProbability",
        "reason",
        "temporary",
        "monetary",
        "fact",
        "expectedDate",
        "consequenceSum",
        "effect",
        "powerDecision",
        "proposedSolutions"
})
public class EbRisk {
    @XmlElement(name = "Name", required = true)
    private String name;

    @XmlElement(name = "IsSolved")
    private boolean isSolved;

    @XmlElement(name = "SolveDate")
    private XMLGregorianCalendar solveDate;

    @XmlElement(name = "Justification")
    private String justification;

    @XmlElement(name = "RiskProbability", required = true)
    private String riskProbability;

    @XmlElement(name = "Reason", required = true)
    private String reason;

    @XmlElement(name = "Temporary")
    private boolean temporary;

    @XmlElement(name = "Monetary")
    private boolean monetary;

    @XmlElement(name = "Fact")
    private boolean fact;

    @XmlElement(name = "ExpectedDate")
    private XMLGregorianCalendar expectedDate;

    @XmlElement(name = "ConsequenceSum", required = true, nillable = true)
    private BigDecimal consequenceSum;

    @XmlElement(name = "Effect", required = true, nillable = true)
    @XmlNullPolicy(emptyNodeRepresentsNull = true, nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE)
    private String effect;

    @XmlElement(name = "PowerDecision")
    private String powerDecision;

    @XmlElement(name = "ProposedSolutions")
    private List<? extends EbProposedSolutions> proposedSolutions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public XMLGregorianCalendar getSolveDate() {
        return solveDate;
    }

    public void setSolveDate(XMLGregorianCalendar solveDate) {
        this.solveDate = solveDate;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getRiskProbability() {
        return riskProbability;
    }

    public void setRiskProbability(String riskProbability) {
        this.riskProbability = riskProbability;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    public boolean isMonetary() {
        return monetary;
    }

    public void setMonetary(boolean monetary) {
        this.monetary = monetary;
    }

    public boolean isFact() {
        return fact;
    }

    public void setFact(boolean fact) {
        this.fact = fact;
    }

    public XMLGregorianCalendar getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(XMLGregorianCalendar expectedDate) {
        this.expectedDate = expectedDate;
    }

    public BigDecimal getConsequenceSum() {
        return consequenceSum;
    }

    public void setConsequenceSum(BigDecimal consequenceSum) {
        this.consequenceSum = consequenceSum;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getPowerDecision() {
        return powerDecision;
    }

    public void setPowerDecision(String powerDecision) {
        this.powerDecision = powerDecision;
    }

    public List<? extends EbProposedSolutions> getProposedSolutions() {
        return proposedSolutions;
    }

    public void setProposedSolutions(List<? extends EbProposedSolutions> proposedSolutions) {
        this.proposedSolutions = proposedSolutions;
    }
}
