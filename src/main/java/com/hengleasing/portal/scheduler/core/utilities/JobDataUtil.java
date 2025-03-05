package com.hengleasing.portal.scheduler.core.utilities;

public class JobDataUtil {

  // ---------------------------------------------------------------------------//
  // Fields
  // ---------------------------------------------------------------------------//

  private static final String JOB_ID_TEMPLATE = "%s-%s-%s";

  public static final String JOB_TYPE_ONETIME = "OT";
  public static final String JOB_TYPE_CRON = "CR";

  public static final String SCHEDULE_ID = "scheduleId";

  // ---------------------------------------------------------------------------//
  // Constructors
  // ---------------------------------------------------------------------------//

  private JobDataUtil() {
  };

  // ---------------------------------------------------------------------------//
  // Methods
  // ---------------------------------------------------------------------------//

  public static String formatJobId(String origin, String type, String id) {
    return String.format(JOB_ID_TEMPLATE, origin, type, id);
  }

}
