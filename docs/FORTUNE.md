Usage
=====

### Note :
This module is still under development and this document presently shows only currently available utilities.

#### Fortune constants
```
        Fortune.QUOTES_SET_CHANAKYA               - default
        Fortune.QUOTES_SET_MALAYALAM_PROVERBS
        Fortune.QUOTES_SET_THIRUKKURAL
```
To select quotes from Chanakya, Thirukkural, Malayalam proverbs use the above constants respectively.

#### Get random quote
```
        Fortune obj = new Fortune(getContext(), Fortune.QUOTES_SET_CHANAKYA);
        String quote = obj.fortune();
```
The above function `obj.fortune();` returns a random quote from the set.


#### Get random quote with search argument
```
        Fortune obj = new Fortune(getContext(), Fortune.QUOTES_SET_CHANAKYA);
        String quote = obj.fortune("love");
```
The above function `obj.fortune(String);` returns a random quote from the set containing the `search` string argument.


#### FortuneTextView 

```

        <org.silpa.fortune.FortuneTextView
                android:id="@+id/tvFortune"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                fortune:fortuneTimeInterval="4000"
                fortune:fortuneSearchPattern="love"
                fortune:fortuneQuoteSet="chanakya" />

```

Here `fortune:fortuneTimeInterval"` is to set time interval between displays in milli-seconds with default set to 5 seconds.

`fortune:fortuneSearchPattern` is to set search pattern string with default `""`

`fortune:fortuneQuoteSet` is to set quote set with default `Fortune.QUOTES_SET_CHANAKYA`

These properties can also be set from Java code by :

```

        FortuneTextView tvFortuneTextView = (FortuneTextView) view.findViewById(R.id.tvFortune);
        tvFortuneTextView.setPattern("love");
        tvFortuneTextView.setTimeInterval(1000);
        tvFortuneTextView.setQuoteSet(Fortune.QUOTES_SET_MALAYALAM_PROVERBS);

```

To stop updates do `tvFortuneTextView.stopUpdates();`

#### To run tests
Tests present at `/src/test/java/`

  - Select test to run
  - Right Click -> Run Test -> Run as Android Test

