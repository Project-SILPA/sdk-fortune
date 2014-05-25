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

#### To run tests
Tests present at `/src/test/java/`

  - Select test to run
  - Right Click -> Run Test -> Run as Android Test

