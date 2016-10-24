# Requirements

In no particular order:

- Logging middleware that logs the request and the response code. Should be generic on "how" you log (perhaps pass in 
(String) => Unit)
- All requests, and responses should be in JSON. 
- Some endpoints (in no particular order - just some random ones I think are fun to impl):

  - /ip - GET - Returns Origin IP
  - /echo - ALL - Echos back your request, as a response
  - /headers - ALL - Returns the headers you sent
  - /user-agent - ALL - Returns the user-agent you sent
  - /bytes/:n - GET - Generates n random bytes of binary data.
  - /delay/:n - GET - Delays responding for n seconds. (max 30 seconds)
  - /delay/random/:n - GET- Delays responding for a random amount of time, between 0 and n seconds. (max 60 seconds)
  
- Some tests. Ideally use Scalacheck

http://http4sbin-learning.herokuapp.com/

https://travis-ci.org/dbousamra/http4sbin-learning
