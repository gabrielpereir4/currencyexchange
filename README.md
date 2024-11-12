Currency Exchange CLI app built entirely in Java using ExchangeRate API.

This app is organized in MVCS (Model-View-Controller-Service) architecture, using HTTP API calls to gather exchange rates
and supported currencies.

Model layer holds the Exchange class, which encapsulates the attributes for an exchange to happen.
Controller layer communicates between all other 3 layers.
View layer is responsible for CLI (Command Line Interface).
Service layer is responsible for API calls and returning data to Controller layer.

The app also uses dotenv for API key storage.