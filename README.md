# Oxford Dictionary App

### APIs Used
- Input API: Oxford Dictionary 
- Output API: Twilio

###  Features

- User can enter a word in English
- Application retries entry or Lemma 
- All provided information of an Entry is displayed to the user
- Pronunciations can be selected and played by the user
- Synonyms can be selected and Entry can be retrieved by user
- Bread crumb feature for new word user search history
- Bread crumb feature for synonym user search history
- Send short report of user Entry history to configured Phone number via Twilio
- Cache Entry and Lemma Results stored in SQL database
- User can select to use cache or fresh data when cache hit occurs
- User can clear cache
- Theme song plays on loop when Application starts
- User can pause or play theme song 
- User can learn more about Application via About feature on MenuBar
- Spinning progress indicator during API calls 
- Able to add and remove Pronunciations to a list

## How to Use Application
gradle run --args = "[online or offline] [online or offline]"


###  How to Use Application
gradle run --args ="[online or offline] [online or offline]"

###  Quirks to Running Application
To use Extension Feature:

1. Enter word in main Window and Click "Submit"
2. On the bottom of the entry window, Click "Pronounce"
3. Choose (a) pronunciation sound(s) and Click "Add Pronunciation to List"
4. Click the "Additional Features" Menu on the main window, and choose "Pronunciation List" menu item
5. Click a pronunciation to play the sound directly
6. Choose a pronunciation from the list and Click "Remove Pronunciation" to remove from list


### References 
- https://www.twilio.com/docs/sms/api/message-resource
- https://developer.oxforddictionaries.com/documentation#!/Entries

