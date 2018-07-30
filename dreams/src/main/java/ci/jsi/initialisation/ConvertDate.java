package ci.jsi.initialisation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class ConvertDate {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private final SimpleDateFormat ISOformat = new SimpleDateFormat("yyyy-MM-dd");

	public ConvertDate() {

	}
	
	public Date getDateParse(String date) {
		Date notreDate = null;
		try {
			if(date.length() == 10) {
				if(date.indexOf('-') == 2) {
					notreDate = getDateCourt(date);
				}else {
					notreDate = getDateISO(date);
				}
			}else {
				notreDate = getDateLong(date);
			}
		} catch (ParseException e) {
			System.err.println("getDateParse() Erreur dans la date = " + date);
		}
		return notreDate;
	}
	
	private Date getDateCourt(String date) throws ParseException {

		Date notreDate = null;
		try {
			// dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			notreDate = formatter.parse(date);
			// notreDate = dateFormat.parse(date);
		} catch (Exception e) {
			System.err.println("getDateCourt()Erreur dans la date = " + date);
		}
		return notreDate;
	}

	private Date getDateLong(String date) throws ParseException {

		Date notreDate = null;
		try {
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			notreDate = dateFormat.parse(date);
		} catch (Exception e) {
			System.err.println("getDateLong() Erreur dans la date = " + date);
		}
		return notreDate;
	}
	
	private Date getDateISO(String date) throws ParseException {

		Date notreDate = null;
		try {
			ISOformat.setTimeZone(TimeZone.getTimeZone("GMT"));
			notreDate = ISOformat.parse(date);
		} catch (Exception e) {
			System.err.println("getDateISO() Erreur dans la date = " + date);
		}
		return notreDate;
	}

	public String getDateString(Date date) {
		// System.out.println("date = "+date);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormat.format(date);
	}
}
