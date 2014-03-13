JerseyHttpPatch
===============

Experiments related to Rest services and HTTP method Patch verb implementations for them.

HTTP PATCH is a new specification for doing HTTP Patch method requests to web services. For updating Heavy objects, patch is very efficient approach as it only sends the light weight object called patch, comprising of only the necessary data and not the whole object. Service class receives this patch object and applies the patch to the current state of the object in the system. Then the response is sent back to the client. The data mode used for this project is Json. So example of json patch data is -

{ "op" : "replace" , "path" : "/title" , "value" : "new value" }

So we can see here that only the title property of the System objects needs to be updated with the mentioned value. So only this property is sent in the Json patch. For multiple properties, array of Json objects, combined as a single object can be sent to Service class. e.g. - [ {"op" : "replace" , "path" : "/title" , "value" : "new value" }, "op" : "remove" , "path" : "/name"} ]

The Service class is jersey based Rest service. For introducing Patch requests to service class, a new Patch annotation class is created. From Jersey client request is sent for Patch annotated method.
