What did you not test automatically and why?

1) I did not test if the points are shown correctly in the chart.
2) I did not test if the data coming from NYTimes is correct.
3) I did not test the FileHandle class.

How have these things been tested manually?

1) I opened the text document and compared with the chart for more than 20 tests, and all of them showed the precise result.

2) This is a difficult thing, since there are thousands of articles published by NYTimes every year. To make sure that the data coming from the website makes sense I plotted some famous words like war, and the plot showed peaks where it would be expected (for example during the World War II and the Two Tower incident in New York). I also searched for non existent words and the plot showed the expected result.

3) I didn't see a reason to test it, since I already tested if the data coming from the API was parsed correctly (YearHits and LinReg classes), and I could easily check if the files were saved or not. If there were some errors, I could easily see where to correct, since FileHandle class' methods were divided in really different tasks and there were just three of them.

Bugs:

There was a bug related to special characters, but it seems they are resolved now. Another problem it may happend is for people without Java 8, or JavaFX SDK. Those people probably won't be able to see the chart.