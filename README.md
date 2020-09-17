# API Client
This application is capable of consuming RESTful APIs through JAX-RS technology.
It is built in order to consume my own Language Courses API https://github.com/R-Vaccari/course-api.

## How It Works
API Client is divided in 4 layers: Resources, Services, Domain and Persistence.

Layers | Classes | Function
------------ | ------------- | -------------
Resources | GenericResource & implementations, AuthenticateResource, CLientProvider | Retrieve data from API given a target.
Services | GenericService & implementations, GenericParser & implementations | Services request raw data from Resources and serve as bridge to all app functions. Parser classes transform String data in actual classes.
Domain | Course, ClassGroup, Teacher, Student | POJOs that match Language Courses API data.
Persistence | SQLService, DBConnector | Only layer with access to database. SQLService takes in class information from Services and delivers database requests.

Resources recieve their base target from ClientProvider.

Interfaces such as GenericResource stablish a contract which must be carried out by implementations.
This makes the application ready to be extended.

Data is parsed into POJOs using Jackson lib.






Contact Me | Links
------------ | -------------
E-mail | rodrigo.v.melo@hotmail.com
LinkedIn | https://www.linkedin.com/in/rodrigo-vaccari-3128b7167
