package au.edu.sydney.soft3202.majorproject.model.api;

import java.util.List;

public class OfflineOutput implements Output {

  @Override
  public String sendReport(List reportData) {
    reportData.add("this is dummy data");
    return reportData.toString();
  }
}
