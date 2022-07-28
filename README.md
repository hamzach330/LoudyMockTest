# LoudlyTest

Hello, First of all, it was simple yet a very interesting test, because it included a lot of things.
From fetching remote data with Pagination. Show listing, manage paging and etc.

# Some things to consider

So I take these coding challenges to only what I already know, but what I can do if time comes to
it. I have created this app with compose and used Paging 3 library because it was a new library and I wanted to implement it as I like to challenge my self.

The app handles network unavailability too. If there is no internet connectivity, It will show the
error and the try again button to retry loading the data.

For Splash, I copied the logo for Loudly from your website. I hope it is not too much :P.

I have tried to implement the Clean Architecture + SOLID Principles + MVVM with Paging3 in Jetpack
Compose.

The like functionality does work as it was stated in the test document. The paging cache is scoped
to Viewmodel so it can survive the configuration change without needing to load data again.
I have added unit tests to test pagination too. 

Since I had only 48 hours, to submit this, I have tried my best to implement the test and try
to show what I can do. Anything else that you guys would like to know can be discussed during the 
interview.
