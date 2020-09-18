# API Client
This application is capable of consuming RESTful APIs through JAX-RS technology.
It is built in order to consume my own Language Courses API https://github.com/R-Vaccari/course-api.

![UML](https://i.imgur.com/7LA27S3.jpg)

## How It Works
### Layers
API Client is divided in 4 layers: Resources, Services, Domain and Persistence.

Layers | Classes | Function
------------ | ------------- | -------------
Resources | GenericResource & implementations, AuthenticateResource, CLientProvider | Retrieve data from API given a target.
Services | GenericService & implementations, GenericParser & implementations | Services request raw data from Resources and serve as bridge to all app functions. Parser classes transform String data in actual classes.
Domain | Course, ClassGroup, Teacher, Student | POJOs that match Language Courses API data.
Persistence | SQLService, DBConnector | Only layer with access to database. SQLService takes in class information from Services and delivers database requests.

- API base link is defined by **ClientProvider**.
  - This client is then passed down to Resources, which specify a path and query parameters.
- Resources stablish their paths and query parameters. Then, they are authenticated by AuthenticateResource, which is capable of authentication independently.
- Services recieve raw data from Resources.
  - Parsers are called and transform this data in POJOs;
- SQLService is called by Services in order to prepare SQL commands for objects generated in Service scope by Parser.
- SQLService then sends these commands to PostgreSQL database provided by DBConnector;


### Features
- **Flexibility**: Interfaces such as GenericResource stablish a contract which must be carried out by implementations.
This makes the application ready to be extended.

- **Persistence**: The app is capable of parsing nested, composite objects through Jackson lib.

- **Cohesion**: Classes being highly specialized in their functions means less space for errors and more for extension. Each class only has access to information it strickly needs. For example, SQLService is the only class that connects to the database - it also knows nothing of what happened to the data previously or how it is retrieved.


Contact Me | Links
------------ | -------------
E-mail | rodrigo.v.melo@hotmail.com
LinkedIn | https://www.linkedin.com/in/rodrigo-vaccari-3128b7167
