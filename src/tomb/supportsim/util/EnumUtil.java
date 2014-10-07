package tomb.supportsim.util;

import tomb.supportsim.models.enums.AssignmentMethodEnum;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 07/10/2014 Time: 01:27
 */
public class EnumUtil
{

  public static AssignmentMethodEnum fromString(final String text) {
    if (text != null) {
      for (AssignmentMethodEnum b : AssignmentMethodEnum.values()) {
        if (text.equalsIgnoreCase(b.toString())) {
          return b;
        }
      }
    }
    return null;
  }
}
