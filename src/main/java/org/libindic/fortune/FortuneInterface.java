package org.libindic.fortune;

/**
 * Created by sujith on 16/6/14.
 */
public interface FortuneInterface {

    /**
     * Set pattern for searching
     *
     * @param pattern string pattern
     */
    public void setPattern(String pattern);

    /**
     * This function is to set time interval
     *
     * @param timeInterval time in seconds
     */
    public void setTimeInterval(int timeInterval);

    /**
     * This function is used to explicitly set quote set
     *
     * @param quoteSet quote set
     *                 Fortune.QUOTES_SET_CHANAKYA
     *                 Fortune.QUOTES_SET_MALAYALAM_PROVERBS
     *                 Fortune.QUOTES_SET_THIRUKKURAL
     */
    public void setQuoteSet(int quoteSet);

    /**
     * This function gives name of the module
     *
     * @return name of module
     */

    public String getModuleName();

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation();
}
