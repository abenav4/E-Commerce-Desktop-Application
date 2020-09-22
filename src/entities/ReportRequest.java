package entities;

import java.io.Serializable;

public class ReportRequest extends Request implements Serializable {
    private String contentReported;
    private String reportedPerson;

    /**
     * Class constructor.
     * A message sent to the Admins from an account that reports another message
     * @param content the reason that the sender is reporting the message
     * @param sender the reporter's username
     * @param contentReported the content that is being reported
     * @param reportedPerson the username of the account that created the message with the contentReported
     */
    public ReportRequest(String content, String sender, String contentReported, String reportedPerson){
        super(content, new String[]{"Ban", "Ignore", "Ban Reporter"}, sender);
        this.contentReported = contentReported;
        this.reportedPerson = reportedPerson;
    }

    /**
     * Getter of the username person being reported
     * @return the username of the person being reported
     */
    public String getReportedPerson(){
        return reportedPerson;
    }

    /**
     * Getter of the person's username who is reporting the other message
     * @return the person's username who is reporting the other message
     */
    public String getReporter(){
        return super.getSender();
    }

    /**
     * Returns a string representation of the message
     * @return the content and user of the message in a string representation
     */
    @Override
    public String toString() {
        return String.format("Reporting a User\nReason of Reporting: \n%s\nThe Reported Content: " +
                        "\n%s\nPerson being Reported: \n%s",
                getContent(),contentReported,reportedPerson);
    }
}
