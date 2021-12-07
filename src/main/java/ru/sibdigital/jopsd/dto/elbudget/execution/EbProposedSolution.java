package ru.sibdigital.jopsd.dto.elbudget.execution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "executionPeriod",
        "isCompleted",
        "description",
        "comment"
})
public class EbProposedSolution {
    @XmlElement(name = "ExecutionPeriod", required = true)
    protected XMLGregorianCalendar executionPeriod;

    @XmlElement(name = "IsCompleted")
    protected boolean isCompleted;

    @XmlElement(name = "Description", required = true)
    protected String description;

    @XmlElement(name = "Comment", nillable = true)
    protected String comment;

    public XMLGregorianCalendar getExecutionPeriod() {
        return executionPeriod;
    }

    public void setExecutionPeriod(XMLGregorianCalendar executionPeriod) {
        this.executionPeriod = executionPeriod;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
