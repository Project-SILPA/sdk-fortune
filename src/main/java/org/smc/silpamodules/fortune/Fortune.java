package org.smc.silpamodules.fortune;

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

    public static final int PROVERB_SET_CHANAKYA = 0;
    public static final int PROBERB_SET_MALAYALAM = 1;
    public static final int PROVERB_SET_THIRUKKURAL = 2;

    public static final String FORTUNE_MODULE_NAME = "Indic Fortune";
    public static final String FORTUNE_MODULE_INFORMATION = "Fortune cookie database " +
            "and generator for Indic languages.";

    private static final String[] PROVERB_FILES = {"database/chanakya.dic",
            "database/malayalam_proverbs.dic", "database/thirukkural.dic"};

    private static final int DEFAULT_PROVERB_SET = Fortune.PROVERB_SET_CHANAKYA;

    private static String LOG_TAG = Fortune.FORTUNE_MODULE_NAME;

    /**
     * Context of application
     */
    private Context mContext;

    private int mProberbSet;


    private List<String> mQuotes;


    public Fortune(Context context) {
        this(context, Fortune.DEFAULT_PROVERB_SET);
    }

    public Fortune(Context context, int proverbSet) {
        this.mContext = context;
        this.mProberbSet = proverbSet;

        init();
    }

    private void init() {
        loadQuotes();
    }

    private void loadQuotes() {
        this.mQuotes = getQuotesFromFile(Fortune.PROVERB_FILES[this.mProberbSet]);
    }

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

    public void setProverbSet(int proverbSet) {
        this.mProberbSet = proverbSet;
        init();
    }

    public String fortune() {
        return fortune("");
    }

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

    public String getModuleName() {
        return Fortune.FORTUNE_MODULE_NAME;
    }

    public String getModuleInformation() {
        return Fortune.FORTUNE_MODULE_INFORMATION;
    }
}
