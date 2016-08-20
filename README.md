# excel-lib-test
##Test code using Apache POI lib

##Design assumptions:

As the application will be used within an SDK the user will have a familiarity with code concepts.
When processing a list, all bad data should be reported, processing should not terminate at the first invalid item.
Input data will include a description. As this is optional data it may be an empty string.
Choice of library for reading excel sheets:

I did some research and found apache POI to be the most recommended. Key points for me were:

Its free.
I've used other apache products and trust them.
It supports the old xls format and the new xlsx format of files (on using it I found most of the code I wrote could be common to both formats).

Question: Should we set a maximum length for all of the text fields, not just 'text'?

Answer: Yes, however this adds more work to the Card class which is not explicity part of what has been requested in the test. This functionality could easily be added, following the same approach used for checking the size of the 'text' field.

Question: Should we validate the entire excel file and only process it when all the data is valid?

Answer: This might be a good idea, depending on how the user will work. It would be easy to amend the code to do this. A side benefit would be that the memory for the full card list would not be used if the input data was invalid.

Improvements could be made based on user feedback and obviously on peer review.

##Further work:

I've created a CategorisedCard class by extending the Card class. This shows simple use of OO design which wasn't in the original exercise. I haven't included extensive tests as good testing is demonstrated for the Card class.
I've created a CategorisedCardReader class by extending the CardReader class. This shows more OO design and code reuse.
I've also created a new Interface allowing both card reader classes to be called from the same main method, currently in CategorisedCardReader. (The main in CardReader remains but could most likely be removed in a live system.)
