package org.libindic.fortune;

import android.content.Context;

/**
 * Created by sujith on 25/5/14.
 */
public class Fortune {

    private FortuneSQLiteUtil fsqlu;

    public static final int QUOTES_SET_CHANAKYA = 0;
    public static final int QUOTES_SET_MALAYALAM_PROVERBS = 1;
    public static final int QUOTES_SET_THIRUKKURAL = 2;

    public static final String MODULE_NAME = "Indic Fortune";
    public static final String MODULE_INFORMATION = "Fortune cookie database " +
            "and generator for Indic languages.";

    protected static final int DEFAULT_QUOTES_SET = Fortune.QUOTES_SET_CHANAKYA;

    private static final String LOG_TAG = Fortune.MODULE_NAME;

    /**
     * Context of application
     */
    private Context mContext;

    /**
     * Selected Quote set
     */
    private int mQuoteSet;

    /**
     * Constructor
     * Default quote set - QUOTES_SET_CHANAKYA
     *
     * @param context context of application
     */
    public Fortune(Context context) {
        this(context, Fortune.DEFAULT_QUOTES_SET);
    }

    /**
     * Constructor
     *
     * @param context  context of application
     * @param quoteSet quote set namely :
     *                 Fortune.QUOTES_SET_CHANAKYA
     *                 Fortune.QUOTES_SET_MALAYALAM_PROVERBS
     *                 Fortune.QUOTES_SET_THIRUKKURAL
     */
    public Fortune(Context context, int quoteSet) {
        this.mContext = context;
        this.mQuoteSet = quoteSet;
        this.fsqlu = new FortuneSQLiteUtil(this.mContext);
    }

    /**
     * This function is used to explicitly set quote set
     *
     * @param quoteSet quote set
     *                 Fortune.QUOTES_SET_CHANAKYA
     *                 Fortune.QUOTES_SET_MALAYALAM_PROVERBS
     *                 Fortune.QUOTES_SET_THIRUKKURAL
     */
    public void setQuoteSet(int quoteSet) {
        this.mQuoteSet = quoteSet;
    }

    /**
     * This function is used to get random fortune string
     *
     * @return
     */
    public String fortune() {
        return fortune("");
    }

    /**
     * This function is used to get random fortune string containing a search argument.
     *
     * @param pattern string to be searched
     * @return random quote containing a given string pattern
     */
    public String fortune(String pattern) {
        return this.fsqlu.getQuote(mQuoteSet, pattern);
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    public String getModuleName() {
        return Fortune.MODULE_NAME;
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    public String getModuleInformation() {
        return Fortune.MODULE_INFORMATION;
    }
}
