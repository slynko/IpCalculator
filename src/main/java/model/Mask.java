package model;

import static constant.NetworkConstants.FIRST_OCTET_MASK;
import static constant.NetworkConstants.FOURTH_OCTET_MASK;
import static constant.NetworkConstants.SECOND_OCTET_MASK;
import static constant.NetworkConstants.THIRD_OCTET_MASK;
import static validator.NetworkHelper.getIpFrom;
import static validator.NetworkHelper.getMaskFrom;

public class Mask
{
  private long mask;

  public Mask(String mask)
  {
    this.mask = getIpFrom(mask);
  }

  public Mask(long numericMask)
  {
    this.mask = getMaskFrom(numericMask);
  }

  @Override
  public String toString()
  {
    return ((mask & FIRST_OCTET_MASK) >> 24) + "."
        +((mask & SECOND_OCTET_MASK) >> 16) + "."
        + ((mask & THIRD_OCTET_MASK) >> 8) + "."
        + (mask & FOURTH_OCTET_MASK);
  }

  public long getLongValue()
  {
    return mask;
  }
}
