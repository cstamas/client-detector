client-detector
===============

A simple library to discover as much as possible about UA's, in offline mode. You usually get the UA string from
HTTP request "User-Agent" header (if in web environment), but you can use any alternative means to do so. This library
does not have any dependencies whatsoever on any web API.

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

