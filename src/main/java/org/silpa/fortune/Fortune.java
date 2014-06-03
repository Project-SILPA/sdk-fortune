package org.silpa.fortune;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sujith on 25/5/14.
 */
public class Fortune {

    public static final int QUOTES_SET_CHANAKYA = 0;
    public static final int QUOTES_SET_MALAYALAM_PROVERBS = 1;
    public static final int QUOTES_SET_THIRUKKURAL = 2;

    public static final String MODULE_NAME = "Indic Fortune";
    public static final String MODULE_INFORMATION = "Fortune cookie database " +
            "and generator for Indic languages.";

    private static final String[] QUOTES_FILES = {"database/chanakya.dic",
            "database/malayalam_proverbs.dic", "database/thirukkural.dic"};

    private static final int DEFAULT_QUOTES_SET = Fortune.QUOTES_SET_CHANAKYA;

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
     * List of quotes from selected set
     */
    private List<String> mQuotes;


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

        init();
    }

    /**
     * called on object creation
     */
    private void init() {
        loadQuotes();
    }

    /**
     * Load quotes from asset files
     */
    private void loadQuotes() {
        this.mQuotes = getQuotesFromFile(Fortune.QUOTES_FILES[this.mQuoteSet]);
    }

    /**
     * Private function to get all quotes from asset file
     *
     * @param fileName name of the file to be read from
     * @return list with all quotes from the file
     */
    private List<String> getQuotesFromFile(String fileName) {

        String line;
        String quote;
        BufferedReader br;

        List<String> quotes = new ArrayList<String>();

        try {
            br = new BufferedReader(new InputStreamReader(this.mContext.getResources().
                    getAssets().open(fileName)));
            quote = "";
            while (true) {
                try {
                    line = new String((br.readLine().getBytes("UTF-8")), "UTF-8");
                    line = line.trim();

                    if (line.equals("%")) {
                        quotes.add(quote);
                        quote = "";
                    } else {
                        quote = quote + line;
                    }

                } catch (Exception e) {
                    Log.e(LOG_TAG, "Error : " + e.getMessage() + " " +
                            " Loading rules terminated");
                    break;
                }
            }
            br.close();
        } catch (IOException ioe) {
            Log.e(LOG_TAG, "Error : " + ioe.getMessage());
            return null;
        }
        return quotes;
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
        init();
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

        List<String> searchList = new ArrayList<String>();

        for (String quote : this.mQuotes) {
            if (pattern != null && quote.contains(pattern)) {
                searchList.add(quote);
            }
        }
        if (searchList.size() == 0) {
            return null;
        }
        return searchList.get(new Random().nextInt(searchList.size()));
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
