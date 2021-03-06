package test.com.isobar.isohealth;

import junit.framework.TestCase;

import com.isobar.isohealth.GraphConstants;
import com.isobar.isohealth.models.PersonalRecord;
import com.isobar.isohealth.wrappers.PersonalRecordWrapper;
import com.isobar.isohealth.wrappers.RunkeeperService;

public class PersonalRecordServiceTest extends TestCase {
	
	PersonalRecordWrapper personalRecordWrapper;
	
	protected void setUp() {
    	RunkeeperService runkeeperService = new RunkeeperService(GraphConstants.AUTH_CODE);
    	personalRecordWrapper = runkeeperService.personalRecordWrapper;
    }
	
	public void testGetPersonalRecords() {
		try {
			PersonalRecord[] personalRecord = personalRecordWrapper.getPersonalRecord();
			System.out.println("PersonalRecord: " + personalRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}