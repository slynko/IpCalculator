package model;

import static constant.NetworkConstants.MAX_IP_MASK;
import static constant.NetworkConstants.MAX_MASK_VALUE;
import static constant.NetworkConstants.MIN_MASK_VALUE;
import static validator.NetworkHelper.isMaskValid;

public class Mask extends Ip
{
  public Mask(String mask)
  {
    super(mask);
  }

  public Mask(long mask)
  {
    super(mask);
    if (isMaskValid(mask))
    {
      this.ip = MAX_IP_MASK << mask & MAX_IP_MASK;
    }
    else
    {
      throw new IllegalArgumentException("Argument is not correct: " + mask);
    }
  }

  public int getIntMask() {
    int intMask = 1;
    long mask = this.ip;
    while(((mask <<= 1) & MAX_IP_MASK) > 0)
    {
      intMask++;
    }
    return 32 - intMask;
  }
}
