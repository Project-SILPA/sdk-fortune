package org.silpa.fortune;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;

import org.silpa.render.IndicTextView;

/**
 * Created by sujith on 16/6/14.
 */
public class FortuneTextView extends IndicTextView implements FortuneInterface {

    /**
     * Fortune object
     */
    private Fortune fortune;

    /**
     * Quote set to choose from
     */
    private int mQuoteSet;

    /**
     * Time interval between displays
     */
    private int mTimeInterval;

    /**
     * Pattern for searchin quote
     */
    private String mPattern;

    /**
     * For updating UI
     */
    private UIUpdater mUIUpdater;

    /**
     * Default pattern
     */
    private static final String DEFAULT_SEARCH_PATTERN = "";

    /**
     * Default time interval
     */
    private static final int DEFAULT_TIME_INTERVAL = 5000;

    private static final String LOG_TAG = "FortuneTextView";

    /**
     * Constructor
     *
     * @param context context of application
     */
    public FortuneTextView(Context context) {
        super(context);
        init(null, 0);
    }

    /**
     * Constructor
     *
     * @param context context of application
     * @param attrs   attribute set
     */
    public FortuneTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    /**
     * Constructor
     *
     * @param context  context of application
     * @param attrs    attribute set
     * @param defStyle default style
     */
    public FortuneTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    /**
     * Initialize data members
     *
     * @param attrs    attribute set
     * @param defStyle default style
     */
    private void init(AttributeSet attrs, int defStyle) {
        // Init attrs
        initAttrs(attrs, defStyle);
        initView();
    }

    /**
     * Initialize attributes used
     *
     * @param attrs    attribute set
     * @param defStyle default style
     */
    private void initAttrs(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.FortuneTextView,
                defStyle, defStyle
        );

        try {

            this.mQuoteSet = a.getInteger(R.styleable.FortuneTextView_fortuneQuoteSet,
                    Fortune.DEFAULT_QUOTES_SET);
            this.mTimeInterval = a.getInteger(R.styleable.FortuneTextView_fortuneTimeInterval,
                    DEFAULT_TIME_INTERVAL);
            this.mPattern = a.getString(R.styleable.FortuneTextView_fortuneSearchPattern);
            if (this.mPattern == null) {
                this.mPattern = DEFAULT_SEARCH_PATTERN;
            }

            this.fortune = new Fortune(getContext(), this.mQuoteSet);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error : " + e.getMessage());
        } finally {
            a.recycle();
        }
    }

    private void initView() {

        this.mUIUpdater = new UIUpdater(new Runnable() {
            @Override
            public void run() {
                setText(fortune.fortune(mPattern));
            }
        }, this.mTimeInterval);
        this.mUIUpdater.startUpdates();
    }

    public void stopUpdates() {
        this.mUIUpdater.stopUpdates();
    }

    /**
     * Set pattern for searching
     *
     * @param pattern string pattern
     */
    @Override
    public void setPattern(String pattern) {
        if (pattern != null) {
            this.mPattern = pattern;
        }
    }

    /**
     * This function is to set time interval
     *
     * @param timeInterval time in seconds
     */
    @Override
    public void setTimeInterval(int timeInterval) {

        this.mTimeInterval = timeInterval;
        mUIUpdater = new UIUpdater(new Runnable() {
            @Override
            public void run() {
                setText(fortune.fortune(mPattern));
            }
        }, this.mTimeInterval);
        this.mUIUpdater.startUpdates();

    }

    /**
     * This function is used to explicitly set quote set
     *
     * @param quoteSet quote set
     *                 Fortune.QUOTES_SET_CHANAKYA
     *                 Fortune.QUOTES_SET_MALAYALAM_PROVERBS
     *                 Fortune.QUOTES_SET_THIRUKKURAL
     */
    @Override
    public void setQuoteSet(int quoteSet) {
        this.mQuoteSet = quoteSet;
        if (this.fortune != null) {
            this.fortune.setQuoteSet(this.mQuoteSet);
        }
    }

    /**
     * This function gives name of the module
     *
     * @return name of module
     */
    @Override
    public String getModuleName() {
        return this.fortune.getModuleName();
    }

    /**
     * This function gives a brief description of the module
     *
     * @return brief information regarding the module
     */
    @Override
    public String getModuleInformation() {
        return this.fortune.getModuleInformation();
    }


    private class UIUpdater {
        // Create a Handler that uses the Main Looper to run in
        private Handler mHandler = new Handler(Looper.getMainLooper());

        private Runnable mStatusChecker;
        private int UPDATE_INTERVAL = DEFAULT_TIME_INTERVAL;

        /**
         * Creates an UIUpdater object, that can be used to
         * perform UIUpdates on a specified time interval.
         *
         * @param uiUpdater A runnable containing the update routine.
         */
        public UIUpdater(final Runnable uiUpdater) {
            mStatusChecker = new Runnable() {
                @Override
                public void run() {
                    // Run the passed runnable
                    uiUpdater.run();
                    // Re-run it after the update interval
                    mHandler.postDelayed(this, UPDATE_INTERVAL);
                }
            };
        }

        /**
         * The same as the default constructor, but specifying the
         * intended update interval.
         *
         * @param uiUpdater A runnable containing the update routine.
         * @param interval  The interval over which the routine
         *                  should run (milliseconds).
         */
        public UIUpdater(Runnable uiUpdater, int interval) {
            this(uiUpdater);
            UPDATE_INTERVAL = interval;
        }

        /**
         * Starts the periodical update routine (mStatusChecker
         * adds the callback to the handler).
         */
        public synchronized void startUpdates() {
            mStatusChecker.run();
        }

        /**
         * Stops the periodical update routine from running,
         * by removing the callback.
         */
        public synchronized void stopUpdates() {
            mHandler.removeCallbacks(mStatusChecker);
        }
    }
}

