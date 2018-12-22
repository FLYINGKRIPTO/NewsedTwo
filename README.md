# NewsedTwo
This app fetches news items from guardian api, according to the category specified by the user
## Preference Summary -
Settings Activity allows users to see the value of all the preferences right below the preference name, and when the value is changed, the summary updates immediate.

## Main Screen
App contains a main screen which displays multiple news stories

## List Item Contents
The title of the article and the name of the section that it belongs to are required field.

## Settings Activity
Settings Activity is accessed from the Main Activity via a Navigation Drawer or from the toolbar menu.

## API Query
App queries the content.guardianapis.com API to fetch news stories related to the topic chosen by the user, using either the ‘test’ api key or the student’s key.
The query is narrowed down by the preferences selected by the user in the Settings.

## Use of Loaders
Networking operations are done using a Loader rather than an AsyncTask.

## MainActivity
![MainActivity Sports News](https://user-images.githubusercontent.com/34384226/50370294-3909ff80-05ca-11e9-98a7-d82b7d715d05.png)
