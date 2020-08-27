
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	// Attributes

	Integer						numberOfNotices;
	Integer						numberOfTechnologyRecords;
	Integer						numberOfToolRecords;

	Double						minimumMinMoneyActiveInquiries;
	Double						maximumMinMoneyActiveInquiries;
	Double						avgMinMoneyActiveInquiries;
	Double						stddevMinMoneyActiveInquiries;

	Double						minimumMaxMoneyActiveInquiries;
	Double						maximumMaxMoneyActiveInquiries;
	Double						avgMaxMoneyActiveInquiries;
	Double						stddevMaxMoneyActiveInquiries;

	Double						minimumMinMoneyActiveOvertures;
	Double						maximumMinMoneyActiveOvertures;
	Double						avgMinMoneyActiveOvertures;
	Double						stddevMinMoneyActiveOvertures;

	Double						minimumMaxMoneyActiveOvertures;
	Double						maximumMaxMoneyActiveOvertures;
	Double						avgMaxMoneyActiveOvertures;
	Double						stddevMaxMoneyActiveOvertures;

	Double						invRoundPerEntrep;
	Double						appPerEntrep;
	Double						appPerInvestor;

	List<String>				techRecordsSectors;
	List<Long>					techRecordsBySector;

	List<String>				toolRecordsSectors;
	List<Long>					toolRecordsBySector;

	List<Integer>				techRecordsLicence;
	List<Integer>				toolRecordsLicence;

	List<String>				invRoundKinds;
	List<Long>					invRoundsByKind;

	List<String>				appStatus;
	List<Long>					appByStatus;

	List<Long>					pendingAppsPast15Days;
	List<Long>					acceptedAppsPast15Days;
	List<Long>					rejectedAppsPast15Days;

}
