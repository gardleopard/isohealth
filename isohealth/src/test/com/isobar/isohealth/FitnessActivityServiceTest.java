package test.com.isobar.isohealth;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.isobar.isohealth.GraphConstants;
import com.isobar.isohealth.models.FitnessActivity;
import com.isobar.isohealth.models.FitnessActivityFeed;
import com.isobar.isohealth.models.FitnessActivityFeed.Item;
import com.isobar.isohealth.models.NewFitnessActivity;
import com.isobar.isohealth.models.WGS84;
import com.isobar.isohealth.wrappers.FitnessActivityWrapper;
import com.isobar.isohealth.wrappers.RunkeeperService;

public class FitnessActivityServiceTest extends TestCase {

	FitnessActivityWrapper fitnessActivityWrapper;
	FitnessActivityFeed fitnessActivityFeed;
	
	protected void setUp() {
    	RunkeeperService runkeeperService = new RunkeeperService(GraphConstants.AUTH_CODE);
    	fitnessActivityWrapper = runkeeperService.fitnessActivityWrapper;
    	try {
    		fitnessActivityFeed = fitnessActivityWrapper.getFitnessActivityFeed();
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
	
	public void testGetFitnessActivityFeed() {
		try {
//			FitnessActivityFeed fitnessActivityFeed = FitnessActivityService.getFitnessActivityFeed(GraphConstants.AUTH_CODE);
			System.out.println("FitnessActivityFeed: " + fitnessActivityFeed.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testGetFitnessActivity() {
		try {
//			FitnessActivityFeed fitnessActivityFeed = FitnessActivityService.getFitnessActivityFeed(GraphConstants.AUTH_CODE);
			List<FitnessActivity> fitnessActivities =  new ArrayList<FitnessActivity>();
			for (Item item : fitnessActivityFeed.getItems()) {
				FitnessActivity activity = fitnessActivityWrapper.getFitnessActivity(item.getUri());
				System.out.println("FitnessActivity: " + activity.toString());
				fitnessActivities.add(activity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testCreateNewFitnessActivity() {
		try {
		  NewFitnessActivity activity = new NewFitnessActivity();
		  activity.setType("Running");
		  activity.setStart_time("Sat, 1 Jan 2011 00:00:00");
		  activity.setNotes("My first late-night run");
		  WGS84[] path = {new WGS84(), new WGS84()};
		  path[0].setTimestamp(0d);
		  path[0].setAltitude(0d);
		  path[0].setLongitude(-70.95182336425782);
		  path[0].setLatitude(42.312620297384676);
		  path[0].setType("start");
		  path[1].setTimestamp(8d);
		  path[1].setAltitude(0d);
		  path[1].setLongitude(-70.95255292510987);
		  path[1].setLatitude(42.31230294498018);
		  path[1].setType("end");	
		  activity.setPath(path);
		  activity.setPost_to_facebook(false);
		  activity.setPost_to_twitter(false);	  
		  
		  fitnessActivityWrapper.createFitnessActivity(activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testUpdateFitnessActivity() {
		try {
//			FitnessActivityFeed fitnessActivityFeed = FitnessActivityService.getFitnessActivityFeed(GraphConstants.AUTH_CODE);
			for (Item item : fitnessActivityFeed.getItems()) {
				FitnessActivity activity = fitnessActivityWrapper.getFitnessActivity(item.getUri());
				System.out.println("Activity: " + activity);
				activity.setNotes("Updated notes");
				activity = fitnessActivityWrapper.updateFitnessActivity(activity);
				System.out.println("Updated activity: " + activity);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
	
	public void testDeleteFitnessActivity() {
		try {
			FitnessActivityFeed fitnessActivityFeed = fitnessActivityWrapper.getFitnessActivityFeed();
			for (Item item : fitnessActivityFeed.getItems()) {
				FitnessActivity activity = fitnessActivityWrapper.getFitnessActivity(item.getUri());
				fitnessActivityWrapper.deleteFitnessActivity(activity.getUri());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
}
