package au.edu.sydney.soft3202.majorproject.model.api;

import java.util.List;

public interface Output {

  /**
   * send report on text representation of the user's entry history
   *
   * @param reportData The user word search History
   * @return The report of user's word search history
   */
  String sendReport(List reportData);
}
