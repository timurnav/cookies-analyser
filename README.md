# Most Active Cookie Finder

Command line program to process the log file and return the most active cookie for a specific day. If multiple cookies
meet that criteria, all of them would be returned on separate lines.

## Program Parameters

The program requires two parameters:

- `-f` parameter for the cookie log file name to process
- `-d` parameter to specify the date

### File Format

Cookies in the log file are sorted by timestamp (most recent occurrence is the first line of the file). Cookie log file
expected to be a csv-like text document and has the following columns:

    cookie,timestamp
    AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
    SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
    5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
    AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00
    SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00
    4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00
    fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00
    4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00

### Date Format

Date must have YYYY-MM-dd format. Start of day is considered as UTC.

## Build and Run

build script:

    ./gradlew clean build

it creates a fat jar in `./build/libs/cookie-analyser-1.0.jar`.

To run the sample cookie file specified above you can execute following:

    java -jar ./build/libs/cookie-analyser-1.0.jar -f ./src/test/resources/cookies.csv -d 2018-12-09

And it would write to stdout: `AtY0laUfhglK3lC7`

**Note**

In worst case scenario all the cookies can hold the memory, so make sure you've set enough -Xmx for the script