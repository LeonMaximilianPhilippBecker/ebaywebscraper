# ebaywebscraper (work in progress)
This is a simple java application for receiving WhatsApp notifications about recently published offers on ebay Kleinanzeigen. You can freely configure a set of articles to get notified about.

### How to get started quickly
1. Clone this repository
2. Set up a Twilio account for the purpose of receiving WhatsApp messages.
3. Paste your recently attained Account_Sid and Auth_Token in the application.properties file within this project.
4. Install docker.
5. Execute the run-mongodb bash script within this project, to start a local container with a local database.
6. Start the project via the EbaywebscraperApplication.java class

You will now get notified about a preconfigured selection of articles.
