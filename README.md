client-detector
===============

A simple library to discover as much as possible about UA's, in offline mode.

```java
  ClientDetector clientDetector = ... get it as @Inject or just get it from container

  HttpServletRequest request = ...
 
  List<Client> matchedClients = match( request.getHeader( "User-Agent" ) );
 
  if (!matchedClients.isEmpty()) {
  ...
  }
```

Have fun,  
~t~

